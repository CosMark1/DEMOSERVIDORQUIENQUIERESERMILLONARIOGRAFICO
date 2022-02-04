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
    lateinit var pregunta:Preguntas

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       quieroSerMillonario()



    }

        fun quieroSerMillonario() {
            reiniciar()
            val client = OkHttpClient()

            val id = 1
            val respuesta = ""
            val request = Request.Builder()
            request.url("http://10.0.2.2:8081/Pregunta")


            val call = client.newCall(request.build())
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e.toString())
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT)
                            .show()
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

        }

    fun reiniciar() {
        binding.r1.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.purple_700
            )
        )
        binding.r1.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.white
            )
        )
        binding.r2.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.purple_700
            )
        )
        binding.r2.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.white
            )
        )
        binding.r3.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.purple_700
            )
        )
        binding.r3.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.white
            )
        )
        binding.r4.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.purple_700
            )
        )
        binding.r4.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.white
            )
        )
    }
    fun comprobar( respuesta :String, ){

        reiniciar()
        val client = OkHttpClient()

        val id = 1
        val respuesta = ""
        val request = Request.Builder()
        request.url("http://10.0.2.2:8081/Pregunta${question.id}/${respuesta}")


        val call = client.newCall(request.build())
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT)
                        .show()
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

    }
    }
 /*binding.r1.setOnClickListener {
                            reiniciar()
                            binding.comprobar.visibility = View.VISIBLE
                            binding.comprobar.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            request.url("http://10.0.2.2:8081/Pregunta${question.id}/${question.respuesta1}")
                            binding.r1.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            binding.r1.setTextColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.black
                                )
                            )
                        }
                        binding.r2.setOnClickListener {
                            reiniciar()
                            binding.comprobar.visibility = View.VISIBLE
                            binding.comprobar.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            request.url("http://10.0.2.2:8081/Pregunta${question.id}/${answer.respuesta}")
                            binding.r2.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            binding.r2.setTextColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.black
                                )
                            )
                        }
                        binding.r3.setOnClickListener {
                            reiniciar()
                            binding.comprobar.visibility = View.VISIBLE
                            binding.comprobar.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            request.url("http://10.0.2.2:8081/Pregunta${question.id}/${answer.respuesta}")
                            binding.r3.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            binding.r3.setTextColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.black
                                )
                            )
                        }
                        binding.r4.setOnClickListener {
                            reiniciar()
                            binding.comprobar.visibility = View.VISIBLE
                            binding.comprobar.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            request.url("http://10.0.2.2:8081/Pregunta${question.id}/${answer.respuesta}")
                            binding.r4.setBackgroundColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.teal_700
                                )
                            )
                            binding.r4.setTextColor(
                                ContextCompat.getColor(
                                    binding.root.context,
                                    R.color.black
                                )
                            )
                        }
                        binding.comprobar.setOnClickListener {

                            quieroSerMillonario()
                        }*/


                        /*



  */