// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
// Note:
//
//
// 	The same word in the dictionary may be reused multiple times in the segmentation.
// 	You may assume the dictionary does not contain duplicate words.
//
//
// Example 1:
//
//
// Input: s = "leetcode", wordDict = ["leet", "code"]
// Output: true
// Explanation: Return true because "leetcode" can be segmented as "leet code".
//
//
// Example 2:
//
//
// Input: s = "applepenapple", wordDict = ["apple", "pen"]
// Output: true
// Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//              Note that you are allowed to reuse a dictionary word.
//
//
// Example 3:
//
//
// Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output: false
//
//


//139. Word Break 子过程没法简化为比较方便的dp依赖，用记忆化搜索   
class Solution {
    	private int[][] memo;
        public boolean wordBreak(String s, List<String> wordDict) {
            if(s.length()==0){
            	return true;
            }
            //memo[i,j]==0,代表s[i,j]是否能被且分为wordDict中的单词还没计算过;memo[i,j]==1 代表可以;memo[i,j]==-1 代表不可以。默认为0
            this.memo=new int[s.length()][s.length()];
            return wordBreak(0, s.length()-1, s, wordDict);
        }
        //s[i,j]能否被break
        private boolean wordBreak(int i,int j,String s,List<String> wordDict){
        	if(i>j){
        		return true;
        	}
        	if(memo[i][j]!=0){
        		return memo[i][j]==1?true:false;
        	}
        	memo[i][j]=-1;//默认不能
        	for(int k=i;k<=j;k++){
        		if(wordDict.contains(s.substring(i, k+1)) && wordBreak(k+1, j, s, wordDict)==true){
        			memo[i][j]=1;
        			break;//可以提前终止了
        		}
        	}
        	return memo[i][j]==1?true:false;
        }
    }
