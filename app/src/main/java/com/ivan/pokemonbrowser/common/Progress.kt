package com.ivan.pokemonbrowser.common

sealed class Progress {
    object Loading : Progress()
    object Finished : Progress()
}