package io.github.bcmaymonegalvao.bloquinhopy.feature.projects

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class ProjectRepository {
    private val _projects = MutableStateFlow<List<Project>>(
        listOf(
            Project(UUID.randomUUID().toString(), "My First Notebook", description = "Getting started with Python"),
            Project(UUID.randomUUID().toString(), "Data Analysis", description = "Pandas and Numpy experiments")
        )
    )
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    fun createProject(name: String, description: String) {
        val newProject = Project(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description
        )
        _projects.value = _projects.value + newProject
    }

    fun deleteProject(id: String) {
        _projects.value = _projects.value.filter { it.id != id }
    }
}
