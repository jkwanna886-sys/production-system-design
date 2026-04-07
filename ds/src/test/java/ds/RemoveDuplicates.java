package ds;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class RemoveDuplicates {
    @Test
    public void test() {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int newLen = removeDuplicate(arr);
        System.out.println(newLen);
        for(int i=0; i<newLen;i++)
            System.out.print(arr[i]);

        System.out.println("********");
        int[] arr2 = {4,2,2,3,4,1,3};
        System.out.println(hash(arr2));
        for (int i : arr2) {
            System.out.print(i);
        };
    }

    /**
     * remove duplicate in-place(for sorted array), return the new length.
     *  * eg.[0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
     *  "two pointer":
     * slow to maintain(point to) the last unique element.
     * fast to detect new unique element.
     * time:O(n)
     * space:O(1)
     * @param array
     * @return
     */
    static int remove(int[] array) {
        int slow=0;
        for(int fast=1; fast<array.length; fast++) {
            // if the slow and the fast equal, we just need to keep moving fast forward.

            if(array[slow]!=array[fast]) {
                slow++;
                array[slow]=array[fast];
            }
        }
        return slow+1;
    }

    /**
     * non-sorted.
     * insertPos use to maintain last unique element.(like slow pointer in sorted case)
     * @param array
     * @return
     */
    static int hash(int[] array) {
        Set<Integer> seen = new HashSet<>(); // track the visited element.
        int insertPos = 0; // point to(maintain) last unique element, and confirm.
        for (int e : array) {
            if(!seen.contains(e)) { // occur unique element.
                seen.add(e); // record the visited.
                array[insertPos++] = e; // maintain the unique element.
            }
        }

        return insertPos;
    }

    static int removeDuplicate(int[] arr) {
        int slow = 0;
        for(int fast=1;fast<arr.length;fast++) {
            if(arr[slow]!=arr[fast]) {
                slow++;
                arr[slow]=arr[fast];
            }
        }
        return slow+1;
    }
}
