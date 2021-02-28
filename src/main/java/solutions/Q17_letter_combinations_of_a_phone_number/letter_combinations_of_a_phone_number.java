// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//
// A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//  
// Example 1:
//
//
// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// Example 2:
//
//
// Input: digits = ""
// Output: []
//
//
// Example 3:
//
//
// Input: digits = "2"
// Output: ["a","b","c"]
//
//
//  
// Constraints:
//
//
// 	0 <= digits.length <= 4
// 	digits[i] is a digit in the range ['2', '9'].
//
//


class Solution{
    	private String[] dict={"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};//数字对应表
    	private List<String> list=new ArrayList<String>();
        public List<String> letterCombinations(String digits) {
            list.clear();
            if(digits==null || digits.length()==0){
            	return list;
            }
            letterCombinations(new StringBuilder(), 0, digits);
            return list;
        }
        //sb:digits[0,i-1]已经形成的Letter Combinations
        private void letterCombinations(StringBuilder sb,int i,String digits){
        	if(i==digits.length()){
        		list.add(sb.toString());
        		return;
        	}
        	String str=dict[digits.charAt(i)-'0'];
        	for(int j=0;j<str.length();j++){//尝试每个可能放在i位置
        		sb.append(str.charAt(j));
        		letterCombinations(sb, i+1, digits);
        		sb.deleteCharAt(i);//回溯
        	}
        }
    }
