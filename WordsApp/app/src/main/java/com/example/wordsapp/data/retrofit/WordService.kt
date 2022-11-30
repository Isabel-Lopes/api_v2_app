package com.example.wordsapp.data.retrofit

import com.example.wordsapp.model.Palavra
import retrofit2.http.GET
import retrofit2.http.Path

interface WordService {

    @GET("{palavra}")
    suspend fun getPalavra(
        @Path("palavra") palavra: String
    ): List<Palavra>

    @GET("sinonimos/{palavra}")
    suspend fun getSinonimos(
        @Path("palavra") palavra: String
    ): List<String>
}