package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/description/
 */
public class ValidPalindrome {
    @Test
    public void test() {
        String s = "A man, a plan, a canal: Panama";
        Assert.assertEquals(true, valid(s));
        s = "race a car";
        Assert.assertEquals(false, valid(s));

    }

    boolean valid(String s) {
        int left = 0;
        int right = s.length()-1;

        while (left<right) {
            char lChar = s.charAt(left);
            char rChar = s.charAt(right);
            if (!Character.isLetterOrDigit(lChar)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(rChar)) {
                right--;
                continue;
            }

            if(Character.toLowerCase(lChar)!=Character.toLowerCase(rChar))
                return false;

            left++;
            right--;
        }

        return true;
    }
}
