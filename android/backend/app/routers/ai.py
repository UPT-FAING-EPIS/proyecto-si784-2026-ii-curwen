from datetime import datetime
from fastapi import APIRouter
from pydantic import BaseModel
from ..config import settings
from ..services.ai import AIProviderFactory

router=APIRouter(prefix="/ia",tags=["ia"])
class AnalyzeRequest(BaseModel): text:str; courses:list[str]=[]
class PlanRequest(BaseModel): activity_id:int; title:str; due_at:datetime
@router.post("/analizar")
async def analyze(body:AnalyzeRequest):
    provider=AIProviderFactory.create(settings.ai_provider)
    return await provider.extract(body.text,body.courses)
@router.post("/planificar")
async def plan(body:PlanRequest):
    provider=AIProviderFactory.create(settings.ai_provider)
    return await provider.plan(body.activity_id,body.title,body.due_at)
