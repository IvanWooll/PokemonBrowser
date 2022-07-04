package com.ivan.pokemonbrowser.ui.pokedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivan.pokemonbrowser.R
import com.ivan.pokemonbrowser.common.SimpleDiffCallback

class TypesAdapter : ListAdapter<String, TypesAdapter.ViewHolder>(SimpleDiffCallback(String::toString)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_type, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply {
            holder.textViewType.text = this
        }
    }
}