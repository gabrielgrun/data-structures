package queuestack;

import java.util.Stack;

public class OneQueueStack {

	private Stack<Integer> stack;
	
	public OneQueueStack() {
		this.stack = new Stack<>();
	}
	
	public void enqueue(int item) {
		this.stack.push(item);
	}
	
	public int dequeue() {
		
		if(stack.size() == 1) {
			return stack.pop();
		}
		
		int item = stack.pop();
		
		int lastDequeuedItem = dequeue();
		
		enqueue(item);
		
		return lastDequeuedItem;
	}
}
