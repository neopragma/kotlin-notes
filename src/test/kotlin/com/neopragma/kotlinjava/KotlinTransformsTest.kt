package com.neopragma.kotlinjava

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinTransformsTest : StringSpec() {

    init {
        "transform list using Sequence" {
            var count = 1
            val fizzBuzz = generateSequence{ (count++).takeIf {it < 101} }
                    .map{ if (it % 15 == 0) "FizzBuzz" else if (it % 5 == 0) "Buzz" else if (it % 3 == 0) "Fizz" else it.toString() }
            fizzBuzz.forEachIndexed { index, element ->
                val number = index + 1
                if (number % 15 == 0) element shouldBe "FizzBuzz"
                else if (number % 5 == 0) element shouldBe "Buzz"
                else if (number % 3 == 0) element shouldBe "Fizz"
                else element shouldBe (number).toString()
            }
        }

        // This approach is based on work of Ken Kousen
//        "generate Fibonacci series using tail recursion" {
//            @JvmOverloads
//            var count = 1
//            var n = 0;
//            var a = 0;
//            var b = 0
//            val fibonacci = generateSequence { (count++).takeIf { it < 10 } }
//                    .map {
//                        when (it) {
//                            0 -> a
//                            1 -> b
//                            else -> fibonacci(n - 1, b, a + b)
//                        }
//                    }
//        fibonacci shouldBe sequenceOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)

//          println("Fibonacci using tail recursion")
//          fibonacci.iterator().forEach {
//              println(it)
//
//          }
//        }

        // This approach is based on work of Marcin Moskala @marcinmoskala
        "generate Fibonacci series using fold" {
            var fibonacci = listOf<Int>(0, 1)
            for (n in 2..10) {
                fibonacci +=
                    (2 until n).fold(1 to 1) {
                        (prev, curr), _ -> curr to (prev + curr)
                    }.second
                }
            fibonacci shouldBe listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
        }

        "use 'fold' to sum the integers in a list" {
            // 1st pass: sum, element = 0, 86
            // 2nd pass: sum, element = 86, 45
            // 3rd pass: sum, element = 131, -12
            // 4th pass: sum, element = 119, 9
            // 5th pass: sum, element = 128, 28
            // 6th pass: sum, element = 156, -70
            // result = 86
            val numbers = listOf(86, 45, -12, 9, 28, -70)
            val total = numbers.fold(0) { sum, element ->
                sum + element
            }
            total shouldBe 86
        }

        // Based on a Scala example found at
        // URL https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright
        "use 'foldRight' to format a String" {
            class Person(val name: String, val birthYear: Int, val sex: Sex) {}
            val people = listOf(
                    Person("Stan Smith", 1984, Sex.MALE),
                    Person("Francine Smith", 1988, Sex.FEMALE),
                    Person("Roger Smith", 1763, Sex.UNDISCLOSED)
            )

            var formattedStrings = people.foldRight(listOf<String>()) { person, formattedStrings ->
                val title =
                    when (person.sex) {
                        Sex.FEMALE -> "Ms. "
                        Sex.MALE -> "Mr. "
                        else -> ""
                    }
                formattedStrings.plus("${title}${person.name}, born ${person.birthYear}")
            }

            formattedStrings.sorted() shouldBe listOf(
                    "Mr. Stan Smith, born 1984",
                    "Ms. Francine Smith, born 1988",
                    "Roger Smith, born 1763"
            )
        }

    }

    tailrec fun fibonacci(n: Int, a: Int = 0, b: Int = 1): Int =
            when (n) {
                0 -> a
                1 -> b
                else -> fibonacci(n - 1, b, a + b)
            }
    enum class Sex {
        FEMALE, MALE, UNDISCLOSED
    }

}
