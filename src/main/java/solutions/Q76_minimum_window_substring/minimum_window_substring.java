// Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no such window in s that covers all characters in t, return the empty string "".
//
// Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
//
//  
// Example 1:
// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
// Example 2:
// Input: s = "a", t = "a"
// Output: "a"
//
//  
// Constraints:
//
//
// 	1 <= s.length, t.length <= 105
// 	s and t consist of English letters.
//
//
//  
// Follow up: Could you find an algorithm that runs in O(n) time?


class Solution {
    //76. Minimum Window Substring*,包含t中所有字符的最短子串。假设输入范围为ascii
    public String minWindow(String s, String t) {
    	if(s.length()<t.length()){
    		return "";
    	}
    	//1.设frequence[i]=k,若k<0,则该字符还缺abs(k)个;若k==0,刚好够填坑，不多不少;若k>0,多了K个。key. most important
    	int[] frequence=new int[256];
    	for(int i=0;i<t.length();i++){
    		frequence[t.charAt(i)]--;
    	}
    	//2.用双指针维护当前区间
    	int lack=t.length();
    	int i=0,j=0;
    	int start=-1,minLength=Integer.MAX_VALUE;
    	while(i<s.length()){
    		char ch;
    		while(j<s.length() && lack>0){//j不断向后探，直至越界或者能够找到涵盖t的子串
    			ch=s.charAt(j);
    			if(frequence[ch]<0){
    				lack--;
    			}
    			frequence[ch]++;
    			j++;
    		}
    		if(lack!=0){//到s的最后也没能找到,说明找不到了,退出
    			break;
    		}
    		//不断压缩左边
    		while(frequence[s.charAt(i)]>0){//i位置的字符仍有多有，可以丢掉
    			frequence[s.charAt(i)]--;
    			i++;
    		}
    		if(j-i<minLength){
    			minLength=j-i;
    			start=i;
    		}
    		//notice .key
    		frequence[s.charAt(i)]--;
    		i++;
    		lack=1;
    	}
    	if(start==-1){
    		return "";
    	}else{
    		return s.substring(start, start+minLength);
    	}
    }
       //比较繁琐还易错
    public String minWindowOrignal(String s, String t) {
        if(s.length()<t.length() || t.length()==0){
        	return "";
        }
        //1.用辅助数组统计t的词频，设为ascii
        int[] frequence=new int[256];
        for(int i=0;i<t.length();i++){
        	frequence[t.charAt(i)]++;//char自动转换为int索引
        }
        //2.
        int left=0,right=left;//用[left,right)维护当前子串,right为待考虑字符
        int need=t.length();//key目前还需要need个有效字符s[left,right)才能完全覆盖t
        Map<Character,Integer> map=new HashMap<Character,Integer>();//[left,right)中字符出现的次数
        Integer minLength=Integer.MAX_VALUE;
        Integer start=-1;//取得minLength的起点
        while(right<s.length() && s.length()-right>=need){//边界+剪枝(还剩的字符数[right,n-1]>=还缺的字符数)
        	Character ch=s.charAt(right);
        	int count=map.containsKey(ch)?map.get(ch):0;//字符ch在[left,right)中出现的次数
        	if(count<frequence[ch]){//收集到了一个有效字符
        		need--;
        		if(need==0){//s[left,right]涵盖了t中所有字符,不断缩左边界,尽量多的去多余字符
        			while(left<right){
        				Character head=s.charAt(left);
        				if(map.get(head)>frequence[head]){
        					map.put(head, map.get(head)-1);
        					left++;
        				}else{//map.get(head)==frequence[head],不能再缩了
        					break;
        				}
        			}
        			if(right-left+1<minLength){
        				minLength=right-left+1;
        				start=left;//记住起点
        			}
        			//丢掉头，继续往后探
                    if(left<right){//notice,可能T只有一个字符
        			    map.put(s.charAt(left), map.get(s.charAt(left))-1);
                    }
        			left++;
        			need++;
        		}
        	}
            if(left<right){
                if(map.containsKey(ch)){
                    map.put(ch, map.get(ch)+1);
                }else{
                    map.put(ch, 1);
                }
            }
    		right++;
        }
        if(start==-1){
        	return "";
        }else{
        	return s.substring(start,start+minLength);
        }
    }
}
