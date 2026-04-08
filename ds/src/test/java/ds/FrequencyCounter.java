package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Count frequency of each element
 */
public class FrequencyCounter {
    @Test
    public void test() {
        int[] arr = {1, 2, 3, 2, 1, 3, 3, 4, 5, 4};

        Map<Integer, Integer> result = map(arr);

        result.forEach((k, f) -> {
            System.out.print(k + "->" + f + "#");
        });
    }

    Map<Integer, Integer> map(int[] arr) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int e : arr) {
            Integer lastFrequency = result.getOrDefault(e, 0);

            result.put(e, lastFrequency+1);
        }

        return result;
    }


    @Test
    public void testStream() {
        int[] arr = {1, 2, 3, 2, 1, 3, 3, 4, 5, 4};

        Map<Integer, Long> result = stream(arr);

        result.forEach((k, f) -> {
            System.out.print(k + "->" + f + "#");
        });
    }

    Map<Integer, Long> stream(int[] arr) {
        Map<Integer, Long> map = Arrays.stream(arr)
                .boxed() // Collectors like groupingBy work with objects, not primitives
                .collect(Collectors.groupingBy(
                        e->e // Function.identity(): identity() means: group elements by themselves
                        , Collectors.counting())); // Counts elements in each group

        return map;
    }
}
