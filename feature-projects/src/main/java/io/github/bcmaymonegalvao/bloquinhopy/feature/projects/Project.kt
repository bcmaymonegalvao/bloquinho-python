package io.github.bcmaymonegalvao.bloquinhopy.feature.projects

data class Project(
    val id: String,
    val name: String,
    val lastModified: Long = System.currentTimeMillis(),
    val description: String = ""
)
