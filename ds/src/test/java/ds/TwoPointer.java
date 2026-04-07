package ds;

import org.junit.jupiter.api.Test;

public class TwoPointer {
    @Test
    public void testFindTwoNumberSumToTarget() {
        // given a sorted array.
        int[] arr = new int[]{2,3,5,6,9,11,13,15};
        int target = 16;

//        int[] index = find(arr, target);
//        System.out.println(index[0] + ":" + index[1]);
    }


    @Test
    public void testDetectCycle() {
        int[] array = new int[]{5, 5, 6, 7, 7, 8};

//        int len = hasCycle(array);
//        for (int i = 0; i < len; i++) {
//            System.out.println(array[i]);
//        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // move 1 step
            fast = fast.next.next;    // move 2 steps

            if (slow == fast) {
                return true; // cycle detected
            }
        }

        return false; // no cycle
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // only count alphanumeric
    // abc
    @Test
    public void testValidPalindrome() {
        String s = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome(s);
        System.out.println(palindrome);
    }

    private boolean isPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;
        while (left<right) {
            // skip non-alphanumeric
            while(!alphanumeric(input.charAt(left))) left++;
            while(!alphanumeric(input.charAt(right))) right--;

            if(Character.toLowerCase(input.charAt(left))!=Character.toLowerCase(input.charAt(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    boolean alphanumeric(char c) {
        return ((c>='a' && c<='z')
        || (c>='A' && c<='Z')
        || (c>='0' && c<='9'));
    }

}
