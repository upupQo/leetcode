// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//  
// Example 1:
// Input: n = 3
// Output: ["((()))","(()())","(())()","()(())","()()()"]
// Example 2:
// Input: n = 1
// Output: ["()"]
//
//  
// Constraints:
//
//
// 	1 <= n <= 8
//
//


class Solution {
    	private int totalStep;
    	private List<String> parenthesis;
        public List<String> generateParenthesis(int n) {
            parenthesis=new ArrayList<String>();
            if(n<=0){
            	return parenthesis;
            }
            this.totalStep=2*n;//一共要做2n步决策,每步可以选择左括号或右括号
            StringBuilder sb=new StringBuilder();
            generateParenthesis(sb, 0, 0, 0);
            return parenthesis;
        }
        private void generateParenthesis(StringBuilder sb,int stepCurrent,int leftCount,int rightCount){
        	//剪枝
        	if(rightCount>leftCount || leftCount>totalStep/2){//一旦右括号个数大于左括号个数 || 左括号个数大于一半，则无法生成一个有效序列
        		return;
        	}
        	//判断递归结束条件
        	if(stepCurrent==totalStep){
        		parenthesis.add(sb.toString());
        		return;
        	}
        	sb.append('(');
        	generateParenthesis(sb, stepCurrent+1, leftCount+1, rightCount);
        	sb.deleteCharAt(stepCurrent);
        	sb.append(')');
        	generateParenthesis(sb, stepCurrent+1, leftCount, rightCount+1);
        	sb.deleteCharAt(stepCurrent);
        }
    }
