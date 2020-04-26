package com.neopragma.kotlinjava

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinPlatformTypesTest : StringSpec() {

    var javaCode: JavaInteropSample = JavaInteropSample()

    init {
        "platform types can throw NullPointerException" {
            val nullThing = javaCode.listOfStrings()[1] // null is not caught at compile time
            shouldThrow<NullPointerException> {
                nullThing.length
            }
        }

        "using a safe cast, value is not null" {
            val nullThing = javaCode.listOfStrings()[0] // entry [0] is not null
            val myNullableString: String? = nullThing
            myNullableString shouldBe "alpha"
        }

        "using a safe cast, value is null" {
            val nullThing = javaCode.listOfStrings()[1] // entry [1] is null
            val myNullableString: String? = nullThing
            myNullableString shouldBe null
        }
    }
}