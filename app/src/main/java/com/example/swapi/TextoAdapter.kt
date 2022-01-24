package com.example.swapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.databinding.ItemplanetaBinding

class TextoAdapter(var lista: MutableList<Planet>) :
    RecyclerView.Adapter<TextoAdapter.TextoViewHolder>() {

    class TextoViewHolder(var itemBinding: ItemplanetaBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val binding = ItemplanetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {
        holder.itemBinding.nombre.text = lista[position].name


        holder.itemBinding.cl1.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlanetaActivity::class.java)
            intent.putExtra("PLANETA", position)
            holder.itemView.context.startActivity(intent)
        }
    }
}