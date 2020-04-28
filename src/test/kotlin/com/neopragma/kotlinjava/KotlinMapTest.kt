package com.neopragma.kotlinjava

import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.maps.*
import io.kotest.matchers.shouldBe

class KotlinMapTest : StringSpec() {

    val numbers = mapOf<Int, String>(
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five"
    )

    var mutableNumbers = mutableMapOf<Int, String>()

    override fun beforeTest(testCase: TestCase): Unit {
        mutableNumbers = mutableMapOf<Int, String>(
                6 to "six",
                7 to "seven",
                8 to "eight"
        )
    }

    init {

        "it uses isEmpty to verify map is not empty" {
            numbers.isEmpty() shouldBe false
        }

        "it uses kotest to verify map is not empty" {
            numbers.shouldNotBeEmpty()
        }

        "it looks up a value by its key using array index notation" {
            numbers[3] shouldBe "three"
        }

        "it looks up a value by its key using get" {
            numbers.get(2) shouldBe "two"
        }

        "it looks up a value by its key and specifies default" {
            numbers.getOrDefault(99, "this number is not in the map")
        }

        "it knows its size" {
            numbers.size shouldBe 5
        }

        "it uses contains to check existence of a map entry by its key" {
            numbers.contains(4) shouldBe true
        }

        "it uses containsKey to check existence of a map key" {
            numbers.containsKey(1) shouldBe true
        }

        "it uses kotest to check existence of a map key" {
            numbers.shouldContainKey(1)
        }

        "it uses kotest to check existence of multiple map keys" {
            numbers.shouldContainKeys(1, 3, 5)
        }

        "it uses containsKey to check non-existence of a map key" {
            numbers.containsKey(99) shouldBe false
        }

        "it uses kotest to check non-existence of a map key" {
            numbers.shouldNotContainKey(99)
        }

        "it uses kotest to check non-existence of multiple map keys" {
            numbers.shouldNotContainKeys(97, 98, 99)
        }

        "it uses containsValue to check existence of a map value" {
            numbers.containsValue("three") shouldBe true
        }

        "it uses kotest to check existence of a map value" {
            numbers.shouldContainValue("three")
        }

        "it uses kotest to check existence of multiple map values" {
            numbers.shouldContainValues("three", "two", "one")
        }

        "it uses kotest to check non-existence of a map value" {
            numbers.shouldNotContainValue("splat")
        }

        "it uses kotest to check non-existence of multiple map values" {
            numbers.shouldNotContainValues("alpha", "beta", "delta")
        }

        "it processes map keys and values using foreach" {
            var names = listOf<String>()
            numbers.forEach { key, value ->
                if (key % 2 == 0) names += value
            }
            names shouldBe listOf("two", "four")
        }

        "it processes map keys and values with for loop" {
            var names = listOf<String>()
            for ((key, value) in numbers) {
                if (key % 2 == 0) names += value
            }
            names shouldBe listOf("two", "four")
        }

        "it filters map entries" {
            numbers.filter { it.key == 1 || it.value.equals("four") } shouldBe mapOf(1 to "one", 4 to "four")
        }

        "it filters map entries again" {
            numbers.filter { it.key > 2 && it.value.startsWith("t") } shouldBe mapOf(3 to "three")
        }

        "it filters map entries by key" {
            numbers.filterKeys { it < 2 || it > 4 } shouldBe mapOf(1 to "one", 5 to "five")
        }

        "it filters map entries by value" {
            numbers.filterValues { it.startsWith("t") } shouldBe mapOf(2 to "two", 3 to "three")
        }

        "it uses put to add an entry to a mutable map" {
            mutableNumbers.put(11, "eleven")
//            mutableNumbers[11] = "eleven"  // <= this form works, too
            mutableNumbers shouldBe mutableMapOf(6 to "six", 7 to "seven", 8 to "eight", 11 to "eleven")
        }

        "it uses plusAssign to add a pair to a mutable map" {
            mutableNumbers.plusAssign(Pair(11, "eleven"))
            mutableNumbers shouldBe mutableMapOf(6 to "six", 7 to "seven", 8 to "eight", 11 to "eleven")
        }


        "it uses putIfAbsent to avoid adding a duplicate entry to a mutable map" {
            mutableNumbers.putIfAbsent(7, "setseman")
            mutableNumbers shouldBe mutableMapOf(6 to "six", 7 to "seven", 8 to "eight")
        }

        "it uses putAll to add all entries from one map into another map" {
            mutableNumbers.putAll(numbers)
            mutableNumbers shouldBe mutableMapOf(
                    1 to "one", 2 to "two", 3 to "three", 4 to "four",
                    5 to "five", 6 to "six", 7 to "seven", 8 to "eight"
            )
        }

        "it uses plusAssign to add all entries from one map into another map" {
            mutableNumbers.plusAssign(numbers)
//            mutableNumbers += numbers // <= documented to work, but doesn't compile
            mutableNumbers shouldBe mutableMapOf(
                    1 to "one", 2 to "two", 3 to "three", 4 to "four",
                    5 to "five", 6 to "six", 7 to "seven", 8 to "eight"
            )
        }

        "it uses plusAssign to add all entries from an array of pairs into a map" {
            val arrayOfPairs: Array<Pair<Int, String>> = arrayOf(
                    Pair(12, "twelve"),
                    Pair(13, "thirteen")
            )
            mutableNumbers.plusAssign(arrayOfPairs)
//            mutableNumbers += arrayOfPairs // <= documented to work, but doesn't compile
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six", 7 to "seven", 8 to "eight",
                    12 to "twelve", 13 to "thirteen"
            )
        }

