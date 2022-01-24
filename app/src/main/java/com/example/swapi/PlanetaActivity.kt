package com.example.swapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.swapi.databinding.PlanetaActivityBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class PlanetaActivity : AppCompatActivity() {

    private lateinit var binding : PlanetaActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlanetaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var planeta = intent.getIntExtra("PLANETA",0)
        planeta += 1

        binding.bDescargaUno.setOnClickListener {

            val client = OkHttpClient()

            val request = Request.Builder()
            request.url("https://swapi.dev/api/planets/$planeta")


            val call = client.newCall(request.build())
            call.enqueue( object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e.toString())
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(this@PlanetaActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onResponse(call: Call, response: Response) {
                    println(response.toString())
                    response.body?.let { responseBody ->
                        val body = responseBody.string()
                        println(body)
                        val gson = Gson()

                        val planet = gson.fromJson(body, Planet::class.java)

                        println(planet)

                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(this@PlanetaActivity, "$planet", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}