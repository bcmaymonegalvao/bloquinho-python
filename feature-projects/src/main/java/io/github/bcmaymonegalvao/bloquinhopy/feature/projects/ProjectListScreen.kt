package io.github.bcmaymonegalvao.bloquinhopy.feature.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(
    viewModel: ProjectViewModel,
    onProjectClick: (Project) -> Unit
) {
    val projects by viewModel.projects.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Projects") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewProject("New Project", "New description") }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(projects) { project ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onProjectClick(project) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = project.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (project.description.isNotEmpty()) {
                            Text(
                                text = project.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
