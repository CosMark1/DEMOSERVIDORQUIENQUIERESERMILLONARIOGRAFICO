package com.example.swapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapi.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val client = OkHttpClient()

        val id = 1
        val respuestas = ""
        val request = Request.Builder()
        request.url("http://10.0.2.2:8081/Pregunta")


        val call = client.newCall(request.build())
        call.enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)
                    val gson = Gson()

                    val question = gson.fromJson(body, Preguntas::class.java)
                    val answer = gson.fromJson(body, Respuestas::class.java)

                    CoroutineScope(Dispatchers.Main).launch {
                        binding.pregunta.text = question.pregunta
                        binding.r1.text = question.respuesta1
                        binding.r2.text = question.respuesta2
                        binding.r3.text = question.respuesta3
                        binding.r4.text = question.respuesta4

                    }
                }
            }
        })
        fun reiniciar(){
            binding.comprobar.visibility = View.VISIBLE
            binding.r1.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.purple_700))
            binding.r1.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))
            binding.r2.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.purple_700))
            binding.r2.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))
            binding.r3.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.purple_700))
            binding.r3.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))
            binding.r4.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.purple_700))
            binding.r4.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))
        }
        binding.r1.setOnClickListener{
            reiniciar()
            binding.r1.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.teal_700))
            binding.r1.setTextColor(ContextCompat.getColor(binding.root.context,R.color.black))
        }
        binding.r1.setOnClickListener{
            reiniciar()
            binding.r2.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.teal_700))
            binding.r2.setTextColor(ContextCompat.getColor(binding.root.context,R.color.black))
        }
        binding.r1.setOnClickListener{
            reiniciar()
            binding.r3.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.teal_700))
            binding.r3.setTextColor(ContextCompat.getColor(binding.root.context,R.color.black))
        }
        binding.r1.setOnClickListener{
            reiniciar()
            binding.r4.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.teal_700))
            binding.r4.setTextColor(ContextCompat.getColor(binding.root.context,R.color.black))
        }

    }
}