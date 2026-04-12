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

        Assert.assertEquals(frequency(new int[]{0, 0, 1}, 1), 3);

    }

    int frequency(int[] arr, int k) {
        int preSum = 0;
        int c = 0;
        Map<Integer,Integer> frequent = new HashMap<>();
        frequent.put(0, 1);
        /*
        sum(i,j)=k=prefix[j]-prefix[i-1]
        prefix[i-1]=prefix[j]-k
        (only need one variable)
         :0->1
         1: preSum=1, preSum-k=1-2=-1, 1->1
         1: preSum=2, preSum-k=0: 2->1
         1: preSum=3, preSum-k=1: 3->1
         */
        for (int e : arr) {
            preSum += e;

            int x = preSum - k;
            /**
             * NOTE: So when we find target = S - k, we're not just asking "did this prefix sum exist?"
             * but rather "how many times did this prefix sum occur?"
             * because each occurrence represents a different starting point for a subarray ending at j.
             */
            c += frequent.getOrDefault(x, 0);

            frequent.put(preSum, frequent.getOrDefault(preSum, 0) + 1);
        }
        return c;
    }

}
