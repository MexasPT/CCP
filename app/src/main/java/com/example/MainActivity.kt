package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AppBottomNavigation
import com.example.ui.components.AppTopBar
import com.example.ui.components.QuickJumpDialog
import com.example.ui.screens.AdvancedSearchScreen
import com.example.ui.screens.ArticleDetailScreen
import com.example.ui.screens.BookExplorerScreen
import com.example.ui.screens.ExportStudioScreen
import com.example.ui.screens.FavoritesAndNotesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.StudyToolsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.CivilCodeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp(
    viewModel: CivilCodeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showQuickJumpDialog by remember { mutableStateOf(false) }

    val canNavigateBack = uiState.previousScreens.isNotEmpty()

    BackHandler(enabled = canNavigateBack) {
        viewModel.navigateBack()
    }

    if (showQuickJumpDialog) {
        QuickJumpDialog(
            initialArticleId = uiState.selectedArticleId,
            onDismiss = { showQuickJumpDialog = false },
            onConfirmJump = { articleId ->
                showQuickJumpDialog = false
                viewModel.openArticle(articleId)
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                currentScreen = uiState.currentScreen,
                canNavigateBack = canNavigateBack,
                onNavigateBack = { viewModel.navigateBack() },
                onOpenQuickJump = { showQuickJumpDialog = true },
                onOpenSearch = { viewModel.navigateTo(AppScreen.ADVANCED_SEARCH) },
                onOpenExport = { viewModel.navigateTo(AppScreen.EXPORT_STUDIO) },
                exportSelectionCount = uiState.selectedForExport.size
            )
        },
        bottomBar = {
            AppBottomNavigation(
                currentScreen = uiState.currentScreen,
                onNavigate = { screen -> viewModel.navigateTo(screen) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = uiState.currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    AppScreen.HOME -> HomeScreen(
                        viewModel = viewModel,
                        onOpenQuickJump = { showQuickJumpDialog = true },
                        onOpenQuiz = { viewModel.navigateTo(AppScreen.STUDY_TOOLS) }
                    )
                    AppScreen.BOOK_EXPLORER -> BookExplorerScreen(
                        viewModel = viewModel
                    )
                    AppScreen.ARTICLE_DETAIL -> ArticleDetailScreen(
                        viewModel = viewModel
                    )
                    AppScreen.ADVANCED_SEARCH -> AdvancedSearchScreen(
                        viewModel = viewModel
                    )
                    AppScreen.FAVORITES_AND_NOTES -> FavoritesAndNotesScreen(
                        viewModel = viewModel
                    )
                    AppScreen.EXPORT_STUDIO -> ExportStudioScreen(
                        viewModel = viewModel
                    )
                    AppScreen.STUDY_TOOLS -> StudyToolsScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
