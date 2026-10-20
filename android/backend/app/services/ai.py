from abc import ABC, abstractmethod
from datetime import datetime, timedelta
from ..schemas import AiDetected, PlanProposal, PlanBlock

class AIProvider(ABC):
    @abstractmethod
    async def extract(self, text: str, courses: list[str]) -> list[AiDetected]: ...
    @abstractmethod
    async def plan(self, activity_id: int, title: str, due_at: datetime) -> PlanProposal: ...

class MockAIProvider(AIProvider):
    async def extract(self, text: str, courses: list[str]) -> list[AiDetected]:
        return [AiDetected(
            titulo="Informe de Base de Datos",
            curso=courses[0] if courses else "Base de Datos I",
            fecha_limite=datetime(2026,9,30,23,59),
            tipo="TAREA",
            descripcion="Resultado demo del adaptador IA. Sustituye MockAIProvider por el proveedor real.",
        )]
    async def plan(self, activity_id: int, title: str, due_at: datetime) -> PlanProposal:
        start = due_at - timedelta(days=3)
        return PlanProposal(actividad_id=activity_id, bloques=[
            PlanBlock(inicio=start.replace(hour=19, minute=0), fin=start.replace(hour=19, minute=45), descripcion=f"Analizar requisitos: {title}"),
            PlanBlock(inicio=(start+timedelta(days=1)).replace(hour=19, minute=0), fin=(start+timedelta(days=1)).replace(hour=20, minute=0), descripcion="Desarrollar contenido"),
            PlanBlock(inicio=(start+timedelta(days=2)).replace(hour=19, minute=0), fin=(start+timedelta(days=2)).replace(hour=19, minute=45), descripcion="Revisar y preparar entrega")
        ])

class AIProviderFactory:
    @staticmethod
    def create(name: str) -> AIProvider:
        # Punto de extensión Strategy/Adapter: OpenAI, Gemini, Azure AI, etc.
        return MockAIProvider()
