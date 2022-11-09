package binarytrees;

public class App {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		bst.insert(50);
		bst.insert(17);
		
		
		
		//bst.traversal();
		
		System.out.println(bst.treeSize(bst.getRoot()));
	}
}
