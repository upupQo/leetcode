// There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
//
// Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
//
// Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
//
//  
// Example 1:
// Input: nums = [2,5,6,0,0,1,2], target = 0
// Output: true
// Example 2:
// Input: nums = [2,5,6,0,0,1,2], target = 3
// Output: false
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 5000
// 	-104 <= nums[i] <= 104
// 	nums is guaranteed to be rotated at some pivot.
// 	-104 <= target <= 104
//
//
//  
// Follow up: This problem is the same as Search in Rotated Sorted Array, where nums may contain duplicates. Would this affect the runtime complexity? How and why?


class Solution {
    public boolean search(int[] nums, int target) {
    	if(nums.length==0){
    		return false;
    	}
        int low=0,high=nums.length-1;
        int middle;
        //low将保存最小值点即旋转点
    	while(nums[low]>=nums[high]){//仍是两段 || 只有一段(元素全相等)
    		middle=(low+high)/2;
    		if(nums[middle]==nums[low] && nums[middle]==nums[high]){//仍是两段无法在二分 || 只有一段元素全相等。顺序查找
    			while(low+1<=high && nums[low]<=nums[low+1]){
    				low++;
    			}
    			if(low!=high){//有下降
    				low=low+1;
    			}
    			break;
    		}
    		//经过if判断,到此必然仍为可分的两段
    		if(nums[middle]>=nums[low]){//nums[middle]>=nums[low]>=nums[high],不全取'=',则nums[middle]>nums[high],middle在左段
    			low=middle+1;
    		}else{
    			high=middle;
    		}
    	}
        int index=-1;
        if(target<=nums[nums.length-1]){//在右段找
        	index=binarySearch(nums, low, nums.length-1, target);
        }else{
        	index=binarySearch(nums, 0, low-1, target);//先右后左
        }
        return index==-1?false:true;
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
