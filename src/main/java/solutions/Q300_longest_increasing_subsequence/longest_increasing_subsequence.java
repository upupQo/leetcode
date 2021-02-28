// Given an integer array nums, return the length of the longest strictly increasing subsequence.
//
// A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
//
//  
// Example 1:
//
//
// Input: nums = [10,9,2,5,3,7,101,18]
// Output: 4
// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//
//
// Example 2:
//
//
// Input: nums = [0,1,0,3,2,3]
// Output: 4
//
//
// Example 3:
//
//
// Input: nums = [7,7,7,7,7,7,7]
// Output: 1
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 2500
// 	-104 <= nums[i] <= 104
//
//
//  
// Follow up:
//
//
// 	Could you come up with the O(n2) solution?
// 	Could you improve it to O(n log(n)) time complexity?
//
//


class Solution {
    //贪心思想:使已经得到的上升子序列的元素值尽量小(key)，这样对于下一个待考虑元素来说，可以用来延伸某个序列的机会就更大.O(n*logn)
    public int lengthOfLIS(int[] nums) {
        if(nums.length<=1){
        	return nums.length;
        }
        int[] ends=new int[nums.length];//key.ends[i]代表{所有长度为i+1的上升子序列}最后一个元素的最小值
        ends[0]=nums[0];
        int index=0;//ends中最后一个元素的位置
        for(int i=1;i<nums.length;i++){
        	if(nums[i]>ends[index]){
        		ends[++index]=nums[i];
        	}else if(nums[i]<ends[index]){//二分在ends[0,index]中找到第一个大于nums[i]的元素并替换它key
        		int low=0,high=index,mid;
        		while(low<high){//[low,high]维护一个有解区间--二分关键-->不会忽略解
        			mid=(low+high)/2;
        			if(ends[mid]>=nums[i]){
        				high=mid;
        			}else{
        				low=mid+1;
        			}
        		}
        		ends[low]=nums[i];
        	}
        }
        return index+1;
    }
  //标准dp, dp[i]:以nums[i]为该子序列最后一个元素(即结尾)的最长递增子序列的长度，dp[i]=min(dp[k])+1,k∈[0,i-1] && nums[k]<nums[i]  O(n*n)
    public int lengthOfLISOriginal(int[] nums) {
        if(nums.length<=1){
        	return nums.length;
        }
        int lis=1;
        int[] dp=new int[nums.length];//默认初始化为0
        dp[0]=1;
        for(int i=1;i<nums.length;i++){
        	dp[i]=1;//至少唯一,先给一个可取值，方便后续处理
        	for(int k=i-1;k>=0;k--){
        		if(nums[k]<nums[i]){//nums[k]<nums[i],i元素可以作为k元素的下一个元素
        			dp[i]=Math.max(dp[i], dp[k]+1);
        		}
        	}
        	lis=Math.max(lis, dp[i]);
        }
        return lis;
    }
}
