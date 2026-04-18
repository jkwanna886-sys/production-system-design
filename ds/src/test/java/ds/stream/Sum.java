package ds.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Sum {
    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println(stream(arr));
    }

    int sumLoop(int[] arr) {
        int sum = 0;

        for (int e : arr) {
            sum+=e;
        }
        return sum;
    }

    int stream(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}
