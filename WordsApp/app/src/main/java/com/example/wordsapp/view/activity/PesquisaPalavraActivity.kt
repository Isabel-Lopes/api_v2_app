package com.example.wordsapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.R
import com.example.wordsapp.data.retrofit.PalavraRepositorio
import com.example.wordsapp.model.Palavra
import com.example.wordsapp.view.recyclerview.InformacoesPalavraAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.launch

class PesquisaPalavraActivity : AppCompatActivity() {

    private val repositorio = PalavraRepositorio()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_palavra)
        configuraSearchView()
    }


    private fun configuraSearchView() {
        val searchView = findViewById<SearchView>(R.id.searchview_lista_de_palavras)
        searchView.setOnQueryTextListener(object : OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                procuraPalavra(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun procuraPalavra(query: String?) {
        val carregamento = findViewById<ProgressBar>(R.id.progressbar_pesquisa_palavra)
        lifecycleScope.launch {
            query?.let {
                carregamento.visibility = View.VISIBLE
                repositorio.getInformacoes(query)?.let { significados ->
                    preenche(query, significados)
                    carregamento.visibility = View.INVISIBLE
                    configuraBotaoSinonimos(query)
                } ?: run {
                    carregamento.visibility = View.INVISIBLE
                    mostraErro()
                }
            }
        }
    }

    private fun mostraErro() {
        findViewById<ConstraintLayout>(R.id.view_lista_de_informacoes).visibility =
            View.GONE
        findViewById<AppCompatTextView>(R.id.textview_palavra_nao_encontrada).visibility =
            View.VISIBLE
    }

    private fun preenche(
        query: String?,
        significados: List<Palavra>
    ) {
        findViewById<AppCompatTextView>(R.id.textview_palavra_nao_encontrada).visibility =
            View.GONE
        findViewById<AppCompatTextView>(R.id.textview_palavra_nome).text = query
        configuraSignificado(significados)
    }

    private fun configuraBotaoSinonimos(palavra: String) {
        findViewById<MaterialButton>(R.id.button_sinonimos).setOnClickListener {
            Intent(this@PesquisaPalavraActivity, SinonimosActivity::class.java).apply {
                putExtra("palavra", palavra)
                startActivity(this)
            }
        }
    }

    private fun configuraSignificado(significados: List<Palavra>) {
        val significadosAdapter = InformacoesPalavraAdapter(this, significados)
        findViewById<RecyclerView>(R.id.recyclerview_informacoes_palavra).apply {
            adapter = significadosAdapter
            layoutManager = LinearLayoutManager(this@PesquisaPalavraActivity)
        }
        findViewById<ConstraintLayout>(R.id.view_lista_de_informacoes).visibility =
            View.VISIBLE
    }

}