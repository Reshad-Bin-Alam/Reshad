from sqlalchemy.orm import Session

from app import models, schemas


def get_product(db: Session, product_id: int):
    return db.query(models.Product).filter(models.Product.id == product_id).first()


def get_product_by_sku(db: Session, sku: str):
    return db.query(models.Product).filter(models.Product.sku == sku).first()


def list_products(db: Session):
    return db.query(models.Product).order_by(models.Product.id.asc()).all()


def create_product(db: Session, product: schemas.ProductCreate):
    db_product = models.Product(**product.model_dump())
    db.add(db_product)
    db.commit()
    db.refresh(db_product)
    return db_product


def update_product(db: Session, db_product: models.Product, product: schemas.ProductUpdate):
    for key, value in product.model_dump().items():
        setattr(db_product, key, value)
    db.commit()
    db.refresh(db_product)
    return db_product


def delete_product(db: Session, db_product: models.Product):
    db.delete(db_product)
    db.commit()


def get_supplier(db: Session, supplier_id: int):
    return db.query(models.Supplier).filter(models.Supplier.id == supplier_id).first()


def get_supplier_by_email(db: Session, email: str):
    return db.query(models.Supplier).filter(models.Supplier.email == email).first()


def list_suppliers(db: Session):
    return db.query(models.Supplier).order_by(models.Supplier.id.asc()).all()


def create_supplier(db: Session, supplier: schemas.SupplierCreate):
    db_supplier = models.Supplier(**supplier.model_dump())
    db.add(db_supplier)
    db.commit()
    db.refresh(db_supplier)
    return db_supplier


def update_supplier(db: Session, db_supplier: models.Supplier, supplier: schemas.SupplierUpdate):
    for key, value in supplier.model_dump().items():
        setattr(db_supplier, key, value)
    db.commit()
    db.refresh(db_supplier)
    return db_supplier


def delete_supplier(db: Session, db_supplier: models.Supplier):
    db.delete(db_supplier)
    db.commit()


def list_stock_movements(db: Session):
    return db.query(models.StockMovement).order_by(models.StockMovement.id.asc()).all()


def get_stock_movement(db: Session, movement_id: int):
    return db.query(models.StockMovement).filter(models.StockMovement.id == movement_id).first()


def create_stock_movement(db: Session, movement: schemas.StockMovementCreate):
    db_product = get_product(db, movement.product_id)
    if db_product is None:
        return None, "product_not_found"
    new_quantity = db_product.quantity + movement.delta
    if new_quantity < 0:
        return None, "insufficient_stock"

    db_movement = models.StockMovement(**movement.model_dump())
    db_product.quantity = new_quantity
    db.add(db_movement)
    db.commit()
    db.refresh(db_movement)
    return db_movement, None


def update_stock_movement(db: Session, db_movement: models.StockMovement, movement: schemas.StockMovementUpdate):
    current_product = get_product(db, db_movement.product_id)
    target_product = get_product(db, movement.product_id)
    if target_product is None:
        return None, "product_not_found"

    if current_product.id == target_product.id:
        new_quantity = target_product.quantity - db_movement.delta + movement.delta
        if new_quantity < 0:
            return None, "insufficient_stock"
        target_product.quantity = new_quantity
    else:
        reversed_current_quantity = current_product.quantity - db_movement.delta
        if reversed_current_quantity < 0:
            return None, "insufficient_stock"
        new_target_quantity = target_product.quantity + movement.delta
        if new_target_quantity < 0:
            return None, "insufficient_stock"
        current_product.quantity = reversed_current_quantity
        target_product.quantity = new_target_quantity

    db_movement.product_id = movement.product_id
    db_movement.delta = movement.delta
    db_movement.reason = movement.reason
    db.commit()
    db.refresh(db_movement)
    return db_movement, None


def delete_stock_movement(db: Session, db_movement: models.StockMovement):
    db_product = get_product(db, db_movement.product_id)
    reversed_quantity = db_product.quantity - db_movement.delta
    if reversed_quantity < 0:
        return "insufficient_stock"
    db_product.quantity = reversed_quantity
    db.delete(db_movement)
    db.commit()
    return None
