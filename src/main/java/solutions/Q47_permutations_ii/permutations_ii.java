// Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
//
//  
// Example 1:
//
//
// Input: nums = [1,1,2]
// Output:
// [[1,1,2],
//  [1,2,1],
//  [2,1,1]]
//
//
// Example 2:
//
//
// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 8
// 	-10 <= nums[i] <= 10
//
//


class Solution {
    	private List<List<Integer>> permute;
        //47.给定可能包含元素的数字集合，返回所有可能的唯一排列。
        //若该位置已经使用过‘值为val’的元素，则该位置在后续枚举试探过程中不再使用值为val的元素
        //通过预排序或临时hashSet,这里通过预排序
        public List<List<Integer>> permuteUnique(int[] nums) {
            this.permute=new ArrayList<List<Integer>>();
            boolean[] book=new boolean[nums.length];
            ArrayList<Integer> list=new ArrayList<Integer>();
            Arrays.sort(nums);//key,对输入排序，方便后续过程
            permuteUnique(nums, book, list);
            return this.permute;
        }
        private void permuteUnique(int[] nums,boolean[] book,ArrayList<Integer> list){
        	if(list.size()==nums.length){//已经形成了一个排列
        		permute.add(new ArrayList<Integer>(list));//notice,引用类型
        		return;
        	}
        	for(int i=0;i<nums.length;i++){
        		if(book[i]==false){
        			list.add(nums[i]);
        			book[i]=true;
        			permuteUnique(nums, book, list);
        			list.remove(list.size()-1);//移除最新添加的元素
        			book[i]=false;
        			//key,由于nums有序，所以可以方便淘汰后续枚举
        			int val=nums[i];
        			while(i+1<nums.length && nums[i+1]==val){
        				i++;
        			}
        		}
        	}
        }
    
}
