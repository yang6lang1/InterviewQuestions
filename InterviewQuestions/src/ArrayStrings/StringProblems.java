package ArrayStrings;

import java.util.ArrayList;
import java.util.List;

public class StringProblems {

	/* Given a number n, find the largest number just smaller than n 
	 * that can be formed using the same digits as n.
	 * Eg: 1342, return 1324*/
	public int findLargestSmallerNumber(int n) throws Exception{
		// check int valid
		// check int has num with the correct answer: eg 1234
		if(n < 0) throw new Exception("Negative number.");
		
		int scale = 10;
		int currDig = 0;
		int[] status = new int[10];
		int output = 0;
		
		outerLoop:
		do{
			currDig = n - (n / scale) * scale;
			n /= scale;
			for(int i = currDig - 1; i >= 0; i--){
				if(status[i] > 0){
					output = n * scale + i;
					status[i]--;
					status[currDig]++;
					break outerLoop;
				}
			}
			status[currDig]++;
			if(n == 0){
				throw new Exception("Invalid number.");
			}
		}
		while(n != 0);
		
		for(int i = 9; i >= 0; i--){
			while(status[i] > 0){
				output = output * scale + i;
				status[i] --;
			}
		}
		 
		return output;
	}
	
	/* Given two strings, determine whether they are anagrams (Mar 14)
	 * */
	public boolean checkStringAnagrams(String s1, String s2){
		boolean areAnagrams = false;
		
		return areAnagrams;
	}
	
	/* Given a list of strings, group strings, group them by anagrams (Mar 15)
	 * */
	public List<String> groupStrings(List<String> strList) {
		List<String> list = new ArrayList<String>();
		
		return list;
	}
	
	public static void main(String[] args){
		StringProblems sp =new StringProblems();
		try {
	    System.out.println(sp.findLargestSmallerNumber(174567));
    } catch (Exception e) {
	    System.out.println(e.getMessage());
    }
	}
}
