package com.neopragma.kotlinjava

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinNullsTest : StringSpec() {
    init {
        // Kotlin variables are null-safe by default
        "null safe String, value is not null" {
            val myNullSafeString: String = "alpha"
            myNullSafeString shouldBe "alpha"
            myNullSafeString.length shouldBe 5
        }

        // You can't assign null to a null-safe variable
        "null safe String, value is null" {
            // compiler error
            // var myNullSafeString: String = null
        }

        // A nullable reference might be null at run time
        "nullable String is not null" {
            val myNullableString: String? = "alpha"
            myNullableString shouldBe "alpha"
            myNullableString?.length shouldBe 5
        }

        "nullable String is null" {
            val myNullableString: String? = null
            myNullableString shouldBe null
            myNullableString?.length shouldBe null
        }

        // Elvis operator checks for null and assigns a default value
        "using the Elvis operator" {
            val result: String? = null
            val strLen: Int = if (result != null) result.length else -1
            strLen shouldBe -1
            // same as...
            val strLen2 = result?.length ?: -1
            strLen2 shouldBe -1
        }

        // The !! operator asserts the reference is not null and throws
        // NullPointerException if the reference is null at run time
        "using the not-null assertion operator, value is not null" {
            val result: String? = "alpha"
            result!!.length shouldBe 5
        }

        "using the not-null assertion operator, value is null" {
            val result: String? = null
            shouldThrow<NullPointerException> {
                result!!.length
            }
        }

        "filtering null elements from a collection" {
            val myListOfNullables: List<String?> = listOf("alpha", null, "beta", "gamma", null, "delta")
            val myListOfStrings: List<String> = myListOfNullables.filterNotNull()
            myListOfStrings shouldBe listOf("alpha", "beta", "gamma", "delta")
        }

        // Java code may return null references; Kotlin compiler can't stop it.
        // Kotlin calls Java types "platform types".
        "platform types can throw NPE" {
            // returns java.util.ArrayList containing a null entry at [1]
            val javaCode = JavaInteropSample()
            val nullThing = javaCode.listOfStrings()[1] // null is not caught at compile time
            shouldThrow<NullPointerException> {
                nullThing.length
            }
        }

        "using a safe cast, value is not null" {
            val javaCode = JavaInteropSample()
            val nullThing = javaCode.listOfStrings()[0] // entry [0] is not null
            val myNullableString: String? = nullThing as? String
            myNullableString shouldBe "alpha"
        }

        "using a safe cast, value is null" {
            val javaCode = JavaInteropSample()
            val nullThing = javaCode.listOfStrings()[1] // entry [1] is null
            val myNullableString: String? = nullThing as? String
            myNullableString shouldBe null
        }
    }
}