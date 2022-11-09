package heaps;

//Class to convert a max heap into a min heap
public class HeapConverter {

	private int heap[];
	
	public HeapConverter(int[] heap) {
		this.heap = heap;
	}
	
	public int[] transform() {
		for(int i = ((heap.length-2)/2); i>=0; i--) {
			heapify(i);
		}
		
		return this.heap;
	}

	private void heapify(int index) {
		int leftChildIndex = 2*index+1;
		int rightChildIndex = 2*index+2;
		
		int smallest = index;
		
		if(leftChildIndex < heap.length && heap[leftChildIndex] < heap[index]) {
			smallest = leftChildIndex;
		}
		
		if(rightChildIndex < heap.length && heap[rightChildIndex] < heap[smallest]) {
			smallest = rightChildIndex;
		}
		
		if(smallest != index){
			swap(smallest, index);
			
			heapify(smallest);
		}
	}

	private void swap(int index1, int index2) {
		int temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
		
	}
}
