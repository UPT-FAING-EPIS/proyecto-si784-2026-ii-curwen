from datetime import datetime
from sqlalchemy import select
from app.db import SessionLocal, init_db
from app.models import Student, Course, Activity

def main():
    init_db()
    with SessionLocal() as db:
        if not db.execute(select(Student)).scalars().first():
            s=Student(email="antony@upt.edu.pe",name="Antony Solorzano")
            db.add(s);db.flush()
            courses=[
                Course(student_id=s.id,name="Base de Datos I",short_name="BD1",priority="ALTA",color="#2797FF"),
                Course(student_id=s.id,name="Programación III",short_name="P3",priority="ALTA",color="#35C985"),
                Course(student_id=s.id,name="Ingeniería de Software",short_name="IS",priority="MEDIA",color="#FFA629"),
            ]
            db.add_all(courses);db.flush()
            db.add(Activity(student_id=s.id,course_id=courses[0].id,title="Informe de Base de Datos",description="Informe sobre modelos de bases de datos relacionales",type="TAREA",due_at=datetime(2026,9,30,23,59),status="CONFIRMADA",origin="MANUAL"))
            db.commit();print("Datos demo creados")
        else:
            print("La base ya contiene datos")

if __name__ == "__main__": main()
