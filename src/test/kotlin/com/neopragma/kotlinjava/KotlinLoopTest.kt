package com.neopragma.kotlinjava

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinLoopTest : StringSpec() {
    init {

        "it processes a for loop" {
            var result: List<String> = listOf()
            val input = listOf("alpha", "beta", "delta", "gamma", "epsilon")
            for (thing in input) {
                result += thing
            }
            result shouldBe input
        }

        "it processes a do while loop" {
            var counter = 0
            do {
                counter++
            } while (counter < 6)
            counter shouldBe 6
        }

        "it processes a while loop" {
            var counter = 0
            while (counter < 6) {
                counter++
            }
            counter shouldBe 6
        }
    }
}

