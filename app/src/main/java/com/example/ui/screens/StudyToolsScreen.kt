package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.QuizProgressEntity
import com.example.data.model.LatinLegalTerm
import com.example.data.model.PrescriptionRule
import com.example.data.model.QuizLevel
import com.example.ui.theme.LegalGold
import com.example.ui.theme.LegalGoldDark
import com.example.ui.theme.LegalNavyLight
import com.example.ui.theme.LegalNavyPrimary
import com.example.ui.viewmodel.CivilCodeViewModel
import com.example.ui.viewmodel.UiState

@Composable
fun StudyToolsScreen(
    viewModel: CivilCodeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val quizProgressList by viewModel.quizProgressList.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }

    val latinTerms = viewModel.latinTerms
    val prescriptionRules = viewModel.prescriptionRules
    val quizLevels = viewModel.quizLevels

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("screen_study_tools")
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                modifier = Modifier.testTag("tab_quiz"),
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Quiz", fontWeight = FontWeight.Bold)
                    }
                }
            )

            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                modifier = Modifier.testTag("tab_prescription_calc"),
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Prescrições", fontWeight = FontWeight.Bold)
                    }
                }
            )

            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                modifier = Modifier.testTag("tab_latin_glossary"),
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Brocardos", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }

        when (selectedTab) {
            0 -> GamifiedQuizHub(viewModel, uiState, quizLevels, quizProgressList)
            1 -> PrescriptionCalculatorView(viewModel, prescriptionRules)
            2 -> LatinGlossaryView(viewModel, latinTerms)
        }
    }
}

@Composable
private fun GamifiedQuizHub(
    viewModel: CivilCodeViewModel,
    uiState: UiState,
    levels: List<QuizLevel>,
    progressList: List<QuizProgressEntity>
) {
    if (uiState.isPlayingQuizLevel) {
        val currentLevel = levels.find { it.levelId == uiState.selectedQuizLevelId } ?: levels.first()
        if (uiState.isLevelFinished) {
            LevelResultView(
                viewModel = viewModel,
                level = currentLevel,
                score = uiState.currentLevelAttemptScore,
                totalQuestions = currentLevel.questions.size,
                onRetry = { viewModel.retryCurrentLevel() },
                onNextLevel = { viewModel.nextQuizLevel() },
                onBackToList = { viewModel.exitLevelToLevelList() }
            )
        } else {
            ActiveLevelQuizView(
                viewModel = viewModel,
                uiState = uiState,
                level = currentLevel,
                onExit = { viewModel.exitLevelToLevelList() }
            )
        }
    } else {
        LevelListView(
            viewModel = viewModel,
            levels = levels,
            progressList = progressList,
            onSelectLevel = { levelId -> viewModel.startQuizLevel(levelId) }
        )
    }
}

@Composable
private fun LevelListView(
    viewModel: CivilCodeViewModel,
    levels: List<QuizLevel>,
    progressList: List<QuizProgressEntity>,
    onSelectLevel: (Int) -> Unit
) {
    val totalPoints = progressList.sumOf { it.earnedPoints }
    val completedCount = progressList.count { it.isCompleted }
    val perfectCount = progressList.count { it.isPerfect }

    val juristRank = when {
        perfectCount >= 10 -> "Juiz Conselheiro Emérito 🌟"
        completedCount >= 10 -> "Juiz de Direito ⚖️"
        completedCount >= 7 -> "Advogado Civilista 📜"
        completedCount >= 4 -> "Advogado Estagiário 🎓"
        completedCount >= 2 -> "Bacharel em Leis 🏛️"
        completedCount >= 1 -> "Estudante Notável 📚"
        else -> "Caloiro de Direito 📖"
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("quiz_level_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Gamification Header Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_gamification_header"),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(LegalNavyPrimary, LegalNavyLight)
                            )
                        )
                        .padding(18.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "JOGO DOS NÍVEIS CCP",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = LegalGold,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp
                                    )
                                )
                                Text(
                                    text = juristRank,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }

                            // Total Points Pill
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = LegalGold.copy(alpha = 0.25f),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LegalGold)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = LegalGold,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "$totalPoints pts",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Progress summary
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Progresso: $completedCount de ${levels.size} Níveis Concluídos",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.White.copy(alpha = 0.9f))
                            )
                            Text(
                                text = "${(completedCount * 100) / levels.size}%",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = LegalGold,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { completedCount.toFloat() / levels.size.toFloat() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = LegalGold,
                            trackColor = Color.White.copy(alpha = 0.2f)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Game Rules Pill info
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color.Black.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "• Sucesso: falhar até 3 em 10 (mínimo 7/10 para avançar)\n• Cada resposta certa = 1 ponto | 100% perfeito = +3 pontos extra\n• Pode repetir sem limitações para bater recordes!",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White.copy(alpha = 0.85f),
                                    lineHeight = 16.sp
                                ),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }

        // Section Title
        item {
            Text(
                text = "10 Níveis de Desafio Civilista",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        // 10 Level Cards
        items(levels) { level ->
            val isUnlocked = viewModel.isLevelUnlocked(level.levelId, progressList)
            val progress = progressList.find { it.levelId == level.levelId }
            val isCompleted = progress?.isCompleted == true
            val isPerfect = progress?.isPerfect == true
            val bestScore = progress?.bestScore ?: 0
            val earnedPoints = progress?.earnedPoints ?: 0

            QuizLevelCard(
                level = level,
                isUnlocked = isUnlocked,
                isCompleted = isCompleted,
                isPerfect = isPerfect,
                bestScore = bestScore,
                earnedPoints = earnedPoints,
                attempts = progress?.attemptsCount ?: 0,
                onPlay = { if (isUnlocked) onSelectLevel(level.levelId) }
            )
        }
    }
}

