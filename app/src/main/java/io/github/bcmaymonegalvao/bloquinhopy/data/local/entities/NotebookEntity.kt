package io.github.bcmaymonegalvao.bloquinhopy.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.PrimaryKey

@Entity(
    tableName = "notebooks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotebookEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val name: String,
    val content: String,
    val createdAt: Long,
    val lastModifiedAt: Long
)
