package com.neopragma.kotlinjava;

public class JavaClient {

    public String[] callKotlinFunctionWithRegularArguments(String arg0, String arg1, String arg2) {
        return KotlinFunctions.callMeWithRegularArguments("alpha", "beta", "gamma");
    }

    public String[] callKotlinFunctionWithNamedArguments(String arg0, String arg1, String arg2) {
        return KotlinFunctions.callMeWithNamedArguments("alpha", "beta", "gamma");
    }

    public String[] callKotlinFunctionSpecifyingJvmOverloadsAnnotation(String... args) {
        switch (args.length) {
            case 1:
                return KotlinFunctions.callMeWithJvmOverloads(args[0]);
            case 2:
                return KotlinFunctions.callMeWithJvmOverloads(args[0], args[1]);
            case 3:
                return KotlinFunctions.callMeWithJvmOverloads(args[0], args[1], args[2]);
        }
        return KotlinFunctions.callMeWithJvmOverloads();
    }

    public String[] callKotlinMemberFunction(String arg0, String arg1, String arg2) {
        KotlinClassWithMemberFunctions kotlinClass = new KotlinClassWithMemberFunctions();
        return kotlinClass.callMeInAClass("alpha", "beta", "gamma");
    }
}
