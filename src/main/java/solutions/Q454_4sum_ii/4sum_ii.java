// Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
//
// To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
//
// Example:
//
//
// Input:
// A = [ 1, 2]
// B = [-2,-1]
// C = [-1, 2]
// D = [ 0, 2]
//
// Output:
// 2
//
// Explanation:
// The two tuples are:
// 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
// 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
//
//
//  
//


class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count=0;
        int target=0;
        if(A.length==0 || B.length==0 || C.length==0 || D.length==0){
        	return count;
        }
        //1.预处理C,D:(sum,count),用map保存仅由C,D可以得到的和的次数O(n*n)
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<C.length;i++){
        	for(int j=0;j<D.length;j++){
        		int cdSum=C[i]+D[j];
        		if(map.containsKey(cdSum)){
        			map.put(cdSum, map.get(cdSum)+1);
        		}else{
        			map.put(cdSum, 1);
        		}
        	}
        }
        //1.对A.B可以组成的所有和，在map中检查是否有target-sum存在
        for(int i=0;i<A.length;i++){
        	for(int j=0;j<B.length;j++){
        		if(map.containsKey(target-A[i]-B[j])){
        			count+=map.get(target-A[i]-B[j]);
        		}
        	}
        }
        return count;
    }
}
