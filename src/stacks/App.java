package stacks;

public class App {
	public static void main(String[] args) {
		MaxItemStack maxItemStack = new MaxItemStack();
		
		maxItemStack.push(1);
		maxItemStack.push(23);
		maxItemStack.push(3);
		maxItemStack.push(4);
		maxItemStack.push(5);
		
		System.out.println(maxItemStack.getMaxItem());
	}
}
