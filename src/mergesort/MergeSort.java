package mergesort;

public class MergeSort {

	private int[] nums;
	private int[] tempArray;
	
	public MergeSort(int[] nums) {
		this.nums = nums;
		this.tempArray = new int[nums.length];
	}
	
	public void sort() {
		mergeSort(0, nums.length-1);
	}
	
	private void mergeSort(int low, int high) {
		if(low >= high) {
			return;
		}
		
		int middleIndex = (low+high) / 2;
		
		//we keep spliting the problem into smaller sub-problems
		mergeSort(low, middleIndex);
		mergeSort(middleIndex + 1, high);
		
		merge(low, middleIndex, high);
	}

	private void merge(int low, int middleIndex, int high) {
		for (int i = low; i <= high; i++) {
			tempArray[i] = nums[i];
		}
		
		int i = low;
		int j = middleIndex + 1;
		int k = low;
		
		while(i <= middleIndex && j <= high) {
			if(tempArray[i] < tempArray[j]) {
				nums[k] = tempArray[i];
				i++;
			} else {
				nums[k] = tempArray[j];
				j++;
			}
			
			k++;
		}
		
		//copy the items from the left subarray (if there are any yet)
		while(i <= middleIndex) {
			nums[k] = tempArray[i];
			k++;
			i++;
		}
		
		//copy the items from the right subarray (if there are any yet)
		
		while(j <= high) {
			nums[k] = tempArray[j];
			k++;
			j++;
		}
	}

	public void showArray() {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}
}
