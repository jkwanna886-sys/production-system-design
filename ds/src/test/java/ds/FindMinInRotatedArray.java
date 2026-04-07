package ds;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class FindMinInRotatedArray {
    @Test
    public void test() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(nonDuplicate(nums));

        assertEquals(0, findMin(nums));

        nums = new int[]{2,0,1,2,2,2};
        System.out.println(withDuplicate(nums));

    }


    static int nonDuplicate(int[] array) {
        /*
        7,0,1,2(m),4,5,6	A
        6,7,0,1(m)2,4,5		B
        5,6,7,0(m),1,2,4	C
        4,5,6,7(m),0,1,2	D
        2,4,5,6(m),7,0,1	E
        1,2,4,5(m)6,7,0		F
        */

        // from D/E/F, we know that, when middle > right(length-1), then the minimum on the right part. range [m+1, lenght-1]
        // otherwise,  range [0,m]

        int left = 0;
        int right = array.length-1;
        while(left<right) {
            int mid = left + (right-left)/2;
            if(array[mid]>array[right]) // right part rotated,the min on right part
                left = mid+1;
            else // rotated on the left part, but might be the mid element.
                right = mid;
        }
        // finally, left = right,
        return array[left];
    }

    static int withDuplicate(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while(left<right) {
            int mid = left + (right-left)/2;

            if(array[mid]>array[right]) // rotated on the right
                left = mid + 1;
            else if(array[mid]<array[right]) // rotated on left or mid
                right = mid;
            else // flat, can not decide, take a bit shift
                right--;
        }
        // finally, left = right.
        return array[left];
    }

    static int findMin(int[] arr) {
        int left =0;
        int right =arr.length-1;
        while(left<right) {
            int mid = left+(right-left)/2;

            if(arr[mid]>arr[right]) { // right part rotated.
                left=mid+1;
            } else { // left rotated.
                right=mid;
            }
        }
        return arr[left];
    }
}
