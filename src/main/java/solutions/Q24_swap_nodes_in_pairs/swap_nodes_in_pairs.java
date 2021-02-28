// Given a linked list, swap every two adjacent nodes and return its head.
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,4]
// Output: [2,1,4,3]
//
//
// Example 2:
//
//
// Input: head = []
// Output: []
//
//
// Example 3:
//
//
// Input: head = [1]
// Output: [1]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is in the range [0, 100].
// 	0 <= Node.val <= 100
//
//
//  
// Follow up: Can you solve the problem without modifying the values in the list's nodes? (i.e., Only nodes themselves may be changed.)


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {//用head保存待反转链表的头
        if(head==null || head.next==null){
            return head;
        }
        ListNode dummy=new ListNode(0);//新链的虚拟头结点
        ListNode tail=dummy;//新链的尾
        ListNode first,second;//保存第一，第二个结点
        while(head!=null){
        	//1.获取两个临时变量first，second结点
        	first=head;
        	second=head.next;
        	if(second!=null){
        		head=second.next;//'用head保存待反转链表的头'
        	}else{
        		head=null;
        	}
        	//2.
        	if(second!=null){
        		tail.next=second;
        		second.next=first;
        		tail=first;
        	}else{
        		tail.next=first;
        		tail=first;
        	}
        }
        tail.next=null;//notice，不然可能在链尾形成两个节点的环
        return dummy.next;
    }
}
