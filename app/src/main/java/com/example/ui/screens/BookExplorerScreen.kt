package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.ArticleCard
import com.example.ui.theme.LegalGold
import com.example.ui.theme.LegalNavyPrimary
import com.example.ui.viewmodel.CivilCodeViewModel

@Composable
fun BookExplorerScreen(
    viewModel: CivilCodeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val books = viewModel.books
    val currentBook = books.find { it.id == uiState.selectedBookId } ?: books.first()
    val articles = viewModel.getArticlesForCurrentBook()
    val categories = viewModel.categories.filter { it.bookId == currentBook.id }

    val bookColor = Color(currentBook.colorHex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("screen_book_explorer")
    ) {
        // Tab Row for 5 Books
        ScrollableTabRow(
            selectedTabIndex = books.indexOfFirst { it.id == currentBook.id }.coerceAtLeast(0),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            edgePadding = 12.dp,
            indicator = {},
            divider = {}
        ) {
            books.forEach { book ->
                val isSelected = book.id == currentBook.id
                val tabColor = Color(book.colorHex)
                Tab(
                    selected = isSelected,
                    onClick = { viewModel.selectBook(book.id) },
                    modifier = Modifier.testTag("tab_book_${book.id}"),
                    text = {
                        Surface(
                            color = if (isSelected) tabColor else Color.Transparent,
                            shape = RoundedCornerShape(20.dp),
                            border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)) else null
                        ) {
                            Text(
                                text = "${book.romanNumber} • ${book.title.replace("LIVRO ", "").take(14)}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                ),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                )
            }
        }

        // Book Header Summary Card
        Surface(
            color = bookColor.copy(alpha = 0.08f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentBook.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = bookColor
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Surface(
                        color = bookColor.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Art. ${currentBook.articleRangeStart} a ${currentBook.articleRangeEnd}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = bookColor
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                Text(
                    text = currentBook.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Category chips in this book if available
                if (categories.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // "Todos" chip
                        val isAllSelected = uiState.selectedCategoryId == null
                        FilterChip(
                            selected = isAllSelected,
                            onClick = { viewModel.selectBook(currentBook.id) },
                            label = { Text("Todos os Artigos") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = bookColor,
                                selectedLabelColor = Color.White
                            )
                        )

                        categories.forEach { category ->
                            val isSelected = uiState.selectedCategoryId == category.id
                            FilterChip(
                                selected = isSelected,
                                onClick = { viewModel.selectCategory(category.id) },
                                label = { Text(category.name) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(category.colorHex),
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }

                // Batch Selection Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${articles.size} artigos listados",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row {
                        TextButton(
                            onClick = {
                                viewModel.selectAllInCurrentView(articles.map { it.id })
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.SelectAll,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = LegalGold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Selecionar Todos", style = MaterialTheme.typography.labelSmall)
                        }

                        if (uiState.selectedForExport.isNotEmpty()) {
                            TextButton(onClick = { viewModel.clearExportSelection() }) {
                                Text("Limpar (${uiState.selectedForExport.size})", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            }
        }

        // Articles List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(articles) { article ->
                ArticleCard(
                    article = article,
                    isFavorite = viewModel.isArticleFavorite(article.id),
                    isSelectedForExport = uiState.selectedForExport.contains(article.id),
                    onArticleClick = { viewModel.openArticle(article.id) },
                    onToggleFavorite = { viewModel.toggleFavorite(article.id) },
                    onToggleExport = { viewModel.toggleExportSelection(article.id) }
                )
            }
        }
    }
}
