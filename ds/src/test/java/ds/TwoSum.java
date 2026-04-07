package ds;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class TwoSum {

    @Test
    public void test() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        for (int i : twoSum(nums, target)) {
            System.out.println(i);
        }
    }


    // x + y = target -> x=target-y(current)
// 2->0
    static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<arr.length;i++) {
            int curr = arr[i];

            int x = target-curr;
            // we've found the two elements that we need.
            if(map.containsKey(x)) return new int[] {i, map.get(x)};

            map.put(curr, i);
        }

        return new int[]{-1,-1};
    }

    /**
     * x + y = target.(normal)
     * x = target - current. (reverse)
     * @param array
     * @param target
     * @return
     */
    public static int[] hash(int[] array, int target) {
        // key: item, value: indicate.
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int x = target - array[i];
            if(m.containsKey(x)) { // we have found the two elements.
                return new int[] {m.get(x), i};
            }
            m.put(array[i], i);
        }
        return new int[]{-1, -1}; // not found.
    }

    // the array is not guarantee sorted.
// core: x+a=target.
//-> x=target-a. (this depends on record each element when we visit)
    static int[] hash2(int[] array, int target) {
        // use map to record the element vs the index.
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<array.length; i++) {
            int x = target-array[i]; // try to find an element that plus the current element that sum to the target.
            if(map.containsKey(x)) // found.
                return new int[] {i, map.get(x)};

            // record when we visit.
            map.put(array[i], i);
        }

        return new int[]{-1,-1}; // not found
    }

    /**
     * sum the two pointers:
     * target=? -> return directly
     * target>? -> right--
     * target<? -> left++
     * @param array
     * @param target
     * @return
     */
    // the array is sorted
    static int[] twoPointer(int[] array, int target) {
        int left = 0;
        int right = array.length-1;
        int sum; // we need a variable try to sum.
        while(left<right) { // the title needs two element, if left equals right, it just means one element.
            sum = array[left]+array[right];
            if(target==sum) // found
                return new int[]{left, right};
            else if(sum<target) // too small, left forward.
                left++;
            else // too large, right backward.
                right--;
        } // end: while

        return new int[]{-1,-1};
    }
}

