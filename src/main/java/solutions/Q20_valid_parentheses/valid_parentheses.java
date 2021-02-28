// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
// An input string is valid if:
//
//
// 	Open brackets must be closed by the same type of brackets.
// 	Open brackets must be closed in the correct order.
//
//
//  
// Example 1:
//
//
// Input: s = "()"
// Output: true
//
//
// Example 2:
//
//
// Input: s = "()[]{}"
// Output: true
//
//
// Example 3:
//
//
// Input: s = "(]"
// Output: false
//
//
// Example 4:
//
//
// Input: s = "([)]"
// Output: false
//
//
// Example 5:
//
//
// Input: s = "{[]}"
// Output: true
//
//
//  
// Constraints:
//
//
// 	1 <= s.length <= 104
// 	s consists of parentheses only '()[]{}'.
//
//


class Solution {
    public boolean isValid(String s) {
        if(s==null || (s.length()&1)==1){//长度为奇无法形成有效
        	return false;
        }
        Stack<Character> stack=new Stack<Character>();
        char ch;
        int i=0;
        while(i<s.length()){
        	ch=s.charAt(i);
        	if(ch=='(' || ch=='[' || ch=='{'){
        		stack.push(ch);
        	}else{
        		if(stack.isEmpty() || stack.peek()=='(' && ch!=')' || stack.peek()=='[' && ch!=']' || stack.peek()=='{' && ch!='}'){
        			return false;
        		}
        		stack.pop();
        	}
        	i++;
        }
        return stack.isEmpty();//notice
    }
}
