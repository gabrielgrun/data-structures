package dijkstrainterpreter;

public class App {
	public static void main(String[] args) {
		Algorithm alg = new Algorithm();
		alg.interpreterExpression("( ( 1 + 2 ) * ( 2 + 1 ) )");
		alg.result();
	}
}
