package ds;

import ds.dto.SingleNode;
import ds.util.LinkListUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {
    private SingleNode head1;
    private SingleNode head2;

    @BeforeEach
    public void init() {
        head1 = new SingleNode(1);
        SingleNode sec = new SingleNode(2);
        SingleNode third = new SingleNode(4);

        head1.next = sec;
        sec.next = third;

        head2 = new SingleNode(1);
        SingleNode sec2 = new SingleNode(3);
        SingleNode third2 = new SingleNode(4);
        head2.next = sec2;
        sec2.next=third2;
    }

    @Test
    public void test() {
        LinkListUtil.traverse(head1);
        System.out.println("****");
        LinkListUtil.traverse(head2);
        SingleNode merge = merge(head1, head2);
        System.out.println("****");
        LinkListUtil.traverse(merge);
    }

    SingleNode merge(SingleNode list1, SingleNode list2) {
        SingleNode dummy = new SingleNode(0);

        SingleNode curr=dummy; // rebuild
        while (list1!=null && list2!=null) {
            if(list1.value<=list2.value) {
                curr.next = list1;
                list1 = list1.next;
            }else {
                curr.next = list2;
                list2 = list2.next;
            }

            curr = curr.next;
        } // end: while

        if(list1!=null) { // remain list1
            curr.next = list1;
        }else {
            curr.next = list2;
        }

        return dummy.next;
    }
}
