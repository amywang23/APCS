// Name: Amy Wang
// Date: 1/14/2021
//uses PostfixEval

import java.util.*;
public class Infix_Extension
{
	public static final String LEFT  = "([{<";
	public static final String RIGHT = ")]}>";
	public static final String operators = "+ - * / % ^ !";

	public static void main(String[] args)
	{
		System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
		/*build your list of Infix expressions here  */
		ArrayList<String> infixExp = new ArrayList<String>();
		infixExp.add("( 3.0 + -1.0 ) ^ 3.0");
		infixExp.add("2 ^ 3 + 3");
		infixExp.add("3 * 2 ^ 3");
		infixExp.add("( 1 + 3 ) !");
		infixExp.add("1 + 3 !");
		infixExp.add("1 * 3 !");
		infixExp.add("3 ? 2");
		infixExp.add("3 @ 2");
		infixExp.add("( 3 + 2");
		infixExp.add("3 + 2 ]");
		infixExp.add("( 3 + 2 ]");
//		infixExp.add("3 * ( 4 + 5 ]");
//		infixExp.add("4 2 : 3 +");


		for( String infix : infixExp )
		{
			if(mathExpression(infix)) {
				if(ParenMatch.checkParen(infix)) {
					String pf = infixToPostfix(infix);  //get the conversion to work first
					//            System.out.println(infix + "\t\t\t" + pf );  
					System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
				}
				else
					System.out.println(infix + "\t\t\t" + "ERROR");
			}
			else {
				System.out.println(infix + "\t\t\t" + "ERROR");  
			}

		}
	}

	public static String infixToPostfix(String infix)
	{
		List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
		/* enter your code here  */
		Stack<String> s = new Stack<String>();
		String result = "";
		for(String str: nums) {
			if(operators.contains(str)) {
				while(!s.isEmpty()) {
					if(ParenMatch.isLeftParen(s.peek()) >= 0 || isLower(s.peek().charAt(0), str.charAt(0))) {
						break;
					}
					else {
						result += s.pop() + " ";
					}
				}
				s.push(str);
			} //end if operator
			if(ParenMatch.isLeftParen(str) >= 0) {
				s.push(str);
			}

			if(ParenMatch.isRightParen(str) >= 0) {
				while(!s.isEmpty()) {
					String a = s.pop();
					if(ParenMatch.isRightParen(str) == ParenMatch.isLeftParen(a)) //check if match
						break;
					else {
						result += a + " ";
					}
				}

			}
			if(!operators.contains(str) && ParenMatch.isLeftParen(str) < 0 && ParenMatch.isRightParen(str) < 0){
				result += str + " ";
			} //end number
		} //end for
		while(!s.isEmpty()) {
			result += s.pop() + " ";
		}
		return result.trim();
	}

	//returns true if c1 has strictly lower precedence than c2
	public static boolean isLower(char c1, char c2)
	{
		//operators = "+ - * / % ^ !";
		// + - same level
		// * / % same level
		// ^ ! same level
		if(operators.indexOf(c1) >= 0 && operators.indexOf(c1) < 3 && operators.indexOf(c2) > 3)
			return true;
		if(operators.indexOf(c1) <= 8 && operators.indexOf(c1) >= 0 && operators.indexOf(c2) > 8)
			return true;
		return false;
	}

	public static boolean mathExpression(String infix)
	{
		List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));

		for(String str : nums) {
			if (ParenMatch.isRightParen(str) < 0 && ParenMatch.isLeftParen(str) < 0 && !PostfixEval.isOperator(str)) {
				try
				{
					Double.parseDouble(str);
				}
				catch(NumberFormatException e)
				{
					return false; 
				}
			}
		}
		return true;
	}

}


/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * + 			23.0
 3 * 4 + 5			3 4 5 + * 			27.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * + 			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + * 			-100.0
 3 * 4 + 5 / 2 - 5			3 4 5 2 5 - / + * 			6.999999999999999
 8 + 1 * 2 - 9 / 3			8 1 2 9 3 / - * + 			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 6 + * * 			132.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - + 			-10.0
 2 + 7 % 3			2 7 3 % + 			3.0
 ( 2 + 7 ) % 3			2 7 + 3 % 			0.0

 ***********************************************/
