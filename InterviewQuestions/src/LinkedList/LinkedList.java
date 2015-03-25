package LinkedList;

/**
 * This is the most common doubly-head-singly-linked list
 * */
public class LinkedList<T> {
	private Node<T> front;
	private Node<T> back;
	private int size;
	
	public LinkedList()	{
		front = back = null;
		size = 0;
	}
	
	public void addFront(T element){
		if(element == null) return;
		
		Node<T> newNode = new Node<T>(element, null);
		if(size == 0){
			front = back = newNode;
		}
		else{
			newNode.setNext(front);
			front = newNode;
		}
		size++;
	}
	
	public void addBack(T element){
		if(element == null) return;
		
		Node<T> newNode = new Node<T>(element, null);
		if(size == 0){
			front = back = newNode;
		}
		else{
			back.setNext(newNode);
			back = newNode;
		}
		size++;
	}
	
	public T removeFront(){
		T e = null;
		if(size == 0){
			return null;
		}
		else if(size == 1){
			e = front.getElement();
			front = back = null;
		}
		else{
			e = front.getElement();
			front = front.getNext();
		}
		size--;
		return e;
	}
	
	public void update(int index, T element){
		if(index > size - 1 || index < 0) return;
		
		Node<T> curr = front;
		for(int i = 0; i < index; i++){
			curr = curr.getNext();
		}
		
		curr.setElement(element);
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0 ? true : false;
	}
	
	public void printAll(){
		for(Node<T> curr = front; curr != null; curr = curr.getNext()){
			System.out.print(curr.getElement().toString() + ", ");
		}
		System.out.println();
//		System.out.println("Size: " + size);
	}
}
