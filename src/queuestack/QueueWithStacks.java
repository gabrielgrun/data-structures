package queuestack;

import java.util.Stack;

public class QueueWithStacks {

	private Stack<Integer> enqueueStack;
	private Stack<Integer> dequeueStack;
	
	public QueueWithStacks() {
		this.enqueueStack = new Stack<>();
		this.dequeueStack = new Stack<>();
	}
	
	public void enqueue(int item) {
		this.enqueueStack.push(item);
	}
	
	public int dequeue() {
		if(enqueueStack.isEmpty() && dequeueStack.isEmpty()) {
			throw new RuntimeException("Stacks are empty...");
		}
		
		if(dequeueStack.isEmpty()) {
			while(!enqueueStack.isEmpty()) {
				dequeueStack.push(enqueueStack.pop());
			}
		}
		
		return dequeueStack.pop();
	}
}
