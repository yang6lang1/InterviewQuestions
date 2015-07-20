package ArrayStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringProblems {

	/** Implement an algorithm to determine if a string has all unique characters.
	 *  What if you cannot use additional data structure (Mar 17)
	 * */
	public boolean stringHasUniqueChars(String s){
		//approach 1: use boolean[] status = new boolean[256] - assume ASCII characters
		//approach 2: use Set<Character> status = new HashSet<Character>()
		//approach 3: use double and bit operation - assume 'a' - 'Z'
		return stringHasUniqueChars_2(s);
	}

	/**
	 * approach 2: use Set<Character> status = new HashSet<Character>()
	 * */
	private boolean stringHasUniqueChars_1(String s){
		if (s == null || s.length() <= 1) return true;

		Set<Character> status = new HashSet<Character>();
		for(char c : s.toCharArray()){
			if(status.contains(c)){
				return false;
			}
			else{
				status.add(c);
			}
		}

		return true;
	}

	/** approach 3: use double and bit operation - assume 'a' - 'Z'
	 * */
	private boolean stringHasUniqueChars_2(String s){
		if(s == null || s.length() <= 1) return true;
		long status = 0;

		for(char c : s.toCharArray()){
			int offset = c - 'a';
			System.out.println(offset);
			if ((status & (1 << offset)) != 0){
				return false;
			}
			else{
				status |= (1 << offset);
			}
		}

		return true;
	}


	/** Replace all the spaces in a string with "%20"
	 * 	You can assume the string has enough spaces at the back
	 * */
	public String replaceSpaces(String oldStr, int trueLen){
		if(oldStr == null || oldStr.length() == 0) return "";
		char[] chars = oldStr.toCharArray();

		int p1 = trueLen - 1, p2 = chars.length - 1;
		while(p1 >= 0) {
			if(chars[p1] == ' '){
				chars[p2--] = '0';
				chars[p2--] = '2';
				chars[p2--] = '%';
			}
			else{
				chars[p2--] = chars[p1];
			}
			p1--;
		}

		return new String(chars);
	}

	/** Check if two strings are permutations of each other
	 * */
	private String sortString(String s){
		if (s == null) return null;
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	private boolean isPermutation(String s1, String s2){
		return sortString(s1).equals(sortString(s2));
	}

	private boolean isPermutation2(String s1, String s2){
		if(s1 == null || s2 == null) return false;
		if(s1.length() != s2.length()) return false;

		int[] count = new int[256];
		for(int i = 0; i < s1.length(); i++){
			count[s1.charAt(i)]++;
		}

		for(int i = 0; i < s2.length(); i++){
			if(count[s2.charAt(i)] <= 0){
				return false;
			}
			else{
				count[s2.charAt(i)]--;
			}
		}
		return true;
	}

	private String compression(String str){
		if(str == null) return null;

		StringBuffer sb = new StringBuffer();
		char prev = '\0';
		int count = 0;

		for(int i = 0; i < str.length(); i++){
			if(prev == '\0'){
				prev = str.charAt(i);
				count = 1;
				sb.append(prev);
			}
			else{
				if(prev == str.charAt(i)){
					count++;
				}
				else{
					sb.append(count);
					prev = str.charAt(i);
					count = 1;
					sb.append(prev);
				}
			}

			if(str.length() == (i+1)){
				sb.append(count);
				break;      
			}
		}

		String newStr = sb.toString();
		if(newStr.length() < str.length()){
			return newStr;
		}
		else{
			return str;
		}
	}

	public boolean isSubstring(String str, String sub){
		if(str == null || sub == null) return false;
		if(str.length() < sub.length()) return false;
		if(str.length() == sub.length()) return str.compareTo(sub) == 0? true : false;

		for(int i = 0; i < str.length() - sub.length(); i++){
			for(int j = 0; j < sub.length(); j++){
				if(sub.charAt(j) != str.charAt(i+j)){
					break;
				}
				if(j == sub.length() -1){
					return true;
				}
			}
		}

		return false;
	}

	public boolean isRotataion(String s1, String s2){
		if(s1 == null || s2 == null) return false;
		if(s1.length() != s2.length()) return false;

		String longStr = s1.concat(s1);
		return isSubstring(longStr, s2);
	}

	/** Given a number n, find the largest number just smaller than n 
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

	/** Given two strings, determine whether they are anagrams (Mar 14)
	 * This solution is case sensitive
	 * */
	public boolean checkAnagrams(String s1, String s2){
		if(s1 == null || s2 == null) return false;
		Map<Character, Integer> status = new HashMap<Character, Integer>();
		for(char c : s1.toCharArray()){
			if(status.containsKey(c)){
				status.put(c, status.remove(c) + 1);
			}
			else{
				status.put(c, 1);
			}
		}

		for(char c : s2.toCharArray()){
			if(status.containsKey(c) && status.get(c) >= 1){
				if(status.get(c) == 1){
					status.remove(c);
				}
				else{
					status.put(c, status.remove(c) - 1);
				}
			}
			else {
				return false;
			}
		}

		if(status.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}

	public void useGroupStrings(){
		List<String> list = new ArrayList<String>();
		list.add("bbccc");
		list.add("anagram");
		list.add("aaaa");
		list.add("margana");
		list.add("cbcbc");
		list.add("a");
		System.out.println();
		System.out.println("Original string list:");
		for(String s : list){
			System.out.println(s);
		}

		System.out.println();
		System.out.println("Grouped string list");
		list = groupStrings(list);
		for(String s : list){
			System.out.println(s);
		}
	}

	/** Given a list of strings, group strings, group them by anagrams (Mar 15)
	 * */
	public List<String> groupStrings(List<String> strList) {
		if(strList == null || strList.isEmpty()) return strList;
		List<String> list = new ArrayList<String>();
		Map<String, List<String>> groupMap = new HashMap<String, List<String>>();

		//for each string, find its base form: average # of chars in each string m -> O(m), O(m)
		//insert this into a hashmap: baseString -> string in a list
		//  - if no key, put(key, list with one node)
		//  - if key exists, map.get(key).add(new node);
		String anagram = "";
		for(String s : strList){
			anagram = findAnagramForm(s);
			if(groupMap.containsKey(anagram)){
				groupMap.get(anagram).add(s);
			}
			else{
				List<String> newList = new ArrayList<String>();
				newList.add(s);
				groupMap.put(anagram, newList);
			}
		}

		//traverse the map and all its list and copy them one by one into the output list
		for(Map.Entry<String, List<String>> e : groupMap.entrySet()){
			for(String s: e.getValue()){
				list.add(s);
			}
		}

		return list;
	}
	private String findAnagramForm(String s){
		int[] chars = new int[256];
		for(char c : s.toCharArray()){
			chars[c]++;
		}

		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < chars.length; i++){
			while(chars[i] > 0){
				sb.append((char)i);
				chars[i]--;
			}
		}

		return sb.toString();
	}

	public static void main(String[] args){
		StringProblems sp =new StringProblems();
		try {
			System.out.println(sp.findLargestSmallerNumber(174567));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		String s1 = "daC", s2 = "Cad";
		System.out.println("String s1: " + s1 + " and s2: "+ s2 + " are anagrams? " +sp.checkAnagrams(s1, s2));

		System.out.println();
		System.out.println("String s1: " + s1 + " and s2: "+ s2 + " are permutations? " +sp.isPermutation2(s1, s2));

		sp.useGroupStrings();

		System.out.println();
		String s = "alex";
		System.out.println("String '" + s + "' contains only unique characters: " + sp.stringHasUniqueChars(s));

		System.out.println();
		s = "al e x    ";
		System.out.println(sp.replaceSpaces(s, 6));

		System.out.println();
		s ="aaabbba";
		System.out.println("Compressed string: \"" + sp.compression(s) + "\"");
		System.out.println();

		System.out.println();
		s ="abcdef";
		String sub = "abcde";
		System.out.println("Is string " + sub + " a substring of " + s + "? " + sp.isSubstring(s, sub));
		System.out.println();

		System.out.println();
		s ="abcdef";
		sub = "efabcd";
		System.out.println("Is string " + sub + " a rotation of " + s + "? " + sp.isRotataion(s, sub));
		System.out.println();
	}
}
