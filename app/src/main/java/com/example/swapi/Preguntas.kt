package com.example.swapi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Preguntas (
    var Npregunta :String,
    var pregunta :String,
    var respuestas:String
) : Parcelable

@Parcelize
data class Respuestas (
    var respuesta :String
    ): Parcelable
