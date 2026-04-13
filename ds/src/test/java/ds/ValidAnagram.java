package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {
    @Test
    public void test() {
        String s = "anagram";
        String t = "nagaram";

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        Arrays.sort(charsS);
        Arrays.sort(charsT);

        boolean valid = Arrays.equals(charsS, charsT);
        Assert.assertEquals(true, valid);
    }
}
