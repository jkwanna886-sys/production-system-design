package ds;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 23;
        System.out.println(searchIn2D(matrix, target));
    }


    /**
     * we can consider it as 1-dimension array, so "left" vs "right" pointer just need to handle for 1-dimension.
     * only when need to access the element of the array, when transfer to 2-dimension.
     * use index i range [0, m*n-1]. and only r change when i is 4, 8. so r=i/n.
     * similar, within each row(r), c change from 0-3, so c=i%n.
     * use n since within the ful row, it has n elements.
     * @param array
     * @param target
     * @return
     */
    static boolean searchIn2D(int[][] array, int target) {
        int m = array.length;
        int n = array[0].length;

        int left = 0; // init at first
        int right = m*n-1; // init last

        while(left<=right) {
            int mid = left + (right-left)/2; // 1 dimension of index.

            // transfer 1 dimension of index to 2 dimension of index.
            int r = mid/n;
            int c = mid%n;

            // middle element equals target element.found
            if(target==array[r][c]) return true;

            if(target>array[r][c]) left = mid+1; // middle element too small, try to find larger in the right part.
            else right = mid-1; // middle element too large, try to find smaller in the left part.
        }

        return false;
    }
}
