package bubblesort;

public class App {

	public static void main(String[] args) {
		int[] nums = {5, 2, 8, 10, 1, 0, 11};
		BubbleSort sort = new BubbleSort(nums);
		sort.sort();
	}
}
