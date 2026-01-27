package io.github.bcmaymonegalvao.bloquinhopy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks.NotebookScreen
import io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks.NotebookViewModel
import io.github.bcmaymonegalvao.bloquinhopy.feature.projects.ProjectListScreen
import io.github.bcmaymonegalvao.bloquinhopy.feature.projects.ProjectViewModel

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Notebooks : Screen("notebooks", "Notebooks", Icons.Default.Home)
    object Projects : Screen("projects", "Projects", Icons.Default.List)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    val items = listOf(Screen.Notebooks, Screen.Projects)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Notebooks.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Notebooks.route) {
                val viewModel: NotebookViewModel = viewModel()
                NotebookScreen(viewModel = viewModel)
            }
            composable(Screen.Projects.route) {
                val viewModel: ProjectViewModel = viewModel()
                ProjectListScreen(
                    viewModel = viewModel,
                    onProjectClick = { /* Handle click */ }
                )
            }
        }
    }
}
