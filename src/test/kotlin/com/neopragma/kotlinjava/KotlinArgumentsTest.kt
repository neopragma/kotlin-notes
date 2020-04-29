package com.neopragma.kotlinjava

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinArgumentsTest : StringSpec() {

    var javaCode: JavaMethodsCalledFromKotlin = JavaMethodsCalledFromKotlin()

    init {
        "call Java method with regular arguments" {
            val returnedValue = javaCode.callMeWithRegularArguments("alpha", "beta", "gamma")
            returnedValue shouldBe arrayOf("alpha", "beta", "gamma")
        }

        "call Java method and pass a null reference" {
            val returnedValue = javaCode.callMeWithRegularArguments("alpha", null, "gamma")
            returnedValue shouldBe arrayOf("alpha", null, "gamma")
        }

        "call Java method that has varargs" {
            val varargs: Array<String> = arrayOf("beta", "gamma")
            val returnedValue = javaCode.callMeWithVarargs("alpha", *varargs)
            returnedValue shouldBe(arrayOf("alpha", "beta", "gamma"))
        }

        "call Kotlin function with regular arguments" {
            val returnedValue = callMeWithRegularArguments("alpha", "beta", "gamma")
            returnedValue shouldBe(arrayOf("alpha", "beta", "gamma"))
        }

        "call Kotlin function with named arguments" {
            val returnedValue = callMeWithNamedArguments(first = "alpha", second = "beta", third = "gamma")
            returnedValue shouldBe(arrayOf("alpha", "beta", "gamma"))
        }

        "call Kotlin function with named arguments in different order" {
            val returnedValue = callMeWithNamedArguments(third = "gamma", second = "beta", first = "alpha")
            returnedValue shouldBe(arrayOf("alpha", "beta", "gamma"))
        }

        "call Kotlin function omitting some named arguments" {
            val returnedValue = callMeWithNamedArguments(second = "beta")
            returnedValue shouldBe(arrayOf("defaultAlpha", "beta", "defaultGamma"))
        }

    }
}
