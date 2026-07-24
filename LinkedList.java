what is Linked List?
- A linked list is a linear data structure where each element is a separate object, called a node, that contains a reference (link) to the next node in the sequence.

class ListNode{
    int val;
    ListNode next;

    ListNode(int x){
        val=x;
        next=null;
    }
}

public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


-------------------- fast and slow pointer related problems ----------

1. Find Middle of the Linked List
Link -https://leetcode.com/problems/middle-of-the-linked-list/description/

 
solution 
class Solution {
    public ListNode middleOfTheLinkedList(ListNode head) {
        
        ListNode fast=head, slow = head;
        // why fast!=null && fast.next!=null because we are moving fast pointer by 2 steps so we need to check 
        // if fast is not null and fast.next is not null to avoid null pointer exception
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

        // why fast!=null && fast.next!=null because we are moving fast pointer by 2 steps so we need to check 
        // if fast is not null and fast.next is not null to avoid null pointer exception
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
        // if fast is null, it means we need to remove the head node, so we return head.next
        if (fast == null) {
            return head.next;
        }

        // Move both fast and slow pointers until fast reaches the end of the list. At this point, slow will be just before the node to be removed.
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }

       slow.next = slow.next.next;

        return head;
    }
}
TC-O(n)  SC - O(1)

explanation of the above code -
1. We use two pointers, fast and slow, both initialized to the head of the linked list. The fast pointer is moved n steps ahead of the slow pointer. This creates a gap of n nodes between the two pointers.
2. If the fast pointer becomes null after moving n steps, it means we need to remove the head node. In this case, we return head.next.
3. If the fast pointer is not null, we move both pointers one step at a time until the fast pointer reaches the end of the list. At this point, the slow pointer will be just before the node that needs to be removed.
4. We then update the next pointer of the slow node to skip the nth node from the end, effectively removing it from the list. Finally, we return the head of the modified list.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

/*
Use the dummy node (or sentinel) technique in a Linked List (LL) when the head of your list is subject to change, 
 removal, or re-assignment. It acts as a stable placeholder before the actual head, eliminating the need to write separate, 
 clunky edge-case logic for the very first element. 
 */

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

explanation of the above code -
1. Create a dummy node to serve as the starting point of the merged list. This simplifies edge cases where the head of the merged list might change.
2. Use a pointer (node) to build the merged list by comparing the values of the current nodes in list1 and list2. Append the smaller node to the merged list and move the corresponding pointer forward.
3. After the main loop, if there are remaining nodes in either list1 or list2, append them to the merged list.  
4. Return dummyNode.next, which points to the head of the merged list, skipping the dummy node itself.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
TC - O(N)  SC - O(1)

explanation of the above code -
1. Check if the list is empty or has only one node. If so, return the head as there are no duplicates to remove.
2. Initialize two pointers: prev (starting at the head) and curr (starting at the second node).
3. Traverse the list using the curr pointer:        
    - If the value of curr is equal to the value of prev, it means we have found a duplicate. In this case, update prev.next to skip the curr node, 
      effectively removing it from the list. Move curr to the next node.
    - If the values are different, move both prev and curr forward by one node.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


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

Explanation of the above code -
1. Create a dummy node and point its next to the head of the list. This helps handle edge cases where the head itself might be removed.
2. Initialize two pointers: prev (starting at the dummy node) and curr (starting at the head of the list).
3. Traverse the list using the curr pointer:        
   - If the current node has a duplicate (i.e., curr.val == curr.next.val), store the duplicate value and move curr forward until all nodes with that value are skipped.
   - Update prev.next to point to curr, effectively removing the duplicates from the list.
   - If there are no duplicates, simply move both prev and curr forward by one node.    

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

9. Reverse Linked List
link - https://leetcode.com/problems/reverse-linked-list/description/ 
solution
class Solution {
    public ListNode reverseList(ListNode head) {
        
        ListNode prev=null; 
        ListNode curr=head;

        while(curr!=null){
            ListNode nextTemp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nextTemp;
        }

        return prev;
    }
}

TC - O(N)  SC - O(1)

explanation of the above code -
1. Initialize two pointers: prev (set to null) and curr (set to the head of the list).
2. Traverse the list using the curr pointer:    
   - Store the next node (curr.next) in a temporary variable (nextTemp).
   - Reverse the link by pointing curr.next to prev.
   - Move prev to curr and curr to nextTemp.    
3. Continue this process until curr becomes null, indicating the end of the list.
4. Return prev, which will be the new head of the reversed list.   

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

10. Reverse Linked List II
link - https://leetcode.com/problems/reverse-linked-list-ii/description/

solution
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dummy = new ListNode(0); // Create a dummy node to simplify edge cases
        dummy.next = head;

        ListNode leftPre = dummy; // initialize leftPre, it will point to the node just before the left position
        ListNode curr = head;

        // Move curr to the left position and leftPre to the node just before it
        for(int i=1;i<left;i++){
            leftPre=leftPre.next;
            curr=curr.next;
        }

        ListNode refNode = curr; // Store the reference to the node at the left position, which will become the tail of the reversed sublist

        ListNode preNode = null;

        /*
        Reverse the sublist from left to right using the standard linked list reversal technique.
        The loop runs (right - left + 1) times to reverse the nodes in the specified range. After the loop, preNode will point to the new head of the reversed sublist, 
        and curr will point to the node just after the right position.
         */
        for(int i=1;i<=(right-left+1);i++){
            ListNode nextNode = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr= nextNode;
        }

        leftPre.next = preNode;
        refNode.next = curr;


       return dummy.next;
                
    }
}

   
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

