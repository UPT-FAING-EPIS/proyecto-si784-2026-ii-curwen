from datetime import datetime
from pydantic import BaseModel, ConfigDict

class CourseIn(BaseModel):
    nombre: str
    abreviatura: str
    prioridad: str = "MEDIA"
class CourseOut(CourseIn):
    id: int
    model_config = ConfigDict(from_attributes=True)

class ActivityIn(BaseModel):
    titulo: str
    curso: str | None = None
    fecha_limite: datetime
    estado: str = "CONFIRMADA"
    descripcion: str = ""
    tipo: str = "TAREA"
class ActivityOut(ActivityIn):
    id: int

class AiDetected(BaseModel):
    titulo: str
    curso: str | None = None
    fecha_limite: datetime | None = None
    tipo: str = "TAREA"
    descripcion: str = ""
    campos_faltantes: list[str] = []

class PlanBlock(BaseModel):
    inicio: datetime
    fin: datetime
    descripcion: str
class PlanProposal(BaseModel):
    actividad_id: int
    bloques: list[PlanBlock]
    conflictos: list[str] = []
