// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//  
// Example 1:
//
//
// Input: l1 = [2,4,3], l2 = [5,6,4]
// Output: [7,0,8]
// Explanation: 342 + 465 = 807.
//
//
// Example 2:
//
//
// Input: l1 = [0], l2 = [0]
// Output: [0]
//
//
// Example 3:
//
//
// Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
// Output: [8,9,9,9,0,0,0,1]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in each linked list is in the range [1, 100].
// 	0 <= Node.val <= 9
// 	It is guaranteed that the list represents a number that does not have leading zeros.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(-1);
        ListNode tail=dummy;
        int pre=0;//进位值
        while(l1!=null && l2!=null){
            ListNode node=new ListNode((l1.val+l2.val+pre)%10);
            pre=(l1.val+l2.val+pre)/10;
            tail.next=node;
            tail=tail.next;
            l1=l1.next;
            l2=l2.next;
        }
        //l1==null || l2==null || l1==null && l2==null,用l1保存不为null的链表的剩余部分
        if(l1==null){
            l1=l2;
        }
        while(l1!=null){
            ListNode node=new ListNode((l1.val+pre)%10);
            pre=(l1.val+pre)/10;
            tail.next=node;
            tail=tail.next;
            l1=l1.next;
        }
        if(pre!=0){
            ListNode node=new ListNode(pre);
            tail.next=node;
            tail=tail.next;
        }
        tail.next=null;//尾指针置空
        return dummy.next;
    }
}
