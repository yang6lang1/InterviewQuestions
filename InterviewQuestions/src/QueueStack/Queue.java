package QueueStack;

import LinkedList.Node;

public class Queue<T> {
	private Node<T> front, back;
	private int size;
	
	public Queue(){
		front = back = null;
		size = 0;
	}
	
	public void push(T element){
		if (element == null) return;
		
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
	
	public T pull(){
		Node<T> newNode = null;
		
		if(size == 0) {
			newNode = null;
		}
		else if(size == 1) {
			newNode = back;
			front = back = null;
		}
		else {
			newNode = front;
			front = front.getNext();
		}
		
		size--;
		return newNode.getElement();
	}
	
	public T peek(){
		if(size == 0) return null;
		return front.getElement();
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0 ? true : false;
	}
}
