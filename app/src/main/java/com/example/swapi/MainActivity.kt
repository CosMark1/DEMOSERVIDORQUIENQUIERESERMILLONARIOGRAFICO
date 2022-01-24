package com.example.swapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    lateinit var adapter: TextoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listaPlanetas = mutableListOf<Planet>()


        val client = OkHttpClient()

        val request = Request.Builder()
        request.url("https://swapi.dev/api/planets/")


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

                    val planet = gson.fromJson(body, PlanetResponse::class.java)

                    CoroutineScope(Dispatchers.Main).launch {
                        planet.results.forEach {
                            listaPlanetas.add(it)
                        }
                        adapter = TextoAdapter(listaPlanetas)
                        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                        binding.recyclerview.adapter = adapter
                    }
                }
            }
        })
    }
}