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
    val isExecuting: Boolean = false
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
            } catch (e: Exception) {
                _updateCellOutput(id, listOf(NotebookOutput.Error(e.message ?: "Unknown error")))
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
}
