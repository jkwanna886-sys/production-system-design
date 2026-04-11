package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {
    /**
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     */
    @Test
    public void test() {

        Assert.assertEquals(frequency(new int[]{1,1,1}, 2), 2);

        Assert.assertEquals(frequency(new int[]{1,2,3}, 3), 2);

    }

    int frequency(int[] arr, int k) {
        int preSum = 0;
        int c = 0;
        Map<Integer,Integer> frequent = new HashMap<>();
        frequent.put(0, 1);
        /*
         :0->1
         1: preSum=1, preSum-k=1-2=-1, 1->1
         1: preSum=2, preSum-k=0: 2->1
         1: preSum=3, preSum-k=1: 3->1
         */
        for (int e : arr) {
            preSum += e;

            int x = preSum - k;

            if(frequent.containsKey(x))
                c++;

            frequent.put(preSum, frequent.getOrDefault(preSum, 0) + 1);
        }
        return c;
    }


}
