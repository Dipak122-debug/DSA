1. Design a queue using stacks.
2. Implement the following operations of a queue using stacks:
   - push(x) -- Push element x to the back of queue.
   - pop() -- Removes the element from in front of queue.
   - peek() -- Get the front element.
   - empty() -- Return whether the queue is empty.

link - https://leetcode.com/problems/implement-queue-using-stacks/

class MyQueue {

    Stack<Integer> s1;
    Stack<Integer> s2;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    public int peek() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

explanation -
1. We use two stacks, s1 and s2, to implement the queue.
2. The push operation is straightforward; we simply push the element onto stack s1.
3. For the pop and peek operations, we check if stack s2 is empty. If it is empty, we transfer all elements from stack s1 to stack s2. This reversal of elements allows us 
to access the front of the queue (the bottom of stack s1) at the top of stack s2.
4. After ensuring that stack s2 has the elements in the correct order, we can perform the pop and peek operations on stack s2.  



2, Valid Parentheses
Question - https://leetcode.com/problems/valid-parentheses/description/

solution
class Solution {
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for(char ch : s.toCharArray()){

            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
            }else{
                if(st.isEmpty()) return false;
                char top = st.pop();
                if((ch==')' && top!='(') || (ch=='}' && top!='{') || (ch==']' && top!='[')){
                    return false;
                }
            }
        }

        return st.isEmpty();
    }

TC - O(n) 
SC - O(n)