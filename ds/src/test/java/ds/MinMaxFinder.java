package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * Find max and min in one pass.
 */
public class MinMaxFinder {
    @Test
    public void testFindTwoNumberSumToTarget() {
        // given a sorted array.
        int[] arr = {3, 5, 1, 9, 2, 8, -1, 4};
        for (int e : stream(arr)) {
            System.out.println(e);
        }
    }

    /**
     * manually
     * @param arr
     * @return
     */
    int[] minMax(int[] arr) {
        int min=arr[0];
        int max=arr[0];

        for(int i=0; i<arr.length; i++) {
            if(arr[i]<min)
                min = Math.min(min, arr[i]);

            if(arr[i]>max)
                max = Math.max(max, arr[i]);
        }

        return new int[] {min, max};
    }

    int[] stream(int[] arr) {
        IntSummaryStatistics intSummaryStatistics = Arrays.stream(arr).summaryStatistics();

        return new int[] {intSummaryStatistics.getMin(), intSummaryStatistics.getMax()};
    }
}
