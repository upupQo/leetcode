// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
//
// You may return the answer in any order.
//
//  
// Example 1:
//
//
// Input: n = 4, k = 2
// Output:
// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4],
// ]
//
//
// Example 2:
//
//
// Input: n = 1, k = 1
// Output: [[1]]
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 20
// 	1 <= k <= n
//
//


class Solution {
    //77. 给定两个整数n和k，返回1 ... n中所有可能的k个数的组合。组合:与顺序无关。
    public List<List<Integer>> combine(int n, int k) {
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
    	if(n<1 || k>n || k<=0){//不可能形成有效组合
    		return res;
    	}
    	List<Integer> currentList=new ArrayList<Integer>();
    	combine(n, k, 1, currentList, res);
    	return res;
    }
    private void combine(int n,int k,int currentVal,List<Integer> currentList,List<List<Integer>> res){
    	if(currentList.size()==k){//已经有了k个数，无需继续向下试探
    		res.add(new ArrayList<Integer>(currentList));
    		return;
    	}
    	if(currentVal>n || n-currentVal+1<k-currentList.size()){//[currentVal,n]的个数小于还需要的元素个数(k-currentList.size())
    		return;
    	}
    	//把值currentVal加入集合
    	currentList.add(currentVal);
    	combine(n, k, currentVal+1, currentList, res);
    	//不要currentVal,继续向下
    	currentList.remove(currentList.size()-1);
    	combine(n, k, currentVal+1, currentList, res);
    }
}
