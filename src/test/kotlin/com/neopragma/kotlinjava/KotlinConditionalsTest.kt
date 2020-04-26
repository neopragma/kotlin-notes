package com.neopragma.kotlinjava

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

// This import avoids having to write Color.BLUE, etc.
import com.neopragma.kotlinjava.Color.*

class KotlinConditionalsTest : StringSpec() {

    init {
        "'if' as an expression in an assignment statement" {
            higherOf(15, 36) shouldBe 36
        }

        "'when' as a case statement" {
            table(
                    headers("input", "expected"),
                    row(BLUE, "cold"),
                    row(ORANGE, "mild"),
                    row(RED, "hot"),
                    row(INVISIBLE, "indeterminate")
            ).forAll { input, expected ->
                colorToTemperature(input) shouldBe expected
            }
        }

        "processing multiple options with 'when'" {
            val affirmative = "I'm glad you agree"
            val negative = "Sorry to hear that"
            val undetermined = "What do you mean?"
            table(
                    headers("input", "expected"),
                    row("y", affirmative),
                    row("yes", affirmative),
                    row("Y", affirmative),
                    row("Yes", affirmative),
                    row("n", negative),
                    row("no", negative),
                    row("N", negative),
                    row("No", negative),
                    row( "splat", undetermined)
            ).forAll { input, expected ->
                yesOrNoResponse(input) shouldBe expected
            }
        }

        "using 'when' in conjunction with 'in setOf'" {
            val affirmative = "I'm glad you agree"
            val negative = "Sorry to hear that"
            val undetermined = "What do you mean?"
            table(
                    headers("input", "expected"),
                    row("y", affirmative),
                    row("yes", affirmative),
                    row("Y", affirmative),
                    row("Yes", affirmative),
                    row("n", negative),
                    row("no", negative),
                    row("N", negative),
                    row("No", negative),
                    row( "splat", undetermined)
            ).forAll { input, expected ->
                yesOrNoResponse2(input) shouldBe expected
            }
        }

        "another example of 'when' with 'setOf'" {
            table(
                    headers("base color 1", "base color 2", "expected blended color"),
                    row(RED, YELLOW, ORANGE),
                    row(YELLOW, BLUE, GREEN),
                    row(BLUE, VIOLET, INDIGO),
                    row(INVISIBLE, BLUE, INVALID_BLEND)
            ).forAll { baseColor1, baseColor2, expectedBlendedColor ->
                blendColors(baseColor1, baseColor2) shouldBe expectedBlendedColor
            }
        }

        "using 'when' in conjunction with 'Pair'" {
            table(
                    headers("base colors", "expected blended color"),
                    row(Pair(RED, YELLOW), ORANGE),
                    row(Pair(YELLOW, BLUE), GREEN),
                    row(Pair(BLUE, VIOLET), INDIGO),
                    row(Pair(INVISIBLE, BLUE), INVALID_BLEND)
            ).forAll { baseColors, expectedBlendedColor ->
                blendColorPair(baseColors) shouldBe expectedBlendedColor
            }
        }

        "using 'when' with a local variable" {
            table(
                    headers("animalName", "animalSound"),
                    row("cow", "The cow says 'moo'"),
                    row("sheep", "The sheep says 'baa'"),
                    row("dog", "The dog says 'Stewie, what are you doing?'")
            ).forAll { animalName, animalSound ->
                val firstApostrophe = animalSound.indexOf("'", 0)
                val lastApostrophe = animalSound.indexOf("'", firstApostrophe+1)
                val theSound = animalSound.substring(firstApostrophe, lastApostrophe+1)
                whatTheAnimalSays(animalName.toUpperCase()) shouldBe "The $animalName says $theSound"
            }
        }

        "using 'when' in conjunction with infix function 'to'" {
            table(
                    headers("temperatureCelsius", "color", "expectedHeatLevel"),
                    row(4, BLUE, "cold"),
                    row(22, ORANGE, "mild"),
                    row(24, RED, "hot")
            ).forAll { temperatureCelsius, color, expectedHeatLevel ->
                deriveHeatLevelAndColorFromTemperature(temperatureCelsius) shouldBe Pair(color, expectedHeatLevel)
            }
        }
    }
}
