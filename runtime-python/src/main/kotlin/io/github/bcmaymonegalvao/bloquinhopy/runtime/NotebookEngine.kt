package io.github.bcmaymonegalvao.bloquinhopy.runtime

data class NotebookCellId(val index: Int)

sealed class NotebookOutput {
    data class Text(val value: String) : NotebookOutput()
    data class Error(val message: String, val traceback: String) : NotebookOutput()
    data class Image(val path: String) : NotebookOutput()
    data class Table(val html: String) : NotebookOutput()
}

interface NotebookEngine {
    suspend fun load(path: String)
    suspend fun save(path: String)
    suspend fun runCell(id: NotebookCellId): List<NotebookOutput>
    suspend fun runAll(): Map<NotebookCellId, List<NotebookOutput>>
}

class HelloNotebookEngine : NotebookEngine {
    override suspend fun load(path: String) {
        // Stub
    }

    override suspend fun save(path: String) {
        // Stub
    }

    override suspend fun runCell(id: NotebookCellId): List<NotebookOutput> {
        return listOf(
            NotebookOutput.Text("Hello from BloquinhoPy (cell ${id.index})")
        )
    }

    override suspend fun runAll(): Map<NotebookCellId, List<NotebookOutput>> {
        return (0 until 3).associate { idx ->
            NotebookCellId(idx) to runCell(NotebookCellId(idx))
        }
    }
}