        "it uses plusAssign to add all entries from a sequence of pairs into a map" {
            val sequenceOfPairs: Sequence<Pair<Int, String>> = sequenceOf(
                    Pair<Int, String>(12, "twelve"),
                    Pair<Int, String>(13, "thirteen")
            )
            mutableNumbers.plusAssign(sequenceOfPairs)
//            mutableNumbers += sequenceOfPairs  // documented to work, but doesn't compile
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six", 7 to "seven", 8 to "eight",
                    12 to "twelve", 13 to "thirteen"
            )
        }

        "it uses plusAssign to add all entries from an iterable of pairs into a map" {
            numbers.asIterable().forEach {
                mutableNumbers.plusAssign(Pair(it.key, it.value))
            }
            mutableNumbers shouldBe mutableMapOf(
                    1 to "one", 2 to "two", 3 to "three", 4 to "four",
                    5 to "five", 6 to "six", 7 to "seven", 8 to "eight"
            )
        }

        "it uses getOrPut to store a key-value pair in a mutable map" {
            val result = mutableNumbers.getOrPut(15) { "fifteen" }
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six", 7 to "seven", 8 to "eight", 15 to "fifteen"
            )
            result shouldBe "fifteen"
        }

        "it uses getOrPut to retrieve an existing value pair from a mutable map" {
            val result = mutableNumbers.getOrPut(8) { "ocho" }
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six", 7 to "seven", 8 to "eight"
            )
            result shouldBe "eight"
        }

        "it processes a map iterator and populates another map" {
            mutableNumbers.clear()
            numbers.iterator().forEach {
                mutableNumbers.put(it.key + 100, "one hundred and ${it.value}")
            }
            mutableNumbers shouldBe mutableMapOf(
                    101 to "one hundred and one",
                    102 to "one hundred and two",
                    103 to "one hundred and three",
                    104 to "one hundred and four",
                    105 to "one hundred and five"
            )
        }

        "it uses minusAssign to remove an entry from a map based on its key" {
            mutableNumbers.minusAssign(7)
//            mutableNumbers -= 7  // <= documented to work, but doesn't compile
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    8 to "eight"
            )
        }

        "it uses minusAssign to remove entries from a map based on an array of keys" {
            mutableNumbers.minusAssign(arrayOf(6, 8))
            mutableNumbers shouldBe mutableMapOf(
                    7 to "seven"
            )
        }

        "it uses minusAssign to remove entries from a map based on a sequence of keys" {
            mutableNumbers.minusAssign(sequenceOf(7, 8))
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six"
            )
        }

        "it uses minusAssign to remove entries from a map based on an iterator of keys" {
            numbers.keys.iterator().forEach {
                if (mutableNumbers.containsKey(it * 2)) mutableNumbers.minusAssign(it * 2)
            }
            mutableNumbers shouldBe mutableMapOf(
                    7 to "seven"
            )
        }

        "it uses remove to remove an entry from a map based on its key" {
            mutableNumbers.remove(7)
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    8 to "eight"
            )
        }

        "it uses remove to remove an entry from a map based on its key and value" {
            mutableNumbers.remove(7, "seven")
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    8 to "eight"
            )
        }

        "it uses remove based on both key and value but the value doesn't match the map entry" {
            mutableNumbers.remove(7, "entry")
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    7 to "seven",
                    8 to "eight"
            )
        }

        "it uses replace to change a value in a map entry" {
            mutableNumbers.replace(7, "siete")
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    7 to "siete",
                    8 to "eight"
            )
        }

        "it uses replace passing old and new values to change a value in a map entry" {
            mutableNumbers.replace(7, "seven", "siete")
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    7 to "siete",
                    8 to "eight"
            )
        }

        "it calls replace passing old and new values but the old value doesn't match" {
            mutableNumbers.replace(7, "seven-up", "siete")
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    7 to "seven",
                    8 to "eight"
            )
        }

        "it calls replaceAll with a closure" {
            mutableNumbers.replaceAll { key, value ->
                if (key % 2 == 0) {
                    "even ${value}"
                } else {
                    "odd ${value}"
                }
            }
            mutableNumbers shouldBe mutableMapOf(
                    6 to "even six",
                    7 to "odd seven",
                    8 to "even eight"
            )
        }

        "it uses computeIfAbsent to modify values in a map" {
            for (key in 2..8) {
                mutableNumbers.computeIfAbsent(key) { theKey ->
                    if (theKey % 2 == 0) "${theKey} is even" else "${theKey} is odd"
                }
            }
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six",
                    7 to "seven",
                    8 to "eight",
                    2 to "2 is even",
                    3 to "3 is odd",
                    4 to "4 is even",
                    5 to "5 is odd"
            )
        }

        "it uses computeIfPresent to modify values in a map" {
            for (key in 2..8) {
                mutableNumbers.computeIfPresent(key) { theKey, value ->
                    if (theKey % 2 == 0) "${value} is even" else "${value} is odd"
                }
            }
            mutableNumbers shouldBe mutableMapOf(
                    6 to "six is even",
                    7 to "seven is odd",
                    8 to "eight is even"
            )
        }

        "it creates a union of 3 maps" {
            val unionOfMaps = (mutableNumbers.asSequence() + numbers.asSequence() + mapOf(7 to "siete").asSequence())
                    .distinct()
                    .groupBy({ it.key }, { it.value })
                    .mapValues { (_, values) -> values.joinToString(",") }
                    .toSortedMap()
            unionOfMaps shouldBe mutableMapOf(
                    1 to "one",
                    2 to "two",
                    3 to "three",
                    4 to "four",
                    5 to "five",
                    6 to "six",
                    7 to "seven,siete",
                    8 to "eight"
            )
        }

