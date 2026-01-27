package io.github.bcmaymonegalvao.bloquinhopy.feature.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val repository: ProjectRepository = ProjectRepository()
) : ViewModel() {
    val projects: StateFlow<List<Project>> = repository.projects

    fun createNewProject(name: String, description: String) {
        viewModelScope.launch {
            repository.createProject(name, description)
        }
    }

    fun deleteProject(id: String) {
        viewModelScope.launch {
            repository.deleteProject(id)
        }
    }
}
