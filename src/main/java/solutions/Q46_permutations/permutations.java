// Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
//  
// Example 1:
// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// Example 2:
// Input: nums = [0,1]
// Output: [[0,1],[1,0]]
// Example 3:
// Input: nums = [1]
// Output: [[1]]
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 6
// 	-10 <= nums[i] <= 10
// 	All the integers of nums are unique.
//
//


class Solution {
    	private List<List<Integer>> permute;
        public List<List<Integer>> permute(int[] nums) {
            this.permute=new ArrayList<List<Integer>>();
            boolean[] book=new boolean[nums.length];
            ArrayList<Integer> list=new ArrayList<Integer>();
            permute(nums, book, list);
            return this.permute;
        }
        private void permute(int[] nums,boolean[] book,ArrayList<Integer> list){
        	if(list.size()==nums.length){//已经形成了一个排列
        		permute.add(new ArrayList<Integer>(list));//notice,引用类型
        		return;
        	}
        	for(int i=0;i<nums.length;i++){
        		if(book[i]==false){//i位置的值还没被用过
        			list.add(nums[i]);
        			book[i]=true;
        			permute(nums, book, list);
        			list.remove(list.size()-1);//移除最新添加的元素
        			book[i]=false;
        		}
        	}
        }
    }
