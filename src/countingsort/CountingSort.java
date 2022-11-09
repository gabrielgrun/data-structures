package countingsort;

public class CountingSort {

	private static final int ITEMS = 10;
	private int[] data;
	
	public CountingSort(int[] data) {
		this.data = data;
	}

	public void sort() {
		int[] output = new int[data.length];
		int[] count = new int[ITEMS];

		for (int i = 0; i < data.length; i++) {
			count[data[i]]++;
		}

		//transforming the count array in a cumulative array
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = data.length - 1; i >= 0; i--) {
			count[data[i]]--;
			output[count[data[i]]] = data[i];
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = output[i];
		}
	}

	public void showArray() {
		for (int i : data) {
			System.out.print(i + " ");
		}
	}
}
