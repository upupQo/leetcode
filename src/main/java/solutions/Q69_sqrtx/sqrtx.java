// Given a non-negative integer x, compute and return the square root of x.
//
// Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
//
//  
// Example 1:
//
//
// Input: x = 4
// Output: 2
//
//
// Example 2:
//
//
// Input: x = 8
// Output: 2
// Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
//
//  
// Constraints:
//
//
// 	0 <= x <= 231 - 1
//
//


class Solution {
//开平方,x>=0--二分法
    public int mySqrt(int x) {
    	if(x<=1){
    		return x;
    	}
        int low=1,high=x;//用[low,high]维护一个解区间
        int mid;
        while(low<high){
        	mid=(low+high)/2;
        	if(mid==x/mid){
        		return mid;
        	}else if(mid>x/mid){
        		high=mid-1;
        	}else{
        		//2*2<8,但sqrt(8)==2
        		//key,mid*mid<x but (mid+1)*(mid+1)>x,因此sqrt=x
        		if((mid+1)>x/(mid+1)){
        			return mid;
        		}
        		low=mid+1;
        	}
        }
        return low;
    }
}
