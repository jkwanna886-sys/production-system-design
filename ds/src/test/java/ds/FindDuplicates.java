package ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * Example 3:
 *
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 *
 */
public class FindDuplicates {

    /**
     * group->check count>1
     */
    @Test
    public void stream() {
        List<Integer> list = List.of(3, 1, 3, 4, 2);

        /**
         * 3->2
         * 1->1
         */

        Map<Integer, Long> countMap = list.stream()
                .collect(Collectors.groupingBy(k -> k, Collectors.counting()));

        List<Map.Entry<Integer, Long>> result = countMap
                .entrySet()
                .stream()
                .filter(e->e.getValue()>1).collect(Collectors.toList());

        System.out.println(result);
    }

    /**
     * 1->n with size of "n+1"
     */
    @Test
    public void nonStream() {
        int[] arr = new int[] {1,3,4,2,2};

        // fast, slow: fast finally catch slow.
        // slow=arr[slow]
        // 1->3->2->4->2->4->2

        int slow=0;
        int fast=0;
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(slow!=fast);

        /*
        x:0->entry
        y:entry->meeting
        slow:x+y
        fast:2(x+y)=x+kC > 2x+2y=x+kC+y -> x=kC-y
        meaning: one at start point, another at meeting point, they will meet at entry point(duplicate)
        with same speed, same direction.
        * */
        slow =0;
        while (slow!=fast) {
            slow = arr[slow];
            fast = arr[fast];
        }

        Assert.assertEquals(2, slow);
    }
}
