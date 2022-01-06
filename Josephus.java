//Name: Amy Wang
//Date: 11/24/2020

import java.util.*;
import java.io.*;

public class Josephus
{
	private static String WINNER = "Josephus";

	public static void main(String[] args) throws FileNotFoundException
	{
		ListNode p = new ListNode("A", null);
		p.setNext(p);
		p = insert(p, "B");
		p = insert(p, "C");
		p = insert(p, "D");
		print(p);

		/* run numberCircle first with J_numbers.txt  */
		Scanner sc = new Scanner(System.in);
		System.out.print("How many names (2-20)? ");
		int n = Integer.parseInt(sc.next());
		System.out.print("How many names to count off each time? ");
		int countOff = Integer.parseInt(sc.next());
		ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
		System.out.println(winningPos.getValue() + " wins!");  

		/* run josephusCircle next with J_names.txt  */
		System.out.println("\n ****  Now start all over. **** \n");
		System.out.print("Where should "+WINNER+" stand?  ");
		int winPos = Integer.parseInt(sc.next());        
		ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
		System.out.println(theWinner.getValue() + " wins!");  
	}

	public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
	{
		ListNode p = null;
		p = readNLinesOfFile(n, new File(filename));
		p = countingOff(p, countOff, n);
		return p;
	}

	public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
	{
		ListNode p = null;
		p = readNLinesOfFile(n, new File(filename));
		replaceAt(p, WINNER, winPos);
		p = countingOff(p, countOff, n);
		return p;
	}

	/* reads the names, calls insert(), builds the linked list.
	 */
	public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
	{
		int counter = 0;
		ListNode p = null;
		Scanner sc = new Scanner(f);
		while(sc.hasNext()) {
			p = insert(p, sc.next());
			counter++;
			if(counter == n)
				break;
		}
		sc.close();
		return p;
	}

	/* helper method to build the list.  Creates the node, then
	 * inserts it in the circular linked list.
	 */
	public static ListNode insert(ListNode p, Object obj)
	{
		ListNode head = p;
		ListNode node = new ListNode(obj, null);
		node.setNext(node);
		if(p == null)
			return node;
		while(head.getNext() != p) {
			head = head.getNext();
		}
		head.setNext(node);
		node.setNext(p);
		return p;
	}

	/* Runs a Josephus game, counting off and removing each name. Prints after each removal.
   Ends with one remaining name, who is the winner. 
	 */
	public static ListNode countingOff(ListNode p, int count, int n)
	{
		print(p);
		for(int i = 0; i < n-1; i++) {
			p = remove(p, count);
			print(p);
		}
		return p;
	}

	/* removes the node after counting off count-1 nodes.
	 */
	public static ListNode remove(ListNode p, int count)
	{
		ListNode nd = p;
		ListNode previous = null;
		if(count <= 0)
			return p;
		if(count == 1) {
			while(nd.getNext() != p)
			{
				nd = nd.getNext();
			}
			nd.setNext(p.getNext());
			return p.getNext();
		}
		for(int i = 1; i < count; i++) {
			previous = nd;
			nd = nd.getNext();
		}
		previous.setNext(nd.getNext());
		return previous.getNext();
	}

	/* prints the circular linked list.
	 */
	public static void print(ListNode p)
	{
		ListNode lp = p;
		if(p == null)
			System.out.println("");
		while(lp.getNext() != p)
		{
			System.out.print(lp.getValue());
			lp = lp.getNext();
			if(lp != p)
				System.out.print(" ");
		}
		System.out.println(lp.getValue());
	}

	/* replaces the value (the string) at the winning node.
	 */
	public static void replaceAt(ListNode p, Object obj, int pos)
	{
		ListNode lp = p;
		for(int i = 0;i < pos-1; i++) {
			lp = lp.getNext();
		}
		lp.setValue(obj);
	}
}
/**********************************************************
  ----jGRASP exec: java Josephus_Teacher
A B C D
How many names (2-20)? 5
How many names to count off each time? 2
1 2 3 4 5
3 4 5 1
5 1 3
3 5
3
3 wins!

****  Now start all over. **** 

Where should Josephus stand?  3
Michael Hannah Josephus Ruth Matthew
Josephus Ruth Matthew Michael
Matthew Michael Josephus
Josephus Matthew
Josephus
Josephus wins!

----jGRASP: operation complete.

**************************************************/
