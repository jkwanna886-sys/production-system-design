package ds;

import org.junit.jupiter.api.Test;

/**
 * 344. Reverse String
 * https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {
    @Test
    public void test() {
        char[] array = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverse(array);

        for (char i : array) {
            System.out.print(i);
        }
    }



    void reverse(char[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left<right) {
            char t = array[left];
            array[left] = array[right];
            array[right] = t;

            left++;
            right--;
        }
    }



}
