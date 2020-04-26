package com.neopragma.kotlinjava

import com.neopragma.kotlinjava.Color.*
import com.neopragma.kotlinjava.Animal.*

enum class Color {
    BLUE, ORANGE, RED, YELLOW, GREEN, VIOLET, INDIGO, INVISIBLE, INVALID_BLEND
}

enum class Animal {
    COW, SHEEP, DOG
}

fun higherOf(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun colorToTemperature(color: Color): String {
    return when (color) {
        Color.BLUE -> "cold"
        Color.ORANGE -> "mild"
        Color.RED -> "hot"
        else -> "indeterminate"
    }
}

fun yesOrNoResponse(input: String): String {
    return when (input.toLowerCase()) {
        "y", "yes" -> "I'm glad you agree"
        "n", "no" -> "Sorry to hear that"
        else -> "What do you mean?"
    }
}

fun yesOrNoResponse2(input: String): String {
    val lowerCaseInput = input.toLowerCase()
    return when (true) {
        lowerCaseInput in setOf("y", "yes") -> "I'm glad you agree"
        lowerCaseInput in setOf("n", "no") -> "Sorry to hear that"
        else -> "What do you mean?"
    }
}

fun blendColors(baseColor1: Color, baseColor2: Color): Color {
    return when (setOf(baseColor1, baseColor2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> INVALID_BLEND
    }
}

fun blendColorPair(baseColors: Pair<Color, Color>): Color {
    return when (baseColors) {
        Pair(RED, YELLOW) -> ORANGE
        Pair(YELLOW, BLUE) -> GREEN
        Pair(BLUE, VIOLET) -> INDIGO
        else -> INVALID_BLEND
    }
}

fun whatTheAnimalSays(animalName: String): String {
    return when (val animalType = Animal.valueOf(animalName)) {
        COW -> "The cow says 'moo'"
        SHEEP -> "The sheep says 'baa'"
        DOG -> "The dog says 'Stewie, what are you doing?'"
        else -> "The $animalType don't say nuttin'"
    }
}

fun deriveHeatLevelAndColorFromTemperature(temperatureCelsius: Int): Pair<Color, String> {
    val(color, heatLevel) = when {
        temperatureCelsius < 5 -> BLUE to "cold"
        temperatureCelsius < 23 -> ORANGE to "mild"
        else -> RED to "hot"
    }
    return Pair(color, heatLevel)
}