package ds;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Must run in O(n).
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4   // sequence [1, 2, 3, 4]
 */
public class LongestConsecutive {
    @Test
    public void test() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = test(nums);
        System.out.println(result);
    }

    private int longestConsecutive(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int e : array) {
            set.add(e);
        }

        int maxLen = 0;
        for (Integer e : set) {
            // only count if it's the start of the sequence.
            if(!set.contains(e-1)) {
                int count = 1;
                int current = e;
                while(set.contains(current+1)) { // note that, since we need to upgrade current number every time, we need extra variable.
                    current++;
                    count++;
                }
                maxLen = Math.max(maxLen, count);
            }
        }
        return maxLen;
    }

    static int test(int[] array) {
        // first we use set to initialize, since later we visit,which just need O(1) time.
        Set<Integer> set = new HashSet<>(array.length);
        for (int e : array) {
            set.add(e);
        }

        int maxLen = 0; // store the result max length.
        int current;
        for (int e : array) {
            // e is the start of the sequence.
            if(!set.contains(e-1)) {
                current = e; // start new count.
                int count=1;

                // continue to check whether it has consecutive.
                while (set.contains(current+1)) {
                    current++;
                    count++;
                }
                maxLen = Math.max(maxLen, count);
            }
        }

        return maxLen;
    }
}
