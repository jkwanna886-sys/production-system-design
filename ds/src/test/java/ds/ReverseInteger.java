package ds;

import org.junit.jupiter.api.Test;

public class ReverseInteger {
    @Test
    public void test() {
        int i = 123;

        System.out.println(reverse(i));
    }

    int reverse(int i) {
        long result = 0;
        // 21
        /*
        i % 10: 2
        i=1
        */
        while(i!=0) {
            int mod = i % 10;
            result = result * 10 + mod;

            i/=10;
        }
        if(result<Integer.MIN_VALUE || result>Integer.MAX_VALUE)
            return 0;

        return (int)result;
    }
}
