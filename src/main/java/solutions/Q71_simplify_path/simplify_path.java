// Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
//
// In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
//
// The canonical path should have the following format:
//
//
// 	The path starts with a single slash '/'.
// 	Any two directories are separated by a single slash '/'.
// 	The path does not end with a trailing '/'.
// 	The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
//
//
// Return the simplified canonical path.
//
//  
// Example 1:
//
//
// Input: path = "/home/"
// Output: "/home"
// Explanation: Note that there is no trailing slash after the last directory name.
//
//
// Example 2:
//
//
// Input: path = "/../"
// Output: "/"
// Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
//
//
// Example 3:
//
//
// Input: path = "/home//foo/"
// Output: "/home/foo"
// Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
//
//
// Example 4:
//
//
// Input: path = "/a/./b/../../c/"
// Output: "/c"
//
//
//  
// Constraints:
//
//
// 	1 <= path.length <= 3000
// 	path consists of English letters, digits, period '.', slash '/' or '_'.
// 	path is a valid absolute Unix path.
//
//


class Solution {
    public String simplifyPath(String path) {
    	int start=0,end;//用[start,end]比较一个路径单元:'/'+name
    	Stack<String> stack=new Stack<String>();
    	String str;
    	while(start<path.length()){
    		end=start+1;
    		while(end<path.length() && path.charAt(end)!='/'){
    			end++;
    		}
    		str=path.substring(start, end);
    		if(!(str.equals("/") || str.equals("/."))){//忽略无实际意义的路径单元'/','/.'
    			if(str.equals("/..")){//回到父级目录
    				if(!stack.isEmpty()){
    					stack.pop();
    				}	
    			}else{//添加一个下级路径单元
    				stack.push(str);
    			}
    		}
    		start=end;
    	}
    	if(stack.isEmpty()){//在系统根目录
    		return "/";
    	}
    	StringBuilder sb=new StringBuilder();
    	for(int i=0;i<stack.size();i++){//对于栈也可以这样顺序访问,就没必要手工模拟栈了
    		sb.append(stack.get(i));
    	}
    	return sb.toString();
    }
}
