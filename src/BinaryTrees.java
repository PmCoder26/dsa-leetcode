import java.util.*;
import java.util.Arrays;

public class BinaryTrees {

    private static class Node{
        int data;
        Node left,right;
        Node(int d){
            data=d;
            left=right=null;
        }
    }

    private static class ListNode {
        int data;
        ListNode next;
        public ListNode(int data) {
            this.data = data;
            this.next = null;
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
            the data of a node is equal to the sum of the nodes present
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
            1. Create a root node whose dataue is the maximum dataue in nums.
            2. Recursively build the left subtree on the subarray prefix to the left of the maximum dataue.
            3. Recursively build the right subtree on the subarray suffix to the right of the maximum dataue.
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
            absolute difference between the sum of all left subtree node dataues and all right subtree node dataues.
            If a node does not have a left child, then the sum of the left subtree node dataues is treated as 0. The
            rule is similar if the node does not have a right child.

            Input: root = [4,2,9,3,5,null,7]
            Output: 15
            Explanation:
            Tilt of node 3 : |0-0| = 0 (no children)
            Tilt of node 5 : |0-0| = 0 (no children)
            Tilt of node 7 : |0-0| = 0 (no children)
            Tilt of node 2 : |3-5| = 2 (left subtree is just left child, so sum is 3; right subtree is just right child, so sum is 5)
            Tilt of node 9 : |0-7| = 7 (no left child, so sum is 0; right subtree is just right child, so sum is 7)
            Tilt of node 4 : |(3+5+2)-(9+7)| = |10-16| = 6 (left subtree dataues are 3, 5, and 2, which sums to 10;
                                right subtree dataues are 9 and 7, which sums to 16)
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
            Node Representation: Each node in the tree should be represented by its integer dataue.
            Parentheses for Children: If a node has at least one child (either left or right), its children should be represented
                inside parentheses.
            Specifically: If a node has a left child, the dataue of the left child should be enclosed in
                parentheses immediately following the node's dataue. If a node has a right child, the dataue of the right child should
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
             node dataues in the path equals targetSum. Each path should be returned as a list of the node dataues, not node
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
            Given the root of a binary tree, return the bottom-up level order traversal of its nodes' dataues.
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
            in the list, and the left child pointer is always null.
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

    public void x(Node root) {
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

    /*
            Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
            original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

            Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
            Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     */

    private class Sum{
        int sum;
        Sum(int sum){
            this.sum = sum;
        }
    }

    private void helper(Node root, Sum s){
        if(root != null){
            helper(root.right, s);
            s.sum += root.data;
            root.data = s.sum;
            helper(root.left, s);
        }
    }

    private Node convertBST(Node root) {
        if(root == null){
            return root;
        }
        else{
            helper(root, new Sum(0));
            return root;
        }
    }

    /*
            You are given the root of a binary tree containing digits from 0 to 9 only.
            Each root-to-leaf path in the tree represents a number.
            For example, root-to-leaf path 1 -> 2 -> 3 represents the number 123.
            Return the total sum of all root-to-leaf numbers.
            Test cases are generated so that the answer will fit in a 32-bit integer.

            Input: root = [1,2,3]
            Output: 25
            Explanation:
                The root-to-leaf path 1->2 represents the number 12.
                The root-to-leaf path 1->3 represents the number 13.
                Therefore, sum = 12 + 13 = 25
     */

    static class Info2 {
        int data;
        public Info2(int data) {
            this.data = data;
        }
    }

    private static void helper(Node root, int sum, Info2 info) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            info.data += sum * 10 + root.data;
            return;
        }
        helper(root.left, sum * 10 + root.data, info);
        helper(root.right, sum * 10 + root.data, info);
        return;
    }

    public int sumNumbers(Node root) {
        Info2 info = new Info2(0);
        helper(root, 0, info);
        return info.data;
    }

    /*
            Given the root of a binary tree, return the zigzag level order traversal of its nodes' dataues.
            (i.e., from left to right, then right to left for the next level and alternate between).

            Example 1:
                Input: root = [3,9,20,null,null,15,7]
                Output: [[3],[20,9],[15,7]]
            Example 2:
                Input: root = [1]
                Output: [[1]]
            Example 3:
                Input: root = []
                Output: []
     */

