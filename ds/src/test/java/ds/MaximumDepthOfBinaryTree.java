package ds;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {
    TreeNode root;
    @BeforeEach
    public void init() {
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }
    @Test
    public void test() {

        int deepResult = traverse(root);
        Assert.assertEquals(deepResult, 3);
    }

    /*
     put parent node into queue
     handle all node of current level
     increase height
     put children nodes into queue
     */
    int queue(TreeNode root) {
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();

                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            depth++;
        }

        return depth;
    }

    /*
    function: calculate the depth of subtree.
    (knew)depth of left subtree
    (knew)depth of right subtree
    max
    */
    int traverse(TreeNode root) {
        if(root==null) return 0;

        int depthLeft = traverse(root.left);
        int depthRight = traverse(root.right);
        return Math.max(depthLeft, depthRight) + 1;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
