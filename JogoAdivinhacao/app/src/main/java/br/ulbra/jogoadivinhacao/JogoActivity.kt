package br.ulbra.jogoadivinhacao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.ulbra.jogoadivinhacao.R
import kotlin.math.abs
import kotlin.random.Random

class JogoActivity : AppCompatActivity() {

    private var numeroSecreto = 0
    private var tentativas = 0
    private var nome = ""
    private var maximo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        nome = intent.getStringExtra("nome") ?: ""
        maximo = intent.getIntExtra("maximo", 10)

        numeroSecreto = Random.nextInt(1, maximo + 1)

        val txtMensagem = findViewById<TextView>(R.id.txtMensagem)

        txtMensagem.text =
            "$nome, pensei em um número de 1 a $maximo!"

        val edtPalpite = findViewById<EditText>(R.id.edtPalpite)
        val btnChutar = findViewById<Button>(R.id.btnChutar)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnJogarNovamente = findViewById<Button>(R.id.btnJogarNovamente)

        btnChutar.setOnClickListener {

            val textoPalpite = edtPalpite.text.toString()

            if (textoPalpite.isEmpty()) {
                Toast.makeText(
                    this,
                    "Digite um palpite!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val palpite = textoPalpite.toInt()
            tentativas++

            if (palpite == numeroSecreto) {

                txtResultado.text =
                    "$nome acertou em $tentativas tentativas!"

                btnChutar.isEnabled = false
                btnJogarNovamente.visibility = Button.VISIBLE

            } else {

                val diferenca = abs(numeroSecreto - palpite)
                val limiteQuente = maximo * 0.10

                val temperatura =
                    if (diferenca <= limiteQuente) {
                        "Quente!"
                    } else {
                        "Frio!"
                    }

                val direcao =
                    if (numeroSecreto > palpite) {
                        "O número é MAIOR."
                    } else {
                        "O número é MENOR."
                    }

                txtResultado.text =
                    "$temperatura $direcao\nTentativas: $tentativas"
            }

            edtPalpite.text.clear()
        }

        btnJogarNovamente.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}