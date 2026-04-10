package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/count-the-digits-that-divide-a-number/
 */
public class CountTheDigitsThatDivideANumber {
    /**
     * Input: num = 121
     * Output: 2
     * Explanation: 121 is divisible by 1, but not 2. Since 1 occurs twice as a digit, we return 2.
     */
    @Test
    public void test() {
        int num = 121;

        int t = num;
        int c = 0;

        while(t>0) {
            int mod = t % 10;
            if(num%mod==0) c++;
            t/=10;
        }

        Assert.assertEquals(2, c);
    }
}
