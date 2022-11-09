package radixsort;

import java.util.Arrays;

public class RadixSort {

	private static final int ITEMS = 10;
	private int[] data;
	
	public RadixSort(int[] data) {
		this.data = data;
	}
	
	public void sort() {
		int place = 1;
		for (int i = 0; i < getMaxDigit(); i++) {
			countingSort(place);
			place *= 10;
		}
	}

	public void countingSort(int place) {
		int[] output = new int[data.length];
		int[] count = new int[ITEMS];

		for (int i = 0; i < data.length; i++) {
			count[(data[i]/place) % ITEMS]++;
		}

		//transforming the count array in a cumulative array
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = data.length - 1; i >= 0; i--) {
			count[(data[i]/place) % ITEMS]--;
			output[count[(data[i]/place) % ITEMS]] = data[i];
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = output[i];
		}
	}
	
	private int getMaxDigit() {
		int maxItem = Arrays.stream(data).max().getAsInt();
		return String.valueOf(maxItem).length();
	}

	public void showArray() {
		for (int i : data) {
			System.out.print(i + " ");
		}
	}
}
