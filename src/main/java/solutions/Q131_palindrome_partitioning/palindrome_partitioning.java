// Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
//
// A palindrome string is a string that reads the same backward as forward.
//
//  
// Example 1:
// Input: s = "aab"
// Output: [["a","a","b"],["aa","b"]]
// Example 2:
// Input: s = "a"
// Output: [["a"]]
//
//  
// Constraints:
//
//
// 	1 <= s.length <= 16
// 	s contains only lowercase English letters.
//
//


//131.求所有的回文划分方式--求具体解 递归回溯搜索
class Solution {
    	private List<List<String>> res;
        public List<List<String>> partition(String s) {
            this.res=new ArrayList<List<String>>();
            List<String> currentList=new ArrayList<String>();
            partition(0, s, currentList);
            return this.res;
        }
        //将s[start,n-1]变成回文串
        private void partition(int start,String s,List<String> currentList){
        	if(start==s.length()){
        		res.add(new ArrayList<String>(currentList));
        		return;
        	}
        	for(int i=start;i<s.length();i++){//枚举所有的可能尾
        		if(isPalindrome(start, i, s)){
        			currentList.add(s.substring(start, i+1));
        			partition(i+1, s, currentList);//递归
        			currentList.remove(currentList.size()-1);//回溯
        		}
        	}
        }
        //验证s[start,end]是否是回文串
        private boolean isPalindrome(int start,int end,String s){
        	while(start<end){
        		if(s.charAt(start++)!=s.charAt(end--)){
        			return false;
        		}
        	}
        	return true;
        }
    }
