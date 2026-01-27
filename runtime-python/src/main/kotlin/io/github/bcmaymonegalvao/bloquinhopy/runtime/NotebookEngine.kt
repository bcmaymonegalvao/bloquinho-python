package io.github.bcmaymonegalvao.bloquinhopy.runtime

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Representa o ID único de uma célula no notebook.
 */
data class NotebookCellId(val index: Int)

/**
 * Representa os diferentes tipos de saída que uma execução pode gerar.
 */
sealed class NotebookOutput {
    data class Text(val value: String) : NotebookOutput()
    data class Error(val message: String, val traceback: String) : NotebookOutput()
    data class Image(val path: String) : NotebookOutput()
    data class Table(val html: String) : NotebookOutput()
}

/**
 * Interface principal do motor de execução Python.
 */
interface NotebookEngine {
    /**
     * Executa o código de uma célula específica.
     */
    suspend fun runCell(id: NotebookCellId, code: String): List<NotebookOutput>

    /**
     * Limpa o estado atual do interpretador (variáveis, imports, etc).
     */
    suspend fun reset()

    /**
     * Fluxo de eventos de saída em tempo real (stdout/stderr).
     */
    fun outputFlow(): Flow<String>
}

/**
 * Implementação base do motor Python.
 * No futuro, integrará com o Chaquopy ou similar.
 */
class PythonNotebookEngine : NotebookEngine {
    private val _outputFlow = MutableSharedFlow<String>()
    
    // Simula o estado das variáveis entre células
    private val variables = mutableMapOf<String, Any?>()

    override suspend fun runCell(id: NotebookCellId, code: String): List<NotebookOutput> {
        val outputs = mutableListOf<NotebookOutput>()
        
        try {
                        // Initialize Python if not started
            if (!Python.isStarted()) {
                Python.start(AndroidPlatform(context))
            }
            val python = Python.getInstance()
            val mainModule = python.getModule("__main__")
            
            // Execute code and capture result
            val result = mainModule.callAttr("eval", code)
            outputs.add(NotebookOutput.Text(result.toString()))
            _outputFlow.emit("Executando célula ${id.index}...
")
            
            
        } catch (e: Exception) {
            outputs.add(NotebookOutput.Error(
                message = e.message ?: "Erro desconhecido",
                traceback = e.stackTraceToString()
            ))
        }
        
        return outputs
    }

    override suspend fun reset() {
        variables.clear()
        _outputFlow.emit("Engine resetado.
")
    }

    override fun outputFlow(): Flow<String> = _outputFlow.asSharedFlow()
}
