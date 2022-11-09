package mergesort;

public class App {

	public static void main(String[] args) {
		int[] nums = {-5, 3, 0, -3, 7};
		
		MergeSort sort = new MergeSort(nums);
		sort.sort();
		sort.showArray();
	}
}