11. Design a LRU Cache
link - https://leetcode.com/problems/lru-cache/description/


solution

//Define a Doubly Linked List and using DLL to add and delete values in O(1) time.
class ListNode{

    int key; // why key is needed because when we remove the least recently used node from the cache, we need to know its key to remove it from the map as well.
    int val;
    ListNode prev;
    ListNode next;

    ListNode(int key,int val){
        this.key = key;
        this.val = val;
    }
}


class LRUCache {

    // using to get corresponding in O(1) time.
    Map<Integer, ListNode> map; 
    ListNode head;
    ListNode tail;
    int capacity;

    public LRUCache(int capacity) {

        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);

        head.next = tail;
        tail.prev = head;
        this.capacity = capacity; 

    }
    
    public int get(int key) {       
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key);
        remove(node);
        add(node);
        return node.val;
    }
    // The put method adds a new key-value pair to the cache. If the key already exists, it removes the old node from the doubly linked list and updates the value.
    // If the cache exceeds its capacity, it removes the least recently used node (the one just before the tail) from both the doubly linked list and the map.
    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode oldNode = map.get(key);
            remove(oldNode);
        }

        ListNode newNode = new ListNode(key,value);
        add(newNode);
        map.put(key, newNode);
        if(map.size()>capacity){
            ListNode prevNode = tail.prev;
            remove(prevNode);
            map.remove(prevNode.key);
        }

    }

    // Add a new node right after the head of the doubly linked list. This operation is O(1) because we have direct access to the head and the next node.

    public void add(ListNode node){

        ListNode nextNode = head.next;
        node.next = nextNode;
        nextNode.prev=node;
        node.prev=head;
        head.next=node;        
    }

    // Remove a node from the doubly linked list. This operation is O(1) because we have direct access to the node to be removed and its neighbors.

    public void remove(ListNode node){

        node.prev.next = node.next;
        node.next.prev = node.prev;

    }
}


Explanation 

why DLL is used in LRU cache?(DLL is used for adding and removing nodes in O(1) time complexity & maintaining the order of elements based on their usage)
    A Doubly Linked List (DLL) is used in an LRU (Least Recently Used) cache implementation to efficiently manage the order of elements based on their usage. 
    The DLL allows for O(1) time complexity for both adding and removing nodes, which is crucial for maintaining the LRU property. When an item is accessed or added, 
    it can be moved to the front of the list (indicating it was recently used), and when the cache exceeds its capacity, the least recently used item (at the end of the list) can be removed quickly.
    This combination of a DLL and a HashMap enables fast access, insertion, and deletion operations, making it ideal for implementing an LRU cache.

why map is used in LRU cache?(Hashmap is used for get operation in O(1) time complexity)
    A HashMap is used in an LRU (Least Recently Used) cache implementation to provide O(1) time complexity for accessing elements by their keys. The HashMap allows for quick lookups to determine 
    if a key exists in the cache and to retrieve the corresponding node in the doubly linked list.

why not singly linked list is used in LRU cache?
    A singly linked list is not used in an LRU (Least Recently Used) cache implementation because it does not allow for efficient removal of nodes from the middle of the list. 
    In a singly linked list, to remove a node, you need to traverse the list from the head to find the previous node, which results in O(n) time complexity. 
    In contrast, a doubly linked list allows for O(1) time complexity for both adding and removing nodes, as each node has pointers to both its previous and next nodes. 
    This efficiency is crucial for maintaining the LRU property, where nodes need to be frequently moved to the front of the list upon access and removed from the end when the cache exceeds its capacity.    

HEAD <-> TAIL
Map = {}
put(1,1) : Map={1:Node(1,1)}, HEAD <-> Node(1,1) <-> TAIL
put(2,2) : Map={1:Node(1,1), 2:Node(2,2)}, HEAD <-> Node(2,2) <-> Node(1,1) <-> TAIL
get(1)   : Map={1:Node(1,1), 2:Node(2,2)}, HEAD <-> Node(1,1) <-> Node(2,2) <-> TAIL  (1 is most recently used so it is moved to the front)
put(3,3) : Map={1:Node(1,1), 2  :Node(2,2), 3:Node(3,3)}, HEAD <-> Node(3,3) <-> Node(1,1) <-> Node(2,2) <-> TAIL (2 is least recently used so it is removed)
get(2)   : Map={1:Node(1,1), 3:Node(3,3)}, HEAD <-> Node(3,3) <-> Node(1,1) <-> TAIL (2 is not found)
put(4,4) : Map={1:Node(1,1), 3:Node(3,3), 4:Node(4,4)}, HEAD <-> Node(4,4) <-> Node(3,3) <-> Node(1,1) <-> TAIL (1 is least recently used so it is removed)
get(1)   : Map={3:Node(3,3), 4:Node(4,4)}, HEAD <-> Node(4,4) <-> Node(3,3) <-> TAIL (1 is not found)
get(3)   : Map={3:Node(3,3), 4:Node(4,4)}, HEAD <-> Node(3,3) <-> Node(4,4) <-> TAIL (3 is most recently used so it is moved to the front)
get(4)   : Map={3:Node(3,3), 4:Node(4,4)}, HEAD <-> Node(4,4) <-> Node(3,3) <-> TAIL (4 is most recently used so it is moved to the front)

TC - O(1)  SC - O(capacity)