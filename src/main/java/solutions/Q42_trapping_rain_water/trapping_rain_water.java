// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//  
// Example 1:
//
//
// Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//
//
// Example 2:
//
//
// Input: height = [4,2,0,3,2,5]
// Output: 9
//
//
//  
// Constraints:
//
//
// 	n == height.length
// 	0 <= n <= 3 * 104
// 	0 <= height[i] <= 105
//
//


class Solution {
    public int trap(int[] height) {
        if(height.length<=2){
        	return 0;
        }
        int waterTraped=0;//捕获的雨量
        int left=0,right=height.length-1;//用[left,right]围住待计算的区域
        int maxLeft=0,maxRight=0;//用maxLeft记录[0,left-1]出现的最大值，用maxRight记录[right+1,n-1]出现的最大值;根据实际情况，可以均初始化为0
        while(left<=right){//仍有待计算的区域
        	if(height[left]>=maxLeft || height[right]>=maxRight){
        		if(height[left]>=maxLeft){//left位置无法接水
        			maxLeft=height[left];
        			left++;
        		}
        		if(height[right]>=maxRight){//right位置无法接水
        			maxRight=height[right];
        			right--;
        		}
        	}else{//height[left]<maxLeft &&  height[right]<maxRight--keyPoint
        		if(maxLeft>=maxRight){//right位置可以计算了,maxRight已经成为right位置的短板
        			waterTraped+=maxRight-height[right];
        			right--;
        		}else{//maxLeft<maxRight,left位置可以计算了,maxLeft已经成为left位置的短板
        			waterTraped+=maxLeft-height[left];
        			left++;
        		}
        	}
        }
        return waterTraped;
    }
}
