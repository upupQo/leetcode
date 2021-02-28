// Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//  
// Example 1:
//
//
// Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
// Output: [[1,6],[8,10],[15,18]]
// Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//
//
// Example 2:
//
//
// Input: intervals = [[1,4],[4,5]]
// Output: [[1,5]]
// Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//
//  
// Constraints:
//
//
// 	1 <= intervals.length <= 104
// 	intervals[i].length == 2
// 	0 <= starti <= endi <= 104
//
//


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
    	List<Interval> res=new ArrayList<Interval>();
        Interval current;
        //1.key多区间集合按左端点排序
        Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			}
		});
        int i=0;//待合并部分的索引
        while(i<intervals.size()){
            current=intervals.get(i);
        	while(i<intervals.size() && intervals.get(i).start<=current.end){
        		current.end=Math.max(current.end, intervals.get(i).end);//合并两个区间
        		i++;
        	}
        	res.add(current);
        }
        return res;
    }
}
