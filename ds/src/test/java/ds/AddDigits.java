package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddDigits {
    @Test
    public void test() {
        int num = 38;

        while (num>=10) {
            int sum = 0;
            // extract all the digits to sum.
            while (num>0) {
                sum += num%10;
                num/=10;
            }
            num = sum;// if num still >=10, use sum as the next round.
        }

        Assert.assertEquals(2, num);
    }
}
