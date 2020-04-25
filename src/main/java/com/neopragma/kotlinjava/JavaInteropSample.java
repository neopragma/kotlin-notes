package com.neopragma.kotlinjava;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JavaInteropSample {

    List<String> listOfStrings() {
        ArrayList<String> newList = new ArrayList();
        newList.add("alpha");
        newList.add(null);
        newList.add("beta");
        return newList;
    }
}
