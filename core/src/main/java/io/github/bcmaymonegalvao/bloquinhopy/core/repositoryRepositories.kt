package io.github.bcmaymonegalvao.bloquinhopy.core.repository

import io.github.bcmaymonegalvao.bloquinhopy.core.database.dao.NotebookDao
import io.github.bcmaymonegalvao.bloquinhopy.core.database.dao.ProjectDao
import io.github.bcmaymonegalvao.bloquinhopy.core.database.entity.NotebookEntity
import io.github.bcmaymonegalvao.bloquinhopy.core.database.entity.ProjectEntity
import io.github.bcmaymonegalvao.bloquinhopy.core.model.Notebook
import io.github.bcmaymonegalvao.bloquinhopy.core.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val projectDao: ProjectDao
) {
    fun getProjects(): Flow<List<Project>> = projectDao.getAllProjects().map { entities ->
        entities.map { Project(it.id, it.name, it.description) }
    }

    suspend fun saveProject(project: Project) {
        projectDao.insertProject(ProjectEntity(project.id, project.name, project.description))
    }
}

@Singleton
class NotebookRepository @Inject constructor(
    private val notebookDao: NotebookDao
) {
    fun getNotebooks(projectId: String): Flow<List<Notebook>> = 
        notebookDao.getNotebooksForProject(projectId).map { entities ->
            entities.map { Notebook(it.id, it.name, it.content, it.projectId, it.lastModified) }
        }

    suspend fun saveNotebook(notebook: Notebook) {
        notebookDao.insertNotebook(
            NotebookEntity(notebook.id, notebook.projectId, notebook.name, notebook.content, notebook.lastModified)
        )
    }
}
