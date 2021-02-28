// You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.
//
// You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
//
// Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
//
//  
// Example 1:
//
//
// Input: rods = [1,2,3,6]
// Output: 6
// Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
//
//
// Example 2:
//
//
// Input: rods = [1,2,3,4,5,6]
// Output: 10
// Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
//
//
// Example 3:
//
//
// Input: rods = [1,2]
// Output: 0
// Explanation: The billboard cannot be supported, so we return 0.
//
//
//  
// Constraints:
//
//
// 	1 <= rods.length <= 20
// 	1 <= rods[i] <= 1000
// 	sum(rods[i]) <= 5000
//
//


class Solution {
	//dp[i][j]:sum(a[k]*[1,-1,0])==j-5000时,正数和的最大值,k∈[0,i]。如果无法构成j-5000，设为-1。
    public int tallestBillboard(int[] rods) {
    	if(rods.length<1){
    		return 0;
    	}
        int[][] dp=new int[rods.length][10001];
        //1.初始化首行:arr[0]元素只能构成值 -arr[0],arr[0],0
        for(int j=0;j<dp[0].length;j++){
        	dp[0][j]=-1;
        }
        dp[0][-1*rods[0]+5000]=0;//arr[0]与-1相乘
        dp[0][rods[0]+5000]=rods[0];//arr[0]与+1相乘
        dp[0][5000]=0;//arr[0]与0相乘
        for(int i=1;i<rods.length;i++){
        	for(int j=0;j<dp[0].length;j++){
        		int max=-1;
        		if(j-rods[i]>=0 && dp[i-1][j-rods[i]]!=-1){//rods[i]]*1
        			max=Math.max(max, dp[i-1][j-rods[i]]+rods[i]);
        		}
        		if(j+rods[i]<=10000 && dp[i-1][j+rods[i]]!=-1){//rods[i]*-1
        			max=Math.max(max, dp[i-1][j+rods[i]]);
        		}
        		if(dp[i-1][j]!=-1){//rods[i]*0
        			max=Math.max(max, dp[i-1][j]);
        		}
        		dp[i][j]=max;
        	}
        }
        return dp[rods.length-1][5000];
    }
}
