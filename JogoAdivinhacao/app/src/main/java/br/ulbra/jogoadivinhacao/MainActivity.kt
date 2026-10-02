package br.ulbra.jogoadivinhacao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.ulbra.jogoadivinhacao.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnJogar = findViewById<Button>(R.id.btnJogar)

        btnJogar.setOnClickListener {

            val nome = edtNome.text.toString()

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite seu nome!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idSelecionado = radioGroup.checkedRadioButtonId

            if (idSelecionado == -1) {
                Toast.makeText(this, "Escolha um limite!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val radioSelecionado =
                findViewById<RadioButton>(idSelecionado)

            val maximo = radioSelecionado.text.toString().toInt()

            val intent = Intent(this, JogoActivity::class.java)

            intent.putExtra("nome", nome)
            intent.putExtra("maximo", maximo)

            startActivity(intent)
        }
    }
}