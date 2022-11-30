package com.example.wordsapp.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.R

class SignificadosAdapter(
    private val context: Context,
    private val lista: List<String> = emptyList()
) : RecyclerView.Adapter<SignificadosAdapter.PalavrasViewHolder>() {

    private val listaSignificados = lista.toMutableList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PalavrasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_significado, parent, false)
        return PalavrasViewHolder(view)
    }

    override fun onBindViewHolder(holder: PalavrasViewHolder, position: Int) {
        holder.vincula(listaSignificados[position])
    }

    override fun getItemCount(): Int = listaSignificados.size

    fun atualiza(palavras: List<String>) {
        listaSignificados.clear()
        listaSignificados.addAll(palavras)
        notifyDataSetChanged()
    }


    inner class PalavrasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun vincula(significado: String) {
            val palavraTextView: AppCompatTextView =
                itemView.findViewById(R.id.textview_palavra_item)
            palavraTextView.text = significado
        }
    }

}