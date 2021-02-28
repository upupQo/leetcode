// Given a string s, partition s such that every substring of the partition is a palindrome.
//
// Return the minimum cuts needed for a palindrome partitioning of s.
//
//  
// Example 1:
//
//
// Input: s = "aab"
// Output: 1
// Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
//
//
// Example 2:
//
//
// Input: s = "a"
// Output: 0
//
//
// Example 3:
//
//
// Input: s = "ab"
// Output: 1
//
//
//  
// Constraints:
//
//
// 	1 <= s.length <= 2000
// 	s consists of lower-case English letters only.
//
//


    //132.将一个字符串切割成一些子串，这些子串都是回文串，求最小分割数 eg "aab"-->{"aa","b"} cut=1
    class Solution {
    	private boolean[][] palindrome;
    	private int cut;
        public int minCut(String s) {
            if(s.length()<=1){
            	return 0;
            }
            fillDp(s);
            return cut(s);
        }
        private int cut(String s){
        	//dp[i]:把s[0,i)切割成回文的最少切割数
        	int[] dp=new int[s.length()+1];
        	dp[0]=-1;//s[0,0)="" 相当于多补了一刀，把它抵消掉skill
        	for(int i=1;i<=s.length();i++){
    			dp[i]=i-1;//[0,i)最多i-1次切割
        		for(int j=i-1;j>=0;j--){
        			if(palindrome[j][i-1]){
        				dp[i]=Math.min(dp[i], dp[j]+1);
        			}
        		}
        	}
        	return dp[s.length()];
        }
        //将s[start,n-1]切割成都是回文串的子串，cut代表[0,start-1]已经切割了的次数.这样暴搜会超时
        private void cutOriginal(int start,String s,int cut){
        	if(start==s.length()){
        		this.cut=Math.min(this.cut, cut-1);//notice,cut-1若上一次已经到了末尾，那么那一次切割是不需要的
        		return;
        	}
        	for(int i=start;i<s.length();i++){
        		if(palindrome[start][i]){
        			cutOriginal(i+1, s, cut+1);
        		}
        	}
        }
        //预处理s得dp数组，dp[i][j]代表s[i,j]是否是回文串
        private void fillDp(String s){
        	this.palindrome=new boolean[s.length()][s.length()];
            for(int i=0;i<s.length();i++){
            	//1.填奇回文
            	palindrome[i][i]=true;//只有一个字符,肯定是回文
            	int x=i-1,y=i+1;
            	while(x>=0 && y<s.length()){
            		if(s.charAt(x)!=s.charAt(y)){
            			palindrome[x][y]=false;
            		}else{
            			palindrome[x][y]=palindrome[x+1][y-1];
            		}
            		x--;
            		y++;
            	}
            	//2.填偶回文
            	x=i;y=i+1;
            	if(x<s.length()-1){
            		palindrome[x][y]=s.charAt(x)==s.charAt(y)?true:false;
            		x--;y++;
                	while(x>=0 && y<s.length()){
                		if(s.charAt(x)!=s.charAt(y)){
                			palindrome[x][y]=false;
                		}else{
                			palindrome[x][y]=palindrome[x+1][y-1];
                		}
                		x--;
                		y++;
                	}
            	}
            }
        }
    }
