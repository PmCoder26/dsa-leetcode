

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

    private int helper1(Node root){
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

    boolean isSumTree(Node root) {
        return helper1(root) - root.data == root.data;
    }


    public static void main(String[] args) {

    }

}
