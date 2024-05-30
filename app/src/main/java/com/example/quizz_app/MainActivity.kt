package com.example.quizz_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizz_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    private val questions = arrayOf(
        "Nas séries de kamen rider, quem é conhecido como 'o sol' ou 'herdeiro/escolhido do sol'?",
        "Qual das temporadas de digimon cartas são utilizadas para evoluir?",
        "Em nier automata, quantos Engels aparecem após a derrota do primeiro",
        "Em kamen rider kuuga qual gesto de mão é feito pelo Godai?",
        "Qual dos 7 pecados capitais é representado pela lilithmon?",
        "Qual das cores abaixo é considerada a mais rara ?",
        "Qual dos personagens possui a alcunha de 'Cavaleiro do Abismo'?",
        "O que é a 'black box' em nier automata?",
        "Qual nome aparece em todos os jogos da série Final Fantasy",
        "Qual desses pokemons não é usado pela Cynthia",
    )

    private val options = arrayOf(
        arrayOf("Tendou Souji", "Inui Takumi", "Shinji Kido"),
        arrayOf("Adventure", "Frontier", "Tamers"),
        arrayOf("4", "2", "3"),
        arrayOf("Paz e amor", "Joinha", "Ele não faz nenhum"),
        arrayOf("Luxúria", "Avareza", "Preguiça"),
        arrayOf("Verde", "Azul", "Magenta") ,
        arrayOf("Gael", "Manus", "Artorias") ,
        arrayOf("Dispositivo de autodestruição", "Uma caixa", "Código de ativação"),
        arrayOf("Biggs", "Cid", "Wedge") ,
        arrayOf("Torterra", "Garchomp", "Lucario")
        )

    private val correctAnswers = arrayOf(0, 2, 2, 1, 0, 1, 2, 0, 1,0)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }

        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }

        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }

    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }


    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        if(score > 5)
            Toast.makeText(this, "Uau, você é espetacular. Me curvo a sua sabedoria", Toast.LENGTH_LONG).show()
        else if(score < 5)
            Toast.makeText(this, "Poxa você não foi muito bem. Mais sorte na próxima tentativa", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "Muito bem, você está no caminho certo, vamos melhorar", Toast.LENGTH_LONG).show()
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if(selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestionIndex < questions.size -1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        }else{
            showResults()
        }
    }
}