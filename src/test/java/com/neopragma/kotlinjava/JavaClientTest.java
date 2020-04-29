package com.neopragma.kotlinjava;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class JavaClientTest {

    private static JavaCodeCallingKotlinFunctions javaClient;

    @BeforeAll
    static void initializeJavaClient() {
        javaClient = new JavaCodeCallingKotlinFunctions();
    }

    @Test
    void java_calls_top_level_kotlin_function_with_regular_arguments() {
        String[] returnedValue = javaClient.callKotlinFunctionWithRegularArguments("alpha", "beta", "gamma");
        assertArrayEquals(new String[] { "alpha", "beta", "gamma"}, returnedValue);
    }

    @Test
    void java_calls_top_level_kotlin_function_with_named_arguments() {
        // Java can't specify the argument names, so arguments must be coded in the same order
        // as they are declared in the Kotlin code.
        String[] returnedValue = javaClient.callKotlinFunctionWithNamedArguments("alpha", "beta", "gamma");
        assertArrayEquals(new String[] { "alpha", "beta", "gamma"}, returnedValue);
    }

    @Test
    void java_calls_top_level_kotlin_function_with_one_argument_specifying_JvmOverloads_annotation() {
        String[] returnedValue = javaClient.callKotlinFunctionSpecifyingJvmOverloadsAnnotation("alpha");
        assertArrayEquals(new String[] { "alpha", "defaultBeta", "defaultGamma"}, returnedValue);
    }

    @Test
    void java_calls_top_level_kotlin_function_with_two_arguments_specifying_JvmOverloads_annotation() {
        String[] returnedValue = javaClient.callKotlinFunctionSpecifyingJvmOverloadsAnnotation("alpha", "beta");
        assertArrayEquals(new String[] { "alpha", "beta", "defaultGamma"}, returnedValue);
    }

    @Test
    void java_calls_kotlin_member_function() {
        String[] returnedValue = javaClient.callKotlinMemberFunction("alpha", "beta", "gamma");
        assertArrayEquals(new String[] { "alpha", "beta", "gamma"}, returnedValue);
    }
}
