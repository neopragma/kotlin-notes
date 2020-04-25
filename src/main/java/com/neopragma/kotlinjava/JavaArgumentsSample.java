package com.neopragma.kotlinjava;

public class JavaArgumentsSample {

    public String[] callMeWithRegularArguments(String arg0, String arg1, String arg2) {
        return new String[] { arg0, arg1, arg2 };
    }

    public String[] callMeWithVarargs(String arg0, String... moreArgs) {
        String[] theArgs = new String[moreArgs.length + 1];
        theArgs[0] = arg0;
        for (int i = 1 ; i < theArgs.length ; i++) {
            theArgs[i] = moreArgs[i-1];
        }
        return theArgs;
    }

}
