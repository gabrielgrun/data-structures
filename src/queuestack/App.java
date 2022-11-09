package queuestack;

public class App {
	public static void main(String[] args) {

		QueueWithStacks queue = new QueueWithStacks();

		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);

		System.out.println(queue.dequeue());
		
		queue.enqueue(4);
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
		//------------------------------------------------------------//
		
		OneQueueStack queue2 = new OneQueueStack();

		queue2.enqueue(1);
		queue2.enqueue(2);
		queue2.enqueue(3);

		System.out.println(queue2.dequeue());
		
		queue2.enqueue(4);
		
		System.out.println(queue2.dequeue());
		System.out.println(queue2.dequeue());
		System.out.println(queue2.dequeue());
	}
}
