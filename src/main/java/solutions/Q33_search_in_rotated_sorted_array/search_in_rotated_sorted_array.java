// There is an integer array nums sorted in ascending order (with distinct values).
//
// Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
//
// Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
//
//  
// Example 1:
// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4
// Example 2:
// Input: nums = [4,5,6,7,0,1,2], target = 3
// Output: -1
// Example 3:
// Input: nums = [1], target = 0
// Output: -1
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 5000
// 	-104 <= nums[i] <= 104
// 	All values of nums are unique.
// 	nums is guaranteed to be rotated at some pivot.
// 	-104 <= target <= 104
//
//
//  
// Follow up: Can you achieve this in O(log n) time complexity?


class Solution {
    public int search(int[] nums, int target) {
        if(nums.length==0){
            return -1;
        }
        int low=0,high=nums.length-1;
        int middle;
        //low将保存最小值点即旋转点
        while(nums[low]>nums[high]){//仍是两段
        	middle=(low+high)/2;//key使middle保存上中位数。什么时候保存上中位数或下中位数视情况
        	if(nums[middle]>=nums[low]){//middle在左段,=时为左段只有一个点
        		low=middle+1;
        	}else{//middle在右段,可能是起点
        		high=middle;
        	}
        }
        int index=-1;
        if(target<=nums[nums.length-1]){//在右段找
        	index=binarySearch(nums, low, nums.length-1, target);
        }else{
        	index=binarySearch(nums, 0, low-1, target);//先右后左
        }
        return index;
    }
    //在有序数组arr[start,end]中找target，返回其索引
    public int binarySearch(int[] arr,int start,int end,int target){
    	int middle;
    	while(start<=end){
    		middle=(start+end)/2;
    		if(arr[middle]==target){
    			return middle;
    		}else if(arr[middle]>target){
    			end=middle-1;
    		}else{
    			start=middle+1;
    		}
    	}
    	return -1;
    }
}
