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

        Assert.assertEquals(true, balance(s, t));
    }

    /**
     * convert into array
     * sort
     * compare
     * time:nlog(n)
     * space: n
     */
    boolean sort(String s, String t) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        Arrays.sort(charsS);
        Arrays.sort(charsT);

        return Arrays.equals(charsS, charsT);
    }

    /**
     * s increase proper index
     * t decrease proper index
     * check balance
     */
    boolean balance(String s, String t) {
        if(s.length()!=t.length()) return false;

        int[] balance = new int[26];
        for(int i=0; i<s.length(); i++) {

            balance[s.charAt(i)-'a']++;
            balance[t.charAt(i)-'a']--;
        }

        for (int i : balance) {
            if(i!=0) return false;
        }
        return true;
    }
}
