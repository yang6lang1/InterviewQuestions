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

	/**
	 * You are given two non-empty linked lists representing two non-negative integers.
	 * The digits are stored in reverse order and each of their nodes contain a single digit.
	 * Add the two numbers and return it as a linked list.
	 *
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 * Example:
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 * Explanation: 342 + 465 = 807.
	 */
	public Node<Integer> addTwoNumbers(Node<Integer> l1, Node<Integer> l2) {
		Node<Integer> p1 = l1, p2 = l2, result = null, resultEnd = null;
		boolean overflow = false;
		while (p1 != null || p2 != null) {
			int valOne = p1 != null ? p1.getElement() : 0;
			int valTwo = p2 != null ? p2.getElement() : 0;
			int sum = (valOne + valTwo + (overflow ? 1 : 0)) % 10;
			overflow = (valOne + valTwo + (overflow ? 1 : 0)) >= 10;
			Node<Integer> newNode = new Node<Integer>(sum, null);
			if (result == null) {
				result = resultEnd = newNode;
			} else {
				resultEnd.setNext(newNode);
				resultEnd = newNode;
			}
			if (p1 != null) {
				p1 = p1.getNext();
			}
			if (p2 != null) {
				p2 = p2.getNext();
			}
		}
		if (overflow) {
			Node<Integer> newNode = new Node<Integer>(1, null);
			resultEnd.setNext(newNode);
		}
		return result;
	}

	public static void main(String[] args){
		LinkedListProblems p = new LinkedListProblems();
		// String a = "321";
		// String b = "32";
		// try {
		// System.out.println(p.sumOfNums(a, b));
		Node<Integer> n11 = new Node<Integer>(3, null);
		Node<Integer> n12 = new Node<Integer>(2, n11);
		Node<Integer> n13 = new Node<Integer>(1, n12);
		Node<Integer> n21 = new Node<Integer>(7, null);
		Node<Integer> n22 = new Node<Integer>(0, n21);
		Node<Integer> n23 = new Node<Integer>(0, n22);
		Node<Integer> result = p.addTwoNumbers(n13, n23);
		while (result != null) {
			System.out.print(result.getElement() + " ");
			result = result.getNext();
		}
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

		// System.out.println();
		// System.out.println("LinkedList test:");
		// LinkedList<Integer> list = new LinkedList<Integer>();
		// list.addBack(5);
		// list.addBack(6);
		// list.addBack(7);
		// list.addBack(8);
		// list.addBack(9);
		// list.printAll();
		// int index = 3;
		// System.out.println("Delete index " + index + " : ");
		// list.delete(index);
		// list.printAll();
		// System.out.println();
	}
}
