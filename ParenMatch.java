// Name: Amy Wang
// Date: 1/6/2021

import java.util.*;

public class ParenMatch
{
	public static final String LEFT  = "([{<";
	public static final String RIGHT = ")]}>";

	public static void main(String[] args)
	{
		System.out.println("Parentheses Match");
		ArrayList<String> parenExp = new ArrayList<String>();
		/* enter test cases here */
		parenExp.add("5 + 7");
		parenExp.add("( 15 + -7 )");
		parenExp.add(") 5 + 7 (");
		parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
		parenExp.add("< { 5 + 7 } * 3 >");
		parenExp.add("[ ( 5 + 7 ) * ] 3");
		parenExp.add("( 5 + 7 ) * 3");
		parenExp.add("5 + ( 7 * 3 )");
		parenExp.add("( ( 5 + 7 ) * 3");
		parenExp.add("[ ( 5 + 7 ] * 3 )");
		parenExp.add("[ ( 5 + 7 ) * 3 ] )");
		parenExp.add("( [ ( 5 + 7 ) * 3 ]");
		parenExp.add("( ( ( ) $ ) )");
		parenExp.add("( ) [ ]");

		for( String s : parenExp )
		{
			boolean good = checkParen(s);
			if(good)
				System.out.println(s + "\t good!");
			else
				System.out.println(s + "\t BAD");
		}
	}

	//returns the index of the left parentheses or -1 if is not
	public static int isLeftParen(String p)
	{
		return LEFT.indexOf(p);
	}

	//returns the index of the right parentheses or -1 if is not
	public static int isRightParen(String p)
	{
		return RIGHT.indexOf(p);
	}

	public static boolean checkParen(String exp)
	{
		Stack<String> s = new Stack<String>();
		for(int i = 0; i < exp.length(); i++) {
			String p = exp.substring(i, i+1);
			if(isLeftParen(p) >= 0) {
				s.push(p);
			}
			if(isRightParen(p) >= 0) {
				if(s.isEmpty())
					return false;
				String o = s.pop();
				if(isRightParen(p) != isLeftParen(o)) //check if match
					return false;
			}
		}
		if(!s.isEmpty())
			return false;
		return true;
	}
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!

 *******************************************/
