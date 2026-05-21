# Python Inventory Management App

This directory contains a standalone FastAPI + SQLite inventory management app added alongside the existing Java codebase.

## Features

- Product CRUD (`id`, `name`, `sku`, `price`, `quantity`)
- Supplier CRUD (`id`, `name`, `email`, `phone`)
- Stock movement CRUD (`id`, `product_id`, `delta`, `reason`, `created_at`) with product quantity updates
- SQLite persistence via SQLAlchemy
- Request/response validation via Pydantic
- Minimal pytest coverage for product endpoints

## Setup

From the repository root:

```bash
cd python-management-app
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

## Run

```bash
cd python-management-app
uvicorn main:app --reload
```

Open:

- API docs: `http://127.0.0.1:8000/docs`
- Health endpoint: `http://127.0.0.1:8000/health`

## Test

```bash
cd python-management-app
pytest -q
```
