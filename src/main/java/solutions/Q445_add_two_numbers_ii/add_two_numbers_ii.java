// You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Follow up:
// What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
//
//
//
// Example:
//
// Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 8 -> 0 -> 7
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
        Stack<Integer> stackA=new Stack<Integer>();
        Stack<Integer> stackB=new Stack<Integer>();
        while(l1!=null){
        	stackA.push(l1.val);
        	l1=l1.next;
        }
        while(l2!=null){
        	stackB.push(l2.val);
        	l2=l2.next;
        }
        ListNode head=null;//notice:-高位在链表头,所以用尾插法建链表
        int pre=0;//进位
        int sum;
        ListNode node;
        while(!(stackA.isEmpty() || stackB.isEmpty())){//栈A,B均不为空
        	sum=stackA.pop()+stackB.pop()+pre;
        	node=new ListNode(sum%10);
        	pre=sum/10;
        	node.next=head;
        	head=node;
        }
        //至多有一个stack不为空，统一处理
        if(!stackB.isEmpty()){//栈b不为空，栈a肯定为空
        	stackA=stackB;
        }
        while(!stackA.isEmpty()){
        	sum=stackA.pop()+pre;
        	node=new ListNode(sum%10);
        	pre=sum/10;
        	node.next=head;
        	head=node;
        }
        if(pre!=0){
        	node=new ListNode(pre);
        	node.next=head;
        	head=node;
        }
        return head;
    }
}
