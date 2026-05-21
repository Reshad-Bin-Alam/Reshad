import sys
from pathlib import Path

from fastapi.testclient import TestClient
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.pool import StaticPool

sys.path.append(str(Path(__file__).resolve().parents[1]))

from app.database import Base, get_db
from main import app


TEST_DATABASE_URL = "sqlite:///:memory:"
engine = create_engine(
    TEST_DATABASE_URL, connect_args={"check_same_thread": False}, poolclass=StaticPool
)
TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)


def override_get_db():
    db = TestingSessionLocal()
    try:
        yield db
    finally:
        db.close()


app.dependency_overrides[get_db] = override_get_db
client = TestClient(app)


def setup_function():
    Base.metadata.drop_all(bind=engine)
    Base.metadata.create_all(bind=engine)


def test_create_and_list_products():
    payload = {
        "name": "Laptop",
        "sku": "LAP-001",
        "price": 999.99,
        "quantity": 5,
    }
    response = client.post("/products", json=payload)
    assert response.status_code == 201
    body = response.json()
    assert body["name"] == payload["name"]
    assert body["sku"] == payload["sku"]

    list_response = client.get("/products")
    assert list_response.status_code == 200
    list_body = list_response.json()
    assert len(list_body) == 1
    assert list_body[0]["sku"] == payload["sku"]


def test_create_product_rejects_duplicate_sku():
    payload = {
        "name": "Laptop",
        "sku": "LAP-001",
        "price": 999.99,
        "quantity": 5,
    }
    first_response = client.post("/products", json=payload)
    assert first_response.status_code == 201

    duplicate_response = client.post("/products", json=payload)
    assert duplicate_response.status_code == 400
    assert duplicate_response.json()["detail"] == "SKU already exists"
