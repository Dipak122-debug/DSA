what is Linked List?

class ListNode{
    int val;
    ListNode next;

    ListNode(int x){
        val=x;
        next=null;
    }
}


 #--------------- fast and slow pointer related problems ----------

1. Find Middle of the Linked List
Link -https://leetcode.com/problems/middle-of-the-linked-list/description/

 
solution 
class Solution {
    public ListNode middleOfTheLinkedList(ListNode head) {
        
        ListNode fast=head, slow = head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }


        return slow;

    }
}

TC- o(N)  SC- O(1)


2. Find Middle of LL?

public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode fast=head, slow = head;

        while(fast!=null && fast.next!=null){

            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow) return true;
        }
        
        return false;
    }
}


3. Problem - Remove Nth Node From End of List

Link - https://leetcode.com/problems/remove-nth-node-from-end-of-list/

solution
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode fast= head, slow = head;

        for(int i=0;i<n;i++){
            fast = fast.next;
        }

        //example [1,2,3] and n=3 so fast is moving 3 times so fast = null - in that case always head needs to be removed 
        // thats why return head.next. Edge case
        if (fast == null) {
            return head.next;
        }

        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }

       slow.next = slow.next.next;

        return head;
    }
}
TC-O(n)  SC - O(1)



4. Linked List Cycle II

link - https://leetcode.com/problems/linked-list-cycle-ii/description/

explanation - https://claude.ai/chat/c9d098d3-fa87-429f-87c7-180caa830628

solution
public class Solution {
    public ListNode detectCycle(ListNode head) {

        ListNode fast=head,slow=head;

        while(fast!=null && fast.next!=null){

            fast=fast.next.next;
            slow=slow.next;

            if(slow==fast){
                slow=head;

                while(slow!=fast){
                    slow=slow.next;
                    fast=fast.next;
                }

                return slow;
            }
        }

        return null;
        
    }
}

TC - O(N)   SC - O(1)

5. Odd Even Linked List

link - https://leetcode.com/problems/odd-even-linked-list/description/

solution 
class Solution {
    public ListNode oddEvenList(ListNode head) {

        if(head==null) return head; 
        
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while(even!=null && even.next!=null){
            odd.next = even.next; // odd next value is solely depends on even next so boundary condition is based on even next
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }

        odd.next =evenHead; // odd is now pointing to last node of odd LL

        return head; // returning head as because we have just rearragned the nodes after head
    }
}
TC - O(N)  SC-O(1)
    

#-------- dummy Node technique -------------    

6. Merge Two Sorted Lists

link - https://leetcode.com/problems/merge-two-sorted-lists/description/

solution
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode dummyNode = new ListNode(0);
        ListNode node =   dummyNode;

        while(list1!=null && list2!=null){
            if(list1.val<list2.val){
                node.next=list1;
                list1=list1.next;
            }
            else{
                node.next=list2;
                list2=list2.next;
            }
            node=node.next;
        }

        if(list1!=null) node.next=list1;
        else node.next=list2;

        return dummyNode.next;
    }
}

TC - O(N)  SC -O(1)

7. Remove Duplicates from Sorted List 

link - https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/

solution

class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        if(head==null || head.next==null) return head;
        ListNode prev=head;
        ListNode curr=head.next;

        while(curr!=null){

            if(curr.val==prev.val){
                prev.next=curr.next;
                curr=curr.next;
            }
            else{
                prev=prev.next;
                curr=curr.next;
            }
        }


        return head;
        
    }
}

8. Remove Duplicates from Sorted List II

link - https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/

solution
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        if(head==null || head.next==null) return head;
        ListNode prev=head;
        ListNode curr=head.next;

        while(curr!=null){ // curr!=null because prev.next is depending on curr

            if(curr.val==prev.val){
                prev.next=curr.next;
                curr=curr.next;
            }
            else{
                prev=prev.next;
                curr=curr.next;
            }
        }


        return head;
        
    }
}

TC-O(N)   SC-O(1)

8. Remove Duplicates from Sorted List II
link - https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/

solution
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr=head;

        while(curr!=null){
            if(curr.next!=null && curr.val==curr.next.val){
                int dupVal=curr.val;

                while(curr!=null && curr.val==dupVal){
                    curr=curr.next;
                }
                prev.next=curr;
            }
            else{
                prev=prev.next;
                curr=curr.next;
            }
        }

        return dummy.next;
    }
}

TC-O(N)   SC-O(1)