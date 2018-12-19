package TreeGraph;

import java.util.ArrayList;
import java.util.List;

import LinkedList.LinkedList;
import QueueStack.Queue;
import QueueStack.Stack;

public class TreeProblems {

	public void useGetLCA(){
		System.out.println();
		TreeNode<Integer> b5 = new TreeNode<Integer>(8,null,null);
		TreeNode<Integer> b4 = new TreeNode<Integer>(9,b5,null);
		TreeNode<Integer> b3 = new TreeNode<Integer>(6,null,null);
		TreeNode<Integer> b2 = new TreeNode<Integer>(4,null, null);
		TreeNode<Integer> b1 = new TreeNode<Integer>(1,null,null);
		TreeNode<Integer> a2 = new TreeNode<Integer>(7,b3,b4);
		TreeNode<Integer> a1 = new TreeNode<Integer>(3,b1,b2);
		TreeNode<Integer> root = new TreeNode<Integer>(5, a1, a2);
		System.out.println("LCA: "+getLCA(root, 8, 6).getElement());
	}
	/** Given two nodes, find the lowest common ancestors in a binary tree (not BST)
	 * (Assume tree doesn't have duplicate nodes) (Mar 16)
	 * */
	public TreeNode<Integer> getLCA(TreeNode<Integer> root, int n1, int n2){
		if(n1 == n2) return null; // invalid
		if(root == null) return null;

		Result n1r = findNode(root, n1);
		Result n2r = findNode(root, n2);
		if(!n1r.exist || !n2r.exist) return null;

		int leftStep, rightStep;
		TreeNode<Integer> LCA = root;
		do{
			leftStep = n1r.steps.pull();
			rightStep = n2r.steps.pull();
			if(leftStep != rightStep) break;
			if(leftStep == -1 && rightStep == -1) {
				LCA = LCA.getLeft();
			}
			else if(leftStep == 1 && rightStep == 1) {
				LCA = LCA.getRight();
			}
		}while(leftStep != 0 && rightStep != 0);

		return LCA;
	}

	public void useTreeMethods(){
		System.out.println();
		TreeNode<Integer> b5 = new TreeNode<Integer>(8,null,null);
		TreeNode<Integer> b4 = new TreeNode<Integer>(9,b5,null);
		TreeNode<Integer> b3 = new TreeNode<Integer>(6,null,null);
		TreeNode<Integer> b2 = new TreeNode<Integer>(4,null, null);
		TreeNode<Integer> b1 = new TreeNode<Integer>(1,null,null);
		TreeNode<Integer> a2 = new TreeNode<Integer>(7,b3,b4);
		TreeNode<Integer> a1 = new TreeNode<Integer>(3,b1,b2);
		TreeNode<Integer> root = new TreeNode<Integer>(5, a1, a2);
		System.out.println("print tree breath first:");
		printTreeBreathFirst(root);

		System.out.println();
		List<LinkedList<TreeNode<Integer>>> list = getListOfLLFromTree(root);
		for(LinkedList<TreeNode<Integer>> ll : list){
			ll.printAll();
		}
	}
	/** Print a tree, breath first (March 18)
	 * */
	public void printTreeBreathFirst(TreeNode<Integer> root){
		Queue<TreeNode<Integer>> curr = new Queue<TreeNode<Integer>>();
		Queue<TreeNode<Integer>> todo = new Queue<TreeNode<Integer>>();

		TreeNode<Integer> currNode = null;
		todo.push(root);
		while(!todo.isEmpty()){
			curr = todo;
			todo = new Queue<TreeNode<Integer>>();

			while(!curr.isEmpty()){
				currNode = curr.pull();
				System.out.print(currNode.getElement()+"\t");
				if(currNode.getLeft() != null) todo.push(currNode.getLeft());
				if(currNode.getRight() != null) todo.push(currNode.getRight());
			}
			System.out.println();
		}
	}

