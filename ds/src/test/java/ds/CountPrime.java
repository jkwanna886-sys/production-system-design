package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;



/**
 * https://leetcode.com/problems/count-primes/description/
 */
public class CountPrime {
    /*
n=10

2/3/5/7
 i=2:
     2*2=4
     2*3=6
     2*4=8
     2*5=10
 i=3(3*1 and 3*2 already handled before)
    3*3=9
 */
    @Test
    public void test() {
        int n = 10;

        Boolean[] primes = new Boolean[n+1];

        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for(int i=2; i*i<=n; i++) {
            if(primes[i]) {
                for(int j=i*i; j<=n; j+=i) {
                    primes[j] = false;
                }
            }
        }

        long count = Arrays.stream(primes).filter(p -> p).count();

        Assert.assertEquals(4, count);
    }
}
