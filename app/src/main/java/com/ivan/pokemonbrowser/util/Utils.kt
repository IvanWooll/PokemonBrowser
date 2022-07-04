package com.ivan.pokemonbrowser.util

object Utils {

    /**
     * @return id of pokemon from a string representing a url in the expected format
     * @param url string representing a url in the form: https://pokeapi.co/api/v2/pokemon/{id}/
     * @throws IllegalArgumentException if url is not in expected format or is empty
     */
    @Throws(NumberFormatException::class)
    fun idFromUrl(url: String): Int {
        return try {
            url.removeSuffix("/")
                .substringAfterLast("/")
                .toInt()
        } catch (exception: NumberFormatException) {
            throw IllegalArgumentException("url is empty or does not conform to the expected format")
        }
    }

    /**
     * @return value in hectograms converted to a kgs expressed as a floating point number
     * @param hectogramValue the value to apply the conversion to
     */
    fun hectogramToKgs(hectogramValue: Int): Float = (hectogramValue * 0.1).toFloat()


    /**
     * @return value in decimeters converted to centimeters
     * @param decimeterValue the value to apply the conversion to
     */
    fun decimeterToCentimeters(decimeterValue: Int): Int = decimeterValue * 10
}