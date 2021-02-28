// Write a program to find the n-th ugly number.
//
// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
//
// Example:
//
//
// Input: n = 10
// Output: 12
// Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
//
// Note:  
//
//
// 	1 is typically treated as an ugly number.
// 	n does not exceed 1690.
//


class Solution {
   public int nthUglyNumber(int n) {
       if(n<=1){
           return 1;
       }
       ArrayList<Integer> list=new ArrayList<Integer>();
       list.add(1);
       int index2=0,index3=0,index5=0;//
       while(n>1){
           list.add(Math.min(list.get(index2)*2, Math.min(list.get(index3)*3, list.get(index5)*5)));
           int size=list.size();
           while(list.get(index2)*2<=list.get(size-1)){
        	   index2++;
           }
           while(list.get(index3)*3<=list.get(size-1)){
        	   index3++;
           }
           while(list.get(index5)*5<=list.get(size-1)){
        	   index5++;
           }
           n--;
       }
       return list.get(list.size()-1);
   }
}
