import java.util.HashSet;
import java.util.Stack;

public class LinkedLists {
    private static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            next = null;
        }
    }


    // Que.1:
        /*
            Given the heads of two singly linked-lists headA and headB,
             return the node at which the two lists intersect. If the two
             linked lists have no intersection at all, return null.
        */

    public static Node getIntersectionNode(Node headA, Node headB) {
        if(headA == headB){
            return headA;
        }
        else{
            int lenA = 0;
            int lenB = 0;
            Node a = headA;
            Node b = headB;
            while(a != null){
                lenA++;
                a = a.next;
            }
            while(b != null){
                lenB++;
                b = b.next;
            }
            int difference = Math.abs(lenA - lenB);
            a = headA;
            b = headB;
            if(lenA > lenB){
                for(int x=0; x<difference; x++){
                    a = a.next;
                }
            }
            else{
                for(int x=0; x<difference; x++){
                    b = b.next;
                }
            }
            while(a != null && b != null){
                if(a == b){
                    return a;
                }
                a = a.next;
                b = b.next;
            }
            return null;
        }
    }

    /*
        Given the head of a linked list, return the node where the cycle begins.
        If there is no cycle, return null. There is a cycle in a linked list if
        there is some node in the list that can be reached again by continuously
        following the next pointer. Internally, pos is used to denote the index
        of the node that tail's next pointer is connected to (0-indexed). It is
        -1 if there is no cycle. Note that pos is not passed as a parameter.
        Do not modify the linked list.
     */

    public Node detectCycle(Node head) {
        if(head == null || head.next == null){
            return null;
        }
        else{
            if(head.next != null){
                if(head.next.next == head){
                    return head;
                }
            }
            Node slow = head;
            Node fast = head;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast){
                    Node curr = head;
                    while(curr != fast){
                        fast = fast.next;
                        curr = curr.next;
                    }
                    return curr;
                }
            }

            return null;
        }
    }


    /*
        Given the head of a singly linked list and an integer k, split the linked list
        into k consecutive linked list parts. The length of each part should be as equal
        as possible: no two parts should have a size differing by more than one. This may
        lead to some parts being null. The parts should be in the order of occurrence in
        the input list, and parts occurring earlier should always have a size greater than
        or equal to parts occurring later. Return an array of the k parts.
     */

    public Node[] splitListToParts(Node head, int k) {
        Node[] result = new Node[k];

        if(head == null){
            return result;
        }
        else{
            int listSize = 0;
            Node curr = head;
            // calculating list size;
            while(curr != null){
                listSize++;
                curr = curr.next;
            }
            curr = head;
            // if the size of the linked list is less than or equal to 'k'.
            if(listSize <= k){
                int i=0;
                while(curr != null){
                    Node temp = curr.next;
                    curr.next = null;
                    result[i++] = curr;
                    curr = temp;
                }
                while(i < k){
                    result[i++] = null;
                }
            }
            // if the size of the linked list is greater.
            // hence we need to partition the list.
            else{
                Node tempPtr = curr;
                int rem = listSize % k;
                int partSize = listSize / k;
                int i = 0;
                // partitioning the list.
                while(i < k){
                    int j = 1;
                    while(j < partSize){
                        tempPtr = tempPtr.next;
                        j++;
                    }
                    if(rem > 0){
                        tempPtr = tempPtr.next;
                        rem--;
                    }
                    Node temp = tempPtr.next;
                    tempPtr.next = null;
                    result[i++] = curr;
                    curr = temp;
                    tempPtr = curr;
                }
            }
            return result;
        }
    }

    /*
            You are given the head of a singly linked-list. The list can be represented as:
            L0 → L1 → … → Ln - 1 → Ln

            Reorder the list to be on the following form:
            L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …

            You may not modify the dataues in the list's nodes. Only nodes themselves may be changed.
     */
    private Node getMid(Node head){                 // part 1.
        if(head == null){
            return head;
        }
        else{
            Node slow = head;
            Node fast = head.next;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    private Node reverse(Node head){                // part 2.
        if(head == null){
            return head;
        }
        else{
            Node prev = null;
            while(head != null){
                Node temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return prev;
        }
    }

    public void reorderList(Node head) {                // part 3.
        if(head == null || head.next == null){
            return;
        }
        else{
            Node curr = getMid(head);
            Node part2 = reverse(curr.next);
            curr.next = null;
            curr = head;
            Node currNext = curr;
            Node part2Next = part2;
            while(part2 != null){
                currNext = currNext.next;
                part2Next = part2.next;
                curr.next = part2;
                part2.next = currNext;
                part2 = part2Next;
                curr = currNext;
            }
            return;
        }
    }

    /*
            You are given the head of a linked list.
            Remove every node that has a node with a greater dataue anywhere to the right side of it.
            Return the head of the modified linked list.
     */

    private Node add(Node head, Node curr){     // part 1
        if(head == null){
            return curr;
        }
        else{
            curr.next = head;
            head = curr;
            return head;
        }
    }

    public Node removeNodes(Node head) {        // part 2
        if(head == null || head.next == null){
            return head;
        }
        else{
            Node ans = null;
            Stack<Node> st = new Stack();
            int max = Integer.MIN_VALUE;
            while(head != null){
                st.push(head);
                head = head.next;
            }
            while(!st.isEmpty()){
                Node curr = st.pop();
                if(curr.data >= max){
                    ans = add(ans, curr);
                    max = curr.data;
                }
                else{
                    continue;
                }
            }
            return ans;
        }
    }

    /*
            You are given two linked lists: list1 and list2 of sizes n and m respectively.
            Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
                Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
                Output: [10,1,13,1000000,1000001,1000002,5]
                Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place.
     */

    public Node mergeInBetween(Node list1, int a, int b, Node list2) {
        int a1 = a - 1;
        int b1 = b - 1;
        int x = 0;
        Node curr = list1;
        Node temp = null;
        while(x < a1) {
            curr = curr.next;
            x++;
        }
        temp = curr.next;
        curr.next = list2;
        while(x < b1) {
            temp = temp.next;
            x++;
        }
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = temp.next;
        temp.next = null;
        return list1;
    }

    public Node modifiedList(int[] nums, Node head) {
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) {
            hs.add(num);
        }
        Node curr = head;
        Node ansStart = null;
        Node ansNext = null;
        boolean start = true;
        while(curr != null) {
            Node temp = curr.next;
            curr.next = null;
            if(!hs.contains(curr.data)) {
                if(start) {
                    start = false;
                    ansStart = curr;
                    ansNext = ansStart;
                }
                else {
                    ansNext.next = curr;
                    ansNext = ansNext.next;
                }
            }
            curr = temp;
        }
        return ansStart;
    }

    public static void main(String[] args) {

    }

}
