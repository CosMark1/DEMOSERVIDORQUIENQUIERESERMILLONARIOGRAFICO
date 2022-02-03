package com.example.swapi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Preguntas (
    var Npregunta :String,
    var pregunta :String,
    var respuesta1:String,
    var respuesta2:String,
    var respuesta3:String,
    var respuesta4:String
) : Parcelable

@Parcelize
data class Respuestas (
    var respuesta :String
): Parcelable
