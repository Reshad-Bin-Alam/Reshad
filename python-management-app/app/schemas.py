from datetime import datetime

from pydantic import BaseModel, ConfigDict, EmailStr, Field, field_validator


class ProductBase(BaseModel):
    name: str = Field(min_length=1, max_length=255)
    sku: str = Field(min_length=1, max_length=100)
    price: float = Field(ge=0)
    quantity: int = Field(ge=0)


class ProductCreate(ProductBase):
    pass


class ProductUpdate(ProductBase):
    pass


class Product(ProductBase):
    id: int
    model_config = ConfigDict(from_attributes=True)


class SupplierBase(BaseModel):
    name: str = Field(min_length=1, max_length=255)
    email: EmailStr
    phone: str = Field(min_length=1, max_length=50)


class SupplierCreate(SupplierBase):
    pass


class SupplierUpdate(SupplierBase):
    pass


class Supplier(SupplierBase):
    id: int
    model_config = ConfigDict(from_attributes=True)


class StockMovementBase(BaseModel):
    product_id: int = Field(gt=0)
    delta: int
    reason: str = Field(min_length=1, max_length=255)

    @field_validator("delta")
    @classmethod
    def validate_delta(cls, value: int):
        if value == 0:
            raise ValueError("delta must not be zero")
        return value


class StockMovementCreate(StockMovementBase):
    pass


class StockMovementUpdate(StockMovementBase):
    pass


class StockMovement(StockMovementBase):
    id: int
    created_at: datetime
    model_config = ConfigDict(from_attributes=True)
