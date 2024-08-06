
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

    /*
        Given the head of a linked list, return the node where the cycle begins.
        If there is no cycle, return null. There is a cycle in a linked list if
        there is some node in the list that can be reached again by continuously
        following the next pointer. Internally, pos is used to denote the index
        of the node that tail's next pointer is connected to (0-indexed). It is
        -1 if there is no cycle. Note that pos is not passed as a parameter.
        Do not modify the linked list.
     */

    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        else{
            if(head.next != null){
                if(head.next.next == head){
                    return head;
                }
            }
            ListNode slow = head;
            ListNode fast = head;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast){
                    ListNode curr = head;
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

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];

        if(head == null){
            return result;
        }
        else{
            int listSize = 0;
            ListNode curr = head;
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
                    ListNode temp = curr.next;
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
                ListNode tempPtr = curr;
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
                    ListNode temp = tempPtr.next;
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

            You may not modify the values in the list's nodes. Only nodes themselves may be changed.
     */
    private ListNode getMid(ListNode head){                 // part 1.
        if(head == null){
            return head;
        }
        else{
            ListNode slow = head;
            ListNode fast = head.next;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    private ListNode reverse(ListNode head){                // part 2.
        if(head == null){
            return head;
        }
        else{
            ListNode prev = null;
            while(head != null){
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return prev;
        }
    }

    public void reorderList(ListNode head) {                // part 3.
        if(head == null || head.next == null){
            return;
        }
        else{
            ListNode curr = getMid(head);
            ListNode part2 = reverse(curr.next);
            curr.next = null;
            curr = head;
            ListNode currNext = curr;
            ListNode part2Next = part2;
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

    public static void main(String[] args) {

    }

}
