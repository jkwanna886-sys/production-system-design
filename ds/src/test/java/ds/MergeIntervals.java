package ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * merge overlap internal.
 */
public class MergeIntervals {
    public static void main(String[] args) {
        //{1,6}, {8, 10}, {15, 18}
        int[][] intervals = { {1, 3}, {2, 6}, {8, 10}, {15, 18} };
//        int[][] intervals = {{4, 7}, {1, 4} };
        List<int[]> result = merge2(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }


    public static List<int[]> merge(int[][] array) {
        List<int[]> result = new ArrayList<>();
        // sort by the first element.
        Arrays.sort(array, (a,b)->a[0]-b[0]);

        int[] current=array[0];
        for(int i=1; i<array.length;i++) {
            int[] e = array[i];

            // overlap, merge
            if(current[1]>=e[0]) {
//                current = new int[]{current[0], Math.max(current[1],e[1])};
                current[1] = Math.max(current[1],e[1]);
            } else {
                // not overlap
                result.add(current); // record the current element.
                current = e; // current element move to next.
            }
        } // end: for
        result.add(current);

        return result;
    }

    static List<int[]> merge2(int[][] arr) {
        Arrays.sort(arr,(x,y)-> x[0]-y[0]);

        int[] curr = arr[0];
        List<int[]> result = new ArrayList<>();
        for(int i=1;i<arr.length;i++) {
            if(curr[1]>=arr[i][0]) { // overlap, extend curr
                curr[1]=Math.max(curr[1],arr[i][1]);
            }else {
                result.add(curr); // record current
                curr=arr[i]; // move current to next
            }
        }
        result.add(curr);
        return result;
    }

}

