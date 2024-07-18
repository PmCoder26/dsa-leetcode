
public class LinkedLists {
    private static class ListNode{
        int data;
        ListNode next;
        public ListNode(int data){
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

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == headB){
            return headA;
        }
        else{
            int lenA = 0;
            int lenB = 0;
            ListNode a = headA;
            ListNode b = headB;
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

    public static void main(String[] args) {

    }

}
