package com.amazon.treebuilder;

public class TreeBuilder {

	private static class Node {
		
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}

		public int getValue() {
			return value;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		
		int[] preorder = {4, 2, 1, 3, 6, 5, 7};
		int[] inorder = {1, 2, 3, 4, 5, 6, 7};
		
		try {
			Node root = buildTree(inorder, preorder);
		} catch (IllegalTreeException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	private static Node buildTree(int[] inorder, int[] preorder) throws IllegalTreeException {

		if (preorder.length == 0) {
			throw new IllegalTreeException("That tree is dead.  It ain't got no root element.");
		}
		
		if (preorder.length != inorder.length) {
			throw new IllegalTreeException("What are you trying to pull?  These trees ain't the same length man.");
		}
		
		Node root = new Node(preorder[0]);
		
		if (preorder.length > 1) { 
			int preIndex;
			int inIndex;

			boolean found = false;
			for (inIndex = 0; inIndex < inorder.length; inIndex++) {
				if (inorder[inIndex] == root.value) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				throw new IllegalTreeException("Oh you done did it again.  These trees ain't got the same values.");
			}
			
			if (inIndex > 0) {
				root.setLeft(new Node(preorder[1]));
				root.setRight(new Node(preorder[inIndex + 1]));
			}
			else {
				root.setRight(new Node(preorder[1]));
			}
		} 
		
		
		return root;
		
	}
}
