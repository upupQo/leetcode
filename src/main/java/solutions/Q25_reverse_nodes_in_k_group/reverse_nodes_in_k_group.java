// Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//
// k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
//
// Follow up:
//
//
// 	Could you solve the problem in O(1) extra memory space?
// 	You may not alter the values in the list's nodes, only nodes itself may be changed.
//
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,4,5], k = 2
// Output: [2,1,4,3,5]
//
//
// Example 2:
//
//
// Input: head = [1,2,3,4,5], k = 3
// Output: [3,2,1,4,5]
//
//
// Example 3:
//
//
// Input: head = [1,2,3,4,5], k = 1
// Output: [1,2,3,4,5]
//
//
// Example 4:
//
//
// Input: head = [1], k = 1
// Output: [1]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is in the range sz.
// 	1 <= sz <= 5000
// 	0 <= Node.val <= 1000
// 	1 <= k <= sz
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k<=0){
        	return head;
        }
        ListNode dummy=new ListNode(0);
        ListNode tail=dummy;
        int count=0;
        ListNode current;
        while(head!=null){//‘head代表带反转部分链表的头’
        	current=head;
        	//检测剩下的结点是否足够k个
        	while(count<k && current!=null){
        		count++;
        		current=current.next;
        	}
        	if(count<k){//剩下的结点不足k个,直接接上剩余部分返回
        		tail.next=head;
        		break;
        	}
        	current=head;
        	ListNode pre=null;
        	ListNode next;
        	//反转k个结点，while结束pre将保存k子链的头，current指向未反转部分
        	while(count>0){
        		next=current.next;
        		current.next=pre;
        		pre=current;
        		current=next;
        		count--;
        	}
        	tail.next=pre;
        	tail=head;//此时head已经成为k子链反转后的尾
        	head=current;//更新head
        }
        return dummy.next;
    }
}
