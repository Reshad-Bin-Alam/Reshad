from sqlalchemy import Column, DateTime, Float, ForeignKey, Integer, String, func
from sqlalchemy.orm import relationship

from app.database import Base


class Product(Base):
    __tablename__ = "products"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(255), nullable=False, index=True)
    sku = Column(String(100), unique=True, nullable=False, index=True)
    price = Column(Float, nullable=False, default=0.0)
    quantity = Column(Integer, nullable=False, default=0)
    stock_movements = relationship("StockMovement", back_populates="product", cascade="all,delete")


class Supplier(Base):
    __tablename__ = "suppliers"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(255), nullable=False)
    email = Column(String(255), nullable=False, unique=True, index=True)
    phone = Column(String(50), nullable=False)


class StockMovement(Base):
    __tablename__ = "stock_movements"

    id = Column(Integer, primary_key=True, index=True)
    product_id = Column(Integer, ForeignKey("products.id"), nullable=False, index=True)
    delta = Column(Integer, nullable=False)
    reason = Column(String(255), nullable=False)
    created_at = Column(DateTime, nullable=False, server_default=func.now())
    product = relationship("Product", back_populates="stock_movements")
