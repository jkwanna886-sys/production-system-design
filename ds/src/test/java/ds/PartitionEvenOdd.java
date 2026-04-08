package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Partition integers into even/odd
 */
public class PartitionEvenOdd {
    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(e -> e % 2 == 0));

        List<Integer> even = partitioned.get(true);
        List<Integer> odd = partitioned.get(false);

        System.out.println(even);
        System.out.println(odd);
    }
}
