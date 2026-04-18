package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {
    @Test
    public void test() {
        int[] arr = new int[] {1,2,3,1};
        Assert.assertEquals(true, duplicate(arr));
    }

    boolean duplicate(int[] arr) {
        Set<Integer> seens = new HashSet<>();
        for (int e : arr) {
            if(seens.contains(e)) return true;

            seens.add(e);
        }

        return false;
    }
}
