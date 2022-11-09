package heaps;

public class Heap {

	private int[] heap;
	private int heapSize;
	
	public Heap() {
		heap = new int[Constants.CAPACITY];
	}
	
	public void insert(int data) {
		if(isFull()) {
			throw new RuntimeException("Heap is full...");
		}
		
		// append the data to the end of the array
		heap[heapSize] = data;
		heapSize++;
		
		//O(logN)
		fixUp(heapSize-1);
	}
	
	
	private void fixUp(int index) {
		
		int parentIndex = (index-1) / 2;
		// in a maximum heap, the parent is always larger than the children
		if(index > 0 && heap[index] > heap[parentIndex]) {
			swap(index, parentIndex);
			fixUp(parentIndex);
		}
	}
	
	public int getMax() {
		return heap[0];
	}
	
	public int poll() {
		int max = getMax();
		
		//swap the root with the last item
		swap(0, heapSize-1);
		heapSize--;
		
		fixDown(0);
		
		return max;
	}

	private void fixDown(int index) {
		int leftChildIndex = 2*index+1;
		int rightChildIndex = 2*index+2;
		
		//in a max heap, the parent is always larger than the children
		int largestIndex = index;
		
		//compare the leftChild with the parent
		if(leftChildIndex < heapSize && heap[leftChildIndex] > heap[index]) {
			largestIndex = leftChildIndex;
		}
		
		//compare the rightChild with the max(parent, leftChild)
		if (rightChildIndex < heapSize && heap[rightChildIndex] > heap[largestIndex]) {
			largestIndex = rightChildIndex;
		}
		
		//one of the children is larger than the parent
		if(index!=largestIndex) {
			swap(index, largestIndex);
			fixDown(largestIndex);
		}
	}
	
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	public void heapSort() {
		int n = heapSize;
		
		for (int i = 0; i < n; i++) {
			int max = poll();
			System.out.println(max);
		}
	}

	private void swap(int index1, int index2) {
		int temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	private boolean isFull() {
		return heapSize == Constants.CAPACITY;
	}
}