	/** Design an algorithm which creates a linked list of all the nodes at
	 * each depth. (March 19)
	 *
	 *
	 * */
	public List<LinkedList<TreeNode<Integer>>> getListOfLLFromTree(TreeNode<Integer> root){
		/*
		1. tree empty
		2. pre order traversal
		   check if a node is null, return
		   get i linkedlist from arraylist,
		       if exist, insert the current node into the linkedlist
		       if not, create a new ll
		   pass down to left
		   pass down to right
		 */
		List<LinkedList<TreeNode<Integer>>> list = new ArrayList<LinkedList<TreeNode<Integer>>>();
		list = createList(root, 0, list);
		return list.isEmpty()? null : list;
	}

	private List<LinkedList<TreeNode<Integer>>> createList(TreeNode<Integer> root,
			int depth, List<LinkedList<TreeNode<Integer>>> list){
		if (root == null) return list;
		if (depth > list.size()-1){
			LinkedList<TreeNode<Integer>> ll = new LinkedList<TreeNode<Integer>>();
			ll.addBack(root);
			list.add(ll);
		}
		else{
			LinkedList<TreeNode<Integer>> ll = list.get(depth);
			ll.addBack(root);
			list.set(depth, ll);
		}

		list = createList(root.getLeft(), depth+1, list);
		list = createList(root.getRight(), depth+1, list);

		return list;
	}

	public Result findNode(TreeNode<Integer> root, int n){
		if (root == null) return new Result();

		if(root.getElement() == n){
			Result r = new Result();
			r.exist = true;
			r.steps.push(0);
			return r;
		}

		Result leftR = findNode(root.getLeft(), n);
		if(leftR.exist){
			leftR.steps.push(-1);
			return leftR;
		}
		Result rightR = findNode(root.getRight(), n);
		if(rightR.exist){
			rightR.steps.push(1);
			return rightR;
		}

		return new Result();
	}

	class Result{
		public  boolean exist;
		public Stack<Integer> steps;

		public Result(){
			exist = false;
			steps = new Stack<Integer>();
		}
	}

	/** Given a binary search tree of integers and a number K, find the number in the tree that has the closest value */
	private static class BSTNode<T> {
		private T value;
		private BSTNode<T> left;
		private BSTNode<T> right;
		public BSTNode(T val, BSTNode<T> l, BSTNode<T> r) {
			this.value = val;
			this.left = l;
			this.right = r;
		}

		public BSTNode<T> getLeft() {
			return this.left;
		}
		public BSTNode<T> getRight() {
			return this.right;
		}
		public T getValue() {
			return this.value;
		}
	}
	public int findClosestValue(BSTNode<Integer> root, int k) {
		if (root == null) {
			return -1;
		}
		if (root.getValue() == k) {
			return root.getValue();
		} else if (root.getValue() < k) { //compare with right
			if (root.getRight() == null) {
				return root.getValue();
			} else if (root.getRight().getValue() >= k) {
				int deltaToRoot = Math.abs(root.getValue() - k);
				int deltaToRight = Math.abs(root.getRight().getValue() - k);
				return deltaToRight > deltaToRoot ? root.getValue() : root.getRight().getValue();
			} else {
				return this.findClosestValue(root.getRight(), k);
			}
		} else { //compare with left
			if (root.getLeft() == null) {
				return root.getValue();
			} else if (root.getLeft().getValue() <= k) {
				int deltaToRoot = Math.abs(root.getValue() - k);
				int deltaToLeft = Math.abs(root.getLeft().getValue() - k);
				return deltaToLeft > deltaToRoot ? root.getValue() : root.getLeft().getValue();
			} else {
				return this.findClosestValue(root.getLeft(), k);
			}
		}
	}

	/* TODO: write a method to print out a tree level by level
	 * */
	public static void main(String[] args){
		TreeProblems tp = new TreeProblems();
		// tp.useGetLCA();
		// tp.useTreeMethods();
		BSTNode<Integer> l1 = new BSTNode<Integer>(5, null, null);
		BSTNode<Integer> l2 = new BSTNode<Integer>(12, null, null);
		BSTNode<Integer> l3 = new BSTNode<Integer>(18, null, null);
		BSTNode<Integer> m1 = new BSTNode<Integer>(10, l1, l2);
		BSTNode<Integer> m2 = new BSTNode<Integer>(20, l3, null);
		BSTNode<Integer> r = new BSTNode<Integer>(15, m1, m2);
		System.out.println(tp.findClosestValue(r, 16));
	}
}
