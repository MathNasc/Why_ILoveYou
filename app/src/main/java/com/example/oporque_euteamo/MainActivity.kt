package com.example.oporque_euteamo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

/**
 * MainActivity - Tela principal do aplicativo "O Porquê Eu Te Amo"
 *
 * Este aplicativo exibe frases românticas aleatórias toda vez que é aberto,
 * permitindo ao usuário compartilhar a frase atual através do botão de compartilhamento.
 *
 * Funcionalidades:
 * - Exibe uma frase de amor aleatória a cada abertura do app
 * - Permite compartilhar a frase atual via redes sociais ou mensagem
 * - Interface minimalista e romântica
 */
class MainActivity : AppCompatActivity() {

    private lateinit var lovePhraseText: TextView
    private lateinit var shareButton: FloatingActionButton
    private lateinit var refreshButton: FloatingActionButton
    private lateinit var lovePhrases: Array<String>
    private var currentPhrase: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar componentes da interface
        initializeViews()

        // Carregar as frases de amor do arquivo de recursos
        loadLovePhrases()

        // Exibir uma frase aleatória
        displayRandomPhrase()

        // Configurar o botão de compartilhamento
        setupShareButton()

        // Configurar o botão de atualização
        setupRefreshButton()
    }

    /**
     * Inicializa as views da interface do usuário
     */
    private fun initializeViews() {
        lovePhraseText = findViewById(R.id.love_phrase_text)
        shareButton = findViewById(R.id.share_button)
        refreshButton = findViewById(R.id.refresh_button)
    }

    /**
     * Carrega as frases de amor do arquivo strings.xml
     */
    private fun loadLovePhrases() {
        lovePhrases = resources.getStringArray(R.array.love_phrases)
    }

    /**
     * Exibe uma frase de amor aleatória na tela
     */
    private fun displayRandomPhrase() {
        if (lovePhrases.isNotEmpty()) {
            // Gerar um índice aleatório
            val randomIndex = Random.nextInt(lovePhrases.size)

            // Obter a frase correspondente ao índice
            currentPhrase = lovePhrases[randomIndex]

            // Exibir a frase na TextView
            lovePhraseText.text = "$currentPhrase\n\nCom amor, Matheus ❤️"
        }
    }

    /**
     * Configura o comportamento do botão de compartilhamento
     */
    private fun setupShareButton() {
        shareButton.setOnClickListener {
            shareCurrentPhrase()
        }
    }    /**
     * Compartilha a frase atual através de um Intent de compartilhamento
     */
    private fun shareCurrentPhrase() {
        // Gerar a imagem com a frase
        val imageUri = generateImageForSharing(currentPhrase)

        if (imageUri != null) {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, imageUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                putExtra(Intent.EXTRA_SUBJECT, "Uma mensagem de amor para você ❤️")
            }

            val chooser = Intent.createChooser(shareIntent, "Compartilhar frase de amor")
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        }
    }

    /**
     * Configura o comportamento do botão de atualização
     */
    private fun setupRefreshButton() {
        refreshButton.setOnClickListener {
            displayRandomPhrase()
        }
    }

    /**
     * Método chamado quando a activity é retomada
     * Exibe uma nova frase aleatória quando o usuário retorna ao app
     */
    override fun onResume() {
        super.onResume()
        // Opcional: exibir uma nova frase quando o app é retomado
        // displayRandomPhrase()
    }

    /**
     * Gera uma imagem com a frase de amor e a assinatura para compartilhamento.
     * @param phrase A frase de amor a ser desenhada na imagem.
     * @return Uri da imagem gerada, ou null se houver um erro.
     */
    private fun generateImageForSharing(phrase: String): Uri? {
        val textToDraw = "$phrase\n\nCom amor, Matheus ❤️"
        val bitmapWidth = 800
        val bitmapHeight = 600

        val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Desenhar o fundo gradiente (simulando o background do app)
        val gradientStartColor = resources.getColor(R.color.background_start, theme)
        val gradientEndColor = resources.getColor(R.color.background_end, theme)
        val paintBackground = Paint()
        paintBackground.shader = android.graphics.LinearGradient(
            0f, 0f, 0f, bitmapHeight.toFloat(),
            gradientStartColor, gradientEndColor,
            android.graphics.Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, bitmapWidth.toFloat(), bitmapHeight.toFloat(), paintBackground)

        // Configurar o texto
        val textPaint = Paint().apply {
            color = resources.getColor(R.color.text_primary, theme)
            textSize = 40f
            textAlign = Paint.Align.CENTER
            // Carregar a fonte Dancing Script
            typeface = resources.getFont(R.font.dancing_script)
        }

        // Quebrar o texto em linhas para centralizar e ajustar
        val textLines = mutableListOf<String>()
        val words = textToDraw.split(" ")
        var currentLine = ""
        for (word in words) {
            if (textPaint.measureText(currentLine + " " + word) < bitmapWidth - 100) {
                currentLine += (if (currentLine.isEmpty()) "" else " ") + word
            } else {
                textLines.add(currentLine)
                currentLine = word
            }
        }
        textLines.add(currentLine)

        // Desenhar cada linha de texto
        var yPos = (bitmapHeight / 2) - ((textLines.size - 1) * textPaint.descent() / 2)
        for (line in textLines) {
            canvas.drawText(line, bitmapWidth / 2f, yPos, textPaint)
            yPos += textPaint.descent() - textPaint.ascent() + 10 // Adiciona espaçamento entre linhas
        }

        // Salvar o bitmap em um arquivo temporário
        val imagesFolder = File(cacheDir, "images")
        imagesFolder.mkdirs()
        val file = File(imagesFolder, "shared_phrase.png")

        return try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
            FileProvider.getUriForFile(this, "com.example.oporque_euteamo.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}







