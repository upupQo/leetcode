// Given the head of a linked list, rotate the list to the right by k places.
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,4,5], k = 2
// Output: [4,5,1,2,3]
//
//
// Example 2:
//
//
// Input: head = [0,1,2], k = 4
// Output: [2,0,1]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is in the range [0, 500].
// 	-100 <= Node.val <= 100
// 	0 <= k <= 2 * 109
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || k<=0){
        	return head;
        }
        int count=0;
        ListNode node=head;
        ListNode tail=head;//预先保存链表尾，将来用
        while(node!=null){
        	tail=node;
        	node=node.next;
        	count++;
        }
        if(k%count==0){//notice,若是链表长度整数倍，相当于没有旋转
        	return head;
        }
        k=k%count;//k∈[1,n-1]
        //找倒数第k+1个结点,由于k<链表长度,所以倒数第k个结点不是第一个结点，所以倒数第k+1个结点是找得到的
        k++;
        ListNode second=head;
        node=head;
        while(k>0){
        	node=node.next;
        	k--;
        }
        //while结束,second将指向倒数第k+1个结点
        while(node!=null){
        	node=node.next;
        	second=second.next;
        }
        tail.next=head;
        head=second.next;
        second.next=null;
        return head;
    }
}
