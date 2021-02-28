// Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
//
// If possible, output any possible result.  If not possible, return the empty string.
//
// Example 1:
//
//
// Input: S = "aab"
// Output: "aba"
//
//
// Example 2:
//
//
// Input: S = "aaab"
// Output: ""
//
//
// Note:
//
//
// 	S will consist of lowercase letters and have length in range [1, 500].
//
//
//  
//


class Solution{
        private class Cell implements Comparable<Cell>{
        	char ch;
        	int count;
        	public Cell(char ch,int count){
        		this.ch=ch;
        		this.count=count;
        	}
			@Override
			public int compareTo(Cell other) {
				return other.count-this.count;//要大堆,PriorityQueue默认实现为小堆
			}
        }
        //思路:尽量先交错使用频次高的--贪心,子问题,优先队列,
        public String reorganizeString(String S) {
        	//1.预处理统计词频
        	HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        	char ch;
        	int count;
        	for(int i=0;i<S.length();i++){
        		ch=S.charAt(i);
        		count=1;//字符ch出现的频次
        		if(map.containsKey(ch)){
        			count+=map.get(ch);
        		}
        		if(count>(S.length()/2+1)){//频次>(n/2+1);
        			return "";
        		}
        		map.put(ch, count);
        	}
        	//2.用词频做为输入初始化优先队列
        	PriorityQueue<Cell> queue=new PriorityQueue<Cell>();
        	for(char c : map.keySet()){
        		queue.add(new Cell(c,map.get(c)));
        	}
        	//3.
        	StringBuilder sb=new StringBuilder();
        	Cell first,second;
        	while(queue.size()>=2){
        		first=queue.poll();//现存最高词频
        		second=queue.poll();//现存次高词频second.count<=first.count;
        		while(second.count>0){
        			sb.append(first.ch);
        			sb.append(second.ch);
        			first.count--;
        			second.count--;
        		}
        		if(first.count>0){//keyPoint,用词频最高和词频次高的元素先重组成无重复字符串(贪心),若有first有剩余,继续留给子问题,second不会有剩余。
        			queue.add(first);
        		}
        	}
        	if(queue.size()==1){
        		if(queue.peek().count>1){
        			return "";
        		}else{
        			sb.append(queue.peek().ch);
        		}
        	}
        	return sb.toString();
        }
}
