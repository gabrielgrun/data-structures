package dijkstrainterpreter;

import java.util.Stack;

public class Algorithm {

	private Stack<String> operationStack;
	private Stack<Double> valueStack;
	
	public Algorithm() {
		this.operationStack = new Stack<>();
		this.valueStack = new Stack<>();
	}
	
	public void interpreterExpression(String expressions) {
		String[] expressionsArray = expressions.split(" ");

		for (String exp : expressionsArray) {
			if(exp.equals("(")) {
				//do nothing
			} else if(exp.equals("+")) {
				operationStack.push(exp);
			} else if(exp.equals("*")) {
				operationStack.push(exp);
			} else if(exp.equals(")")) {
				String operation = this.operationStack.pop();
				if(operation.equals("+")) {
					this.valueStack.push(this.valueStack.pop() + this.valueStack.pop());
				} else if (operation.equals("*")) {
					this.valueStack.push(this.valueStack.pop() * this.valueStack.pop());
				}
			} else {
				this.valueStack.push(Double.parseDouble(exp));
			}
		}
	}
	
	public void result() {
		System.out.println(this.valueStack.pop());
	}
}
