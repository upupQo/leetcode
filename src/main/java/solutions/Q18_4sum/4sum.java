// Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
//
// Notice that the solution set must not contain duplicate quadruplets.
//
//  
// Example 1:
// Input: nums = [1,0,-1,0,-2,2], target = 0
// Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// Example 2:
// Input: nums = [], target = 0
// Output: []
//
//  
// Constraints:
//
//
// 	0 <= nums.length <= 200
// 	-109 <= nums[i] <= 109
// 	-109 <= target <= 109
//
//


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
    	if(nums==null || nums.length<4){
    		return res;
    	}
    	int ptarget=target;
    	ArrayList<Integer> list;
    	Arrays.sort(nums);
    	for(int pa=0;pa<nums.length-3;pa++){
    		if(pa>0 && nums[pa]==nums[pa-1]){
    			continue;
    		}
    		for(int pb=pa+1;pb<nums.length-2;pb++){
    			if(pb>pa+1 && nums[pb]==nums[pb-1]){
    				continue;
    			}
    			target=ptarget-nums[pa]-nums[pb];
    			int pc=pb+1,pd=nums.length-1;
    			while(pc<pd){
    				int temp=nums[pc]+nums[pd];
    				if(temp==target){
    					list=new ArrayList<Integer>();
    					list.add(nums[pa]);
    					list.add(nums[pb]);
    					list.add(nums[pc]);
    					list.add(nums[pd]);
    					res.add(list);
    					pc++;
    					pd--;
 					   while(pc<pd && nums[pc]==nums[pc-1])
						   pc++;
					   while(pd>pc && nums[pd]==nums[pd+1])
						   pd--;
    				}else if(temp>target){
    					pd--;
    				}else{
    					pc++;
    				}
    			}
    		}
    	}
    return res;
    }
}
