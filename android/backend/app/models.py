from datetime import datetime
from sqlalchemy import String, DateTime, Boolean, ForeignKey, Text
from sqlalchemy.orm import Mapped, mapped_column, relationship
from .db import Base

class Student(Base):
    __tablename__ = "students"
    id: Mapped[int] = mapped_column(primary_key=True)
    google_id: Mapped[str] = mapped_column(String(200), unique=True, default="demo-google-id")
    email: Mapped[str] = mapped_column(String(200), unique=True)
    name: Mapped[str] = mapped_column(String(160))
    active: Mapped[bool] = mapped_column(Boolean, default=True)

class Course(Base):
    __tablename__ = "courses"
    id: Mapped[int] = mapped_column(primary_key=True)
    student_id: Mapped[int] = mapped_column(ForeignKey("students.id"), default=1)
    name: Mapped[str] = mapped_column(String(160))
    short_name: Mapped[str] = mapped_column(String(20))
    priority: Mapped[str] = mapped_column(String(20), default="MEDIA")
    color: Mapped[str] = mapped_column(String(20), default="#6C4DFF")
    active: Mapped[bool] = mapped_column(Boolean, default=True)

class Content(Base):
    __tablename__ = "contents"
    id: Mapped[int] = mapped_column(primary_key=True)
    student_id: Mapped[int] = mapped_column(ForeignKey("students.id"), default=1)
    filename: Mapped[str] = mapped_column(String(255))
    mime_type: Mapped[str] = mapped_column(String(100))
    recognized_text: Mapped[str | None] = mapped_column(Text, nullable=True)
    status: Mapped[str] = mapped_column(String(30), default="RECIBIDO")
    received_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)

class Activity(Base):
    __tablename__ = "activities"
    id: Mapped[int] = mapped_column(primary_key=True)
    student_id: Mapped[int] = mapped_column(ForeignKey("students.id"), default=1)
    course_id: Mapped[int | None] = mapped_column(ForeignKey("courses.id"), nullable=True)
    content_id: Mapped[int | None] = mapped_column(ForeignKey("contents.id"), nullable=True)
    title: Mapped[str] = mapped_column(String(220))
    description: Mapped[str] = mapped_column(Text, default="")
    type: Mapped[str] = mapped_column(String(30), default="TAREA")
    due_at: Mapped[datetime] = mapped_column(DateTime)
    status: Mapped[str] = mapped_column(String(30), default="CONFIRMADA")
    origin: Mapped[str] = mapped_column(String(20), default="MANUAL")

class StudyPlan(Base):
    __tablename__ = "study_plans"
    id: Mapped[int] = mapped_column(primary_key=True)
    activity_id: Mapped[int] = mapped_column(ForeignKey("activities.id"))
    status: Mapped[str] = mapped_column(String(20), default="PROPUESTA")
    version: Mapped[int] = mapped_column(default=1)
    generated_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)

class StudyBlock(Base):
    __tablename__ = "study_blocks"
    id: Mapped[int] = mapped_column(primary_key=True)
    plan_id: Mapped[int] = mapped_column(ForeignKey("study_plans.id"))
    start_at: Mapped[datetime] = mapped_column(DateTime)
    end_at: Mapped[datetime] = mapped_column(DateTime)
    description: Mapped[str] = mapped_column(String(255))
    completed: Mapped[bool] = mapped_column(Boolean, default=False)

class TrashItem(Base):
    __tablename__ = "trash_items"
    id: Mapped[int] = mapped_column(primary_key=True)
    student_id: Mapped[int] = mapped_column(ForeignKey("students.id"), default=1)
    element_type: Mapped[str] = mapped_column(String(30))
    element_id: Mapped[int] = mapped_column()
    deleted_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)
