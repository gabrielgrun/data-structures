package countingsort;


public class App {

	public static void main(String[] args) {
		int[] nums = {5, 2, 8, 9, 1, 0};
		CountingSort sort = new CountingSort(nums);
		sort.sort();
		sort.showArray();
	}
}
