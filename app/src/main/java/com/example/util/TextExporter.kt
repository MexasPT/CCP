package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.data.model.CivilArticle

object TextExporter {

    fun formatArticlesAsText(articles: List<CivilArticle>, includeNotes: Boolean = true): String {
        val sb = StringBuilder()
        sb.append("========================================\n")
        sb.append("REPÚBLICA PORTUGUESA • CÓDIGO CIVIL\n")
        sb.append("Decreto-Lei n.º 47344/66 com atualizações\n")
        sb.append("========================================\n\n")

        for ((index, article) in articles.withIndex()) {
            sb.append("${article.numberFormatted} - ${article.epigraph}\n")
            sb.append("${article.bookTitle} > ${article.titleName}\n")
            sb.append("----------------------------------------\n")
            sb.append("${article.text}\n")

            if (includeNotes && article.practicalCommentary.isNotBlank()) {
                sb.append("\n[Nota Prática]: ${article.practicalCommentary}\n")
            }

            if (index < articles.size - 1) {
                sb.append("\n----------------------------------------\n\n")
            }
        }
        sb.append("\n========================================\n")
        sb.append("Exportado via CCP - Código Civil Português\n")
        return sb.toString()
    }

    fun shareAsPlainText(context: Context, articles: List<CivilArticle>, includeNotes: Boolean = true) {
        val text = formatArticlesAsText(articles, includeNotes)
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Artigos do Código Civil Português")
        }
        val shareIntent = Intent.createChooser(sendIntent, "Partilhar texto dos artigos")
        context.startActivity(shareIntent)
    }

    fun copyToClipboard(context: Context, text: String, label: String = "Código Civil") {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Texto copiado para a área de transferência", Toast.LENGTH_SHORT).show()
    }
}
