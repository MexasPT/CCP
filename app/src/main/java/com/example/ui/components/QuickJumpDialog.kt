package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CivilCodeArticles
import com.example.ui.theme.LegalGold

@Composable
fun QuickJumpDialog(
    initialArticleId: Int,
    onDismiss: () -> Unit,
    onConfirmJump: (Int) -> Unit
) {
    var textInput by remember { mutableStateOf(initialArticleId.toString()) }
    var sliderValue by remember { mutableFloatStateOf(initialArticleId.toFloat()) }

    val currentArticleId = textInput.toIntOrNull() ?: sliderValue.toInt()
    val previewArticle = remember(currentArticleId) {
        val validId = currentArticleId.coerceIn(1, 2334)
        CivilCodeArticles.getArticle(validId)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Dialpad,
                    contentDescription = null,
                    tint = LegalGold
                )
                Text(
                    text = " Saltar para Artigo",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Insira o número ou utilize a barra de navegação rápida (1 a 2334):",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = textInput,
                    onValueChange = { input ->
                        val digits = input.filter { it.isDigit() }.take(4)
                        textInput = digits
                        val num = digits.toIntOrNull()
                        if (num != null && num in 1..2334) {
                            sliderValue = num.toFloat()
                        }
                    },
                    label = { Text("Número do Artigo") },
                    prefix = { Text("Art. ") },
                    suffix = { Text(".º") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            val id = textInput.toIntOrNull()?.coerceIn(1, 2334) ?: 1
                            onConfirmJump(id)
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quick_jump_number")
                )

                Spacer(modifier = Modifier.height(10.dp))

                Slider(
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                        textInput = it.toInt().toString()
                    },
                    valueRange = 1f..2334f,
                    colors = SliderDefaults.colors(
                        thumbColor = LegalGold,
                        activeTrackColor = LegalGold
                    ),
                    modifier = Modifier.testTag("slider_quick_jump")
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Art. 1.º", style = MaterialTheme.typography.labelSmall)
                    Text("Art. 1250.º", style = MaterialTheme.typography.labelSmall)
                    Text("Art. 2334.º", style = MaterialTheme.typography.labelSmall)
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Live Preview Box
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "${previewArticle.numberFormatted} - ${previewArticle.epigraph}",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            maxLines = 1
                        )
                        Text(
                            text = previewArticle.bookTitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val target = textInput.toIntOrNull()?.coerceIn(1, 2334) ?: sliderValue.toInt().coerceIn(1, 2334)
                    onConfirmJump(target)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.testTag("btn_confirm_jump")
            ) {
                Text("Abrir Artigo")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("btn_cancel_jump")
            ) {
                Text("Cancelar")
            }
        }
    )
}
