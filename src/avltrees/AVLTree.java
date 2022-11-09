package avltrees;


public class AVLTree<T extends Comparable<T>> implements Tree<T>{

	private Node<T> root;
	
	@Override
	public void insert(T data) {
		if(root == null) {
			root = new Node<>(data, null);
		} else {
			insert(data, root);
		}
	}

	private void insert(T data, Node<T> node) {
		
		//LEFT
		if(node.getData().compareTo(data) > 0) {
			if(node.getLeftChild() != null) {
				insert(data, node.getLeftChild());
			} else {
				node.setLeftChild(new Node<>(data, node));
			}
		//RIGHT	
		} else {
			if(node.getRightChild() != null) {
				insert(data, node.getRightChild());
			} else {
				node.setRightChild(new Node<>(data, node));
			}
		}
		
		updateHeight(node);
		settleViolations(node);
	}

	@Override
	public void remove(T data) {
		if(root != null) {
			remove(data, root);
		}
		
		
	}

	private void remove(T data, Node<T> node) {
		if(node == null) {
			return;
		}
		
		if(data.compareTo(node.getData()) < 0) {
			remove(data, node.getLeftChild());
		} else if(data.compareTo(node.getData()) > 0) {
			remove(data, node.getRightChild());
		} else {
			//item found
			
			//leaf node
			if(node.getLeftChild()==null && node.getRightChild() == null) {
				System.out.println("Removing a leaf node...");
				Node<T> parent = node.getParentNode();
				
				if(parent != null && parent.getLeftChild() == node) {
					parent.setLeftChild(null);
				} else if (parent != null && parent.getRightChild() == node) {
					parent.setRightChild(null);
				}
				
				//root node
				if(parent == null) {
					root = null;
				}
				
				updateHeight(parent);
				settleViolations(parent);
				//items with a single child
			} else if(node.getLeftChild() == null && node.getRightChild() != null) {
				System.out.println("Removing a node with a single right child...");
				Node<T> parent = node.getParentNode();
				
				if(parent != null && parent.getLeftChild() == node) {
					parent.setLeftChild(node.getRightChild());
				} else if (parent != null && parent.getRightChild() == node) {
					parent.setRightChild(node.getRightChild());
				}
				
				if(parent == null) {
					root = node.getRightChild();
				}
				
				node.getRightChild().setParentNode(parent);
				node = null;
				
				updateHeight(parent);
				settleViolations(parent);
			} else if(node.getLeftChild() != null && node.getRightChild() == null) {
				System.out.println("Removing a node with a single left child...");
				Node<T> parent = node.getParentNode();
				
				if(parent != null && parent.getLeftChild() == node) {
					parent.setLeftChild(node.getLeftChild());
				} else if (parent != null && parent.getRightChild() == node) {
					parent.setRightChild(node.getLeftChild());
				}
				
				if(parent == null) {
					root = node.getLeftChild();
				}
				
				node.getLeftChild().setParentNode(parent);
				node = null;
				updateHeight(parent); 
				settleViolations(parent);
			// remove 2 children
			} else {
				System.out.println("Removing a node with 2 children...");
				//find the max item in the left subtree
				Node<T> predecessor = getPredecessor(node.getLeftChild());
				T temp = predecessor.getData();
				predecessor.setData(node.getData());
				node.setData(temp);
				
				remove(data, predecessor);
			}
		}
		
		
	}

	private Node<T> getPredecessor(Node<T> node) {
		if(node.getRightChild() != null) {
			return getPredecessor(node.getRightChild());
		}
		
		return node;
	}

	@Override
	public void traverse() {
		if(root == null) {
			return;
		}
		
		traversal(root);
		
	}

	private void traversal(Node<T> node) {
		if(node.getLeftChild() != null) {
			traversal(node.getLeftChild());
		}
		
		System.out.print(node + " - ");
		
		if(node.getRightChild() != null) {
			traversal(node.getRightChild());
		}
	}
	
	private void settleViolations(Node<T> node) {
		while(node != null) {
			updateHeight(node);
			settleViolationsHelper(node);
			node = node.getParentNode();
		}
	}
	
	private void settleViolationsHelper(Node<T> node) {
		
		int balance = getBalance(node);
		
		if(balance > 1) {
			//left right heavy
			if(getBalance(node.getLeftChild()) < 0) {
				leftRotation(node.getLeftChild());
			}
			
			//doubly left heavy then just a right rotation 
			
			rightRotation(node);
		}
		
		if(balance < -1) {
			//right left heavy
			if(getBalance(node.getRightChild()) > 0) {
				rightRotation(node.getRightChild());
			}
			
			//doubly right heavy then just a right rotation 
			
			leftRotation(node);
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
		
		updateHeight(node);
		updateHeight(tempLeftChild);
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
		
		updateHeight(node);
		updateHeight(tempRightChild);
	}
	
	private void updateHeight(Node<T> node) {
		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
	}
	
	private int height(Node<T> node) {
		if(node == null) {
			return -1;
		}
		
		return node.getHeight();
	}
	
	private int getBalance(Node<T> node) {
		if(node == null) {
			return 0;
		}
		
		return height(node.getLeftChild()) - height(node.getRightChild());
	}
}
