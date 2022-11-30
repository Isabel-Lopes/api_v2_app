package com.example.wordsapp.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.R
import com.example.wordsapp.model.Palavra

class InformacoesPalavraAdapter(
    private val context: Context,
    informacoes: List<Palavra> = emptyList()
): RecyclerView.Adapter<InformacoesPalavraAdapter.InformacoesViewHolder>() {

    private val listaInformacoes = informacoes.toMutableList()

    inner class InformacoesViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        fun vincula(palavra: Palavra){
            itemView.apply {
                findViewById<AppCompatTextView>(R.id.textview_palavra_classe_gramatical).text = palavra.partOfSpeech
                configuraSignificados(palavra.meanings)
                findViewById<AppCompatTextView>(R.id.textview_palavra_etimologia).text = palavra.etymology
            }
        }

        private fun configuraSignificados(significados: List<String>) {
            val significadosAdapter = SignificadosAdapter(context, significados)
            itemView.findViewById<RecyclerView>(R.id.recyclerview_significados).apply {
                adapter = significadosAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformacoesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_informacoes_palavra, parent, false)
        return InformacoesViewHolder(view)
    }

    override fun onBindViewHolder(holder: InformacoesViewHolder, position: Int) {
        holder.vincula(listaInformacoes[position])
    }

    override fun getItemCount(): Int = listaInformacoes.size
}