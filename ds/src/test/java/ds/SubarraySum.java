package ds;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class SubarraySum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 7, 5};
        System.out.println(slidingWindow(nums, 12));;

        int[] arr2 = {1, 2, 3};
        System.out.println(subarrayHash(arr2, 3));;
    }

    /**
     * or return the number of the number(how many sub array).
     * suitable for array only contain positive.
     * @param arr
     * @param target
     */
    static int slidingWindow(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int count = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {// shrink the window.
                sum -= arr[left++];
            }
            if (target == sum) {
//                System.out.println("left=" + left + ",right=" + right);
                count++;
            }
        }
        return count;
    }

    static int subarray(int[] array, int target) {
        int sum=0; // sum for the sub array.

        int left = 0;
        for(int right=0;right<array.length; right++) {
            // right pointer moving forward.
            sum += array[right];

            // found
            if(target==sum) return right-left + 1;

            // moving forward means that, target != sum.
            // so, if sum<target, just moving on.
            // otherwise, when sum>target, we need to move left pointer forward.
            if(sum>target) left++;
        }
        return 0;
    }

    // each presum in map can be considered as total element up to current,
    static int subarrayHash(int[] array, int target) {
        // 1, 2, 3 -> 1, 3, 6
        // presum[3]-presum[1]= 6 - 1 = 5  target ( from array[1] to array[2]), is what we need subarray)
        // presum[j]-target=presum[i] : 6 - 5 = 1.(index start from 1+1=2)

        int presum = 0; // store current presum.
        int count = 0; // the result count of subarray that sum to target.
        // store each presum and its count. the count is what we need, since we might have multi subarray
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // this is use to handle the first time we found subarray
        for(int e: array) {
            presum+= e;
            // try to detect whether there exist presum[i] that meet "presum[j]-target=presum[i]"
            int presumI = presum - target;
            // each time we find subarray, we increase count.
            if(map.containsKey(presumI))
                count+=map.get(presumI);

            // we also need to increase count of presum.
            map.put(presum, map.getOrDefault(presum,0)+1);
        } // end: for

        return count;
    }
}
