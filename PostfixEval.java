// Name: Amy Wang
// Date: 1/6/2021

import java.util.*;

public class PostfixEval
{
	public static final String operators = "+ - * / % ^ !";

	public static void main(String[] args)
	{
		System.out.println("Postfix  -->  Evaluate");
		ArrayList<String> postfixExp = new ArrayList<String>();
		/*  build your list of expressions here  */
		postfixExp.add("3 4 5 * +");
		postfixExp.add("3 4 * 5 +");
		postfixExp.add("10 20 + -6 6 * +");
		postfixExp.add("3 4 + 5 6 + *");
		postfixExp.add("3 4 5 + * 2 - 5 /");
		postfixExp.add("8 1 2 * + 9 3 / -");
		postfixExp.add("2 3 ^");
		postfixExp.add("20 3 %");
		postfixExp.add("21 3 %");
		postfixExp.add("22 3 %");
		postfixExp.add("23 3 %");
		postfixExp.add("5 !");
		postfixExp.add("1 1 1 1 1 + + + + !");
		postfixExp.add("2 5 - 4 3 + *");


		for( String pf : postfixExp )
		{
			System.out.println(pf + "\t\t" + eval(pf));
		}
	}

	public static double eval(String pf)
	{
		List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
		/*  enter your code here  */
		Stack<Double> s = new Stack<Double>();
		for(String str: postfixParts) {
			if(isOperator(str)) {
				if(s.isEmpty()) {
					return 0;
				}
				else {
					double d1 = s.pop();
					if(str.equals("!")) {
						s.push((double) fact((int) d1));
						continue;
					}
					if(s.isEmpty()) {
						return d1;
					}
					else {
						double d2 = s.pop();
						s.push(eval(d2, d1, str));
//						if(str.equals("+")) {
//							s.push(d1 + d2);
//						}
//						if(str.equals("-")) {
//							s.push(d2 - d1);
//						}
//						if(str.equals("*")) {
//							s.push(d1 * d2);
//						}
//						if(str.equals("/")) {
//							s.push(d2 / d1);
//						}
//						if(str.equals("%")) {
//							s.push(d2 % d1);
//						}
//						if(str.equals("^")) {
//							s.push(Math.pow(d2, d1));
//						}
						
					}
				}
			}//end of operator
			else {
				double d = Double.parseDouble(str);
				s.push(d);
			}
		}
		if(!s.isEmpty())
			return s.pop();
		return 0;

	}
	public static int fact(int n) 
	{
		if(n <= 0) {
			return 1; 
		}
		return n*fact(n-1); 
	} 

	public static double eval(double a, double b, String ch)
	{
		if(ch.equals("+")) {
			return a + b;
		}
		if(ch.equals("-")) {
			return a - b;
		}
		if(ch.equals("*")) {
			return a * b;
		}
		if(ch.equals("/")) {
			return a / b;
		}
		if(ch.equals("%")) {
			return a % b;
		}
		if(ch.equals("^")) {
			return Math.pow(a, b);
		}
		return 0;
	}

	public static boolean isOperator(String op)
	{
		if(operators.contains(op))
			return true;
		return false;
	}
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120


 *************************************/