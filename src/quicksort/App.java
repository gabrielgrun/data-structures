package quicksort;

public class App {

	public static void main(String[] args) {
		int[] nums = {-5, 3, 0, -3, 7};
		QuickSort sort = new QuickSort(nums);
		sort.sort();
		sort.showArray();
	}
}
