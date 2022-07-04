package com.ivan.pokemonbrowser.util

import org.junit.Assert.*
import org.junit.Test

class UtilsTest {

    @Test
    fun `value of zero hectograms returns zero point zero kgs`() {
        val hectograms = 0
        val result = Utils.hectogramToKgs(hectograms)

        assertEquals(0.0F, result)
    }

    @Test
    fun `value of one hectogram returns zero point one kgs`() {
        val hectograms = 1
        val result = Utils.hectogramToKgs(hectograms)

        assertEquals(0.1F, result)
    }

    @Test
    fun `value of ten hectogram returns one point zero kgs`() {
        val hectograms = 10
        val result = Utils.hectogramToKgs(hectograms)

        assertEquals(1.0F, result)
    }

    @Test
    fun `value of zero decimeters returns zero centimeters`() {
        val decimeters = 0
        val result = Utils.decimeterToCentimeters(decimeters)

        assertEquals(0, result)
    }

    @Test
    fun `value of one decimeter returns ten centimeters`() {
        val decimeters = 1
        val result = Utils.decimeterToCentimeters(decimeters)

        assertEquals(10, result)
    }

    @Test
    fun `url in valid format returns integer id`() {
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val result = Utils.idFromUrl(url)

        assertEquals(1, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `url in invalid format throws IllegalArgumentException`() {
        val url = "https://pokeapi.co/api/v2/pokemon/"
        Utils.idFromUrl(url)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `empty url throws IllegalArgumentException`() {
        val url = ""
        Utils.idFromUrl(url)
    }
}