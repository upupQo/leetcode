// Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
//
// Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
//
//  
// Example 1:
//
//
// Input: nums = [3,0,1]
// Output: 2
// Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
//
//
// Example 2:
//
//
// Input: nums = [0,1]
// Output: 2
// Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
//
//
// Example 3:
//
//
// Input: nums = [9,6,4,2,3,5,7,0,1]
// Output: 8
// Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
//
//
// Example 4:
//
//
// Input: nums = [0]
// Output: 1
// Explanation: n = 1 since there is 1 number, so all numbers are in the range [0,1]. 1 is the missing number in the range since it does not appear in nums.
//
//
//  
// Constraints:
//
//
// 	n == nums.length
// 	1 <= n <= 104
// 	0 <= nums[i] <= n
// 	All the numbers of nums are unique.
//
//


class Solution {
    //268*.给定一个包含n个不同数字的数组0, 1, 2, ..., n，找到数组中缺少的数字。异或运算.将索引和值均一起进行异或运算
    //索引为[0,n-1]-->索引提供值{0,n-1}个一次;若nums缺失的是n,则nums提供值{0,n-1}各一次,循环结束的xor为0
    //若nums缺失的是{0.n-1}中的任一个,设缺失的为i∈[0,n-1],则nums提供{0,i-1}U{i+1,n-1}U{n}各一次,循环结束的xor=i^n
    public int missingNumber(int[] nums) {
    	int xor=0;
    	for(int i=0;i<nums.length;i++){
    		xor=xor^i^nums[i];
    	}
    	return xor^nums.length;
    }
}
