package io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookOutput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotebookScreen(
    viewModel: NotebookViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("BloquinhoPy") },
                actions = {
                    IconButton(onClick = { viewModel.runAll() }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Run All")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addCell() }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.cells, key = { it.id.value }) { cell ->
                CellItem(
                    cell = cell,
                    onCodeChange = { viewModel.updateCellCode(cell.id, it) },
                    onRunClick = { viewModel.runCell(cell.id) }
                )
            }
        }
    }
}

@Composable
fun CellItem(
    cell: CellState,
    onCodeChange: (String) -> Unit,
    onRunClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Cell #${cell.id.value}", style = MaterialTheme.typography.labelSmall)
                IconButton(onClick = onRunClick, enabled = !cell.isRunning) {
                    if (cell.isRunning) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    } else {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Run")
                    }
                }
            }
            
            TextField(
                value = cell.code,
                onValueChange = onCodeChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Python code...") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
            
            if (cell.outputs.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                cell.outputs.forEach { output ->
                    when (output) {
                        is NotebookOutput.Success -> {
                            Text(output.result, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                        }
                        is NotebookOutput.Error -> {
                            Text(output.message, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
