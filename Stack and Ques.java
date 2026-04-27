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



2. Valid Parentheses
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

3. Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
link - https://leetcode.com/problems/min-stack/description/ 

approach 1 
explanation - We can use two stacks, one to store the actual elements of the stack and another to keep track of the minimum elements. Whenever we push a new element onto the main stack, we compare it with the current minimum (the top of the min stack). 
If the new element is smaller than or equal to the current minimum, we also push it onto the min stack. When popping an element from the main stack, if that element is the same as the top of the min stack, we pop from the min stack as well. This way, the top of the min stack always holds the minimum element of the main stack.

Solution

class MinStack {

    Stack<Integer> st;
    Stack<Integer> minSt;
    public MinStack() {
        st = new Stack<>();
        minSt = new Stack<>();
    }
    
    public void push(int x) {
        st.push(x);
        if(minSt.isEmpty() || x <= minSt.peek()){
            minSt.push(x);
        }
    }
    
    public void pop() {
        int top = st.pop();
        if(top == minSt.peek()){
            minSt.pop();
        }
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minSt.peek();
    }
}

approach 2 - more optimized solution with O(1) space for min stack
explanation - Instead of using a separate stack to keep track of the minimum elements, we can encode the minimum information directly into the main stack. When we push a new element that is smaller than the current minimum, we push a special value onto the stack that encodes both the new minimum and the previous minimum. This way, we can retrieve the minimum in constant time without needing extra space for a separate min stack.
push(5):  stack=[5],          min=5
push(3):  stack=[5, 1],       min=3   (encoded = 2*3-5 = 1)
push(7):  stack=[5, 1, 7],    min=3   (7 > min, store as-is)
top()  :  peek=7 ≥ min → return 7  ✓
pop()  :  x=7 ≥ min → min stays 3
top()  :  peek=1 < min → return min=3  ✓
pop()  :  x=1 < min → min = 2*3-1 = 5  ✓  (recovered!)
getMin():  → 5  ✓

Solution 

class MinStack {

    Stack<Long> st;

    Long min;

    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int val) {
    
        long x = val;
        if(st.isEmpty()){
            st.push(x);
            min=x;
        }
        else if(x<min){
            st.push(2*x-min);
            min=x;
        }
        else{
            st.push(x);
        }
    }
    
    public void pop() {
        long x=st.pop();
        if(x<min){
            min=2*min-x;
        }
    }
    
    public int top() {
        
        long x = st.peek();
        if(x < min){
            return min.intValue();
        }
        else{
            return st.peek().intValue();
        }
    }
    
    public int getMin() {
        return min.intValue();
    }
}

What is a Monotonic Stack?
A monotonic stack is just a normal stack with one rule:

 -Elements in the stack are always maintained in a strictly increasing or decreasing order.
 -When a new element violates that order, you pop until the order is restored, then push.






 