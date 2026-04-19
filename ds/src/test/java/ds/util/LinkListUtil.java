package ds.util;

import ds.dto.SingleNode;

public class LinkListUtil {
    public static void traverse(SingleNode head) {
        SingleNode cur = head.next;
        System.out.print(head.value + " ");
        while (cur!=null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }
}
