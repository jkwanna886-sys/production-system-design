package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class FibonacciNumber {
    @Test
    public void test() {
        Assert.assertEquals(1, fib(2));
        Assert.assertEquals(2, fib(3));
        Assert.assertEquals(3, fib(4));
    }

    /**
     * f(0)=0
     * f(1)=1
     * f(2)=f(1)+f(0)=f(1)
     * f(3)=f(2)+f(1)=2f(1)
     * f(4)=f(3)+f(2)=
     */
    int fib(int n) {
        if(n==1|| n==0) return n;

        return fib(n-1) + fib(n-2);
    }
}
