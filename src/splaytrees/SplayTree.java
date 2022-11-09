package splaytrees;

public class SplayTree<T extends Comparable<T>> implements Tree<T> {

	private Node<T> root;
	
	@Override
	public void insert(T data) {
		if(root == null) {
			root = new Node<>(data, null);
		} else {
			insert(data, root);
		}
	}

	private void insert(T data, Node<T> root) {
		
		//LEFT
		if(root.getData().compareTo(data) > 0) {
			if(root.getLeftChild() != null) {
				insert(data, root.getLeftChild());
			} else {
				Node<T> newNode = new Node<>(data, root);
				root.setLeftChild(newNode);
				splay(newNode);
			}
		//RIGHT	
		} else {
			if(root.getRightChild() != null) {
				insert(data, root.getRightChild());
			} else {
				Node<T> newNode = new Node<>(data, root);
				root.setRightChild(newNode);
				splay(newNode);
			}
		}
		
	}

	@Override
	public void traverse() {
		if(root != null) {
			traverse(root);
		}
	}

	private void traverse(Node<T> node) {
		if(node.getLeftChild() != null) {
			traverse(node.getLeftChild());
		}
		
		System.out.println(node + " - ");
		
		if(node.getRightChild() != null) {
			traverse(node.getRightChild());
		}
	}
	
	private void splay(Node<T> node) {
		
		while(node.getParentNode() != null) {
			
			//grandparent is null - zig rotation
			if(node.getParentNode().getParentNode() == null) {
				if(node.getParentNode().getLeftChild() == node) {
					rightRotation(node.getParentNode());
				} else {
					leftRotation(node.getParentNode());
				}
			//zig-zig case	
			} else if(node.getParentNode().getLeftChild() == node
					&& node.getParentNode().getParentNode().getLeftChild() == node.getParentNode()) {
				rightRotation(node.getParentNode().getParentNode());
				rightRotation(node.getParentNode());
			} else if (node.getParentNode().getRightChild() == node
					&& node.getParentNode().getParentNode().getRightChild() == node.getParentNode()) {
				leftRotation(node.getParentNode().getParentNode());
				leftRotation(node.getParentNode());
			//zig-zag case	
			} else if(node.getParentNode().getLeftChild() == node
					&& node.getParentNode().getParentNode().getRightChild() == node.getParentNode()) {
				rightRotation(node.getParentNode());
				leftRotation(node.getParentNode());
			} else if (node.getParentNode().getRightChild() == node
					&& node.getParentNode().getParentNode().getLeftChild() == node.getParentNode()) {
				leftRotation(node.getParentNode());
				rightRotation(node.getParentNode());
			}
		}
	}
	
	private void rightRotation(Node<T> node) {
		
		System.out.println("Rotating right on node " + node);
		
		Node<T> tempLeftChild = node.getLeftChild();
		Node<T> grandChild = tempLeftChild.getRightChild();
		
		// tempLeftChild will be the root
		tempLeftChild.setRightChild(node);
		node.setLeftChild(grandChild);
		
		if(grandChild != null) {
			grandChild.setParentNode(node);
		}
		
		Node<T> tempParent = node.getParentNode();
		node.setParentNode(tempLeftChild);
		tempLeftChild.setParentNode(tempParent);
		
		if(tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getLeftChild() == node) {
			tempLeftChild.getParentNode().setLeftChild(tempLeftChild);
		}
		
		if(tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getRightChild() == node) {
			tempLeftChild.getParentNode().setRightChild(tempLeftChild);
		}
		
		//no parent after rotation
		if(node == root) {
			root = tempLeftChild;
		}
	}
	
	private void leftRotation(Node<T> node) {
		
		System.out.println("Rotating left on node " + node);
		
		Node<T> tempRightChild = node.getRightChild();
		Node<T> grandChild = tempRightChild.getLeftChild();
		
		// tempLeftChild will be the root
		tempRightChild.setLeftChild(node);
		node.setRightChild(grandChild);
		
		if(grandChild != null) {
			grandChild.setParentNode(node);
		}
		
		Node<T> tempParent = node.getParentNode();
		node.setParentNode(tempRightChild);
		tempRightChild.setParentNode(tempParent);
		
		if(tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getLeftChild() == node) {
			tempRightChild.getParentNode().setLeftChild(tempRightChild);
		}
		
		if(tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getRightChild() == node) {
			tempRightChild.getParentNode().setRightChild(tempRightChild);
		}
		
		//no parent after rotation
		if(node == root) {
			root = tempRightChild;
		}
	}
	
	@Override
	public T getRoot() {
		if(root == null) return null;
		
		return root.getData();
	}
	
	@Override
	public T find(T data) {
		if(root == null) return null;
		
		return find(data, root);
	}

	private T find(T data, Node<T> node) {
		if(data.compareTo(node.getData()) < 0) {
			find(data, node.getLeftChild());
		} else if (data.compareTo(node.getData()) > 0) {
			find(data, node.getRightChild());
		} else {
			splay(node);
			return data;
		}
		
		return null;
	}
}
