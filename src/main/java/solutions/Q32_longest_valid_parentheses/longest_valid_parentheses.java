// Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
//
//  
// Example 1:
//
//
// Input: s = "(()"
// Output: 2
// Explanation: The longest valid parentheses substring is "()".
//
//
// Example 2:
//
//
// Input: s = ")()())"
// Output: 4
// Explanation: The longest valid parentheses substring is "()()".
//
//
// Example 3:
//
//
// Input: s = ""
// Output: 0
//
//
//  
// Constraints:
//
//
// 	0 <= s.length <= 3 * 104
// 	s[i] is '(', or ')'.
//
//


class Solution {
       public int longestValidParentheses(String s) {
        Stack<Integer> stack=new Stack<Integer>();//存贮左括号的索引
        int max=0;
        int start=0;//有效序列的开始，关键变量
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i)=='('){
        		stack.push(i);
        	}else{
        		if(stack.isEmpty()){//key.被多余的右括号阻断
        			start=i+1;
        			continue;
        		}else{
        			stack.pop();
        		}
        		max=stack.isEmpty()?Math.max(max,i-start+1):Math.max(max, i-stack.peek());//arr[start,i],arr[stack.peek()+1,i]
        	}
        }
        return max;
    }
}
