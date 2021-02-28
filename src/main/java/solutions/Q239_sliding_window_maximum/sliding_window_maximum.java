// You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
// Return the max sliding window.
//
//  
// Example 1:
//
//
// Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
// Output: [3,3,5,5,6,7]
// Explanation: 
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
//
//
// Example 2:
//
//
// Input: nums = [1], k = 1
// Output: [1]
//
//
// Example 3:
//
//
// Input: nums = [1,-1], k = 1
// Output: [1,-1]
//
//
// Example 4:
//
//
// Input: nums = [9,11], k = 2
// Output: [11]
//
//
// Example 5:
//
//
// Input: nums = [4,-2], k = 2
// Output: [4]
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 105
// 	-104 <= nums[i] <= 104
// 	1 <= k <= nums.length
//
//


class Solution {
    //w为窗口大小。直接解为O(w*n),用双端队列为O(n)
    //假定1=<k<=nums.size()
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k>nums.length){//不能形成有效窗口，返回空数组
    		return new int[]{};
    	}
    	Deque<Integer> queue=new LinkedList<Integer>();
        //1.初始化队列,维护一个降序列，放索引(方便检查过期)不放值
        queue.add(0);
        for(int i=1;i<k;i++){
	        	while(!queue.isEmpty() && nums[queue.peekLast()]<=nums[i]){//<=当前值的出来
	        			queue.pollLast();
	        	}
	        	//进入候选队列
	        	queue.addLast(i);
        }
        //2.借助辅助队列找窗口里的最大值
        int[] res=new int[nums.length-k+1];//窗口个数为nums.length-k+1
        res[0]=nums[queue.peekFirst()];
        int i=k;
        while(i<nums.length){
        	if(queue.peekFirst()<i-k+1){//淘汰过期的,保证不过期，由于上一次里面的元素都没过期，因此这一次最多只有一个元素过期
        		queue.pollFirst();
        	}
        	while(!queue.isEmpty() && nums[queue.peekLast()]<=nums[i]){//淘汰<=当前值的
    			queue.pollLast();
        	}
        	//进入候选队列
        		queue.addLast(i);
        	//注意淘汰顺序，先淘汰过期，再淘汰<=当期值的,在看当前值是否能够加入序列
        	res[i-k+1]=nums[queue.peekFirst()];
        	i++;
        }
        return res;
    }
}
