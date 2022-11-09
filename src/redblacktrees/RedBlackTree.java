package redblacktrees;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

	private Node<T> root;

	public void insert(T data) {
		if(root == null) {
			root = new Node<>(data, null);
			settleViolations(root);
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
				Node<T> newNode = new Node<>(data, node);
				node.setLeftChild(newNode);
				settleViolations(newNode);
			}
			//RIGHT	
		} else {
			if(node.getRightChild() != null) {
				insert(data, node.getRightChild());
			} else {
				Node<T> newNode = new Node<>(data, node);
				node.setRightChild(newNode);
				settleViolations(newNode);
			}
		}
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
	
	// O(logN)
	private void settleViolations(Node<T> node) {
		Node<T> parentNode = null;
		Node<T> grandParentNode = null;
		
		while(node != root && isRed(node) && isRed(node.getParentNode())) {
			
			parentNode = node.getParentNode();
			grandParentNode = node.getParentNode().getParentNode();
			
			if(parentNode == grandParentNode.getLeftChild()) {
				Node<T> uncle = grandParentNode.getRightChild();
				
				//Have to recolor
				if(uncle != null && isRed(uncle)) {
					grandParentNode.setColor(NodeColor.RED);
					parentNode.setColor(NodeColor.BLACK);
					uncle.setColor(NodeColor.BLACK);
					node = grandParentNode;
				} else {
					if(node == parentNode.getRightChild()) {
						leftRotation(parentNode);
						node = parentNode;
						parentNode = grandParentNode;
					}
					
					rightRotation(grandParentNode);
					System.out.println("Recoloring " + parentNode + " + " + grandParentNode);
					NodeColor tempColor = parentNode.getColor();
					parentNode.setColor(grandParentNode.getColor());
					grandParentNode.setColor(tempColor);
					
					node = parentNode;
				}
			} else {
				Node<T> uncle = grandParentNode.getLeftChild();
				
				if(uncle != null && isRed(uncle)) {
					grandParentNode.setColor(NodeColor.RED);
					parentNode.setColor(NodeColor.BLACK);
					uncle.setColor(NodeColor.BLACK);
					node = grandParentNode;
				}  else {
					if(node == parentNode.getLeftChild()) {
						rightRotation(parentNode);
						node = parentNode;
						parentNode = grandParentNode;
					}
					
					leftRotation(grandParentNode);
					System.out.println("Recoloring " + parentNode + " + " + grandParentNode);
					NodeColor tempColor = parentNode.getColor();
					parentNode.setColor(grandParentNode.getColor());
					grandParentNode.setColor(tempColor);
					
					node = parentNode;
				}
			}
		}
		
		if(isRed(root)) {
			System.out.println("Recoloring the root to black...");
			root.setColor(NodeColor.BLACK);
		}
	}

	private boolean isRed(Node<T> node) {
		if(node == null) {
			return false;
		}
		
		return node.getColor() == NodeColor.RED;
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
	public void traverse() {
		if(root == null) {
			return;
		}

		traverse(root);

	}

	private void traverse(Node<T> node) {
		if(node.getLeftChild() != null) {
			traverse(node.getLeftChild());
		}

		System.out.print(node + " - ");

		if(node.getRightChild() != null) {
			traverse(node.getRightChild());

		}
	}
}
