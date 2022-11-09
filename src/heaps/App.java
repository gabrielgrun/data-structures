package heaps;

import java.util.PriorityQueue;
import java.util.Queue;

public class App {

	public static void main(String[] args) {
		Heap heap = new Heap();
		
		for (int i = 0; i < 10000; i++) {
			heap.insert(i);
		}
		
		heap.heapSort();
		
		//-----------------------------------------------------
		//HEAP BY JAVA
		//by default, is a MIN heap
		Queue<Integer> heap2 = new PriorityQueue<>(10000);
		
		//to create a MAX heap
		//Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		heap2.add(12);
		heap2.add(5);
		heap2.add(7);
		heap2.add(0);
		heap2.add(-2);
		
		while(!heap2.isEmpty()) {
			System.out.println(heap2.poll());
		}
		
		//-----------------------------------------------------
		//Convert a max heap into a min heap
		System.out.println("CONVERTING A MAX HEAP INTO A MIN HEAP");
		int[] maxHeap = {210,100,23,2,5};
		HeapConverter heapConverter = new HeapConverter(maxHeap);
		int[] minHeap = heapConverter.transform();
		
		for (int i = 0; i < minHeap.length; i++) {
			System.out.print(minHeap[i] + " ");
		}
	}
}
