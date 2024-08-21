

public class BinaryTrees {

    private static class Node{
        int data;
        Node left,right;
        Node(int d){
            data=d;
            left=right=null;
        }
    }

    /*
        You are given a binary tree, and you need to remove all the
         half nodes (which have only one child). Return the root node
         of the modified tree after removing all the half-nodes.
     */

    public Node RemoveHalfNodes(Node root) {
        if(root == null || (root.left == null && root.right == null)){
            return root;
        }
        if(root.right == null){
            return RemoveHalfNodes(root.left);
        }
        if(root.left == null){
            return RemoveHalfNodes(root.right);
        }
        root.left = RemoveHalfNodes(root.left);
        root.right = RemoveHalfNodes(root.right);
        return root;
    }

    /*
            Given a Binary Tree. Check for the Sum Tree for every node
            except the leaf node. Return true if it is a Sum Tree
            otherwise, return false. A SumTree is a Binary Tree where
            the value of a node is equal to the sum of the nodes present
            in its left subtree and right subtree. An empty tree is also
            a Sum Tree as the sum of an empty tree can be considered to
            be 0. A leaf node is also considered a Sum Tree.
     */

    private int helper1(Node root){     // part 1.
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return root.data;
        }
        else{
            int left = helper1(root.left);
            int right = helper1(root.right);
            if(right + left == root.data){
                return left + right + root.data;
            }
            return -1;
        }
    }

    boolean isSumTree(Node root) {      // part 2.
        return helper1(root) - root.data == root.data;
    }

    /*
            You are given an integer array nums with no duplicates. A maximum binary
            tree can be built recursively from nums using the following algorithm:
            1. Create a root node whose value is the maximum value in nums.
            2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
            3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.
            Return the maximum binary tree built from nums.
     */

    private int findMaxIdx(int[] nums, int start, int end){
        int maxIdx = start;
        for(int x = start; x < end; x++){
            if(nums[maxIdx] <= nums[x]){
                maxIdx = x;
            }
        }
        return maxIdx;
    }

    private Node helper(int[] nums, int start, int end){
        if(start >= end){
            return null;
        }
        else{
            int maxIdx = findMaxIdx(nums, start, end);
            Node root = new Node(nums[maxIdx]);
            root.left = helper(nums, start, maxIdx);
            root.right = helper(nums, maxIdx + 1, end);
            return root;
        }
    }

    public Node constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length);
    }
    
    
    


    public static void main(String[] args) {

    }

}
