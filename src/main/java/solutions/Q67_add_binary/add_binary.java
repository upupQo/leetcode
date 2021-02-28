// Given two binary strings a and b, return their sum as a binary string.
//
//  
// Example 1:
// Input: a = "11", b = "1"
// Output: "100"
// Example 2:
// Input: a = "1010", b = "1011"
// Output: "10101"
//
//  
// Constraints:
//
//
// 	1 <= a.length, b.length <= 104
// 	a and b consist only of '0' or '1' characters.
// 	Each string does not contain leading zeros except for the zero itself.
//
//


class Solution {
    public String addBinary(String a, String b) {
        if(a.length()==0){
        	return b;
        }
        if(b.length()==0){
        	return a;
        }
        a=new StringBuffer(a).reverse().toString();
        b=new StringBuffer(b).reverse().toString();
        //1.预处理将结果保存到b上。保证b较长
        if(a.length()>b.length()){
        	String temp=b;
        	b=a;
        	a=temp;
        }
        StringBuffer sb=new StringBuffer();
        int pre=0;//上一次是否有进位，对于两个二进制位相加,有进位必为1
        int indexA=0,indexB=0;
        int sum=0;
        //1.先把公共长度段处理完
        while(indexA<a.length()){
        	sum=a.charAt(indexA)-'0'+b.charAt(indexB)-'0'+pre;
        	if(sum<2){//[0,1]
        		sb.append((char)(sum+'0'));
        		pre=0;
        	}else{//sum==2,3
        		sb.append((char)(sum%2+'0'));
        		pre=1;
        	}
        	indexA++;
        	indexB++;
        }
        while(pre!=0){//pre==1
        	if(indexB==b.length()){
        		sb.append('1');
        		break;
        	}
        	sum=b.charAt(indexB)-'0'+1;//有进位，必为1
        	if(sum<2){//[0,1]
        		sb.append((char)(sum+'0'));
        		pre=0;
        	}else{//sum==2,3
        		sb.append((char)(sum%2+'0'));
        		pre=1;
        	}
        	indexB++;
        }
        while(indexB<b.length()){
        	sb.append(b.charAt(indexB++));
        }
        return sb.reverse().toString();
    }
}
