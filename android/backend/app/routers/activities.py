from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy import select
from sqlalchemy.orm import Session
from ..db import get_db
from ..models import Activity, Course
from ..schemas import ActivityIn, ActivityOut
from ..services.rules import is_duplicate

router = APIRouter(prefix="/actividades", tags=["actividades"])

@router.get("", response_model=list[ActivityOut])
def list_activities(db: Session = Depends(get_db)):
    rows = db.execute(select(Activity)).scalars().all()
    out=[]
    for a in rows:
        course = db.get(Course, a.course_id) if a.course_id else None
        out.append(ActivityOut(id=a.id,titulo=a.title,curso=course.name if course else None,fecha_limite=a.due_at,estado=a.status,descripcion=a.description,tipo=a.type))
    return out

@router.post("", response_model=ActivityOut)
def create_activity(body: ActivityIn, db: Session = Depends(get_db)):
    course_id=None
    if body.curso:
        course=db.execute(select(Course).where(Course.name==body.curso)).scalar_one_or_none()
        course_id=course.id if course else None
    if is_duplicate(db, body.titulo, course_id, body.fecha_limite):
        raise HTTPException(409,"Actividad duplicada: mismo título, curso y fecha límite")
    a=Activity(title=body.titulo,description=body.descripcion,type=body.tipo,due_at=body.fecha_limite,status=body.estado,origin="MANUAL",course_id=course_id)
    db.add(a);db.commit();db.refresh(a)
    return ActivityOut(id=a.id,**body.model_dump())

@router.patch("/{activity_id}/confirmar")
def confirm(activity_id:int,db:Session=Depends(get_db)):
    a=db.get(Activity,activity_id)
    if not a: raise HTTPException(404,"Actividad no encontrada")
    if not a.title or not a.due_at: raise HTTPException(422,"Faltan título o fecha límite")
    a.status="CONFIRMADA";db.commit();return {"ok":True,"estado":"CONFIRMADA"}
