// Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
//
//  
// Example 1:
//
//
// Input: head = [1,2,3,4,5], left = 2, right = 4
// Output: [1,4,3,2,5]
//
//
// Example 2:
//
//
// Input: head = [5], left = 1, right = 1
// Output: [5]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the list is n.
// 	1 <= n <= 500
// 	-500 <= Node.val <= 500
// 	1 <= left <= right <= n
//
//
//  
// Follow up: Could you do it in one pass?


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next==null || m>=n){//反转一个结点相当于没反转
        	return head;
        }
        //1.找到待反转子链的第一个结点，并记录左字段的尾巴
        ListNode tailPre=null;//记录左段链表的尾巴
        ListNode cur=head;
        while(m>1 && cur!=null){
        	tailPre=cur;
        	cur=cur.next;
        	m--;
        	n--;//记录相对m的偏移
        }
        //while结束后cur应该保存待反转部分的头,即反转后的尾
        if(cur==null){//无需反转
        	return head;
        }
        ListNode tail=cur;//notice,保存(反转后)子链的尾
        //2.进行反转操作
        ListNode pre=null;//执行完后pre应该保存反转后子链的头，cur保存右链的头
        ListNode next=cur.next;//notice
        while(n>0 && cur!=null){//此时m=1,n=3
        	next=cur.next;
        	cur.next=pre;
        	pre=cur;
        	cur=next;
        	n--;
        }
        //3.拼接3个子部分
        if(tailPre!=null){//左子段存在
        	tailPre.next=pre;//左子端的尾指向新头
        	tail.next=cur;//新链的尾指向右字段的头
        }else{//左子段不存在
        	head=pre;//notice.新头作为整体的头
        	tail.next=cur;
        }
        return head;
    }
}
