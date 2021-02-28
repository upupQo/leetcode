// Given the head of a singly linked list, reverse the list, and return the reversed list.
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,4,5]
// Output: [5,4,3,2,1]
//
//
// Example 2:
//
//
// Input: head = [1,2]
// Output: [2,1]
//
//
// Example 3:
//
//
// Input: head = []
// Output: []
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is the range [0, 5000].
// 	-5000 <= Node.val <= 5000
//
//
//  
// Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
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
    public ListNode reverseList(ListNode head) {
        ListNode pre=null;
        ListNode next;
        while(head!=null){
        	next=head.next;//保存next指针
        	head.next=pre;
        	pre=head;
        	head=next;
        }
        return pre;//当head为null时，pre即为新链的头 
    }
}
