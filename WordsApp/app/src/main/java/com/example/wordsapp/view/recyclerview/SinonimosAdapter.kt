package com.example.wordsapp.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.wordsapp.R

class SinonimosAdapter(
    private val context: Context,
    palavras: List<String> = emptyList()
) :
    Adapter<SinonimosAdapter.SinonimosAntonimosViewHolder>() {

    private val listaPalavras = palavras.toMutableList()

    inner class SinonimosAntonimosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun vincula(palavra: String) {
            val textView = itemView.findViewById<AppCompatTextView>(R.id.textview_sinonimos_antonimos_item)
            textView.text = palavra
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SinonimosAntonimosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sinonimos, parent, false)
        return SinonimosAntonimosViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinonimosAntonimosViewHolder, position: Int) {
       holder.vincula(listaPalavras[position])
    }

    override fun getItemCount(): Int = listaPalavras.size

}