// Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
//  
// Example 1:
// Input: timePoints = ["23:59","00:00"]
// Output: 1
// Example 2:
// Input: timePoints = ["00:00","23:59","00:00"]
// Output: 0
//
//  
// Constraints:
//
//
// 	2 <= timePoints <= 2 * 104
// 	timePoints[i] is in the format "HH:MM".
//
//


class Solution {
      //桶排序思想,只算hour:minute，一共只有24*60个时间点，时间复杂度O(n)
  public int findMinDifference(List<String> timePoints) {
        boolean[] bucket=new boolean[24*60];//默认为false
        int timePoint;//以分钟的形式来表示时间点hour:minute
        String[] time;
        for(int i=0;i<timePoints.size();i++){
        	time=timePoints.get(i).split(":");
        	int hour=Integer.valueOf(time[0]);
        	int minute=Integer.valueOf(time[1]);
        	if(bucket[hour*60+minute]==true){//如果已经出现，则最小时差为0
        		return 0;
        	}else{
        	   bucket[hour*60+minute]=true;
        	}
        }
        int preTime;
        int i=0;
        int mindiff=Integer.MAX_VALUE;
        int early;//最早时间点
        while(i<bucket.length && bucket[i]==false){//找到第一个出现的时间点
        	i++;
        }
        preTime=i++;
        early=preTime;
        while(i<bucket.length){
        	if(!bucket[i]){
        		i++;
        		continue;
        	}else{
        		mindiff=Math.min(mindiff, i-preTime);
        		preTime=i;
        		i++;
        	}
        }
        mindiff=Math.min(mindiff, 1440-preTime+early);//notice.最晚时间与最早时间的间隔可能最小
        return mindiff;
    }
}
