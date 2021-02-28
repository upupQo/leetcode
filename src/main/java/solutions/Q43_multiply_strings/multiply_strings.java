// Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
//
// Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
//
//  
// Example 1:
// Input: num1 = "2", num2 = "3"
// Output: "6"
// Example 2:
// Input: num1 = "123", num2 = "456"
// Output: "56088"
//
//  
// Constraints:
//
//
// 	1 <= num1.length, num2.length <= 200
// 	num1 and num2 consist of digits only.
// 	Both num1 and num2 do not contain any leading zero, except the number 0 itself.
//
//


class Solution {
    public String multiply(String num1, String num2) {
        int m=num1.length()-1,n=num2.length()-1;
        int[] res=new int[m+n+2];//res[i]属于[0,9]
        int mul,pCarry,pRemain,sum;
        num1=new StringBuffer(num1).reverse().toString();
        num2=new StringBuffer(num2).reverse().toString();
        for(int i=0;i<=n;i++){//用nums2的第i位依次与nums1的各位相乘
        	for(int j=0;j<=m;j++){
        		mul=(num2.charAt(i)-'0')*(num1.charAt(j)-'0');
        		pCarry=i+j+1;
        		pRemain=i+j;
        		sum=mul+res[pRemain];
        		res[pRemain]=sum%10;
        		res[pCarry]+=sum/10;
        	}
        }
        StringBuffer sb=new StringBuffer();
        int index=m+n+1;
        while(index>=0 && res[index]==0){//去掉可能的前驱0
        	index--;
        }
        while(index>=0){
        	sb.append(res[index--]);
        }
        return sb.length()==0?"0":sb.toString();
    }
}
