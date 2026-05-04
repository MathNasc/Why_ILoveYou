package com.example.oporque_euteamo

import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var lovePhraseText: TextView
    private lateinit var shareButton: FloatingActionButton
    private lateinit var refreshButton: FloatingActionButton
    private lateinit var lovePhrases: Array<String>
    private var currentPhrase: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        loadLovePhrases()
        displayRandomPhrase()
        setupButtons()
    }

    private fun initializeViews() {
        lovePhraseText = findViewById(R.id.love_phrase_text)
        shareButton = findViewById(R.id.share_button)
        refreshButton = findViewById(R.id.refresh_button)
    }

    private fun loadLovePhrases() {
        lovePhrases = resources.getStringArray(R.array.love_phrases)
    }

    private fun displayRandomPhrase() {
        if (lovePhrases.isNotEmpty()) {
            val randomIndex = Random.nextInt(lovePhrases.size)
            currentPhrase = lovePhrases[randomIndex]
            lovePhraseText.text = currentPhrase
        }
    }

    private fun setupButtons() {
        shareButton.setOnClickListener {
            shareAsImage()
        }

        refreshButton.setOnClickListener {
            displayRandomPhrase()
        }
    }

    private fun shareAsImage() {
        val bitmap = createPhraseBitmap()
        val uri = saveBitmapToCache(bitmap)

        if (uri != null) {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(shareIntent, "Compartilhar com amor"))
        }
    }

    private fun createPhraseBitmap(): Bitmap {
        val width = 1080
        val height = 1080
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Fundo
        val paint = Paint()
        paint.color = Color.parseColor("#FFF0F5") // Rosa claro
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        // Texto da Frase
        val textPaint = Paint().apply {
            color = Color.parseColor("#4A4A4A")
            textSize = 60f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
            typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
        }

        val xPos = canvas.width / 2f
        var yPos = canvas.height / 2f - 100f

        // Quebra de linha simples
        val words = currentPhrase.split(" ")
        var line = ""
        for (word in words) {
            if (textPaint.measureText("$line $word") < width - 200) {
                line = if (line.isEmpty()) word else "$line $word"
            } else {
                canvas.drawText(line, xPos, yPos, textPaint)
                yPos += textPaint.descent() - textPaint.ascent()
                line = word
            }
        }
        canvas.drawText(line, xPos, yPos, textPaint)

        // Assinatura
        val signPaint = Paint().apply {
            color = Color.parseColor("#E91E63")
            textSize = 50f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL)
        }
        canvas.drawText("Com amor, Matheus ❤️", xPos, yPos + 150f, signPaint)

        return bitmap
    }

    private fun saveBitmapToCache(bitmap: Bitmap): Uri? {
        val cachePath = File(externalCacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "frase_amor.png")
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()

        return FileProvider.getUriForFile(this, "$packageName.fileprovider", file)
    }
}