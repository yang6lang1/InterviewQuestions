package QueueStack;

import LinkedList.Node;

public class Stack<T> {
	private Node<T> tail;
	private int size;
	
	public Stack(){
		size = 0;
		tail = null;
	}
	
	public void push(T element){
		Node<T> newNode = new Node<T>(element, null);
		if(tail == null) {
			tail = newNode;
		}
		else{
			newNode.setNext(tail);
			tail = newNode;
		}
		
		size++;
	}
	
	public T pull(){
		if(size == 0) return null;
		
		T element = tail.getElement();
		tail = tail.getNext();
		size--;
		return element;
	}
	
	public T peek(){
		if(size == 0) return null;
		
		return tail.getElement();
	}
	
	public boolean isEmpty(){
		return size == 0 ? true : false;
	}
	
	public int getSize(){
		return size;
	}
}
