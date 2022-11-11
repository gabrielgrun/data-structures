package substring.search.rabinkarp;

public class App {

	public static void main(String[] args) {
		RabinKarp rk = new RabinKarp();
		System.out.println(rk.search("This is a test!", "test"));
	}
}
