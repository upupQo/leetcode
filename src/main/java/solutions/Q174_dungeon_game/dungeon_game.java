// table.dungeon, .dungeon th, .dungeon td {
//   border:3px solid black;
// }
//
//  .dungeon th, .dungeon td {
//     text-align: center;
//     height: 70px;
//     width: 70px;
// }
//
// The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
//
// The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
//
// Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
//
// In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
//
//  
//
// Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
//
// For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//
//
//
//
// 			-2 (K)
// 			-3
// 			3
//
//
// 			-5
// 			-10
// 			1
//
//
// 			10
// 			30
// 			-5 (P)
//
//
//
//
//  
//
// Note:
//
//
// 	The knight's health has no upper bound.
// 	Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
//
//


class Solution {
   //dp[i][j]:到达(i,j)点时hp预先最低为多少才能继续从(i,j)走到右下角,
    //设到达(i,j)时的hp为x,则1.耗血后要满足下一步的需要:x+dungeon[i][j]>=Math.min(dp[i+1,j],dp[i][j+1]) && 
    //						2.耗血后要能在当前位置活下来:x+dungeon[i][j]>=1
    //综上,x>=Math.min(dp[i+1,j],dp[i][j+1])+dungeon[i][j]*(-1) && x>=1+dungeon[i][j]*(-1)
    //所以dp[i][j]=Math.max(Math.max(Math.min(dp[i+1,j],dp[i][j+1]),1)+dungeon[i][j]*(-1),0)//x>=0
    public int calculateMinimumHP(int[][] dungeon) {
        int m=dungeon.length;
        int n=dungeon[0].length;
        int[][] dp=new int[m][n];
        //1.初始化最后一行、最后一列
        //x+dungeon[m-1][n-1]*(-1)>=1 则x>=1+dungeon[m-1][n-1]*(-1),最起码取=号
        dp[m-1][n-1]=Math.max(dungeon[m-1][n-1]*(-1)+1,0);
        for(int j=n-2;j>=0;j--){
        	dp[m-1][j]=Math.max(Math.max(dp[m-1][j+1],1)+dungeon[m-1][j]*(-1),0);
        }
        for(int i=m-2;i>=0;i--){
        	dp[i][n-1]=Math.max(Math.max(dp[i+1][n-1],1)+dungeon[i][n-1]*(-1),0);
        }
        //2.逐行填,每行从右往左填
        for(int i=m-2;i>=0;i--){
        	for(int j=n-2;j>=0;j--){
        		dp[i][j]=Math.max(Math.max(Math.min(dp[i+1][j], dp[i][j+1]), 1)+dungeon[i][j]*(-1), 0);
        	}
        }
        return Math.max(dp[0][0],1);//进来之前得活着eg:[[100]],output:1
    }
}
