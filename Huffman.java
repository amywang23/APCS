// name: Amy Wang        date: 4/14/2021
import java.util.*;
import java.io.*;
public class Huffman
{
	public static Scanner keyboard = new Scanner(System.in);
	public static void main(String[] args) throws IOException
	{
		//Prompt for two strings 
		System.out.print("Encoding using Huffman codes");
		System.out.print("\nWhat message? ");
		String message = keyboard.nextLine();
		//	   String message = "TJHSSTS";

		System.out.print("\nEnter middle part of filename:  ");
		String middlePart = keyboard.next();
		//	   String middlePart = "tj";

		huffmanize( message, middlePart );
	}
	public static void huffmanize(String message, String middlePart) throws IOException
	{
		//Make a frequency table of the letters
		Map<String, Integer> freq = new TreeMap<String, Integer>();
		for(int i = 0; i < message.length(); i++) {
			String s = message.charAt(i) + "";
			if(freq.containsKey(s)) {
				freq.put(s, freq.get(s) + 1);
			} else {
				freq.put(s, 1);
			}
		}
		//	   System.out.println(freq);

		//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
		//        node into a priority queue (or a min-heap).     
		PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
		for (Map.Entry<String,Integer> entry : freq.entrySet()) {
			HuffmanTreeNode node = new HuffmanTreeNode(entry.getKey(), entry.getValue());
			pq.add(node);
		}
		//	   System.out.println(pq);


		//Use the priority queue of nodes to build the Huffman tree
		HuffmanTreeNode root = null;
		while(!pq.isEmpty()) {
			HuffmanTreeNode n1 = pq.remove();
			if(!pq.isEmpty()) {
				HuffmanTreeNode n2 = pq.remove();
				int i = (Integer) n1.getValue2();
				int j = (Integer) n2.getValue2();
				HuffmanTreeNode n3 = new HuffmanTreeNode("*", (i+j), n1, n2);
				pq.add(n3);

			} else {
				root = n1;
			}
		}
		//System.out.println(display(root, 0));

		//Process the string letter-by-letter and search the tree for the 
		//       letter. It's recursive. As you recur, build the path through the tree, 
		//       where going left is 0 and going right is 1.
		String code = "";
		for(int i = 0; i < message.length(); i++) {
			String s = message.charAt(i) + "";
			String str = getCode(root, s);
			code += str;
		}

		//System.out.println the binary message 
		System.out.println(code);


		//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
		//System.out.println the scheme from the tree--needs a recursive helper method
		//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")

		try
		{
			System.setOut(new PrintStream(new FileOutputStream("message." + middlePart + ".txt")));
			System.out.println(code);
			System.setOut(new PrintStream(new FileOutputStream("scheme." + middlePart + ".txt")));
			displayScheme(root, "");
		}
		catch(Exception e)
		{
			System.out.println("File not created");
		}




	} //end of huffmanize method

	private static String getCode(HuffmanTreeNode t, String tofind) {
		if(t == null) {
			return "Not Found";
		}
		if(t.getLeft() == null && t.getRight() == null && t.getValue1().toString().equals(tofind)) {
			return "";
		}
		String ls = getCode(t.getLeft(), tofind);
		if(!ls.contains("Not Found")) {
			return "0" + ls;
		} else {
			return "1" + getCode(t.getRight(), tofind);
		}
	}

	public static void displayScheme(HuffmanTreeNode t, String s) {
		if(t == null)
			return;
		if(t.getLeft() == null && t.getRight() == null && !t.getValue1().toString().equals("*")) {
			System.out.println(t.getValue1() + s);
		}

		displayScheme(t.getLeft(), s + "0");
		displayScheme(t.getRight(), s + "1");
	}

//	private static String display(HuffmanTreeNode t, int level)
//	{
//		// turn your head towards left shoulder visualize tree
//		String toRet = "";
//		if(t == null)
//			return "";
//		toRet += display(t.getRight(), level + 1); //recurse right
//		for(int k = 0; k < level; k++)
//			toRet += "\t";
//		toRet += t.getValue1() + ":" + t.getValue2() + "\n";
//		toRet += display(t.getLeft(), level + 1); //recurse left
//		return toRet;
//	}
}
/*
 * This tree node stores two values.  Look at TreeNode's API for some help.
 * The compareTo method must ensure that the lowest frequency has the highest priority.
 */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
	private Object value1, value2; 
	private HuffmanTreeNode left, right;

	public HuffmanTreeNode(Object initValue1, Object initValue2)
	{ 
		value1 = initValue1;
		value2 = initValue2;
		left = null;
		right = null; 
	}

	public HuffmanTreeNode(Object initValue1, Object initValue2, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
	{ 
		value1 = initValue1; 
		value2 = initValue2;
		left = initLeft; 
		right = initRight; 
	}

	public Object getValue1()
	{ 
		return value1; 
	}

	public Object getValue2()
	{ 
		return value2; 
	}

	public HuffmanTreeNode getLeft() 
	{ 
		return left; 
	}

	public HuffmanTreeNode getRight() 
	{ 
		return right; 
	}

	public void setValue1(Object theNewValue) 
	{ 
		value1 = theNewValue; 
	}

	public void setValue2(Object theNewValue) 
	{ 
		value2 = theNewValue; 
	}

	public void setLeft(HuffmanTreeNode theNewLeft) 
	{ 
		left = theNewLeft;
	}

	public void setRight(HuffmanTreeNode theNewRight)
	{ 
		right = theNewRight;
	}

	@Override
	public int compareTo(HuffmanTreeNode node) {
		if(this == node) {
			return 0;
		}
		if(((Integer) value2).compareTo((Integer) node.getValue2()) == 0) {
			return value1.toString().compareTo(node.getValue1().toString());
		}
		else {
			return ((Integer) value2).compareTo((Integer) node.getValue2());
		}

	}

	@Override
	public String toString() {
		return value1 + ":" + value2;
	}
}
