package com.example.wordsapp.data.retrofit

import com.example.wordsapp.model.Palavra

class PalavraRepositorio {

    private val palavraService = Inicializador().palavraService

    suspend fun getInformacoes(palavra: String): List<Palavra>? {
        return try {
            val palavras = palavraService.getPalavra(palavra)

            palavras
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getSinonimos(palavra: String): List<String>? {
        return try {
            palavraService.getSinonimos(palavra)
        } catch (e: Exception) {
            null
        }
    }

}