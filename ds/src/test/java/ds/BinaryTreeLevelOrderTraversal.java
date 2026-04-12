package ds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
    MaximumDepthOfBinaryTree.TreeNode root;
    @BeforeEach
    public void init() {
        root = new MaximumDepthOfBinaryTree.TreeNode(3);
        root.left = new MaximumDepthOfBinaryTree.TreeNode(9);
        root.right = new MaximumDepthOfBinaryTree.TreeNode(20);
        root.right.left = new MaximumDepthOfBinaryTree.TreeNode(15);
        root.right.right = new MaximumDepthOfBinaryTree.TreeNode(7);
    }

    /**
     put root
     handle all current nodes(add into list)
     */
    @Test
    public void test() {
        Queue<MaximumDepthOfBinaryTree.TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList<>();
            for(int i=0; i< size; i++) {
                MaximumDepthOfBinaryTree.TreeNode node = queue.poll();

                list.add(node.value);

                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            } // end: for
            result.add(list);
        } // end: while

        System.out.println(result);
    }
}
