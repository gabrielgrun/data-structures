package shellsort;

public class App {

	public static void main(String[] args) {
		int[] nums = {-5, 3, 0, -3, 7};
		ShellSort sort = new ShellSort(nums);
		sort.sort();
		sort.showArray();
	}
}