@Composable
private fun QuizLevelCard(
    level: QuizLevel,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    isPerfect: Boolean,
    bestScore: Int,
    earnedPoints: Int,
    attempts: Int,
    onPlay: () -> Unit
) {
    val levelIcon = when (level.iconName) {
        "account_balance" -> Icons.Default.AccountBalance
        "person" -> Icons.Default.Person
        "fact_check" -> Icons.Default.FactCheck
        "schedule" -> Icons.Default.Schedule
        "handshake" -> Icons.Default.Handshake
        "balance" -> Icons.Default.Balance
        "shield" -> Icons.Default.Shield
        "menu_book" -> Icons.Default.MenuBook
        "domain" -> Icons.Default.Domain
        "family_restroom" -> Icons.Default.FamilyRestroom
        else -> Icons.Default.Psychology
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("quiz_level_card_${level.levelId}")
            .clickable(enabled = isUnlocked) { onPlay() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isUnlocked) 3.dp else 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = when {
                !isUnlocked -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                isPerfect -> MaterialTheme.colorScheme.surface
                isCompleted -> MaterialTheme.colorScheme.surface
                else -> MaterialTheme.colorScheme.surface
            }
        ),
        border = when {
            isPerfect -> androidx.compose.foundation.BorderStroke(2.dp, LegalGold)
            isCompleted -> androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF2E7D32))
            isUnlocked -> androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            else -> androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Level Number/Status Badge Icon
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                !isUnlocked -> MaterialTheme.colorScheme.surfaceVariant
                                isPerfect -> LegalGold
                                isCompleted -> Color(0xFF2E7D32)
                                else -> MaterialTheme.colorScheme.primary
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (!isUnlocked) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Bloqueado",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    } else if (isPerfect) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Perfeito 100%",
                            tint = Color.Black,
                            modifier = Modifier.size(26.dp)
                        )
                    } else if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Concluído",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = levelIcon,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = level.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )

                        // Status Tag
                        if (!isUnlocked) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = "BLOQUEADO",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        } else if (isPerfect) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = LegalGold.copy(alpha = 0.2f),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LegalGold)
                            ) {
                                Text(
                                    text = "★ 10/10 PERFEITO",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LegalGoldDark
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        } else if (isCompleted) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFFE8F5E9)
                            ) {
                                Text(
                                    text = "CONCLUÍDO ($bestScore/10)",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF2E7D32)
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = "DISPONÍVEL",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Text(
                        text = level.subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = level.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Abrange: ${level.bookReference}",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Action / Score Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isUnlocked) {
                    if (attempts > 0) {
                        Text(
                            text = "Melhor: $bestScore/10 ($earnedPoints pts) • $attempts tentativa(s)",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = if (isPerfect) LegalGoldDark else MaterialTheme.colorScheme.onSurface
                            )
                        )
                    } else {
                        Text(
                            text = "10 Perguntas • Não iniciado",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }

                    Button(
                        onClick = onPlay,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isCompleted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("play_level_button_${level.levelId}")
                    ) {
                        Icon(
                            imageVector = if (attempts > 0) Icons.Default.Refresh else Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isCompleted) "Repetir" else if (attempts > 0) "Tentar +" else "Jogar",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                } else {
                    Text(
                        text = "Conclua o Nível ${level.levelId - 1} com sucesso para desbloquear",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ActiveLevelQuizView(
    viewModel: CivilCodeViewModel,
    uiState: UiState,
    level: QuizLevel,
    onExit: () -> Unit
) {
    val qIndex = uiState.levelQuestionIndex.coerceIn(0, level.questions.size - 1)
    val question = level.questions[qIndex]
    val selectedOption = uiState.levelSelectedOption
    val isSubmitted = uiState.isLevelAnswerSubmitted
    val progress = (qIndex + 1).toFloat() / level.questions.size.toFloat()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("active_quiz_level_view"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Active Quiz Top Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onExit,
                        modifier = Modifier.testTag("quiz_exit_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar aos Níveis"
                        )
                    }
                    Column {
                        Text(
                            text = level.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Pergunta ${qIndex + 1} de ${level.questions.size}",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                        )
                    }
                }

                // Current Score Counter
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "Acertos: ${uiState.currentLevelAttemptScore}/${qIndex + (if (isSubmitted) 1 else 0)}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }

        // Question Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_question_card"),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            text = question.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp
                        )
                    )
                }
            }
        }

        // Option Choices
        items(question.options.indices.toList()) { optIndex ->
            val optionText = question.options[optIndex]
            val isSelected = selectedOption == optIndex
            val isCorrect = optIndex == question.correctIndex

            val optionColor = when {
                !isSubmitted && isSelected -> MaterialTheme.colorScheme.primaryContainer
                !isSubmitted -> MaterialTheme.colorScheme.surface
                isSubmitted && isCorrect -> Color(0xFFE8F5E9) // Light green
                isSubmitted && isSelected && !isCorrect -> Color(0xFFFFEBEE) // Light red
                else -> MaterialTheme.colorScheme.surface
            }

            val borderColor = when {
                !isSubmitted && isSelected -> MaterialTheme.colorScheme.primary
                !isSubmitted -> MaterialTheme.colorScheme.outlineVariant
                isSubmitted && isCorrect -> Color(0xFF2E7D32)
                isSubmitted && isSelected && !isCorrect -> Color(0xFFC62828)
                else -> MaterialTheme.colorScheme.outlineVariant
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_option_$optIndex")
                    .clickable(enabled = !isSubmitted) {
                        viewModel.selectQuizOption(optIndex)
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = optionColor),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isSubmitted && isCorrect -> Color(0xFF2E7D32)
                                    isSubmitted && isSelected && !isCorrect -> Color(0xFFC62828)
                                    isSelected -> MaterialTheme.colorScheme.primary
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        val label = listOf("A", "B", "C", "D").getOrElse(optIndex) { "${optIndex + 1}" }
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected || (isSubmitted && isCorrect)) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = optionText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isSelected || (isSubmitted && isCorrect)) FontWeight.SemiBold else FontWeight.Normal
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    if (isSubmitted) {
                        if (isCorrect) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Correta",
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(22.dp)
                            )
                        } else if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Incorreta",
                                tint = Color(0xFFC62828),
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }
        }

        // Feedback & Article Link (After answer is submitted)
        if (isSubmitted) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("quiz_explanation_card"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Enquadramento Legal & Fundamentação",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = question.explanation,
                            style = MaterialTheme.typography.bodySmall.copy(
                                lineHeight = 18.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedButton(
                            onClick = { viewModel.openArticle(question.articleId) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.testTag("btn_consult_article_${question.articleId}")
                        ) {
                            Text(
                                text = "Consultar Artigo ${question.articleId}.º no Código Civil",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }

        // Bottom Action Button
        item {
            Spacer(modifier = Modifier.height(6.dp))
            if (!isSubmitted) {
                Button(
                    onClick = { viewModel.submitQuizAnswer() },
                    enabled = selectedOption != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_submit_quiz_answer"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Submeter Resposta",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            } else {
                Button(
                    onClick = { viewModel.nextQuizQuestion() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_next_quiz_question"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (qIndex + 1 == level.questions.size) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = if (qIndex + 1 == level.questions.size) "Ver Resultado do Nível" else "Próxima Pergunta",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelResultView(
    viewModel: CivilCodeViewModel,
    level: QuizLevel,
    score: Int,
    totalQuestions: Int,
    onRetry: () -> Unit,
    onNextLevel: () -> Unit,
    onBackToList: () -> Unit
) {
    // Success criteria: fail at most 3 in 10 -> at least 7 correct answers
    val isPassed = score >= 7
    val isPerfect = score == 10
    val earnedPoints = score + (if (isPerfect) 3 else 0)
    val hasNextLevel = level.levelId < 10

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("quiz_level_result_view"),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))

            // Result Hero Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isPassed) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface
                ),
                border = if (isPerfect) androidx.compose.foundation.BorderStroke(2.dp, LegalGold)
                else if (isPassed) androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF2E7D32))
                else androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.error)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isPerfect -> LegalGold
                                    isPassed -> Color(0xFF2E7D32)
                                    else -> Color(0xFFC62828)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when {
                                isPerfect -> Icons.Default.AutoAwesome
                                isPassed -> Icons.Default.CheckCircle
                                else -> Icons.Default.Close
                            },
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(46.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = when {
                            isPerfect -> "DESEMPENHO PERFEITO 100%!"
                            isPassed -> "NÍVEL CONCLUÍDO COM SUCESSO!"
                            else -> "NÍVEL NÃO CONCLUÍDO"
                        },
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = when {
                                isPerfect -> LegalGoldDark
                                isPassed -> Color(0xFF2E7D32)
                                else -> Color(0xFFC62828)
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = level.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Score Circle / Pill
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$score / $totalQuestions",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isPassed) MaterialTheme.colorScheme.primary else Color(0xFFC62828)
                                )
                            )
                            Text(
                                text = if (score == 10) "Erros: 0 de 10" else "Erros: ${10 - score} de 10 (Máx. permitido: 3)",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Points earned breakdown
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isPerfect) LegalGold.copy(alpha = 0.2f) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = if (isPerfect) {
                                "🏆 Pontos ganhos: $score + 3 BÓUS EXTRA = +$earnedPoints PONTOS!"
                            } else {
                                "⭐ Pontos ganhos nesta tentativa: +$earnedPoints PONTOS"
                            },
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isPerfect) LegalGoldDark else MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // Action Buttons
        item {
            if (isPassed && hasNextLevel) {
                Button(
                    onClick = onNextLevel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_next_level_after_win"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                ) {
                    Text(
                        text = "Avançar para o Nível ${level.levelId + 1}",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

        item {
            FilledTonalButton(
                onClick = onRetry,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_retry_level"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isPassed) "Repetir Nível para Melhorar Pontuação" else "Tentar Novamente (Tentativas Ilimitadas)",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }

        item {
            OutlinedButton(
                onClick = onBackToList,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_back_to_level_list"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Voltar à Lista de Níveis",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun PrescriptionCalculatorView(
    viewModel: CivilCodeViewModel,
    rules: List<PrescriptionRule>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("screen_prescriptions"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Guia Prático de Prescrições",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                        Text(
                            text = "Prazos gerais, ordinários e presunções legais do Código Civil",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        )
                    }
                }
            }
        }

        items(rules) { rule ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("prescription_rule_${rule.articleId}"),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = rule.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Text(
                                text = rule.durationText,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = rule.description,
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Exemplos Práticos:",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    rule.examples.forEach { example ->
                        Text(
                            text = "• $example",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.padding(start = 6.dp, top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = { viewModel.openArticle(rule.articleId) },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Ver Artigo ${rule.articleId}.º no Código")
                    }
                }
            }
        }
    }
}

@Composable
private fun LatinGlossaryView(
    viewModel: CivilCodeViewModel,
    terms: List<LatinLegalTerm>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("screen_latin_glossary"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Brocardos & Locuções Latinas",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                        Text(
                            text = "Máximas jurídicas e correspondência nos artigos do Código Civil",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                            )
                        )
                    }
                }
            }
        }

        items(terms) { term ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("latin_term_${term.term.replace(" ", "_")}"),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = term.term,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    Text(
                        text = "Tradução: ${term.translation}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = term.explanation,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = { viewModel.openArticle(term.articleRef) },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Ver Artigo ${term.articleRef}.º")
                    }
                }
            }
        }
    }
}
