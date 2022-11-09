package arrays;

public class App {
	public static void main(String[] args) {
		
		int[] heights = {4,1,3,1,5};
		TrappedRainProblem problem = new TrappedRainProblem();
		
		System.out.println(problem.solve(heights));
	}
}
