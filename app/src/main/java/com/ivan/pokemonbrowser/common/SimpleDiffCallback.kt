package com.ivan.pokemonbrowser.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class SimpleDiffCallback<T, R>(private val getter: (T) -> R) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return getter(oldItem) == getter(newItem)
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}