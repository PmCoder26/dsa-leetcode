import java.math.BigInteger;
import java.util.*;

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

    /*
            Given the root of a binary tree, return the sum of every tree node's tilt. The tilt of a tree node is the
            absolute difference between the sum of all left subtree node values and all right subtree node values.
            If a node does not have a left child, then the sum of the left subtree node values is treated as 0. The
            rule is similar if the node does not have a right child.

            Input: root = [4,2,9,3,5,null,7]
            Output: 15
            Explanation:
            Tilt of node 3 : |0-0| = 0 (no children)
            Tilt of node 5 : |0-0| = 0 (no children)
            Tilt of node 7 : |0-0| = 0 (no children)
            Tilt of node 2 : |3-5| = 2 (left subtree is just left child, so sum is 3; right subtree is just right child, so sum is 5)
            Tilt of node 9 : |0-7| = 7 (no left child, so sum is 0; right subtree is just right child, so sum is 7)
            Tilt of node 4 : |(3+5+2)-(9+7)| = |10-16| = 6 (left subtree values are 3, 5, and 2, which sums to 10;
                                right subtree values are 9 and 7, which sums to 16)
            Sum of every tilt : 0 + 0 + 0 + 2 + 7 + 6 = 15
     */

    private class Info{
        public int netSum = 0;
    }

    private int helper(Node root, Info i){
        if(root == null){
            return 0;
        }
        else{
            int left = helper(root.left, i);
            int right = helper(root.right, i);
            i.netSum += Math.abs(left - right);
            return left + right + root.data;
        }
    }

    public int findTilt(Node root) {
        Info i = new Info();
        helper(root, i);
        return i.netSum;
    }
    

    /*
    Given the root node of a binary tree, your task is to create a string representation of the tree following a specific
    set of formatting rules. The representation should be based on a preorder traversal of the binary tree and must adhere
    to the following guidelines:
    Node Representation: Each node in the tree should be represented by its integer value.
    Parentheses for Children: If a node has at least one child (either left or right), its children should be represented
        inside parentheses.
    Specifically: If a node has a left child, the value of the left child should be enclosed in
        parentheses immediately following the node's value. If a node has a right child, the value of the right child should
        also be enclosed in parentheses. The parentheses for the right child should follow those of the left child.
    Omitting Empty Parentheses: Any empty parentheses pairs (i.e., ()) should be omitted from the final string
        representation of the tree, with one specific exception: when a node has a right child but no left child. In such cases,
        you must include an empty pair of parentheses to indicate the absence of the left child. This ensures that the one-to-one
        mapping between the string representation and the original binary tree structure is maintained.
     */

    public String tree2str(Node root) {
        if (root == null) {
            return "";
        } else {
            String left = tree2str(root.left);
            String right = tree2str(root.right);
            if (left.equals("") && !right.equals("")) {
                left = "()";
            } else if (!left.equals("")) {
                left = "(" + left + ")";
            }
            if (!right.equals("")) {
                right = "(" + right + ")";
            }
            return Integer.toString(root.data) + left + right;
        }
    }

    /*
         Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the
         node values in the path equals targetSum. Each path should be returned as a list of the node values, not node
         references.
         A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

         Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
         Output: [[5,4,11,2],[5,8,4,5]]
         Explanation: There are two paths whose sum equals targetSum:
             5 + 4 + 11 + 2 = 22
             5 + 8 + 4 + 5 = 22
     */
    private void helper(Node root, int target, int sum, List<Integer> list, List<List<Integer>> result){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            if(sum + root.data == target){
                List<Integer> temp = (List<Integer>) ((ArrayList<Integer>) list).clone();
                temp.add(root.data);
                result.add(temp);
            }
        }
        else{
            List<Integer> temp = (List<Integer>) ((ArrayList<Integer>) list).clone();
            temp.add(root.data);
            sum+= root.data;
            helper(root.left, target, sum, temp, result);
            helper(root.right, target, sum, temp, result);
        }
    }

    public List<List<Integer>> pathSum2(Node root, int targetSum) {
        if(root == null){
            return new ArrayList();
        }
        else{
            List<List<Integer>> result = new ArrayList();
            List<Integer> list = new ArrayList();
            helper(root, targetSum, 0, list, result);
            return result;
        }
    }

    /*
            Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
            (i.e., from left to right, level by level from leaf to root).

            Input: root = [3,9,20,null,null,15,7]
            Output: [[15,7],[9,20],[3]]
     */

    private List<List<Integer>> levelOrderBottom(Node root) {
        if(root == null){
            return new ArrayList();
        }
        else{
            Queue<Node> nodeQ = new LinkedList<>();
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();
            nodeQ.add(root);
            nodeQ.add(null);
            while(!nodeQ.isEmpty()){
                Node curr = nodeQ.remove();
                if(curr == null){
                    if(nodeQ.isEmpty()){
                        result.add(temp);
                        break;
                    }
                    else{
                        nodeQ.add(null);
                        result.add(temp);
                        temp = new ArrayList();
                    }
                }
                else{
                    temp.add(curr.data);
                    if(curr.left != null){
                        nodeQ.add(curr.left);
                    }
                    if(curr.right != null){
                        nodeQ.add(curr.right);
                    }
                }
            }
            Collections.reverse(result);
            return result;
        }
    }

    /*
            Given the root of a binary tree, flatten the tree into a "linked list":
            The "linked list" should use the same Node class where the right child pointer points to the next node
            in the list and the left child pointer is always null.
            The "linked list" should be in the same order as a pre-order traversal of the binary tree.

            Input: root = [1,2,5,3,4,null,6]
            Output: [1,null,2,null,3,null,4,null,5,null,6]
     */

    private void helper(Node root, LinkedList<Node> list){
        if(root != null){
            list.add(root);
            helper(root.left, list);
            helper(root.right, list);
            root.left = null;
            root.right = null;
        }
    }

    private void buildList(Node root, LinkedList<Node> list){
        for(int x = 0; x < list.size(); x++){
            root.right = list.get(x);
            root = root.right;
        }
    }

    public void flatten(Node root) {
        if(root == null || (root.left == null && root.right == null)){
            return;
        }
        else{
            LinkedList<Node> list = new LinkedList<>();
            helper(root, list);
            buildList(root, list);
            list = null;
        }
    }



    public static void main(String[] args) {

    }

}
