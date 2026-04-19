package ds;

import ds.dto.SingleNode;
import ds.util.LinkListUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
    private SingleNode head;

    @BeforeEach
    public void init() {
        head = new SingleNode(1);
        SingleNode sec = new SingleNode(2);
        SingleNode third = new SingleNode(3);
        SingleNode fourth = new SingleNode(4);
        SingleNode fifth = new SingleNode(5);

        head.next = sec;
        sec.next = third;
        third.next = fourth;
        fourth.next = fifth;
    }

    @Test
    public void test() {
        LinkListUtil.traverse(head);
        SingleNode reverse = reverse(head);
        System.out.println("-----");
        LinkListUtil.traverse(reverse);
    }

    SingleNode reverse(SingleNode head) {
        SingleNode pre = null;
        SingleNode cur = head;
        while (cur!=null) {
            SingleNode tempNext = cur.next; // store the rest list
            cur.next = pre; // backward instead of forward
            pre = cur; // move previous
            cur = tempNext; // go next operation
        }

        return pre;
    }
}
