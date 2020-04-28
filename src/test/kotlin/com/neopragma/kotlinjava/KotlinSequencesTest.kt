package com.neopragma.kotlinjava

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinSequencesTest : StringSpec() {

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


    }

    tailrec fun fibonacci(n: Int, a: Int = 0, b: Int = 1): Int =
            when (n) {
                0 -> a
                1 -> b
                else -> fibonacci(n - 1, b, a + b)
            }
}
