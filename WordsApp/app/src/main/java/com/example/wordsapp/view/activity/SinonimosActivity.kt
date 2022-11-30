package com.example.wordsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wordsapp.R
import com.example.wordsapp.data.retrofit.PalavraRepositorio
import com.example.wordsapp.view.recyclerview.SinonimosAdapter
import kotlinx.coroutines.launch

class SinonimosActivity : AppCompatActivity() {

    private val repositorio = PalavraRepositorio()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinonimos)
        pegaExtras()
    }

    private fun pegaExtras() {
        intent.extras?.getString("palavra")?.let { palavra ->
            buscaSinonimos(palavra)
        } ?: finish()
    }

    private fun buscaSinonimos(palavra: String) {
        lifecycleScope.launch {
            repositorio.getSinonimos(palavra)?.let { lista ->
                configuraLista(lista)
            } ?: finish()
        }
    }

    private fun configuraLista(lista: List<String>) {
        val sinonimosAdapter = SinonimosAdapter(this, lista)
        findViewById<RecyclerView>(R.id.recyclerview_sinonimos).apply {
            adapter = sinonimosAdapter
            layoutManager = StaggeredGridLayoutManager(2, 0)
        }
    }
}