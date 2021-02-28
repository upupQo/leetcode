// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
// Find the minimum element.
//
// The array may contain duplicates.
//
// Example 1:
//
//
// Input: [1,3,5]
// Output: 1
//
// Example 2:
//
//
// Input: [2,2,2,0,1]
// Output: 0
//
// Note:
//
//
// 	This is a follow up problem to Find Minimum in Rotated Sorted Array.
// 	Would allow duplicates affect the run-time complexity? How and why?
//
//


class Solution {
    public int findMin(int[] nums) {
    	int low=0,high=nums.length-1;
    	int middle;
    	while(nums[low]>=nums[high]){//仍是两段 || 只有一段(元素全相等)
    		middle=(low+high)/2;
    		if(nums[middle]==nums[low] && nums[middle]==nums[high]){//仍是两段无法在二分 || 只有一段元素全相等。顺序查找
    			while(low+1<=high && nums[low]<=nums[low+1]){
    				low++;
    			}
    			if(low==high){//只有一段元素全相等 ||只有一个元素 
    				return nums[low];
    			}else{
    				return nums[low+1];
    			}
    		}
    		//经过if判断,到此必然仍为可分的两段
    		if(nums[middle]>=nums[low]){//nums[middle]>=nums[low]>=nums[high],不全取'=',则nums[middle]>nums[high],middle在左段
    			low=middle+1;
    		}else{
    			high=middle;
    		}
    	}
    	return nums[low];//右段开头
    }
}
