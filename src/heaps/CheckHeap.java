package heaps;

public class CheckHeap {

	public boolean isMinHeap(int[] heap) {
		
		//if 2*i+2 >= N then we know that node is a leaf node (no need to consider)
		for(int i=0;i<=(heap.length-2)/2;i++) {
			if(heap[i] > heap[2*i+1] || heap[i] > heap[2*i+2]) {
				return false;
			}
		}
		
		return true;
	}
}