// Don't understand why this doesn't work. The String values come out without quotation marks.
// That is: (3 to "three") doesn't equal (3 to three).
//        "it creates an intersection of 2 maps" {
//            val moreNumbers: MutableMap<Int, String> = mutableMapOf(3 to "three", 5 to "five", 7 to "seven")
//            val intersectionOfMaps = numbers.flatMap { entry ->
//                moreNumbers.filterKeys { it == entry.key }.map { entry.key to entry.value }
//            }
//            intersectionOfMaps shouldBe mutableMapOf(
//                    3 to "three",
//                    5 to "five"
//            )
//        }

        "it uses a function to provide a default value for a map" {
            mutableNumbers.getOrElse(key = 2, defaultValue = { "unknown" }) shouldBe "unknown"
        }

        "it uses 'set' to add a key-value pair to a mutable map" {
            mutableNumbers.set(0, "zero")
            mutableNumbers.toSortedMap() shouldBe mutableMapOf(
                0 to "zero",
                6 to "six",
                7 to "seven",
                8 to "eight"
            )
        }

        "it uses withDefault to set a default value for keys not present in the map" {
            mutableNumbers = mutableMapOf(
                    1 to "one",
                    2 to "two",
                    3 to "three").withDefault { "unknown" }
            mutableNumbers.getValue(0) shouldBe "unknown"   // <- only works with getValue(key)
            mutableNumbers.get(0) shouldBe null
        }

        "it" {
            mutableNumbers.any { "x" }
        }


    }
}

