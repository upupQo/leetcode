// Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//  
// Example 1:
//
//
// Input: nums1 = [1,3], nums2 = [2]
// Output: 2.00000
// Explanation: merged array = [1,2,3] and median is 2.
//
//
// Example 2:
//
//
// Input: nums1 = [1,2], nums2 = [3,4]
// Output: 2.50000
// Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
// Example 3:
//
//
// Input: nums1 = [0,0], nums2 = [0,0]
// Output: 0.00000
//
//
// Example 4:
//
//
// Input: nums1 = [], nums2 = [1]
// Output: 1.00000
//
//
// Example 5:
//
//
// Input: nums1 = [2], nums2 = []
// Output: 2.00000
//
//
//  
// Constraints:
//
//
// 	nums1.length == m
// 	nums2.length == n
// 	0 <= m <= 1000
// 	0 <= n <= 1000
// 	1 <= m + n <= 2000
// 	-106 <= nums1[i], nums2[i] <= 106
//
//
//  
// Follow up: The overall run time complexity should be O(log (m+n)).


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if(nums1.length==0 || nums2.length==0){//只考虑一个数组为空，不考虑两个数组为空
    		if(nums1.length==0){//统一处理
    			nums1=nums2;
    		}
        	int median=nums1.length/2;//下中位数索引
    		if((nums1.length & 1)==0){//1.nums2长度为偶
    			return (nums1[median-1]+nums1[median])/2.0;
    		}else{//{//1.nums2长度为奇
    			return nums1[median];
    		}
    	}
    	if(nums1.length>nums2.length){//使nums1保存较短数组
    		int[] temp=nums1;
    		nums1=nums2;
    		nums2=temp;
    	}
    	int start1=0,end1=nums1.length-1;
    	int start2=0,end2=nums2.length-1;
    	int count;//当前排除的元素个数
    	int median1,median2;//两个数组的上中位数
    	while(end1-start1>1){//当较短数组还有多余两个元素时--只有两个元素时如果再排除可能导致解丢失
    		median1=(start1+end1)/2;
    		median2=(start2+end2)/2;
    		if(nums1[median1]==nums2[median2]){//case1.上中位数相等
    			if(((end1-start1+1)&1)==1 && ((end2-start2+1)&1)==1){//均为奇数个元素
    				return nums1[median1];
    			}else if(((end1-start1+1)&1)!=1 && ((end2-start2+1)&1)!=1){//均为偶数个元素，key
    				return (nums1[median1]+Math.min(nums1[median1+1], nums2[median2+1]))/2.0;
    			}else{//一奇一偶，画图分析
    				return nums1[median1];
    			}
    		}else if(nums1[median1]>nums2[median2]){
    			if(((end1-start1+1)&1)==1 && ((end2-start2+1)&1)==1){//均为奇数个元素
    				count=end1-median1;//后面的[medain1+1,end1]被排除,共count个
    			}else if(((end1-start1+1)&1)!=1 && ((end2-start2+1)&1)!=1){//均为偶数个元素
    				count=end1-median1-1;//后面的[median1+2,end1]被排除
    			}else{//一奇一偶
    				count=end1-median1;//后面的[medain1+1,end1]被排除,共count个
    			}
    			end1-=count;
    			start2+=count;//前面的[start2,start2+count-1]被排除
    			//key point 为了保证不影响唯一解，前后排除的元素个数不需形同，但对于内部的位置无需考虑
    		}else{
    			count=median1-start1;//前面的[start1,median-1]被排除(四种奇偶组合下均如此)
    			start1+=count;
    			end2-=count;
    		}
    	}
    	median2=(start2+end2)/2;
    	if(start1==end1){//较短数组只有一个元素
    		if(((end2-start2+1)&1)==1){//nums2有奇数个--
    			if(start2==end2){//nums1只有一个notice
    				return (nums1[start1]+nums2[start2])/2.0;
    			}else{//nums1有3，5,7...个
    				if(nums1[start1]>=nums2[median2-1] && nums1[start1]<=nums2[median2+1]){
    					return (nums1[start1]+nums2[median2])/2.0;
    				}else if(nums1[start1]<nums2[median2-1]){
    					return (nums2[median2]+nums2[median2-1])/2.0;
    				}else{
    					return (nums2[median2]+nums2[median2+1])/2.0;
    				}
    			}
    		}else{//nums2有偶数个
    			if(nums1[start1]>=nums2[median2] && nums1[start1]<=nums2[median2+1]){
    				return nums1[start1];
    			}else if(nums1[start1]<nums2[median2]){
    				return nums2[median2];
    			}else{
    				return nums2[median2+1];
    			}
    		}
    	}else if(end1-start1==1){//较短数组有2个元素
    		if(((end2-start2+1)&1)==1){//5个候选
    			return medain3(Math.max(nums1[start1], nums2[median2-1]), nums2[median2], Math.min(nums1[end1], nums2[median2+1]));
    		}else{//6个候选
                if(end2-start2==1){
    				return (Math.max(nums1[start1], nums2[start2])+Math.min(nums1[end1], nums2[end2]))/2.0;
    			}
    			return median4(Math.max(nums1[start1], nums2[median2-1]), nums2[median2], nums2[median2+1], Math.min(nums1[end1], nums2[median2+2]));
    		}
    	}
    	return 0;
    }
     //3个元素的median.Sum-min-max;//可能导致溢出
    private int medain3(int a,int b,int c){
    	int temp;
    	if(a>b){//使a<=b
    		temp=a;
    		a=b;
    		b=temp;
    	}
    	if(a>c){//使a<=c
    		temp=a;
    		a=c;
    		c=temp;
    	}
    	//在较大的两个数中选择较小的
    	return Math.min(b, c);
    }
    //4个数的median
    private double median4(int a,int b,int c,int d){
    	int[] arr=new int[4];
    	arr[0]=a;arr[1]=b;arr[2]=c;arr[3]=d;
    	Arrays.sort(arr);
    	return (arr[1]+arr[2])/2.0;
    }
}
