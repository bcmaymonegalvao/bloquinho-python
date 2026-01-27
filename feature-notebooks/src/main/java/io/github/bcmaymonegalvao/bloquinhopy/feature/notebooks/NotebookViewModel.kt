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

class NotebookViewModel(
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

    fun executeCell(id: NotebookCellId) {
        val cell = _uiState.value.cells.find { it.id == id } ?: return
        
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    cells = state.cells.map { 
                        if (it.id == id) it.copy(isRunning = true, outputs = emptyList()) else it 
                    }
                )
            }

            val results = engine.runCell(id, cell.code)

            _uiState.update { state ->
                state.copy(
                    cells = state.cells.map { 
                        if (it.id == id) it.copy(isRunning = false, outputs = results) else it 
                    }
                )
            }
        }
    }

    fun addCell() {
        _uiState.update { state ->
            val nextId = NotebookCellId(state.cells.size)
            state.copy(cells = state.cells + CellState(nextId))
        }
    }

    fun resetEngine() {
        viewModelScope.launch {
            engine.reset()
            _uiState.update { state ->
                state.copy(
                    cells = state.cells.map { it.copy(outputs = emptyList()) }
                )
            }
        }
    }
}
