package binarytrees;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T>{

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
				root.setLeftChild(new Node<>(data, root));
			}
		//RIGHT	
		} else {
			if(root.getRightChild() != null) {
				insert(data, root.getRightChild());
			} else {
				root.setRightChild(new Node<>(data, root));
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

	@Override
	public void traversal() {
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

	@Override
	public T getMin() {
		if(root == null) {
			return null;
		}
		
		return getMin(root);
	}

	private T getMin(Node<T> node) {
		if(node.getLeftChild() != null) {
			return getMin(node.getLeftChild());
		}
		return node.getData();
	}

	@Override
	public T getMax() {
		if(root == null) {
			return null;
		}
		
		return getMax(root);
	}

	private T getMax(Node<T> node) {
		if(node.getRightChild() != null) {
			return getMax(node.getRightChild());
		}
		return node.getData();
	}

	@Override
	public Node<T> getRoot() {
		return this.root;
	}

	@Override
	public Node<T> getKSmallest(Node<T> node, int k) {
		
		int n = treeSize(node.getLeftChild()) + 1;
		
		if(n == k) {
			return node;
		}
		
		if(n > k) return getKSmallest(node.getLeftChild(), k);
		
		if(n < k) return getKSmallest(node.getRightChild(), k-n);
		return null;
	}
	
	public int treeSize(Node<T> node) {
		
		if(node == null) {
			return 0;
		}
		
		return (treeSize(node.getLeftChild()) + treeSize(node.getRightChild()) + 1);
	}
}
