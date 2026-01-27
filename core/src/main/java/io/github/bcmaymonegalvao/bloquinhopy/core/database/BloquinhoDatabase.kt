package io.github.bcmaymonegalvao.bloquinhopy.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.bcmaymonegalvao.bloquinhopy.core.database.dao.NotebookDao
import io.github.bcmaymonegalvao.bloquinhopy.core.database.dao.ProjectDao
import io.github.bcmaymonegalvao.bloquinhopy.core.database.entity.NotebookEntity
import io.github.bcmaymonegalvao.bloquinhopy.core.database.entity.ProjectEntity

@Database(
    entities = [ProjectEntity::class, NotebookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BloquinhoDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun notebookDao(): NotebookDao
}
