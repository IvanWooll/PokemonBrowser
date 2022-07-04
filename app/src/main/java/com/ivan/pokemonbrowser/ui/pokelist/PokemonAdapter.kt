package com.ivan.pokemonbrowser.ui.pokelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivan.pokemonbrowser.R
import com.ivan.pokemonbrowser.common.SimpleDiffCallback
import com.ivan.pokemonbrowser.ui.pokelist.PokemonAdapter.*
import com.ivan.pokemonbrowser.ui.pokelist.model.PokeListItem


class PokemonAdapter(private val callback: (Int) -> Unit) : ListAdapter<PokeListItem, ViewHolder>(SimpleDiffCallback(PokeListItem::id)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pokemon, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position).let { pokeListItem ->
            holder.textViewName.apply {
                text = pokeListItem.name
                setOnClickListener { callback.invoke(pokeListItem.id) }
            }
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
    }
}