    public List<List<Integer>> zigzagLevelOrder(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }
        int flag = 0;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()) {
            Node curr = q.remove();
            if(curr == null) {
                if(flag == 0) {
                    flag = 1;
                }
                else {
                    flag = 0;
                    Collections.reverse(temp);
                }
                if(q.isEmpty()) {
                    ans.add(temp);
                    break;
                }
                ans.add(temp);
                temp = new ArrayList<>();
                q.add(null);
            }
            else {
                temp.add(curr.data);
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
        return ans;
    }

    /*
            Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
            and inorder is the inorder traversal of the same tree, construct and return the binary tree.

            Example 1:
                Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
                Output: [3,9,20,null,null,15,7]

            Example 2:
                Input: preorder = [-1], inorder = [-1]
                Output: [-1]

     */

    private int childFinder(HashMap<Integer, Integer> pre, List<Integer> inord, int start, int end) {
        int idx = Integer.MAX_dataUE;
        int minIndex = -1;
        for (int x = start; x <= end; x++) {
            int preIndex = pre.get(inord.get(x));
            if (idx > preIndex) {
                idx = preIndex;
                minIndex = x;
            }
        }
        return inord.get(minIndex);
    }

    private Node helper(HashMap<Integer, Integer> pre, List<Integer> inord, int start, int end) {
        if(start == end) {
            return new Node(inord.get(start));
        }
        if(start > end) {
            return null;
        }
        Node newNode = new Node(childFinder(pre, inord, start, end));
        int rootIdx = inord.indexOf(newNode.data);
        newNode.left = helper(pre, inord, start, rootIdx - 1);
        newNode.right = helper(pre, inord, rootIdx + 1, end);
        return newNode;
    }

    public Node buildTree(int[] preorder, int[] inorder) {
        if(preorder[0] == -1) {
            return new Node(-1);
        }
        HashMap<Integer, Integer> pre = new HashMap<>();
        List<Integer> inord = Arrays.stream(inorder).boxed().toList();
        for(int x = 0; x < preorder.length; x++) {
            pre.put(preorder[x], x);
        }
        Node root = new Node(preorder[0]);
        int idx = inord.indexOf(preorder[0]);
        root.left = helper(pre, inord, 0, idx - 1);
        root.right = helper(pre, inord, idx + 1, inord.size() - 1);
        return root;
    }

    /*
            You are given a perfect binary tree where all leaves are on the same level,
            and every parent has two children. The binary tree has the following definition:
            Populate each next pointer to point to its next right node. If there is no next right node,
            the next pointer should be set to NULL.
            Initially, all next pointers are set to NULL.

            Input: root = [1,2,3,4,5,6,7]
            Output: [1,#,2,3,#,4,5,6,7,#]
            Explanation:
                Given the above perfect binary tree (Figure A), your function should populate each next
                pointer to point to its next right node, just like in Figure B. The serialized output is
                in level order as connected by the next pointers, with '#' signifying the end of each level.
     */

    private static class Node2 extends Node {
        Node2 next;
        public Node2(Integer data) {
            super(data);
            this.next = null;
        }
    }

    public Node2 connect(Node2 root) {
        if(root == null || (root.left == null)) {
            return root;
        }
        else {
            Queue<Node2> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()) {
                Node2 curr = q.remove();
                if(curr == null) {
                    if(q.isEmpty()) {
                        break;
                    }
                    else {
                        q.add(null);
                    }
                }
                else {
                    // as every node can have zero or two childs.
                    if(curr.left != null) {
                        q.add((Node2) curr.left);
                        q.add((Node2) curr.right);
                    }
                    curr.next = q.peek();
                }
            }
            return root;
        }
    }

    /*
            Given the root of a binary tree, return the leftmost dataue in the last row of the tree.

            Input: root = [2,1,3]
            Output: 1

            Input: root = [1,2,3,4,null,5,6,null,null,7]
            Output: 7
     */

    private static class Info3 extends Info2 {
        int level;
        int dist;
        public Info3() {
            super(0);
            level = 0;
            dist = 0;
        }
    }

    private void helper(Node root, int level, int dist, Info3 info, boolean isLeft) {
        if(root == null) return;
        if(info.level < level) {
            info.level = level;
            info.data = root.data;
            info.dist = dist;
        }
        else if(info.level == level) {
            if(info.dist > dist && isLeft) {       // isLeft -> for second last test case.
                info.dist = dist;
                info.data = root.data;
            }
        }
        helper(root.left, level + 1, dist - 1, info, isLeft);
        helper(root.right, level + 1, dist + 1, info, isLeft);
    }

    public int findBottomLeftdataue(Node root) {
        if(root.left == null && root.right == null) {
            return root.data;
        }
        else {
            Info3 info = new Info3();
            info.data = root.data;
            helper(root.left, 1, -1, info, true);
            helper(root.right, 1, 1, info, false);
            return info.data;
        }
    }

