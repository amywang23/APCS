// Name: Amy Wang
// Date: 2/12/2021
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */

import java.util.*;

public class BXT
{
	private TreeNode root;   
	public static final String operators = "+ - * / % ^ !";

	public BXT()
	{
		root = null;
	}
	public TreeNode getRoot()   //for Grade-It
	{
		return root;
	}

	public void buildTree(String str)
	{
		List<String> parts = new ArrayList<String>(Arrays.asList(str.split(" ")));
		Stack<TreeNode> s = new Stack<TreeNode>();
		for(String p: parts) {
			TreeNode t = new TreeNode(p);
			if(!isOperator(p)) {
				s.push(t);
			}
			else {
				t.setRight(s.pop());
				t.setLeft(s.pop());
				s.push(t);
			}
		}
		root = s.pop();
	}

	public double evaluateTree()
	{
		return evaluateNode(root);
	}

	private double evaluateNode(TreeNode t)  //recursive
	{
		String s = t.getValue().toString();
		double result = 0.0;
		if(isOperator(s)) {
			double a = evaluateNode(t.getLeft());
			double b = evaluateNode(t.getRight());
			result = computeTerm(s, a, b);
			return result;
		} else {
			return Double.parseDouble(s);
		}
	}

	private double computeTerm(String s, double a, double b)
	{
		if(s.equals("+")) {
			return a + b;
		}
		if(s.equals("-")) {
			return a - b;
		}
		if(s.equals("*")) {
			return a * b;
		}
		if(s.equals("/")) {
			return a / b;
		}
		if(s.equals("%")) {
			return a % b;
		}
		if(s.equals("^")) {
			return Math.pow(a, b);
		}
		return 0;
	}

	private boolean isOperator(String s)
	{
		if(operators.contains(s))
			return true;
		return false;
	}

	public String display()
	{
		return display(root, 0);
	}

	private String display(TreeNode t, int level)
	{
		String toRet = "";
		if(t == null)
			return "";
		toRet += display(t.getRight(), level + 1); //recurse right
		for(int k = 0; k < level; k++)
			toRet += "\t";
		toRet += t.getValue() + "\n";
		toRet += display(t.getLeft(), level + 1); //recurse left
		return toRet;
	}

	public String inorderTraverse()
	{
		return inorderTraverse(root);
	}

	private  String inorderTraverse(TreeNode t)
	{
		String toReturn = "";
		if(t == null)
			return "";
		toReturn += inorderTraverse(t.getLeft());            //recurse left
		toReturn += t.getValue() + " ";    //inorder visit
		toReturn += inorderTraverse(t.getRight());  //recurse right
		return toReturn;
	}

	public String preorderTraverse()
	{
		return preorderTraverse(root);
	}

	private String preorderTraverse(TreeNode root)
	{
		String toReturn = "";
		if(root == null)
			return "";
		toReturn += root.getValue() + " ";              //preorder visit
		toReturn += preorderTraverse(root.getLeft());   //recurse left
		toReturn += preorderTraverse(root.getRight());  //recurse right
		return toReturn;
	}

	/* extension */
	public String inorderTraverseWithParentheses()
	{
		String s = inorderTraverseWithParentheses(root).trim();
		
		if (s.indexOf("(")==0)
			s = s.substring(1, s.length()-1);
		return s;
	}

	private String inorderTraverseWithParentheses(TreeNode t)
	{
		String toReturn = "";
		if(t == null)
			return "";
		String s = t.getValue().toString();

		if(s.equals("+") || s.equals("-"))
			toReturn += "( ";

		toReturn += inorderTraverseWithParentheses(t.getLeft());            //recurse left
		toReturn += t.getValue() + " ";    //inorder visit
		toReturn += inorderTraverseWithParentheses(t.getRight());  //recurse right

		if(s.equals("+") || s.equals("-"))
			toReturn += ") ";

		return toReturn;

	}
}