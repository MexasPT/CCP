package com.example.ui.screens

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.ArticleCard
import com.example.ui.components.BookCard
import com.example.ui.components.CategoryCard
import com.example.ui.theme.LegalGold
import com.example.ui.theme.LegalGoldDark
import com.example.ui.theme.LegalNavyLight
import com.example.ui.theme.LegalNavyPrimary
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.CivilCodeViewModel
import java.util.Locale

@Composable
fun HomeScreen(
    viewModel: CivilCodeViewModel,
    onOpenQuickJump: () -> Unit,
    onOpenQuiz: () -> Unit
) {
    val books = viewModel.books
    val categories = viewModel.categories
    val keyArticles = viewModel.keyArticles

    val speechLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            if (!spokenText.isNullOrBlank()) {
                viewModel.updateSearchQuery(spokenText)
                viewModel.navigateTo(AppScreen.ADVANCED_SEARCH)
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("screen_home"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Hero Card
        item {
            HeroHeader(
                onSearchClick = { viewModel.navigateTo(AppScreen.ADVANCED_SEARCH) },
                onVoiceClick = {
                    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("pt", "PT"))
                        putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga o número do artigo ou termo de pesquisa...")
                    }
                    try {
                        speechLauncher.launch(intent)
                    } catch (e: Exception) {
                        viewModel.navigateTo(AppScreen.ADVANCED_SEARCH)
                    }
                },
                onQuickJumpClick = onOpenQuickJump
            )
        }

        // Section: Os 5 Livros do Código Civil
        item {
            SectionHeader(
                title = "Estrutura do Código Civil",
                subtitle = "Parte Geral e 4 Livros da Parte Especial"
            )
        }

        items(books) { book ->
            BookCard(
                book = book,
                onClick = { viewModel.selectBook(book.id) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        // Section: Categorias Temáticas Segmentadas
        item {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = "Categorias Temáticas",
                subtitle = "Segmentação prática por matéria do direito"
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(
                        category = category,
                        onClick = { viewModel.selectCategory(category.id) },
                        modifier = Modifier.width(280.dp)
                    )
                }
            }
        }

        // Section: Quiz / Estudo Rápido Banner
        item {
            Spacer(modifier = Modifier.height(20.dp))
            QuizBannerCard(
                onStartQuiz = onOpenQuiz,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Section: Artigos Chave / Emblemáticos
        item {
            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader(
                title = "Artigos Mais Consultados",
                subtitle = "Preceitos nucleares da doutrina e jurisprudência"
            )
        }

        items(keyArticles.take(8)) { article ->
            ArticleCard(
                article = article,
                isFavorite = viewModel.isArticleFavorite(article.id),
                isSelectedForExport = viewModel.uiState.value.selectedForExport.contains(article.id),
                onArticleClick = { viewModel.openArticle(article.id) },
                onToggleFavorite = { viewModel.toggleFavorite(article.id) },
                onToggleExport = { viewModel.toggleExportSelection(article.id) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }
    }
}

@Composable
private fun HeroHeader(
    onSearchClick: () -> Unit,
    onVoiceClick: () -> Unit,
    onQuickJumpClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        LegalNavyPrimary,
                        LegalNavyLight
                    )
                )
            )
            .padding(top = 16.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "REPÚBLICA PORTUGUESA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = LegalGold,
                            letterSpacing = 1.2.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Código Civil PT",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontFamily = FontFamily.Serif
                        )
                    )
                    Text(
                        text = "Decreto-Lei n.º 47344/66 • 2334 Artigos Offline",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                }

                Surface(
                    color = LegalGold.copy(alpha = 0.25f),
                    shape = CircleShape
                ) {
                    IconButton(
                        onClick = onQuickJumpClick,
                        modifier = Modifier.testTag("btn_hero_quick_jump")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Dialpad,
                            contentDescription = "Saltar Artigo",
                            tint = LegalGold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Search Bar Component
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearchClick() }
                    .testTag("hero_search_bar")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Pesquisar artigos, nº, termos ou leis...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = onVoiceClick,
                        modifier = Modifier
                            .size(34.dp)
                            .testTag("btn_hero_voice_search")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Pesquisa por Voz",
                            tint = LegalGoldDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun QuizBannerCard(onStartQuiz: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onStartQuiz() }
            .testTag("card_quiz_banner"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF8B263E), Color(0xFF6B1D30))
                    )
                )
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Psychology,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = LegalGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "QUIZ DE DIREITO CIVIL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = LegalGold
                        )
                    )
                }
                Text(
                    text = "Praticar Casos e Artigos",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = "Testes interativos sobre os 5 Livros do Código",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White.copy(alpha = 0.85f)
                    )
                )
            }
        }
    }
}
