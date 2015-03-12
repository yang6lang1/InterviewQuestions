package LinkedList;

public class Node<T> {
	private T element;
	private Node<T> next;
	
	public Node(){
		element = null;
		next = null;
	}
	
	public Node(T element, Node<T> next){
		this.element = element;
		this.next = next;
	}
	
	public void setElement(T element){
		this.element = element;
	}
	
	public void setNext(Node<T> next){
		this.next = next;
	}
	
	public T getElement(){
		return this.element;
	}
	
	public Node<T> getNext(){
		return this.next;
	}
}
