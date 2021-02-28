// Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
//
//  
// Example 1:
//
//
// Input: x = 2.00000, n = 10
// Output: 1024.00000
//
//
// Example 2:
//
//
// Input: x = 2.10000, n = 3
// Output: 9.26100
//
//
// Example 3:
//
//
// Input: x = 2.00000, n = -2
// Output: 0.25000
// Explanation: 2-2 = 1/22 = 1/4 = 0.25
//
//
//  
// Constraints:
//
//
// 	-100.0 < x < 100.0
// 	-231 <= n <= 231-1
// 	-104 <= xn <= 104
//
//


class Solution {
 public double myPow(double x,int n){
    	boolean negative=n<0?true:false;
    	n=Math.abs(n);
    	double res=pow(x,n);
    	if(negative){
    		return 1/res;
    	}
    	return res;
    }
    //计算x的n次方，n>=0
    public double pow(double x, int n) {
        if(n==0){
        	return 1;
        }else if(n==1){
        	return x;
        }else{
        	boolean even=(n&1)==0?true:false;//n是否是偶数,偶数&1==0，奇数&1==1
        	double temp=pow(x, n/2);
        	temp*=temp;
        	if(!even){//奇数
        		return temp*x;
        	}
        	return temp;
        }
    }
}
