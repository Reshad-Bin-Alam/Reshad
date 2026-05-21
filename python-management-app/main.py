from fastapi import Depends, FastAPI, HTTPException, status
from sqlalchemy.orm import Session

from app import crud, models, schemas
from app.database import Base, engine, get_db


Base.metadata.create_all(bind=engine)

app = FastAPI(title="Inventory Management App")


@app.get("/health")
def health_check():
    return {"status": "ok"}


@app.post("/products", response_model=schemas.Product, status_code=status.HTTP_201_CREATED)
def create_product(product: schemas.ProductCreate, db: Session = Depends(get_db)):
    if crud.get_product_by_sku(db, product.sku):
        raise HTTPException(status_code=400, detail="SKU already exists")
    return crud.create_product(db, product)


@app.get("/products", response_model=list[schemas.Product])
def list_products(db: Session = Depends(get_db)):
    return crud.list_products(db)


@app.get("/products/{product_id}", response_model=schemas.Product)
def get_product(product_id: int, db: Session = Depends(get_db)):
    db_product = crud.get_product(db, product_id)
    if db_product is None:
        raise HTTPException(status_code=404, detail="Product not found")
    return db_product


@app.put("/products/{product_id}", response_model=schemas.Product)
def update_product(product_id: int, product: schemas.ProductUpdate, db: Session = Depends(get_db)):
    db_product = crud.get_product(db, product_id)
    if db_product is None:
        raise HTTPException(status_code=404, detail="Product not found")
    if product.sku != db_product.sku and crud.get_product_by_sku(db, product.sku):
        raise HTTPException(status_code=400, detail="SKU already exists")
    return crud.update_product(db, db_product, product)


@app.delete("/products/{product_id}", status_code=status.HTTP_204_NO_CONTENT)
def delete_product(product_id: int, db: Session = Depends(get_db)):
    db_product = crud.get_product(db, product_id)
    if db_product is None:
        raise HTTPException(status_code=404, detail="Product not found")
    crud.delete_product(db, db_product)


@app.post("/suppliers", response_model=schemas.Supplier, status_code=status.HTTP_201_CREATED)
def create_supplier(supplier: schemas.SupplierCreate, db: Session = Depends(get_db)):
    if crud.get_supplier_by_email(db, supplier.email):
        raise HTTPException(status_code=400, detail="Email already exists")
    return crud.create_supplier(db, supplier)


@app.get("/suppliers", response_model=list[schemas.Supplier])
def list_suppliers(db: Session = Depends(get_db)):
    return crud.list_suppliers(db)


@app.get("/suppliers/{supplier_id}", response_model=schemas.Supplier)
def get_supplier(supplier_id: int, db: Session = Depends(get_db)):
    db_supplier = crud.get_supplier(db, supplier_id)
    if db_supplier is None:
        raise HTTPException(status_code=404, detail="Supplier not found")
    return db_supplier


@app.put("/suppliers/{supplier_id}", response_model=schemas.Supplier)
def update_supplier(supplier_id: int, supplier: schemas.SupplierUpdate, db: Session = Depends(get_db)):
    db_supplier = crud.get_supplier(db, supplier_id)
    if db_supplier is None:
        raise HTTPException(status_code=404, detail="Supplier not found")
    if supplier.email != db_supplier.email and crud.get_supplier_by_email(db, supplier.email):
        raise HTTPException(status_code=400, detail="Email already exists")
    return crud.update_supplier(db, db_supplier, supplier)


@app.delete("/suppliers/{supplier_id}", status_code=status.HTTP_204_NO_CONTENT)
def delete_supplier(supplier_id: int, db: Session = Depends(get_db)):
    db_supplier = crud.get_supplier(db, supplier_id)
    if db_supplier is None:
        raise HTTPException(status_code=404, detail="Supplier not found")
    crud.delete_supplier(db, db_supplier)


@app.post("/stock-movements", response_model=schemas.StockMovement, status_code=status.HTTP_201_CREATED)
def create_stock_movement(movement: schemas.StockMovementCreate, db: Session = Depends(get_db)):
    db_movement, error = crud.create_stock_movement(db, movement)
    if error == "product_not_found":
        raise HTTPException(status_code=404, detail="Product not found")
    if error == "insufficient_stock":
        raise HTTPException(status_code=400, detail="Insufficient stock for movement")
    return db_movement


@app.get("/stock-movements", response_model=list[schemas.StockMovement])
def list_stock_movements(db: Session = Depends(get_db)):
    return crud.list_stock_movements(db)


@app.get("/stock-movements/{movement_id}", response_model=schemas.StockMovement)
def get_stock_movement(movement_id: int, db: Session = Depends(get_db)):
    db_movement = crud.get_stock_movement(db, movement_id)
    if db_movement is None:
        raise HTTPException(status_code=404, detail="Stock movement not found")
    return db_movement


@app.put("/stock-movements/{movement_id}", response_model=schemas.StockMovement)
def update_stock_movement(
    movement_id: int, movement: schemas.StockMovementUpdate, db: Session = Depends(get_db)
):
    db_movement = crud.get_stock_movement(db, movement_id)
    if db_movement is None:
        raise HTTPException(status_code=404, detail="Stock movement not found")
    updated, error = crud.update_stock_movement(db, db_movement, movement)
    if error == "product_not_found":
        raise HTTPException(status_code=404, detail="Product not found")
    if error == "insufficient_stock":
        raise HTTPException(status_code=400, detail="Insufficient stock for movement")
    return updated


@app.delete("/stock-movements/{movement_id}", status_code=status.HTTP_204_NO_CONTENT)
def delete_stock_movement(movement_id: int, db: Session = Depends(get_db)):
    db_movement = crud.get_stock_movement(db, movement_id)
    if db_movement is None:
        raise HTTPException(status_code=404, detail="Stock movement not found")
    error = crud.delete_stock_movement(db, db_movement)
    if error == "insufficient_stock":
        raise HTTPException(status_code=400, detail="Cannot delete movement due to stock consistency")
