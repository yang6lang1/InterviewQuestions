package TreeGraph;

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
	/* Given two nodes, find the lowest common ancestors in a binary tree (not BST)
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

	/* TODO: write a method to print out a tree level by level
	 * */
	public static void main(String[] args){
		TreeProblems tp = new TreeProblems();
		tp.useGetLCA();
	}
}
