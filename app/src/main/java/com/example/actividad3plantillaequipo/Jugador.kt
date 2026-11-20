package com.example.actividad3plantillaequipo

import androidx.annotation.DrawableRes

data class Jugador(
    val id: Int,
    val nombre: String,
    val nickname: String,
    val rol: String,
    @DrawableRes val imagen: Int = R.drawable.m01
)
