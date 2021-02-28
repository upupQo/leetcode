// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
// If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
//
// The replacement must be in place and use only constant extra memory.
//
//  
// Example 1:
// Input: nums = [1,2,3]
// Output: [1,3,2]
// Example 2:
// Input: nums = [3,2,1]
// Output: [1,2,3]
// Example 3:
// Input: nums = [1,1,5]
// Output: [1,5,1]
// Example 4:
// Input: nums = [1]
// Output: [1]
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 100
// 	0 <= nums[i] <= 100
//
//


class Solution {
       public void nextPermutation(int[] nums) {
    	int i=nums.length-1;
    	while(i>0 && nums[i-1]>=nums[i]){//从后往前看，找第一个可以下降的位置
    			i--;
    	}
    	if(i==0){//序列是一个递减序列，不存在下一个序列,进行反转
    		reserve(nums, 0, nums.length-1);
    		return;
    	}
    	//二分在arr[i,n-1]这个递减序列中找第一个大于arr[i-1]的数,必定是可以找到的
    	int low=i,high=nums.length-1;
    	int middle;
    	while(low<high){
    		middle=(low+high+1)/2;//使middle成为下中位数skill,方便处理只有两个元素时避免陷入死循环eg{3,1}，target=2
    		if(nums[middle]>nums[i-1]){
    			low=middle;
    		}else{
    			high=middle-1;
    		}
    	}
    	swap(nums,i-1,low);//与后面第一个比自己大的数交换位置,且交换后后面组成的也将最大
    	reserve(nums, i, nums.length-1);
    }
    //反转子数组arr[i,j]
    private void reserve(int[] arr,int i,int j){
    	while(i<j){
    		swap(arr,i++,j--);
    	}
    }
    private void swap(int[] arr,int i,int j){
    	int temp=arr[i];
    	arr[i]=arr[j];
    	arr[j]=temp;
    }
}
