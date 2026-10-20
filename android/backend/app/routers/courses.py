from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy import select
from sqlalchemy.orm import Session
from ..db import get_db
from ..models import Course
from ..schemas import CourseIn, CourseOut
from ..services.rules import unique_course_short_name

router=APIRouter(prefix="/cursos",tags=["cursos"])
@router.get("",response_model=list[CourseOut])
def list_courses(db:Session=Depends(get_db)):
    rows=db.execute(select(Course).where(Course.active.is_(True))).scalars().all()
    return [CourseOut(id=c.id,nombre=c.name,abreviatura=c.short_name,prioridad=c.priority) for c in rows]
@router.post("",response_model=CourseOut)
def create_course(body:CourseIn,db:Session=Depends(get_db)):
    if not unique_course_short_name(db,body.abreviatura): raise HTTPException(409,"La abreviatura ya está en uso")
    c=Course(name=body.nombre,short_name=body.abreviatura,priority=body.prioridad);db.add(c);db.commit();db.refresh(c)
    return CourseOut(id=c.id,**body.model_dump())
