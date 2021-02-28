// You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
// Merge all the linked-lists into one sorted linked-list and return it.
//
//  
// Example 1:
//
//
// Input: lists = [[1,4,5],[1,3,4],[2,6]]
// Output: [1,1,2,3,4,4,5,6]
// Explanation: The linked-lists are:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// merging them into one sorted list:
// 1->1->2->3->4->4->5->6
//
//
// Example 2:
//
//
// Input: lists = []
// Output: []
//
//
// Example 3:
//
//
// Input: lists = [[]]
// Output: []
//
//
//  
// Constraints:
//
//
// 	k == lists.length
// 	0 <= k <= 10^4
// 	0 <= lists[i].length <= 500
// 	-10^4 <= lists[i][j] <= 10^4
// 	lists[i] is sorted in ascending order.
// 	The sum of lists[i].length won't exceed 10^4.
//
//


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){//初始化PriorityQueue如果initialCapacity<1会抛出llegalArgumentException 
    		return null;
    	}
        if(lists.length==1){//只有一个链表
    		return lists[0];
    	}
        ListNode dummy=new ListNode(0);//新链表的虚拟头
        ListNode tail=dummy;//新链表的尾
        PriorityQueue<ListNode> queue=new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				return o1.val-o2.val;
			}
		});//定义优先队列以及比较规则
        //1.将各非空链表头加入小堆
        for(int i=0;i<lists.length;i++){
        	if(lists[i]!=null){
        		queue.add(lists[i]);
        	}
        }
        //2.不断取当前最小元素
        ListNode node;
        while(!queue.isEmpty()){
        	node=queue.poll();//取出堆顶元素
        	tail.next=node;
        	tail=node;
        	node=node.next;
        	if(node!=null){//key
        		queue.add(node);
        	}
        }
        return dummy.next;
    }
}
