package radixsort;


public class App {

	public static void main(String[] args) {
		int[] nums = {5, 2, 8, 9, 1, 0};
		RadixSort sort = new RadixSort(nums);
		sort.sort();
		sort.showArray();
	}
}
