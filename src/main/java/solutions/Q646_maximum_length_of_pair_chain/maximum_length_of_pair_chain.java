//
// You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
//
//
//
// Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion. 
//
//
//
// Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
//
//
//
// Example 1:
//
// Input: [[1,2], [2,3], [3,4]]
// Output: 2
// Explanation: The longest chain is [1,2] -> [3,4]
//
//
//
// Note:
//
// The number of given pairs will be in the range [1, 1000].
//
//


class Solution {
    public int findLongestChain(int[][] pairs) {
        if(pairs.length<=1){
        	return pairs.length;
        }
        //1.对输入按first排序
        Arrays.sort(pairs,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
        int lc=1;//记录当前链的最大长度
        int end=0;//该长度最后一个结点索引--贪心策略:使该节点的second值尽量小
        for(int i=1;i<pairs.length;i++){
        	if(pairs[i][0]>pairs[end][1]){//链上去
        		end=i;
        		lc++;
        	}else{//链不上去,由于已经按照first排序,i结点是完全可以替代end结点的。end结点可以作为结尾，i结点必然也可以，因为i.first>end.first && end.first>(end-1).second -->i.first>(end-).second,end-1是逻辑上end的前一个结点
        		if(pairs[i][1]<pairs[end][1]){
        			end=i;//执行贪心策略
        		}
        	}
        }
        return lc;
    }
}
