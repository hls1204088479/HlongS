package oldTree;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	
	public static void main(String[] args) {
		List<List<String>> partition = partition("vcvcvaxzvccxbccb");
		
		for (List<String> list : partition) {
			for (String string : list) {
				System.out.print(string+",");
			}
			System.out.println();
		}
	}
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    static List R = new ArrayList<ArrayList<String>>();
    public static List<List<String>> partition(String s) {
        // write your code here
        boolean mark[] = new boolean[s.length()];
        fun(s,0,mark);
        return R;
    }
    static void fun(String str, int index,boolean[] mark){
        if(index >= str.length()){
            List<String> L = new ArrayList<String>();
            int beg=0,last=0;
            while(last < str.length()){
                if(mark[last] == true){
                    L.add(str.substring(beg, last+1));
                    beg = last+1;
                }
                ++last;
            }
            R.add(L);
        }
        for(int i=index;i < str.length();++i){
            if(isOK(str, index, i)){
                mark[i] = true;
                fun(str, i+1, mark);
                mark[i] = false;
            }
        }
    }
    static boolean isOK(String str, int beg, int last){
        while(beg < last&&str.charAt(beg)==str.charAt(last)){
            ++beg;
            --last;
        }
        if(beg < last)
            return false;
        else
            return true;
    }
}