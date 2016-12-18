package com.amazon.treebuilder;

import java.util.Arrays;

public class TreeBuilder {

	private Node root;
	
	public TreeBuilder() {
	}
	
	public Node getRoot() {
		return root;
	}

	private class Node {
		
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}

		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
	}

	private void build(int[] preorder, int[] inorder) throws IllegalTreeException {
		this.root = buildTree(preorder, inorder);
	}

	private Node buildTree(int[] preorder, int[] inorder) throws IllegalTreeException {
		
		if (preorder.length != inorder.length) {
			throw new IllegalTreeException("What are you trying to pull?  These trees ain't the same length.");
		}
		
		Node root = null;
		
		if (preorder.length > 0) { 
			root = new Node(preorder[0]);
			Integer inIndex = findRootIndexInInorder(inorder, root.value);
			
			if (inIndex == null) {
				throw new IllegalTreeException("Oh you done did it again.  This root ain't in both trees.");
			}
			
			if (inIndex > 0) {
				root.setLeft(buildTree(Arrays.copyOfRange(preorder, 1, inIndex + 1),
						Arrays.copyOfRange(inorder, 0, inIndex)));
			}
			if (inIndex + 1 <= preorder.length) {
				root.setRight(buildTree(Arrays.copyOfRange(preorder, inIndex + 1, preorder.length),
						Arrays.copyOfRange(inorder, inIndex + 1, inorder.length)));
			}
		} 
		
		return root;
	}
	
	private Integer findRootIndexInInorder(int[] inorder, int value) {
		
		Integer inIndex;
		
		for (inIndex = new Integer(0); inIndex < inorder.length; inIndex++) {
			if (inorder[inIndex] == value) {
				return inIndex;
			}
		}
		
		return null;
	}

	private static void printPreorder(Node root) {
		System.out.print(root.value + " ");
		if (root.left != null) {
			printPreorder(root.left);
		}		
		if (root.right != null) {
			printPreorder(root.right);
		}
	}

	private static void printInorder(Node root) {
		if (root.left != null) {
			printInorder(root.left);
		}		
		System.out.print(root.value + " ");
		if (root.right != null) {
			printInorder(root.right);
		}
	}

	public static void main(String[] args) {
		
		int[] preorder = {4, 2, 1, 3, 6, 5, 7};
		int[] inorder = {1, 2, 3, 4, 5, 6, 7};
		
		TreeBuilder builder = new TreeBuilder();
		
		try {
			builder.build(preorder, inorder);
			printInorder(builder.getRoot());
			System.out.println();
			printPreorder(builder.getRoot());
		} catch (IllegalTreeException e) {
			System.out.println(e.getMessage());
		}
	}
}
