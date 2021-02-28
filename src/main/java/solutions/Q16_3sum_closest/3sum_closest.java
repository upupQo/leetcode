// Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//  
// Example 1:
//
//
// Input: nums = [-1,2,1,-4], target = 1
// Output: 2
// Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
//
//
//  
// Constraints:
//
//
// 	3 <= nums.length <= 10^3
// 	-10^3 <= nums[i] <= 10^3
// 	-10^4 <= target <= 10^4
//
//


class Solution {
 public int threeSumClosest(int[] numbers, int target) {
        Arrays.sort(numbers);
    	int gap=Integer.MAX_VALUE;
    	int closeSum=Integer.MAX_VALUE;
    	int currentSum;
    	for(int i=0;i<numbers.length-2;i++){//对于每一个i,用j,k去逼近
    		int j=i+1,k=numbers.length-1;
    		while(j<k){
    			currentSum=numbers[i]+numbers[j]+numbers[k];
    			if(currentSum==target)
    				return target;
    			if(Math.abs(currentSum-target)<gap){//找个一个更接近的和
    				gap=Math.abs(currentSum-target);
    				closeSum=currentSum;//记录这个最接近的和
    			}
    			if(currentSum>target)//currentSum-target>0；我们减小currentSum的值，使它向0趋近；否则离0越来越远
    				k--;
    			else//currentSum-target<0;我们增大currentSum的值，使它向0趋近；否则离0越来越远
    				j++;
    		}
    	}
    	return closeSum;
    }
}
