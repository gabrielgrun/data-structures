package selectionsort;

public class App {
	public static void main(String[] args) {
		
		int nums[] = {4,-2,0,1,12,8,9,8};
		
		SelectionSort sort = new SelectionSort(nums);
		sort.sort();
		sort.showArray();
	}
}
