package ds;


import org.junit.jupiter.api.Test;

public class SearchTargetInRotatedArray {
    @Test
    public void test() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] arr2 = {2, 2, 2, 3, 2};
        System.out.println(search(nums, 0));
        System.out.println(withDuplicate(new int[]{1, 2, 2, 3, 2}, 3));
    }

    /**
     * given a rotated sorted array(no duplicate), eg.
     * [4,5,6,7,0,1,2]
     * and target number eg. 0
     * return the position of
     */
    static int noDuplicate(int[] array, int target) {
        // each rotated, one part is rotated, another part is still sorted, so we can use binary search.
        int left = 0;
        int right = array.length - 1;
        // we need to keep search when left=right, to check whether the target number is what we're searching for.
        while(left<=right) {
            int mid = left + (right-left)/2; // middle element.

            // found.
            if(array[mid]==target) return mid;

            if(array[mid]>array[right]) { // right is rotated(left still sorted)
                // since the left part still sorted, priority check whether target range in left.
                if(array[left]<=target&&target<array[mid])
                    right = mid-1;
                else
                    left = mid+1;
            } else { // left is rotated(right still sorted)
                // priority check in the right sorted part.
                if(array[mid]<target&&target<=array[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
        }
        return -1; // didn't find.
    }

    /**
     * duplicate on both-side are dangerous(mask rotation structure), so need to consider both-side.
     * @param array
     * @param target
     * @return
     */
    static int withDuplicate(int[] array, int target) {
        // each rotated, one part is rotated, another part is still sorted, so we can use binary search.
        int left = 0;
        int right = array.length - 1;
        // we need to keep search when left=right, to check whether the target number is what we searching for.
        while(left<=right) {
            int mid = left + (right-left)/2; // middle element.

            // fount.
            if(array[mid]==target) return mid;

            if(array[mid]==array[right]) { // can not decide which half
                right--;
            } else if(array[mid]>array[right]) {
                // since the left part still sorted, we can priority check whether target range in left.
                if(array[left]<=target&&target<array[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }else { // left is rotated(right still sorted)
                // priority check in the right sorted part.
                if(array[mid]<target&&target<=array[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
        }
        return -1;
    }


    static int search(int[] array, int target) {
        int left=0;
        int right=array.length-1;
        while(left<=right) {
            int mid = left+(right-left)/2;

            if(target==array[mid]) return mid;

            if(array[mid]>array[right]) { // rotated in right part, left still sorted.
                if(array[left]<=target&&target<array[mid])
                    right=mid-1;
                else
                    left=mid+1;
            } else { // right is sorted
                if(array[mid]<target&&target<=array[right])
                    left=mid+1;
                else
                    right=mid-1;
            }
        }

        return -1;
    }
}
