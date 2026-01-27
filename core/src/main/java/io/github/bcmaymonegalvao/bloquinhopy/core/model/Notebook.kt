package io.github.bcmaymonegalvao.bloquinhopy.core.model

import java.util.UUID

data class Notebook(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val content: String = "",
    val projectId: String? = null,
    val lastModified: Long = System.currentTimeMillis()
)
