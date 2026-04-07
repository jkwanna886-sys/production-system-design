package ds;
public class RotateImage {
    // Quick test
    public static void main(String[] args) {
    System.out.println("--*********************");
        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotateImage(matrix3);

        for (int[] row : matrix3) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
System.out.println("*********************");
    }

    /**
     * 1.from element level to business level(top->right; right->bottom; bottom->left; left->top)
     * 2.from business level to element level.
     * relative concept.
     *
     * we've layer vs element level. so layer is the outer loop. "i" is the element loop.
     */
    static void rotateImage(int[][] array) {
        int n = array.length;
        // outside, layer from 0-n/2. eg 0, 1
        // outside means, how many layers need to handle.
        for(int layer=0; layer<n/2; layer++) {
            // for layer 0:i can be 0,1,2
            // for layer 1:i can be 1
            // the i mean how many elements need to handle within one layer.
            for(int i=layer; i<n-1-layer;i++) {
                int offset = n-1-i;
                // 1. store top
                int top = array[layer][i];
                // 2. left to top
                array[layer][i] = array[offset][layer];
                // 3. bottom to left
                array[offset][layer] = array[n-1-layer][offset];
                // 4. right to bottom
                array[n-1-layer][offset] = array[i][n-1-layer];
                // 5. top to right
                array[i][n-1-layer] = top;
            } // end: for i
        } // end: for layer
    }
}


