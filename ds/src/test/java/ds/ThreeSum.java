package ds;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    @Test
    public void test() {
        List<List<Integer>> result = threeSum(new int[] {-1,0,1,2,-1,-4});

        System.out.println(result);
    }

    List<List<Integer>> threeSum(int[] arr) {
        // [-1,0,1,2,-1,-4]
        // [[-1,-1,2],[-1,0,1]]
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);

        for(int i=0; i<arr.length-2; i++) {
            if(i>0 && arr[i]==arr[i-1]) continue;

            int left = i+1;
            int right = arr.length-1;

            while (left<right) {
                int sum = arr[i] + arr[left] + arr[right];

                if(sum==0) {
                    result.add(List.of(arr[i], arr[left], arr[right]));

                    while (left<right && arr[left]==arr[left+1]) left++;
                    while(left<right && arr[right]==arr[right-1]) right--;

                    left++;
                    right--;
                }else if(sum<0) left++;
                else right--;
            }
        } // end: for

        return result;
    }
}
