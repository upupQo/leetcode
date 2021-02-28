// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
//
// You may assume that the intervals were initially sorted according to their start times.
//
//  
// Example 1:
//
//
// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]
//
//
// Example 2:
//
//
// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// Output: [[1,2],[3,10],[12,16]]
// Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
//
// Example 3:
//
//
// Input: intervals = [], newInterval = [5,7]
// Output: [[5,7]]
//
//
// Example 4:
//
//
// Input: intervals = [[1,5]], newInterval = [2,3]
// Output: [[1,5]]
//
//
// Example 5:
//
//
// Input: intervals = [[1,5]], newInterval = [2,7]
// Output: [[1,7]]
//
//
//  
// Constraints:
//
//
// 	0 <= intervals.length <= 104
// 	intervals[i].length == 2
// 	0 <= intervals[i][0] <= intervals[i][1] <= 105
// 	intervals is sorted by intervals[i][0] in ascending order.
// 	newInterval.length == 2
// 	0 <= newInterval[0] <= newInterval[1] <= 105
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	List<Interval> res=new ArrayList<Interval>();
    	int i=0;
    	while(i<intervals.size() && intervals.get(i).end<newInterval.start){
    		res.add(intervals.get(i));
    		i++;
    	}
    	while(i<intervals.size() && intervals.get(i).start<=newInterval.end){//肯定有交集
    		newInterval.start=Math.min(newInterval.start, intervals.get(i).start);
    		newInterval.end=Math.max(newInterval.end, intervals.get(i).end);
    		i++;
    	}
    	res.add(newInterval);
    	while(i<intervals.size()){
    		res.add(intervals.get(i));
    		i++;
    	}
    	return res;
    }
}
