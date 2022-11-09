package arrays;

public class ReverseArray {
	public int[] solve(int[] nums) {
		int lowIndex = 0;
		int highIndex = nums.length -1;

		while(lowIndex < highIndex) {
			swap(nums, lowIndex, highIndex);
			lowIndex++;
			highIndex--;
		}
		
		return nums;
	}

	private void swap(int[] nums, int lowIndex, int highIndex) {
		int temp = nums[lowIndex];
		nums[lowIndex] = nums[highIndex];
		nums[highIndex] = temp;
	}
}		
