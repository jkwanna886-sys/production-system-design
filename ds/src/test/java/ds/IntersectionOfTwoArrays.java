package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class IntersectionOfTwoArrays {
    @Test
    public void test() {
        int[] arrA = {9,4,9,8,4};

        Set<Integer> result = new HashSet<>();

        List<Integer> listB = Arrays.asList(4,9,5);

        for (int e : arrA) {
            if(listB.contains(e)) result.add(e);
        }

        System.out.println(result);
    }

    @Test
    public void testStream() {
        int[] arrA = {9,4,9,8,4};
        List<Integer> listB = Arrays.asList(4,9,5);

        Set<Integer> result = Arrays.stream(arrA).boxed()
                .filter(listB::contains).collect(Collectors.toSet());

        System.out.println(result);
    }
}
