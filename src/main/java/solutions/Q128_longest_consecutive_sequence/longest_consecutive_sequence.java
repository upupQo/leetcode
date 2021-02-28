// Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//  
// Example 1:
//
//
// Input: nums = [100,4,200,1,3,2]
// Output: 4
// Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//
//
// Example 2:
//
//
// Input: nums = [0,3,7,2,5,8,4,6,0,1]
// Output: 9
//
//
//  
// Constraints:
//
//
// 	0 <= nums.length <= 104
// 	-109 <= nums[i] <= 109
//
//
//  
// Follow up: Could you implement the O(n) solution?


class Solution {
 public int longestConsecutive(int[] nums) {
    	int count=0;//最长连续序列字符数
    	Set<Integer> set=new HashSet<Integer>();
    	for(int i=0;i<nums.length;i++) {
    		set.add(nums[i]);
    	}
    	for(int i=0;i<nums.length;i++) {
    		if(set.remove(nums[i])) {
	    		int left=nums[i]-1;
	    		int right=nums[i]+1;
	    		//消耗set中逻辑上连续的数字
	    		while(set.remove(left)) {
	    			left--;
	    		}
	    		while(set.remove(right)) {
	    			right++;
	    		}
	    		count=Math.max(count, right-left-1);//消耗了数字[left+1,right-1]
    		}
    	}
    	return count;
    }
}
