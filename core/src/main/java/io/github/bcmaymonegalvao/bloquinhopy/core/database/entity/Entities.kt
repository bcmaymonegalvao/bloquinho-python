database/entity/Entities.ktpackage io.github.bcmaymonegalvao.bloquinhopy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)

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
    val projectId: String?,
    val name: String,
    val content: String,
    val lastModified: Long
)
