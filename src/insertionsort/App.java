package insertionsort;

public class App {

	public static void main(String[] args) {
		int[] nums = {-5, 7, -3, 1, 5, -2, 0};
		InsertionSort sort = new InsertionSort(nums);
		sort.sort();
		sort.showArray();
	}
}
