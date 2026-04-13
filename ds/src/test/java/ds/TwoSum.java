package ds;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
    void d(){
        System.out.println(i);
    }
    int i;

    @Test
    public void test() {
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        int[] ints = twoSum(arr, target);
        for (int e : ints) {
            System.out.println(e);
        }
    }
    /**
     * x+c=target
     * x=target-c
     */
    int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int x = target - arr[i];
            if(seen.containsKey(x))
                return new int[] {i, seen.get(x)};

            seen.put(arr[i], i);
        }
        return new int[] {-1, -1};
    }

}

