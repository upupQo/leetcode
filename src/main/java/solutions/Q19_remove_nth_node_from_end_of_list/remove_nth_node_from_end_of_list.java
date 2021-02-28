// Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
// Follow up: Could you do this in one pass?
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,4,5], n = 2
// Output: [1,2,3,5]
//
//
// Example 2:
//
//
// Input: head = [1], n = 1
// Output: []
//
//
// Example 3:
//
//
// Input: head = [1,2], n = 1
// Output: [1]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is sz.
// 	1 <= sz <= 30
// 	0 <= Node.val <= 100
// 	1 <= n <= sz
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
    //思路：找到倒数第n+1个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if(n<=0){
    		return head;
    	}
        ListNode dummy=new ListNode(0);//建一个虚拟头结点，若要删除倒数第n个结点，倒数第n+1个结点还是存在的即dummy。key简化流程
        dummy.next=head;
        ListNode first=dummy;
        ListNode second=first;
        while(n>=0 && first!=null){//first走n+1步,使得second相距first为n+1
        	first=first.next;
        	n--;
        }
        if(n>=0){//n大于输入链表的元素个数，直接返回
        	return head;
        }
        while(first!=null){
        	first=first.next;
        	second=second.next;
        }
        second.next=second.next.next;
        return dummy.next;
    }
}
