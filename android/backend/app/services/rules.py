from sqlalchemy import select
from sqlalchemy.orm import Session
from ..models import Activity, Course

def is_duplicate(db: Session, title: str, course_id: int | None, due_at) -> bool:
    stmt = select(Activity).where(Activity.title == title, Activity.course_id == course_id, Activity.due_at == due_at)
    return db.execute(stmt).scalar_one_or_none() is not None

def unique_course_short_name(db: Session, short_name: str) -> bool:
    stmt = select(Course).where(Course.short_name == short_name, Course.active.is_(True))
    return db.execute(stmt).scalar_one_or_none() is None
