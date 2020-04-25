@file:JvmName("KotlinFunctions")
package com.neopragma.kotlinjava

fun callMeWithRegularArguments(arg0: String, arg1: String, arg2: String): Array<String> {
    return arrayOf(arg0, arg1, arg2)
}

fun callMeWithNamedArguments(first: String = "defaultAlpha",
                             second: String = "defaultBeta",
                             third: String = "defaultGamma"): Array<String> {
    return arrayOf(first, second, third)
}

@JvmOverloads
fun callMeWithJvmOverloads(first: String = "defaultAlpha",
                             second: String = "defaultBeta",
                             third: String = "defaultGamma"): Array<String> {
    return arrayOf(first, second, third)
}

class KotlinClassWithMemberFunctions {
    fun callMeInAClass(arg0: String, arg1: String, arg2: String): Array<String> {
        return arrayOf(arg0, arg1, arg2)
    }
}