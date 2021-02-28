// Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
// Example 1:
//
//
// Input: [3,2,1,5,6,4] and k = 2
// Output: 5
//
//
// Example 2:
//
//
// Input: [3,2,3,1,2,4,5,5,6] and k = 4
// Output: 4
//
// Note: 
// You may assume k is always valid, 1 ≤ k ≤ array's length.
//


class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthSmallest(nums,nums.length-k+1);
    }
    public int findKthSmallest(int[] arr, int k) {
        // 输入检查，不合法就返回第1小
        if(k<0) {
            k=1;
        }
        if(k>arr.length) {
            k=arr.length;
        }
        return find(arr, k - 1, 0, arr.length - 1);
    }

       /**
     * 在arr[start,end]中找排序后应该放在index位置的元素。index∈[start,end]
     */
    private int find(int[] arr, int index, int start, int end) {
        if(start==end) {
            return arr[start];
        }
        int key = (int) (Math.random() * (end - start + 1)) + start;// 随机主元的位置
        swap(arr, start, key);// 把主元放在start位置
        int low = start,high = end;//arr[start,low]<=主元--大智若愚
        while (low != high) {
            while (high != low && arr[high] > arr[start]) {
                high--;
            }
            while (low != high && arr[low] <= arr[start]) {
                low++;
            }
            swap(arr, low, high);
        }
    swap(arr,low,start);
        if (low == index) {
            return arr[low];
        } else if (low < index) {
            return find(arr, index, low + 1, end);
        } else {
            return find(arr, index, start, low - 1);
        }
    }

    private void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
