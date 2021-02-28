// Given a string s, return the longest palindromic substring in s.
//
//  
// Example 1:
//
//
// Input: s = "babad"
// Output: "bab"
// Note: "aba" is also a valid answer.
//
//
// Example 2:
//
//
// Input: s = "cbbd"
// Output: "bb"
//
//
// Example 3:
//
//
// Input: s = "a"
// Output: "a"
//
//
// Example 4:
//
//
// Input: s = "ac"
// Output: "a"
//
//
//  
// Constraints:
//
//
// 	1 <= s.length <= 1000
// 	s consist of only digits and English letters (lower-case and/or upper-case),
//


class Solution {
    public String longestPalindrome(String s) {
        if(s.length()==0){
        	return s;
        }
        //1.预处理s. abc->#a#b#c#.统一处理奇偶回文变为奇回文，n个字符->2n+1个字符
        char[] str=new char[s.length()*2+1];
        for(int i=0;i<s.length();i++){
        	str[2*i]='#';
        	str[2*i+1]=s.charAt(i);
        }
        str[2*s.length()]='#';
        //2.两个关键变量,
        int index=-1,right=-1;
        int resIndex=0;//目前得到最长回文串的回文中心
        int[] lengths=new int[str.length];//length[i]记录以字符sb[i]为回文中心的回文半径(不含中心点)#a#b#b#a#==4
        for(int i=0;i<str.length;i++){
        	int dis;
        	//1.如果当前位置超过最远回文距离--原始的往两边扩
        	if(i>right){
        		dis=0;//当前字符可以往外拓的距离
        		while(i-dis>=0 && i+dis<str.length && str[i-dis]==str[i+dis]){
        			dis++;
        		}
        		dis--;
        	}else{
        		int j=2*index-i;//i关于index的对称位置j:i-index==index-j;
        		dis=j-Math.max(index-lengths[index], j-lengths[j]);//key,三种情况分析后得到的简化表达式
        		while(i-dis>=0 && i+dis<str.length && str[i-dis]==str[i+dis]){
        			dis++;
        		}
        		dis--;
        	}
    		lengths[i]=dis;
    		if(i+dis>right){
    			right=i+dis;
    			index=i;
    		}
        	//更新返回位置
        	if(dis>lengths[resIndex]){
        		resIndex=i;
        	}
        }
        char[] res=new char[lengths[resIndex]];//2*n+1=2*radius+1==>元素个数n==radius.注意这个radius没有包含中心点
        index=0;//变量复用
        for(int i=resIndex-lengths[resIndex]+1;i<=resIndex+lengths[resIndex]-1;i+=2){
        	res[index++]=str[i];
        }
        return new String(res);
    }
}
