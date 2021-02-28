// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//
// P   A   H   N
// A P L S I I G
// Y   I   R
//
//
// And then read line by line: "PAHNAPLSIIGYIR"
//
// Write the code that will take a string and make this conversion given a number of rows:
//
//
// string convert(string s, int numRows);
//
//
//  
// Example 1:
//
//
// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"
//
//
// Example 2:
//
//
// Input: s = "PAYPALISHIRING", numRows = 4
// Output: "PINALSIGYAHRPI"
// Explanation:
// P     I    N
// A   L S  I G
// Y A   H R
// P     I
//
//
// Example 3:
//
//
// Input: s = "A", numRows = 1
// Output: "A"
//
//
//  
// Constraints:
//
//
// 	1 <= s.length <= 1000
// 	s consists of English letters (lower-case and upper-case), ',' and '.'.
// 	1 <= numRows <= 1000
//
//


class Solution {
    public String convert(String s, int numRows) {
        if(s.length()<=1){
        	return s;
        }
        StringBuffer[] rows=new StringBuffer[numRows];
        for(int i=0;i<numRows;i++){
        	rows[i]=new StringBuffer();
        }
        int index=0;
        int currentRow;
        //模拟元素移动轨迹
        while(index<s.length()){
        	//先向下
        	currentRow=0;
        	while(currentRow<numRows && index<s.length()){
        		rows[currentRow++].append(s.charAt(index++));
        	}
        	currentRow-=2;//notice
        	//再斜线
        	while(currentRow>0 && index<s.length()){
        		rows[currentRow--].append(s.charAt(index++));
        	}
        }
        for(int i=1;i<numRows;i++){
        	rows[0].append(rows[i]);
        }
        return rows[0].toString();
    }
}
