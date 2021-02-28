// Given a singly linked list, determine if it is a palindrome.
//
// Example 1:
//
//
// Input: 1->2
// Output: false
//
// Example 2:
//
//
// Input: 1->2->2->1
// Output: true
//
// Follow up:
// Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null){//没有结点或只有一个结点
        	return true;
        }
        int length=1;//保存链表长度
        ListNode node=head;
        while(node!=null){
        	length++;
        	node=node.next;
        }
        //1.从头出发走一半--7个结点node最终指向第4个结点,8个结点node最终指向第5个结点
        node=head;
        length/=2;//变量复用,需要走的步数
        while(length>0){//找到分界点(中点)
        	node=node.next;
        	length--;
        }
        //2.反转后半部分链表,最终preNode将保存反转后的表头
        ListNode preNode=node;
        ListNode next;
        node=node.next;
        preNode.next=null;//notice,将中点的next指针置为空
        while(node!=null){
        	next=node.next;
        	node.next=preNode;
        	preNode=node;
        	node=next;
        }
        //3.从两头开始逐个比较
        ListNode tail=preNode;//保存尾巴，用于还原后半部分链表
        node=head;//变量复用
        while(node!=null && preNode!=null){
        	if(node.val!=preNode.val){
        		return false;
        	}
        	node=node.next;
        	preNode=preNode.next;
        }
        //4.还原后半部分链表
        preNode=null;
        while(tail!=null){
        	next=tail.next;
        	tail.next=preNode;
        	preNode=tail;
        	tail=next;
        }
        return true;
    }
}
