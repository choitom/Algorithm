/**
	Author	: Tom Choi
	Date	: 08/16/2016
	
	Problem
		-> Evaluate a postfix notation
		-> Valid operators are +,-,*,/
*/
import java.util.*;

public class PostfixEval{
	public static Stack<String> stack = new Stack<String>();
	public static String[] exp;
	public static String[] ops = {"+", "-", "*", "/"};
	
	public static void main(String[] args){
		exp = new String[]{"4", "13", "5", "/", "+"};
		double evaled = eval(exp);
		System.out.println(evaled);
	}
	
	public static double eval(String[] exp){
		if(exp.length == 0){
			System.err.println("No expression to evaluate");
			return -1;
		}
		
		for(int i = 0; i < exp.length; i++){
			String token = exp[i];
			if(isOperator(token)){
				try{
					double rhs = Double.parseDouble(stack.pop());
					double lhs = Double.parseDouble(stack.pop());
					double evaled = 0;
					
					switch(token){
						case "+":
							evaled = lhs + rhs;
							break;
						case "-":
							evaled = lhs - rhs;
							break;
						case "*":
							evaled = lhs * rhs;
							break;
						case "/":
							evaled = lhs / rhs;
							break;
					}
					stack.push(String.valueOf(evaled));
				}catch(EmptyStackException ese){
					System.out.println(ese.getMessage());
				}
			}else{
				try{
					// check for the numbe format
					double num = Double.parseDouble(token);
					stack.push(token);
				}catch(NumberFormatException nfe){
					System.out.println(nfe.getMessage());
				}
			}
		}
		double result = 0;
		try{
			result = Double.parseDouble(stack.pop());
		}catch(EmptyStackException ese){
			System.out.println(ese.getMessage());
		}
		if(!stack.empty()){
			System.err.println("The stack is not empty!");
			return -1;
		}
		return result;
	}
	
	public static boolean isOperator(String token){
		boolean isOp = false;
		for(int i = 0; i < ops.length; i++){
			if(token.equals(ops[i])){
				return true;
			}
		}return isOp;
	}
}