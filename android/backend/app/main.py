from contextlib import asynccontextmanager
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from .db import init_db
from .routers import activities, courses, ai, configuration, organization

@asynccontextmanager
async def lifespan(app: FastAPI):
    init_db()
    yield

app=FastAPI(title="TARE·IA API",version="1.0.0",lifespan=lifespan)
app.add_middleware(CORSMiddleware,allow_origins=["*"],allow_credentials=True,allow_methods=["*"],allow_headers=["*"])
app.include_router(activities.router)
app.include_router(courses.router)
app.include_router(ai.router)
app.include_router(configuration.router)
app.include_router(organization.router)

@app.get("/health")
def health(): return {"status":"ok","service":"TARE·IA API"}
