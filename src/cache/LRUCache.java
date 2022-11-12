package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	private int actualSize;
	private Map<Integer, Node> map;
	private DoublyLinkedList linkedList;
	
	public LRUCache() {
		this.map = new HashMap<>();
		this.linkedList = new DoublyLinkedList();
	}
	
	public void put(int id, String data) {
		if(map.containsKey(id)) {
			Node node = this.map.get(id);
			node.setData(data);
			System.out.println("Inside put: " + node);
			update(node);
			return;
		}
		
		Node node = new Node(id, data);
		if(this.actualSize < Constants.CAPACITY) {
			this.actualSize++;
			add(node);
		} else {
			removeTail();
			add(node);
		}
	}

	private void removeTail() {
		Node lastNode = this.map.get(this.linkedList.getTailNode().getId());	
		this.linkedList.setTailNode(linkedList.getTailNode().getPreviousNode());
		
		if(linkedList.getTailNode() != null) {
			this.linkedList.getTailNode().setNextNode(null);
		}
		
		lastNode = null;
	}
	
	public Node get(int id) {
		
		if(!this.map.containsKey(id)) {
			return null;
		}
		
		Node node = this.map.get(id);
		//move to the head bc is the most frequently visited
		update(node);
		return node;
	}

	private void update(Node node) {
		
		Node previousNode = node.getPreviousNode();
		Node nextNode = node.getNextNode();
		
		if(previousNode != null) {
			previousNode.setNextNode(nextNode);
		} else {
			this.linkedList.setHeadNode(nextNode);
		}
		
		if(nextNode != null) {
			nextNode.setPreviousNode(previousNode);
		} else {
			this.linkedList.setTailNode(previousNode);
		}
		
		add(node);
	}

	private void add(Node node) {
		node.setNextNode(this.linkedList.getHeadNode());
		node.setPreviousNode(null);
		
		if(linkedList.getHeadNode() != null) {
			linkedList.getHeadNode().setPreviousNode(node);
		}
		
		linkedList.setHeadNode(node);
		
		if(linkedList.getTailNode() == null) {
			linkedList.setTailNode(node);
		}
		
		this.map.put(node.getId(), node);
	}
	
	public void show() {
		Node actualNode = this.linkedList.getHeadNode();
		
		while(actualNode != null) {
			System.out.print(actualNode + "<->");
			actualNode = actualNode.getNextNode();
		}
	}
}
