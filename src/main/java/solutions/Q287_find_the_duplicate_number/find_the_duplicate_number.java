// Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//
// There is only one repeated number in nums, return this repeated number.
//
//  
// Example 1:
// Input: nums = [1,3,4,2,2]
// Output: 2
// Example 2:
// Input: nums = [3,1,3,4,2]
// Output: 3
// Example 3:
// Input: nums = [1,1]
// Output: 1
// Example 4:
// Input: nums = [1,1,2]
// Output: 1
//
//  
// Constraints:
//
//
// 	2 <= n <= 3 * 104
// 	nums.length == n + 1
// 	1 <= nums[i] <= n
// 	All the integers in nums appear only once except for precisely one integer which appears two or more times.
//
//
//  
// Follow up:
//
//
// 	How can we prove that at least one duplicate number must exist in nums?
// 	Can you solve the problem without modifying the array nums?
// 	Can you solve the problem using only constant, O(1) extra space?
// 	Can you solve the problem with runtime complexity less than O(n2)?
//
//


class Solution {
    //287.给定一个包含n + 1个整数（其中每个整数介于1和n(含)之间)的数组num,则至少有一个重复数字必须存在,找到一个重复的数。O(n)
    //设当前在i位置，则[0,i-1]已经有了对应的值{1,i},case1.nums[i]==i+1,则又收集到了一个数，i++;case2.i<i+1,由于已经收集到了{1，i},所以遇到了重复值;case3.nums[i]>i+1,那么该数(归位后)应该放在nums[i]-1位置,
    public int findDuplicate(int[] nums) {
        int i=0;//[0,i-1]已经有了对应的值
        while(i<nums.length){
        	if(nums[i]==i+1){
        		i++;
        	}else if(nums[i]<i+1 || nums[nums[i]-1]==nums[i]){
        		return nums[i];
        	}else{
        		swap(nums,i,nums[i]-1);
        	}
        }
        return 0;//凑语法
    }
   private void swap(int[] arr,int i,int j){
    	int temp=arr[i];
    	arr[i]=arr[j];
    	arr[j]=temp;
    }
}
