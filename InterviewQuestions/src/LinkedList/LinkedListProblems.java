package LinkedList;

public class LinkedListProblems {

	/**Remove duplicates in an unsorted linked list*/
	
	//Implement a method to add two big numbers represented by string
	public String sumOfNums(String a, String b) throws Exception {
		//generate 1 linekd list that contains the sum: sumList a = 321, b = 32, sum = 3->5->3
		//	start from the end of both string numbers and traverse to the front O(n)
		//generate the stirng that contains the sum value
		if (a == null && b == null) return "0";
		int aPtr = a.length() -1 , bPtr= b.length() - 1;
		if (aPtr < 0 && aPtr < 0) return "0";
		if (aPtr < 0) return b;
		if (bPtr < 0) return a;
		
		int carry = 0;
		Node<Integer> sumLL = null;
		while(aPtr >=0 || bPtr >= 0) {
			Node<Integer> newNode = new Node<Integer>();
			try {
				if(aPtr < 0) aPtr = 0;
				if(bPtr < 0) bPtr = 0;
				//use carry bit to create the value
				int sum = Integer.valueOf(a.charAt(aPtr)+"") + Integer.valueOf(b.charAt(bPtr)+"") + carry;
				carry = sum >= 10 ? 1 : 0;	//update carry bit
				newNode.setElement(sum % 10);	//only need the least significant digit
			} catch (Exception e){
				throw new Exception(e.getMessage());
			}
			
			if(sumLL == null){
				sumLL = newNode;
			}
			else{
				newNode.setNext(sumLL);
				sumLL = newNode;
			}
			
			aPtr--;
			if(aPtr < 0){
				a = "0";
			}
			bPtr--;
			if(bPtr < 0){
				b = "0";
			}
		}
		
		//additional carry bit check
		if(carry > 0) {
			Node<Integer> carryNode = new Node<Integer>();
			carryNode.setElement(1);
			carryNode.setNext(sumLL);
			sumLL = carryNode;
		}
		
		StringBuffer s = new StringBuffer();
		for(Node<Integer> curr = sumLL; curr != null; curr = curr.getNext()){
			s.append(curr.getElement());
		}
		
		return s.toString();
	}
	
	public static void main(String[] args){
		LinkedListProblems p = new LinkedListProblems();
		String a = "321";
		String b = "32";
		try {
	    System.out.println(p.sumOfNums(a, b));
    } catch (Exception e) {
    	e.printStackTrace();
    }

		System.out.println();
		System.out.println("LinkedList test:");
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addBack(5);
		list.addBack(6);
		list.addBack(7);
		list.addBack(8);
		list.addBack(9);
		list.printAll();
		int index = 3;
		System.out.println("Delete index " + index + " : ");
		list.delete(index);
		list.printAll();
		System.out.println();
	}
}
