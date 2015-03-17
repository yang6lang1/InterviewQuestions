package TreeGraph;

public class TreeNode<T>{
	private T element;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	public TreeNode(){
		element = null;
		left = null;
		right = null;
	}
	
	public TreeNode(T element, TreeNode<T> left, TreeNode<T> right){
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	public void setElement(T element){
		this.element = element;
	}
	
	public T getElement(){
		return this.element;
	}
	
	public void setLeft(TreeNode<T> left){
		this.left = left;
	}
	
	public TreeNode<T> getLeft(){
		return this.left;
	}
	
	public void setRight(TreeNode<T> right){
		this.right = right;
	}
	
	public TreeNode<T> getRight(){
		return this.right;
	}
}
