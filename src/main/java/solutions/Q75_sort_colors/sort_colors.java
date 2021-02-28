// Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
//
// We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
//
//  
// Example 1:
// Input: nums = [2,0,2,1,1,0]
// Output: [0,0,1,1,2,2]
// Example 2:
// Input: nums = [2,0,1]
// Output: [0,1,2]
// Example 3:
// Input: nums = [0]
// Output: [0]
// Example 4:
// Input: nums = [1]
// Output: [1]
//
//  
// Constraints:
//
//
// 	n == nums.length
// 	1 <= n <= 300
// 	nums[i] is 0, 1, or 2.
//
//
//  
// Follow up:
//
//
// 	Could you solve this problem without using the library's sort function?
// 	Could you come up with a one-pass algorithm using only O(1) constant space?
//
//


class Solution {
    //用[0,i]维护0区间,[i+1,k]维护1区间,[k+1,j-1]维护2区间，当前待考虑位置为j位置
    public void sortColors(int[] nums) {
        int i=-1,k=-1,j=0;
        for(;j<nums.length;j++){
        	if(nums[j]==2){
        		continue;
        	}else if(nums[j]==1){
        		swap(nums, ++k, j);
        	}else{//nums[j]==0
        		swap(nums, ++k, j);
        		swap(nums, ++i, k);
        	}
        }
    }
    private void swap(int[] arr,int i,int j){
    	int temp=arr[i];
    	arr[i]=arr[j];
    	arr[j]=temp;
    }
}
