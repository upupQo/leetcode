// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//
// 	push(x) -- Push element x onto stack.
// 	pop() -- Removes the element on top of the stack.
// 	top() -- Get the top element.
// 	getMin() -- Retrieve the minimum element in the stack.
//
//
//  
// Example 1:
//
//
// Input
// ["MinStack","push","push","push","getMin","pop","top","getMin"]
// [[],[-2],[0],[-3],[],[],[],[]]
//
// Output
// [null,null,null,null,-3,null,0,-2]
//
// Explanation
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin(); // return -3
// minStack.pop();
// minStack.top();    // return 0
// minStack.getMin(); // return -2
//
//
//  
// Constraints:
//
//
// 	Methods pop, top and getMin operations will always be called on non-empty stacks.
//
//


class MinStack {
    	private Stack<Integer> stackMain;//主栈,设主栈从栈底到栈顶标记为0到n,则辅助栈记录[0,i]的最小值,i∈[0,n]
    	private Stack<Integer> stackHelp;//辅助栈
    /** initialize your data structure here. */ 
        public MinStack() {
            stackMain=new Stack<Integer>();
            stackHelp=new Stack<Integer>();
        }
        public void push(int x) {
            stackMain.push(x);
            if(stackHelp.isEmpty() || x<=stackHelp.peek()){//相同时也得压，考虑到弹出时得同时弹出
            	stackHelp.push(x);
            }
        }
        public void pop() {
            if(stackMain.isEmpty()){
            	return;
            }
            int x=stackMain.pop();
            if(x==stackHelp.peek()){//key
            	stackHelp.pop();
            }
        }
        public int top() {
        	if(stackMain.isEmpty()){
        		return -1;
        	}
        	return stackMain.peek();
        }
        public int getMin() {
            if(stackHelp.isEmpty()){
            	return -1;
            }
            return stackHelp.peek();
        }
}
