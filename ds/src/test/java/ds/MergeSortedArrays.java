package ds;

import org.junit.jupiter.api.Test;

/**
 * Merge two sorted arrays
 */
public class MergeSortedArrays {
    @Test
    public void testExtraSpace() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] merge = merge(arr1, arr2);

        for (int e : merge) {
            System.out.print(e+" ");
        }
    }

    int[] merge(int[] arrA, int[] arrB) {
        int[] arr = new int[arrA.length + arrB.length];

        int pA = 0;
        int pB = 0;
        for (int i = 0; i < arr.length; i++) {
            if(pA<arrA.length && pB<arrB.length) {
                if(arrA[pA]<arrB[pB]) {
                    arr[i] = arrA[pA++];
                }else {
                    arr[i] = arrB[pB++];
                }
            } else { // one of the array finish
                if (pA < arrA.length)
                    arr[i] = arrA[pA++];

                if (pB < arrB.length)
                    arr[i] = arrB[pB++];
            }
        } // end: for

        return arr;
    }

    @Test
    public void testInPlace() {
        int[] arr1 = new int[8];
        arr1[0] = 1;
        arr1[1] = 3;
        arr1[2] = 5;
        arr1[3] = 7;
        // 1,3,5,7,0,0,0,0

        int[] arr2 = {2, 4, 6, 8};

        inPlaceMerge(arr1, 4, arr2, arr2.length);

        for (int e : arr1) {
            System.out.print(e + " ");
        }
    }

    void inPlaceMerge(int[] arr1, int m, int[] arr2, int n) {
        int pA = m - 1;
        int pB = n - 1;

        int i = m + n -1; // last of arr1

        while(pA>=0 && pB>=0) {
            if(arr1[pA]>arr2[pB]) {
                arr1[i--] = arr1[pA--];
            }else {
                arr1[i--] = arr2[pB--];
            }
        }

        while (pB>=0) {
            arr1[i--] = arr2[pB--];
        }
    }
}
