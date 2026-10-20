package com.upt.tareia.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TareiaDao {
    @Query("SELECT * FROM activities") fun observeActivities(): Flow<List<ActivityEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsertActivity(item: ActivityEntity)
    @Query("SELECT * FROM courses") fun observeCourses(): Flow<List<CourseEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsertCourse(item: CourseEntity)
}

@Database(entities = [ActivityEntity::class, CourseEntity::class], version = 1, exportSchema = false)
abstract class TareiaDatabase : RoomDatabase() {
    abstract fun dao(): TareiaDao
}
