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
            // TODO: Integrar com interpretador Python real
            _outputFlow.emit("Executando célula ${id.index}...
")
            
            // Simulação de lógica de execução
            if (code.contains("print")) {
                val content = code.substringAfter("print(").substringBeforeLast(")")
                outputs.add(NotebookOutput.Text(content.trim('\'', '"')))
            } else if (code.contains("error")) {
                throw Exception("Erro simulado na execução Python")
            } else {
                outputs.add(NotebookOutput.Text("Código executado com sucesso: $code"))
            }
            
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
