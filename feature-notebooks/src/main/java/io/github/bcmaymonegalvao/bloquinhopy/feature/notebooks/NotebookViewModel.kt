package io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookCellId
import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookEngine
import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookOutput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

data class CellState(
    val id: NotebookCellId,
    val code: String = "",
    val outputs: List<NotebookOutput> = emptyList(),
    val isRunning: Boolean = false
)

data class NotebookUiState(
    val cells: List<CellState> = listOf(CellState(NotebookCellId(0))),
    val isExecuting: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val lastSavedPath: String? = null
)

@HiltViewModel
class NotebookViewModel @Inject constructor(
    private val engine: NotebookEngine
) : ViewModel() {
    private val _uiState = MutableStateFlow(NotebookUiState())
    val uiState: StateFlow<NotebookUiState> = _uiState.asStateFlow()

    fun updateCellCode(id: NotebookCellId, newCode: String) {
        _uiState.update { state ->
            state.copy(
                cells = state.cells.map {
                    if (it.id == id) it.copy(code = newCode) else it
                }
            )
        }
    }

    fun addCell() {
        _uiState.update { state ->
            val nextIdValue = (state.cells.maxOfOrNull { it.id.value } ?: -1) + 1
            state.copy(
                cells = state.cells + CellState(NotebookCellId(nextIdValue))
            )
        }
    }

    fun runCell(id: NotebookCellId) {
        val cell = _uiState.value.cells.find { it.id == id } ?: return
        
        viewModelScope.launch {
            _updateCellRunning(id, true)
            try {
                val output = engine.execute(cell.code)
                _updateCellOutput(id, listOf(output))
                _clearError()
            } catch (e: Exception) {
                _updateCellOutput(id, listOf(NotebookOutput.Error(e.message ?: "Unknown error")))
                _setError(e.message ?: "Execution error")
            } finally {
                _updateCellRunning(id, false)
            }
        }
    }

    fun runAll() {
        viewModelScope.launch {
            _uiState.value.cells.forEach { cell ->
                runCell(cell.id)
            }
        }
    }

    /**
     * Save current notebook to .ipynb format.
     * Returns the serialized notebook as a JSON string.
     */
    fun saveNotebook(): String {
        return try {
            _setLoading(true)
            val content = NotebookSerializer.toIpynb(_uiState.value.cells)
            _clearError()
            content
        } catch (e: Exception) {
            _setError("Failed to serialize notebook: ${e.message}")
            throw e
        } finally {
            _setLoading(false)
        }
    }
    
    /**
     * Load notebook from .ipynb JSON string.
     */
    fun loadNotebook(ipynb: String) {
        viewModelScope.launch {
            try {
                _setLoading(true)
                val loadedCells = NotebookSerializer.fromIpynb(ipynb)
                _uiState.update { state ->
                    state.copy(
                        cells = loadedCells,
                        errorMessage = null
                    )
                }
                _clearError()
            } catch (e: Exception) {
                _setError("Failed to load notebook: ${e.message}")
                throw e
            } finally {
                _setLoading(false)
            }
        }
    }

    private fun _updateCellRunning(id: NotebookCellId, isRunning: Boolean) {
        _uiState.update { state ->
            state.copy(
                cells = state.cells.map {
                    if (it.id == id) it.copy(isRunning = isRunning) else it
                }
            )
        }
    }

    private fun _updateCellOutput(id: NotebookCellId, outputs: List<NotebookOutput>) {
        _uiState.update { state ->
            state.copy(
                cells = state.cells.map {
                    if (it.id == id) it.copy(outputs = outputs) else it
                }
            )
        }
    }

    private fun _setLoading(isLoading: Boolean) {
        _uiState.update { state ->
            state.copy(isLoading = isLoading)
        }
    }

    private fun _setError(message: String?) {
        _uiState.update { state ->
            state.copy(errorMessage = message)
        }
    }

    private fun _clearError() {
        _setError(null)
    }
}
