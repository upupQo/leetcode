// Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
// Implement the LRUCache class:
//
//
// 	LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
// 	int get(int key) Return the value of the key if the key exists, otherwise return -1.
// 	void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//
//
// Follow up:
// Could you do get and put in O(1) time complexity?
//
//  
// Example 1:
//
//
// Input
// ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
// Output
// [null, null, null, 1, null, -1, null, -1, 3, 4]
//
// Explanation
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 1); // cache is {1=1}
// lRUCache.put(2, 2); // cache is {1=1, 2=2}
// lRUCache.get(1);    // return 1
// lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
// lRUCache.get(2);    // returns -1 (not found)
// lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
// lRUCache.get(1);    // return -1 (not found)
// lRUCache.get(3);    // return 3
// lRUCache.get(4);    // return 4
//
//
//  
// Constraints:
//
//
// 	1 <= capacity <= 3000
// 	0 <= key <= 3000
// 	0 <= value <= 104
// 	At most 3 * 104 calls will be made to get and put.
//
//


class LRUCache {
    	private class Node{
    		private int key;
    		private int value;
    		private	Node pre;
    		private Node next;
    		public Node(int key,int value){
    			this.key=key;
    			this.value=value;
    			this.pre=null;
    			this.next=null;
    		}
    	}
    	private Node dummy;
    	private Node tail;//表头最新，表尾最老
    	private int size;//链表中已有元素个数--不算头结点
    	private int capacity;
    	private Map<Integer,Node> map;//通过key，定位Node
        public LRUCache(int capacity) {
        	if(capacity<=0){
        		throw new IllegalArgumentException();
        	}
        	this.capacity=capacity;
        	this.size=0;
        	this.dummy=new Node(-1, 0);//虚拟头
        	this.tail=dummy;
        	this.map=new HashMap<Integer,Node>();
        }
        public int get(int key) {
            if(map.containsKey(key)){
            	moveToHead(map.get(key));
            	return map.get(key).value;
            }else{
            	return -1;
            }
        }
        //update || insert
        public void put(int key, int value) {
        	if(map.containsKey(key)){//update
        		map.get(key).value=value;
        		moveToHead(map.get(key));
        	}else{//insert
        		if(size==capacity){//已满，移除最老元素
                    map.remove(tail.key);//删除映射
        			tail=tail.pre;
        			tail.next=null;
        		}else{
        			size++;
        		}
    			Node node=new Node(key,value);
    			map.put(key, node);//添加一个映射
    			addToHead(node);
        	}
        }
        /**
         * 把链表中已经存在的一个结点移动到表头
         */
        private void moveToHead(Node node){
        	if(dummy.next==node){//本省就是第一个有效结点,相当于没有移动
        		return;
        	}
        	node.pre.next=node.next;
        	if(node==tail){//是最后一个结点，没有下一个结点但要更新尾指针
        		tail=node.pre;
        	}else{
        		node.next.pre=node.pre;
        	}
        	addToHead(node);
        }
        /**
         * 将一个结点插入到表头
         */
        private void addToHead(Node node){
        	if(dummy.next!=null){
        		dummy.next.pre=node;
        	}
        	node.next=dummy.next;
        	node.pre=dummy;
        	dummy.next=node;
        	if(tail==dummy){
        		tail=node;
        	}
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
