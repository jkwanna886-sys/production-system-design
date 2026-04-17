package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.bouncycastle.util.Arrays;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
    @Test
    public void test() {
        String s = "()[]{}";

        Assert.assertEquals(true, valid(s));
    }

    boolean valid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(Arrays.contains(new char[]{'(','[','{'}, c))
                stack.push(c);
            else {
                if(stack.isEmpty()) return false;

                Character lastOpen = stack.pop();
                if(lastOpen=='('&& ')'!=c || lastOpen=='['&& ']'!=c || lastOpen=='{'&& '}'!=c)
                    return false;
            }
        }
        return true;
    }
}
