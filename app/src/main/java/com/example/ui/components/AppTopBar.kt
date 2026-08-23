package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LegalGold
import com.example.ui.theme.LegalNavyPrimary
import com.example.ui.viewmodel.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onOpenQuickJump: () -> Unit,
    onOpenSearch: () -> Unit,
    onOpenExport: () -> Unit,
    exportSelectionCount: Int = 0
) {
    val titleText = when (currentScreen) {
        AppScreen.HOME -> "Código Civil PT"
        AppScreen.BOOK_EXPLORER -> "Catálogo de Livros"
        AppScreen.ARTICLE_DETAIL -> "Leitor de Artigo"
        AppScreen.ADVANCED_SEARCH -> "Pesquisa Avançada"
        AppScreen.FAVORITES_AND_NOTES -> "Favoritos & Notas"
        AppScreen.EXPORT_STUDIO -> "Exportação PDF & Texto"
        AppScreen.STUDY_TOOLS -> "Estudo"
    }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (currentScreen == AppScreen.HOME) {
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(LegalGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "§",
                            color = LegalNavyPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                    }
                }
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.3.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.testTag("top_bar_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            }
        },
        actions = {
            // Quick Jump to Article Number
            IconButton(
                onClick = onOpenQuickJump,
                modifier = Modifier.testTag("top_bar_quick_jump")
            ) {
                Icon(
                    imageVector = Icons.Default.Dialpad,
                    contentDescription = "Ir para Artigo",
                    tint = LegalGold
                )
            }

            if (currentScreen != AppScreen.ADVANCED_SEARCH) {
                IconButton(
                    onClick = onOpenSearch,
                    modifier = Modifier.testTag("top_bar_search")
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar"
                    )
                }
            }

            // Export Studio Icon with Badge
            IconButton(
                onClick = onOpenExport,
                modifier = Modifier.testTag("top_bar_export")
            ) {
                if (exportSelectionCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(containerColor = LegalGold, contentColor = LegalNavyPrimary) {
                                Text(exportSelectionCount.toString())
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Exportar Artigos"
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Exportar Artigos"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
