package com.example.wordsapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class Inicializador {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://significado.herokuapp.com/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val palavraService = retrofit.create(WordService::class.java)
}