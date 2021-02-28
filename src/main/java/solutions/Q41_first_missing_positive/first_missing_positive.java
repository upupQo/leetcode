// Given an unsorted integer array nums, find the smallest missing positive integer.
//
//  
// Example 1:
// Input: nums = [1,2,0]
// Output: 3
// Example 2:
// Input: nums = [3,4,-1,1]
// Output: 2
// Example 3:
// Input: nums = [7,8,9,11,12]
// Output: 1
//
//  
// Constraints:
//
//
// 	0 <= nums.length <= 300
// 	-231 <= nums[i] <= 231 - 1
//
//
//  
// Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space?
//


class Solution {
	//41*.第一个缺失的正整数
    public int firstMissingPositive(int[] nums) {
    	//对于nums[0,right],理想情况下下可以收集到[1,right+1]
        int left=0;//设[0,left-1]已经收集到了[1,left],现在在left位置，期待的值是left+1
        int right=nums.length-1;
        while(left<=right){//还有待考虑元素
        	if(nums[left]==left+1){
        		left++;
        	}else if(nums[left]<(left+1) || nums[left]>(right+1) || nums[nums[left]-1]==nums[left]){//key
        		nums[left]=nums[right];//该值无用，直接覆盖
        		right--;
        	}else{
        		swap(nums,left,nums[left]-1);//把值left位置的值nums[left]归位(放到自己应该的位置)
        	}
        }
        return left+1;
    }
    private void swap(int[] arr,int i,int j){
    	int temp=arr[i];
    	arr[i]=arr[j];
    	arr[j]=temp;
    }
}
