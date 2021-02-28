// Given an integer n, return the least number of perfect square numbers that sum to n.
//
// A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
//
//  
// Example 1:
//
//
// Input: n = 12
// Output: 3
// Explanation: 12 = 4 + 4 + 4.
//
//
// Example 2:
//
//
// Input: n = 13
// Output: 2
// Explanation: 13 = 4 + 9.
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 104
//
//


class Solution {
    //1.bfs，
   public int numSquaresBFS(int n) {
       if(n<=0){
    	   return 0;
       }
       int count=0;//当前第count轮
       HashSet<Integer> set=new HashSet<Integer>();
       Queue<Integer> queue=new LinkedList<Integer>();
       queue.add(n);
       set.add(n);//这一轮有多少个数
       int size=0;
       while(!queue.isEmpty()){
    	   if(size==0){
    		   size=queue.size();
    		   count++;
    	   }
    	   int cur=queue.poll();
    	   size--;
    	   for(int i=(int)(Math.sqrt(cur));i>=1;i--){//枚举边长
    		   if(cur-i*i==0){
    			   return count;
    		   }
    		   if(!set.contains(cur-i*i)){
    			   queue.add(cur-i*i);
    			   set.add(cur-i*i);
    		   }
    	   }
       }
       return -1;
   }
    //2.dp
       public int numSquares(int n) {
       if(n<=0){
    	   return 0;
       }
       //dp[i]代表数字i转换到0的最小次数
       int[] dp=new int[n+1];
       dp[0]=0;
       dp[1]=1;
       for(int i=2;i<=n;i++){
    	   dp[i]=i;//最差i步，每次减1
    	   for(int j=(int)Math.sqrt(i);j>=1;j--){
    		   dp[i]=Math.min(dp[i-j*j]+1, dp[i]);
    	   }
       }
       return dp[n];
   }
}
