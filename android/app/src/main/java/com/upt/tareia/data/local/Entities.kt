package com.upt.tareia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val course: String,
    val dueDate: String,
    val status: String
)

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val shortName: String,
    val priority: String
)
