package io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookOutput
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotebookScreen(
    viewModel: NotebookViewModel,
    onSave: (String) -> Unit,
    onLoad: (Uri) -> String?
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    var showLoadConfirmDialog by remember { mutableStateOf(false) }
    var pendingLoadUri by remember { mutableStateOf<Uri?>(null) }
    var showProgress by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarIsError by remember { mutableStateOf(false) }
    
    val snackbarHostState = remember { SnackbarHostState() }
    
    // File picker for saving
    val saveFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->
        uri?.let {
            scope.launch {
                showProgress = true
                try {
                    val content = viewModel.saveNotebook()
                    onSave(content)
                    context.contentResolver.openOutputStream(it)?.use { stream ->
                        stream.write(content.toByteArray())
                    }
                    snackbarMessage = "Notebook saved successfully"
                    snackbarIsError = false
                    showSnackbar = true
                } catch (e: Exception) {
                    snackbarMessage = "Error saving: ${e.message}"
                    snackbarIsError = true
                    showSnackbar = true
                } finally {
                    showProgress = false
                }
            }
        }
    }
    
    // File picker for loading
    val loadFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            pendingLoadUri = it
            showLoadConfirmDialog = true
        }
    }
    
    // Load confirmation dialog
    if (showLoadConfirmDialog) {
        AlertDialog(
            onDismissRequest = { 
                showLoadConfirmDialog = false
                pendingLoadUri = null
            },
            title = { Text("Load Notebook?") },
            text = { Text("Loading a notebook will replace all current cells. Any unsaved changes will be lost.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        scope.launch {
                            showProgress = true
                            showLoadConfirmDialog = false
                            try {
                                pendingLoadUri?.let { uri ->
                                    context.contentResolver.openInputStream(uri)?.use { stream ->
                                        val content = stream.readBytes().toString(Charsets.UTF_8)
                                        viewModel.loadNotebook(content)
                                        snackbarMessage = "Notebook loaded successfully"
                                        snackbarIsError = false
                                        showSnackbar = true
                                    }
                                }
                            } catch (e: Exception) {
                                snackbarMessage = "Error loading: ${e.message}"
                                snackbarIsError = true
                                showSnackbar = true
                            } finally {
                                showProgress = false
                                pendingLoadUri = null
                            }
                        }
                    }
                ) {
                    Text("Load")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { 
                        showLoadConfirmDialog = false
                        pendingLoadUri = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
    
    // Show snackbar
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar(
                message = snackbarMessage,
                duration = SnackbarDuration.Short
            )
            showSnackbar = false
        }
    }
    
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("BloquinhoPy") },
                actions = {
                    IconButton(
                        onClick = { loadFileLauncher.launch(arrayOf("application/json")) },
                        enabled = !showProgress
                    ) {
                        Icon(Icons.Default.FileUpload, contentDescription = "Load Notebook")
                    }
                    IconButton(
                        onClick = { saveFileLauncher.launch("notebook.ipynb") },
                        enabled = !showProgress
                    ) {
                        Icon(Icons.Default.FileDownload, contentDescription = "Save Notebook")
                    }
                    IconButton(
                        onClick = { viewModel.runAll() },
                        enabled = !showProgress
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Run All")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.addCell() },
                enabled = !showProgress
            ) {
                Text("+")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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
            
            // Progress indicator
            if (showProgress) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                            Text(
                                output.result,
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        is NotebookOutput.Error -> {
                            Text(
                                output.message,
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
