package binarytrees;

public interface Tree<T> {

	public Node<T> getKSmallest(Node<T> node, int k);
	public Node<T> getRoot();
	public void insert(T data);
	public void remove(T data);
	// in-order
	public void traversal();
	public T getMin();
	public T getMax();
}
