// You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//
// What is the maximum number of envelopes can you Russian doll? (put one inside other)
//
// Note:
// Rotation is not allowed.
//
// Example:
//
//
//
// Input: [[5,4],[6,4],[6,7],[2,3]]
// Output: 3 
// Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
//
//
//


class Solution {
    //354. Russian Doll Envelopes 俄罗斯套娃 与646相比，没有i.first<i.second没法贪心。
    //先按first排序，然后就转化为关于second的最长递增子序列问题
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length<=1){
        	return envelopes.length;
        }
        //1.按起点排序
        Arrays.sort(envelopes,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
        //2.找关于second的最长递增子序列，枚举子序列尾
        int lis=0;
        int[] dp=new int[envelopes.length];
        for(int i=0;i<envelopes.length;i++){
        	dp[i]=1;
        	for(int k=i-1;k>=0;k--){
        		if(envelopes[k][1]<envelopes[i][1] && envelopes[k][0]<envelopes[i][0]){//notice.envelopes[k][0]<envelopes[i][0],按照first排序后,first可能相等
        			dp[i]=Math.max(dp[i], dp[k]+1);
        		}
        	}
        	lis=Math.max(lis, dp[i]);
        }
        return lis;
    }
}
