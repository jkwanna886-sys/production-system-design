package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * Move zeros to end, maintain order
 */
public class MoveZeroes {
    @Test
    public void test() {
        // given a sorted array.
        int[] arr = {0, 1, 0, 3, 12};
        move(arr);
        for (int e : arr) {
            System.out.println(e);
        }
    }

    /**
     * manually
     * @param arr
     * @return
     */
    void move(int[] arr) {
        int nonZeroIndex = 0;

        for (int e : arr) {
            if(e!=0)
                arr[nonZeroIndex++]=e;
        }

        while (nonZeroIndex<arr.length) {
            arr[nonZeroIndex++]=0;
        }
    }

}
