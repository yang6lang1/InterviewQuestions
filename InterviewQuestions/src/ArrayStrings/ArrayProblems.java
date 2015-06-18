package ArrayStrings;

public class ArrayProblems {

	public void removeDuplicates(char[] str){
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		
		int tail = 1;
		for(int i = 0; i < str.length; i++){
			int j = 0;
			for(j = 0; j < tail; j++){
				if(str[i] == str[j]) break;
			}
			if(j == tail){
				str[tail] = str[i];
				tail++;
			}
		}
	}
	
	public static void main(String[] args) {

	}
}
