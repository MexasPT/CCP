package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.NoteEntity
import com.example.ui.components.AddNoteDialog
import com.example.ui.theme.LegalGold
import com.example.ui.theme.LegalNavyPrimary
import com.example.ui.viewmodel.CivilCodeViewModel
import com.example.util.PdfExporter
import com.example.util.TextExporter

@Composable
fun ArticleDetailScreen(
    viewModel: CivilCodeViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val article = viewModel.getArticle(uiState.selectedArticleId)
    val isFavorite = viewModel.isArticleFavorite(article.id)
    val allNotes by viewModel.allNotes.collectAsState()
    val articleNotes = allNotes.filter { it.articleId == article.id }

    var showNoteDialog by remember { mutableStateOf(false) }
    var noteToEdit by remember { mutableStateOf<NoteEntity?>(null) }

    val bookColor = when (article.bookId) {
        "PARTE_GERAL" -> Color(0xFF1A535C)
        "LIVRO_II" -> Color(0xFF1E3A5F)
        "LIVRO_III" -> Color(0xFF2B580C)
        "LIVRO_IV" -> Color(0xFF8B263E)
        "LIVRO_V" -> Color(0xFF9E6200)
        else -> MaterialTheme.colorScheme.primary
    }

    if (showNoteDialog) {
        AddNoteDialog(
            articleId = article.id,
            existingNote = noteToEdit,
            onDismiss = {
                showNoteDialog = false
                noteToEdit = null
            },
            onSaveNote = { title, content, colorTag, noteId ->
                viewModel.saveNote(article.id, title, content, colorTag, noteId)
                showNoteDialog = false
                noteToEdit = null
                Toast.makeText(context, "Anotação guardada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("screen_article_detail"),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Book & Navigation Header Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = bookColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = article.bookTitle,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = bookColor
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Row {
                    // Previous Article Button
                    IconButton(
                        onClick = { if (article.id > 1) viewModel.openArticle(article.id - 1) },
                        enabled = article.id > 1,
                        modifier = Modifier.testTag("btn_prev_article")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Artigo Anterior"
                        )
                    }

                    // Next Article Button
                    IconButton(
                        onClick = { if (article.id < 2334) viewModel.openArticle(article.id + 1) },
                        enabled = article.id < 2334,
                        modifier = Modifier.testTag("btn_next_article")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Artigo Seguinte"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Article Number & Star
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.numberFormatted,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = bookColor
                    )
                )

                if (article.isKeyArticle) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = LegalGold.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = LegalGold,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Artigo Principal",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = LegalGold
                                )
                            )
                        }
                    }
                }
            }

            // Epigraph
            Text(
                text = article.epigraph,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            // Hierarchy
            Text(
                text = "${article.titleName} • ${article.chapterName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Tool Control Bar (Font size, TTS audio, Copy, PDF, Bookmark)
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Audio TTS
                    IconButton(
                        onClick = {
                            if (uiState.isTtsSpeaking) {
                                viewModel.stopTts()
                            } else {
                                viewModel.speakArticle(article)
                            }
                        },
                        modifier = Modifier.testTag("btn_tts_play")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Ouvir Artigo",
                            tint = if (uiState.isTtsSpeaking) LegalGold else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Font Size Minus
                    IconButton(onClick = { viewModel.changeFontSize(-0.1f) }) {
                        Text("A-", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                    }

                    // Font Size Plus
                    IconButton(onClick = { viewModel.changeFontSize(0.1f) }) {
                        Text("A+", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                    }

                    // Copy Text
                    IconButton(
                        onClick = {
                            TextExporter.copyToClipboard(context, "${article.numberFormatted} - ${article.epigraph}\n\n${article.text}")
                        },
                        modifier = Modifier.testTag("btn_copy_article")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copiar Texto"
                        )
                    }

                    // Single PDF Export
                    IconButton(
                        onClick = {
                            val file = PdfExporter.exportArticlesToPdf(context, listOf(article), includeNotes = true)
                            if (file != null) {
                                PdfExporter.sharePdfFile(context, file, "Código Civil - ${article.numberFormatted}")
                            }
                        },
                        modifier = Modifier.testTag("btn_export_pdf_single")
                    ) {
                        Icon(
                            imageVector = Icons.Default.PictureAsPdf,
                            contentDescription = "Gerar PDF",
                            tint = Color(0xFFDC2626)
                        )
                    }

                    // Favorite Toggle
                    IconButton(
                        onClick = { viewModel.toggleFavorite(article.id) },
                        modifier = Modifier.testTag("btn_detail_fav")
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Favorito",
                            tint = if (isFavorite) LegalGold else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Article Text Container
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = article.text,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = (16 * uiState.textScale).sp,
                            lineHeight = (26 * uiState.textScale).sp,
                            fontFamily = if (uiState.useSerifFont) FontFamily.Serif else FontFamily.SansSerif
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Practical Commentary / Doctrine
            if (article.practicalCommentary.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = LegalGold.copy(alpha = 0.08f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LegalGold.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "⚖️ Anotação Prática & Enquadramento",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = LegalNavyPrimary
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = article.practicalCommentary,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (article.keyDoctrineNote.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "• ${article.keyDoctrineNote}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Related Articles Remissões
            if (article.relatedArticles.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Artigos Relacionados & Remissões",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    article.relatedArticles.forEach { relId ->
                        FilledTonalButton(
                            onClick = { viewModel.openArticle(relId) },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("btn_related_art_$relId")
                        ) {
                            Text("Art. $relId.º")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // Notes on this article Header + Add Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Anotações Pessoais (${articleNotes.size})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Button(
                    onClick = {
                        noteToEdit = null
                        showNoteDialog = true
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.testTag("btn_add_note_detail")
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Adicionar Nota", style = MaterialTheme.typography.labelSmall)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        // List of notes for this article
        if (articleNotes.isEmpty()) {
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Ainda não tem anotações neste artigo. Toque em 'Adicionar Nota' para registar comentários de estudo ou jurisprudência.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            items(articleNotes) { note ->
                val noteColor = when (note.colorTag) {
                    "Dourado" -> Color(0xFFC59B27)
                    "Verde" -> Color(0xFF16A34A)
                    "Vinho" -> Color(0xFF8B263E)
                    "Roxo" -> Color(0xFF7C3AED)
                    else -> Color(0xFF1E3A5F)
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, noteColor.copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(noteColor)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = note.title,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }

                            Row {
                                IconButton(
                                    onClick = {
                                        noteToEdit = note
                                        showNoteDialog = true
                                    },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Editar",
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { viewModel.deleteNote(note.id) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Excluir",
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = note.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
