// Name: Amy Wang             Date: 4/7/2021
import java.util.*;
import java.io.*;
public class deHuffman
{
	public static void main(String[] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat binary message (middle part)? ");
		String middlePart = keyboard.next();
		Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
		String binaryCode = sc.next();
		Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 

		TreeNode root = huffmanTree(huffLines);
//		System.out.println(display(root, 0));
		String message = dehuff(binaryCode, root);
		System.out.println(message);

		sc.close();
		huffLines.close();
	}
	public static TreeNode huffmanTree(Scanner huffLines)
	{
		TreeNode root = new TreeNode("");
		while(huffLines.hasNextLine())
		{
			String line = huffLines.nextLine();
			TreeNode pointer = root;
			String sub = line.substring(1);
			for(int i = 0; i< sub.length(); i++) {
				if(sub.charAt(i) == '0') {
					if(pointer.getLeft() != null) {
						pointer = pointer.getLeft();
					} else {
						pointer.setLeft(new TreeNode(""));
						pointer = pointer.getLeft();
					}
				} else {
					if(pointer.getRight() != null) {
						pointer = pointer.getRight();
					} else {
						pointer.setRight(new TreeNode(""));
						pointer = pointer.getRight();
					}
				}
			}
			pointer.setValue(line.substring(0, 1));
		}
		return root;
	}
	public static String dehuff(String text, TreeNode root)
	{
		String msg = "";
		//		System.out.println("Text length = " + text.length());
		int index = 0;
		while(index < text.length()) {
			TreeNode pointer = root;
			String s = "";
			while(pointer != null) {
				//				System.out.println("Index = " + index);
				if(text.charAt(index) == '0') {
					pointer = pointer.getLeft();
					index++;
				} else {
					pointer = pointer.getRight();
					index++;
				}
				if(!pointer.getValue().equals("")) {
					msg += pointer.getValue();
					break;
				}
			}
		}
		return msg;
	}

	//	private static String display(TreeNode t, int level)
	//	{
	//		// turn your head towards left shoulder visualize tree
	//		String toRet = "";
	//		if(t == null)
	//			return "";
	//		toRet += display(t.getRight(), level + 1); //recurse right
	//		for(int k = 0; k < level; k++)
	//			toRet += "\t";
	//		toRet += t.getValue() + "\n";
	//		toRet += display(t.getLeft(), level + 1); //recurse left
	//		return toRet;
	//	}
}

/* TreeNode class for the AP Exams */
class TreeNode
{
	private Object value; 
	private TreeNode left, right;

	public TreeNode(Object initValue)
	{ 
		value = initValue; 
		left = null; 
		right = null; 
	}

	public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
	{ 
		value = initValue; 
		left = initLeft; 
		right = initRight; 
	}

	public Object getValue()
	{ 
		return value; 
	}

	public TreeNode getLeft() 
	{ 
		return left; 
	}

	public TreeNode getRight() 
	{ 
		return right; 
	}

	public void setValue(Object theNewValue) 
	{ 
		value = theNewValue; 
	}

	public void setLeft(TreeNode theNewLeft) 
	{ 
		left = theNewLeft;
	}

	public void setRight(TreeNode theNewRight)
	{ 
		right = theNewRight;
	}
}
