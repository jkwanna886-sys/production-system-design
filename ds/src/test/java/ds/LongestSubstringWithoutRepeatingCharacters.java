package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/description/">longest-substring-without-repeating-characters</a>
 */
public class LongestSubstringWithoutRepeatingCharacters {
    @Test
    public void test() {
        String s = "abcabcbb";

        Assert.assertEquals(3, longest(s));
    }

    int longest(String s) {
        int maxLen = 0;
        int left = 0;
        Set<Character> seen = new HashSet<>();
        for(int right=0; right<s.length(); right++) {

            char c = s.charAt(right);
            while (seen.contains(c)) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(c);

            maxLen = Math.max(maxLen, right-left+1);
        }

        return maxLen;
    }
}
