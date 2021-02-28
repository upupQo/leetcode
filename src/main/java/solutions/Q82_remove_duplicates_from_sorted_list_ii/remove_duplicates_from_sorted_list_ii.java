// Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,3,4,4,5]
// Output: [1,2,5]
//
//
// Example 2:
//
//
// Input: head = [1,1,1,2,3]
// Output: [2,3]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is in the range [0, 300].
// 	-100 <= Node.val <= 100
// 	The list is guaranteed to be sorted in ascending order.
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=new ListNode(0);//虚拟头
        ListNode tail=dummy;//得到的有效新链的尾结点
        ListNode current=head;//当前待考虑结点
        while(current!=null){
        	if(current.next!=null && current.next.val==current.val){//把值为val的结点全部过滤
        		int val=current.val;
        		while(current!=null && current.val==val){
        			current=current.next;
        		}
        	}else{//该结点可用
        		tail.next=current;
        		tail=current;
        		current=current.next;
        	}
        }
        tail.next=null;//notice;
        return dummy.next;
    }
}
