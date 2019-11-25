package de.techfak.gse.template;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.ymokrane.FizzBuzz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class FizzBuzzTest {

    @Test
    public void testEmpty() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        List<String> result = fizzBuzz.fizzBuzz(0);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testOne() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        List<String> result = fizzBuzz.fizzBuzz(1);
        Assertions.assertThat(result).containsExactly("1");
    }

    @Test
    public void testTwo() {
        FizzBuzz fizzBuzz = new FizzBuzz();

        List<String> result = fizzBuzz.fizzBuzz(2);
        Assertions.assertThat(result).containsExactly("1", "2");
    }

    @Test
    public void testThree() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        List<String> result = fizzBuzz.fizzBuzz(3);
        Assertions.assertThat(result).containsExactly("1", "2", "Fizz");
    }

    @Test
    public void testAll() {
        int x = 16;
        FizzBuzz fizzBuzz = new FizzBuzz();
        List<String> result2 = hilfs(x);
        List<String> result = fizzBuzz.fizzBuzz(x);
        Assertions.assertThat(result).isEqualTo(result2);
    }

    public List<String> hilfs(int obereGrenze) {
        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= obereGrenze; i++) {
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
