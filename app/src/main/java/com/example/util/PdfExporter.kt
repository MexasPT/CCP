package com.example.util

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.data.model.CivilArticle
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PdfExporter {

    fun exportArticlesToPdf(
        context: Context,
        articles: List<CivilArticle>,
        includeNotes: Boolean = true,
        customHeaderTitle: String = "Código Civil Português - Extrato Oficial"
    ): File? {
        if (articles.isEmpty()) {
            Toast.makeText(context, "Nenhum artigo selecionado para exportar", Toast.LENGTH_SHORT).show()
            return null
        }

        val pdfDocument = PdfDocument()
        val pageWidth = 595 // A4 standard width at 72dpi
        val pageHeight = 842 // A4 standard height at 72dpi
        val marginX = 40f
        val marginY = 50f
        val contentWidth = pageWidth - (marginX * 2)

        val titlePaint = Paint().apply {
            color = Color.rgb(19, 41, 61) // Navy
            textSize = 16f
            typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val subtitlePaint = Paint().apply {
            color = Color.rgb(197, 155, 39) // Gold
            textSize = 10f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val metaPaint = Paint().apply {
            color = Color.rgb(100, 116, 139) // Slate
            textSize = 8.5f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            isAntiAlias = true
        }

        val articleHeaderPaint = Paint().apply {
            color = Color.rgb(19, 41, 61)
            textSize = 13f
            typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val epigraphPaint = Paint().apply {
            color = Color.rgb(139, 38, 62) // Wine
            textSize = 11f
            typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
            isAntiAlias = true
        }

        val hierarchyPaint = Paint().apply {
            color = Color.rgb(80, 80, 80)
            textSize = 8.5f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
            isAntiAlias = true
        }

        val bodyPaint = Paint().apply {
            color = Color.rgb(30, 41, 59)
            textSize = 10f
            typeface = Typeface.create(Typeface.SERIF, Typeface.NORMAL)
            isAntiAlias = true
        }

        val linePaint = Paint().apply {
            color = Color.rgb(197, 155, 39)
            strokeWidth = 1.5f
        }

        val thinLinePaint = Paint().apply {
            color = Color.rgb(226, 232, 240)
            strokeWidth = 0.8f
        }

        val commentBoxPaint = Paint().apply {
            color = Color.rgb(248, 250, 252)
            style = Paint.Style.FILL
        }

        val commentBorderPaint = Paint().apply {
            color = Color.rgb(203, 213, 225)
            style = Paint.Style.STROKE
            strokeWidth = 0.8f
        }

        val commentTextPaint = Paint().apply {
            color = Color.rgb(51, 65, 85)
            textSize = 8.5f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            isAntiAlias = true
        }

        var pageNumber = 1
        var pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
        var page = pdfDocument.startPage(pageInfo)
        var canvas: Canvas = page.canvas
        var currentY = marginY

        fun drawHeader() {
            canvas.drawText("REPÚBLICA PORTUGUESA • CÓDIGO CIVIL", marginX, currentY, subtitlePaint)
            currentY += 18f
            canvas.drawText(customHeaderTitle, marginX, currentY, titlePaint)
            currentY += 14f

            val dateStr = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "PT")).format(Date())
            canvas.drawText("Decreto-Lei n.º 47344/66 atualizado • Emitido em: $dateStr • ${articles.size} artigo(s)", marginX, currentY, metaPaint)
            currentY += 10f

            canvas.drawLine(marginX, currentY, pageWidth - marginX, currentY, linePaint)
            currentY += 22f
        }

        fun drawFooter(pNum: Int) {
            canvas.drawLine(marginX, pageHeight - marginY + 15f, pageWidth - marginX, pageHeight - marginY + 15f, thinLinePaint)
            canvas.drawText("CCP - Código Civil Português • Documento de Apoio Jurídico", marginX, pageHeight - marginY + 28f, metaPaint)
            val pageText = "Página $pNum"
            val textWidth = metaPaint.measureText(pageText)
            canvas.drawText(pageText, pageWidth - marginX - textWidth, pageHeight - marginY + 28f, metaPaint)
        }

        drawHeader()

        for (article in articles) {
            // Check space needed for article title + epigraph + hierarchy
            if (currentY > pageHeight - marginY - 120) {
                drawFooter(pageNumber)
                pdfDocument.finishPage(page)
                pageNumber++
                pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
                page = pdfDocument.startPage(pageInfo)
                canvas = page.canvas
                currentY = marginY
                drawHeader()
            }

            // Article Header
            canvas.drawText(article.numberFormatted, marginX, currentY, articleHeaderPaint)
            currentY += 15f
            canvas.drawText(article.epigraph, marginX, currentY, epigraphPaint)
            currentY += 12f
            canvas.drawText("${article.bookTitle} > ${article.titleName}", marginX, currentY, hierarchyPaint)
            currentY += 14f

            // Article Text lines
            val lines = wrapText(article.text, bodyPaint, contentWidth)
            for (line in lines) {
                if (currentY > pageHeight - marginY - 40) {
                    drawFooter(pageNumber)
                    pdfDocument.finishPage(page)
                    pageNumber++
                    pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
                    page = pdfDocument.startPage(pageInfo)
                    canvas = page.canvas
                    currentY = marginY
                    drawHeader()
                }
                canvas.drawText(line, marginX, currentY, bodyPaint)
                currentY += 13f
            }

            currentY += 6f

            // Practical Commentary box if requested
            if (includeNotes && article.practicalCommentary.isNotBlank()) {
                val commentLines = wrapText("Nota prática / Enquadramento: ${article.practicalCommentary}", commentTextPaint, contentWidth - 20f)
                val boxHeight = (commentLines.size * 12f) + 14f

                if (currentY + boxHeight > pageHeight - marginY - 40) {
                    drawFooter(pageNumber)
                    pdfDocument.finishPage(page)
                    pageNumber++
                    pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
                    page = pdfDocument.startPage(pageInfo)
                    canvas = page.canvas
                    currentY = marginY
                    drawHeader()
                }

                canvas.drawRect(marginX, currentY, marginX + contentWidth, currentY + boxHeight, commentBoxPaint)
                canvas.drawRect(marginX, currentY, marginX + contentWidth, currentY + boxHeight, commentBorderPaint)

                var cY = currentY + 13f
                for (cLine in commentLines) {
                    canvas.drawText(cLine, marginX + 10f, cY, commentTextPaint)
                    cY += 12f
                }
                currentY += boxHeight + 12f
            }

            // Divider between articles
            canvas.drawLine(marginX, currentY, pageWidth - marginX, currentY, thinLinePaint)
            currentY += 18f
        }

        drawFooter(pageNumber)
        pdfDocument.finishPage(page)

        return try {
            val exportDir = File(context.cacheDir, "exports")
            if (!exportDir.exists()) exportDir.mkdirs()

            val fileName = "CCP_Artigos_${System.currentTimeMillis()}.pdf"
            val file = File(exportDir, fileName)
            val outputStream = FileOutputStream(file)
            pdfDocument.writeTo(outputStream)
            outputStream.flush()
            outputStream.close()
            pdfDocument.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            pdfDocument.close()
            null
        }
    }

    fun sharePdfFile(context: Context, file: File, title: String = "Partilhar Artigos em PDF") {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "com.aistudio.ccp.civilportugal.fileprovider",
                file
            )
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, title)
                putExtra(Intent.EXTRA_TEXT, "Consulte os artigos selecionados do Código Civil Português em anexo.")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, title))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Erro ao partilhar ficheiro PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun wrapText(text: String, paint: Paint, maxWidth: Float): List<String> {
        val result = mutableListOf<String>()
        val paragraphs = text.split("\n")

        for (paragraph in paragraphs) {
            if (paragraph.isBlank()) {
                result.add("")
                continue
            }

            val words = paragraph.split(" ")
            var currentLine = StringBuilder()

            for (word in words) {
                val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
                val width = paint.measureText(testLine)

                if (width <= maxWidth) {
                    currentLine = StringBuilder(testLine)
                } else {
                    if (currentLine.isNotEmpty()) {
                        result.add(currentLine.toString())
                    }
                    currentLine = StringBuilder(word)
                }
            }

            if (currentLine.isNotEmpty()) {
                result.add(currentLine.toString())
            }
        }
        return result
    }
}
