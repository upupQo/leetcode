// Given a non-empty array of integers, return the k most frequent elements.
//
// Example 1:
//
//
// Input: nums = [1,1,1,2,2,3], k = 2
// Output: [1,2]
//
//
//
// Example 2:
//
//
// Input: nums = [1], k = 1
// Output: [1]
//
//
// Note: 
//
//
// 	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
// 	Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
// 	It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
// 	You can return the answer in any order.
//
//


class Solution {
    // public List<Integer> topKFrequent(int[] nums, int k) {
    // 	k=Math.min(k, nums.length);
    // 	//1.遍历一遍用map统计key的频率
    //     Map<Integer,Integer> freq=new HashMap<Integer,Integer>();
    //     for(int i=0;i<nums.length;i++) {
    //     	freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1);
    //     }
    //     //2.任一个元素出现的频次∈[0,n],--->利用桶排序的思想
    //     //int[] bucket=new int[nums.length+1];//bucket[i]保存频次为i的元素值
    //    List<Integer>[] bucket=new ArrayList[nums.length+1];//---考虑到有相同频率的元素,改用List[i]保存所有2.频次为i的元素值
    //    for(int key : freq.keySet()) {
    // 	   if(bucket[freq.get(key)]==null) {
    // 		   bucket[freq.get(key)]=new ArrayList<Integer>();
    // 	   }
    // 	   bucket[freq.get(key)].add(key);
    //    }
    //    //3.从尾到头依次出频率最高的元素
    //    List<Integer> res=new ArrayList<Integer>();
    //    int i=bucket.length-1;
    //    while(i>=0 && res.size()<k) {
    // 	   if(bucket[i]!=null) {
    // 		   res.addAll(bucket[i]);
    // 	   }
    //        i--;
    //    }
    //    return res;
    // }
      /**
     * 桶排时空复杂度稳定O(n),当元素分布很不均时，用堆排可以有效较少空间复杂度：空间复杂度:O(count(distinct key))
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
    	k=Math.min(k, nums.length);
    	//1.遍历一遍用map统计key的频率
        Map<Integer,Integer> freq=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
        	freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1);
        }
        //2.用堆排序
        PriorityQueue<Node> queue=new PriorityQueue<Node>();
        for(int key : freq.keySet()) {
        	queue.add(new Node(key, freq.get(key)));
        }
        //3.返回结果
        List<Integer> res=new ArrayList<Integer>();
        Node temp;
        while(!queue.isEmpty() && k>0) {
        	temp=queue.poll();
        	res.add(temp.key);
        	k--;
        }
        return res;
    }
    private class Node implements Comparable<Node>{
    	private int key;
    	private int frequence;
    	public Node(int key,int frequence) {
    		this.key=key;
    		this.frequence=frequence;
    	}
		@Override
		public int compareTo(Node o) {
			return o.frequence-this.frequence;//PriorityQueue是小堆实现，我们想要大堆
		}
    }
}
