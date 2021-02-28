// Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
//
//
// Example 1:
//
//
// Input: a = 1, b = 2
// Output: 3
//
//
//
// Example 2:
//
//
// Input: a = -2, b = 3
// Output: 1
//
//
//
//


class Solution {
    public int getSum(int a, int b) {
    	int temp;
        while(b!=0){//当没有相同位时,亦或运算就可以得到最终结果
        	temp=a^b;//把不同位保留下来
        	b=(a&b)<<1;//相同位进行运算
        	a=temp;
        	
        }
        return a;
    }
}