    /*
            Given the root of a binary tree and two integers data and depth, add a row of nodes with dataue data at the given depth depth.
            Note that the root node is at depth 1.
            The adding rule is:
                Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with
                dataue data as cur's left subtree root and right subtree root.

                cur's original left subtree should be the left subtree of the new left subtree root.

                cur's original right subtree should be the right subtree of the new right subtree root.

                If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with dataue data as the
                new root of the whole original tree, and the original tree is the new root's left subtree.

            Example 1:
                Input: root = [4,2,6,3,1,5], data = 1, depth = 2
                Output: [4,1,1,2,null,null,6,3,1,5]

            Example 2:
                Input: root = [4,2,null,3,1], data = 1, depth = 3
                Output: [4,2,null,1,1,3,null,null,1]
     */

    public Node addOneRow(Node root, int data, int depth) {
        if(root == null) {
            return new Node(data);
        }
        if(depth == 1) {
            Node newNode = new Node(data);
            newNode.left = root;
            return newNode;
        }
        else {
            Queue<Node> q = new LinkedList<>();
            int currLev = 1;
            q.add(root);
            q.add(null);
            while(currLev < depth - 1) {
                Node curr = q.remove();
                if(curr == null) {
                    if(q.isEmpty()) {
                        break;
                    }
                    else {
                        q.add(null);
                        currLev++;
                    }
                }
                else {
                    if(curr.left != null) {
                        q.add(curr.left);
                    }
                    if(curr.right != null) {
                        q.add(curr.right);
                    }
                }
            }
            while(q.peek() != null){
                Node curr = q.remove();
                Node left = curr.left;
                Node right = curr.right;
                curr.left = new Node(data);
                curr.right = new Node(data);
                curr.left.left = left;
                curr.right.right = right;
            }
            return root;
        }
    }

    /*
            Given the root of a binary search tree and the lowest and highest boundaries as low and high,
            trim the tree so that all its elements lie in [low, high]. Trimming the tree should not change the
            relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain
            a descendant). It can be proven that there is a unique answer.
            Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
            node's descendant should remain a descendant). It can be proven that there is a unique answer.

            Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
            
            Example 1:
                Input: root = [1,0,2], low = 1, high = 2
                Output: [1,null,2]
            Example 2:
                Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
                Output: [3,2,null,1]
     */

    private Node inOrderSucc(Node root, Node sub) {
        if(root == null) return sub;
        root.left = inOrderSucc(root.left, sub);
        return root;
    }

    private Node deleteNode(Node root) {
        if(root == null) return root;
        else {
            Node left = root.left;
            Node right = root.right;
            if(left == null) {
                return right;
            }
            if(right == null) {
                return left;
            }
            left.right = inOrderSucc(right, left.right);
            return left;
        }
    }

    public Node trimBST(Node root, int low, int high) {
        if(root == null) return root;
        while(root != null && (root.data < low || root.data > high)) {
            root = deleteNode(root);
        }
        if(root == null) return root;
        if(root.left != null) root.left = trimBST(root.left, low, high);
        if(root.right != null) root.right = trimBST(root.right, low, high);
        return root;
    }

    /*
            Given a binary tree root and a linked list with head as the first node.
            Return True if all the elements in the linked list starting from the head
            correspond to some downward path connected in the binary tree otherwise return False.
            In this context downward path means a path that starts at some node and goes downwards.

            Example 1:
                Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
                Output: true
                Explanation: Nodes in blue form a subpath in the binary Tree.
            Example 2:
                Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
                Output: true
     */

    private boolean exists(ListNode head, Node root) {
        if(root == null && head == null) return true;
        if(root == null) return false;
        if(head == null) return true;
        if(root.data == head.data) {
            return exists(head.next, root.left) || exists(head.next, root.right);
        }
        return false;
    }

    public boolean isSubPath(ListNode head, Node root) {
        if(root == null && head == null) return true;
        if(root == null || head == null) return false;
        else {
            if(head.data == root.data) {
                if(exists(head.next, root.left) || exists(head.next, root.right)) return true;
            }
            boolean left = isSubPath(head, root.left);
            boolean right = isSubPath(head, root.right);
            return left || right;
        }
    }

    /*
            Given the root of a binary tree, return the same tree where every subtree (of the given tree)
            not containing a 1 has been removed.
            A subtree of a node node is node plus every node that is a descendant of node.

            Example 1:
                Input: root = [1,null,0,0,1]
                Output: [1,null,0,null,1]
                Explanation:
                    Only the red nodes satisfy the property "every subtree not containing a 1".
                    The diagram on the right represents the answer.
            Example 2:
                Input: root = [1,0,1,0,0,0,1]
                Output: [1,null,1,null,1]
     */

    private boolean helper(Node root) {
        if(root == null) return false;
        else {
            boolean left = helper(root.left);
            if(!left) root.left = null;
            boolean right = helper(root.right);
            if(!right) root.right = null;
            return root.data == 1 || left || right;
        }
    }

    public Node pruneTree(Node root) {
        if(helper(root)) return root;
        return null;
    }

    public static void main(String[] args) {}

}
