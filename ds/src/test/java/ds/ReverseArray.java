package ds;

import org.junit.jupiter.api.Test;

public class ReverseArray {
    @Test
    public void test() {
        char[] array = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverse(array);

        for (char i : array) {
            System.out.print(i);
        }
    }



    static void reverse(char[] array) {
        int left=0;
        int right=array.length-1;
        while(left<right) {
            char c = array[left];
            array[left]=array[right];
            array[right]=c;

            left++;
            right--;
        }

    }




}
