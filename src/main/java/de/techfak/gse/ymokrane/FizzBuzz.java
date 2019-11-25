package de.techfak.gse.ymokrane;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public List<String> fizzBuzz(int zahl) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= zahl; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {


                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
