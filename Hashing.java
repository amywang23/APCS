// Name:  Amy Wang
// Date:  3/6/2021

/* 
   Assignment:  This hashing program results in collisions.
   You are to implement three different collision schemes: linear 
   probing, rehashing, and chaining.  Then implement a search 
   algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
	public static void main(String[] args)
	{
		int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
				"Hashing!\n"+
				"Enter the size of the array:  "));//20

		int numItems = Integer.parseInt(JOptionPane.showInputDialog(
				"Add n items:  "));               //15

		int scheme = Integer.parseInt(JOptionPane.showInputDialog(
				"The Load Factor is " + (double)numItems/arrayLength +
				"\nWhich collision scheme?\n"+
				"1. Linear Probing\n" +
				"2. Rehashing\n"+
				"3. Chaining"));
		//				int arrayLength = 30;
		//				int numItems = 20;
		//				int scheme = 3;

		Hashtable table = null;
		switch( scheme )
		{
		case 1:   
			table = new HashtableLinearProbe(arrayLength);
			break;
		case 2: 
			table = new HashtableRehash(arrayLength);
			break;
		case 3:  
			table = new HashtableChaining(arrayLength);
			break;
		default:  System.exit(0);    
		}
		for(int i = 0; i < numItems; i++)
			table.add("Item" + i);
		int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
				"Search for:  Item0" + " to "+ "Item"+(numItems-1)));
		//				int itemNumber = 0;
		while( itemNumber != -1 )
		{
			String key = "Item" + itemNumber;
			int index = table.indexOf(key); 
			if( index >= 0)    //found it
				System.out.println(key + " found  at index " + index);
			else
				System.out.println(key + " not found!");
			itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
					"Search for:  Item0" + " to "+ "Item"+(numItems-1)));
			//			break;
		} 
		System.exit(0);
	}
}

/*********************************************/
interface Hashtable
{
	void add(Object obj);
	int indexOf(Object obj);
}
/***************************************************/ 

class HashtableLinearProbe implements Hashtable
{
	private Object[] array;

	public HashtableLinearProbe(int size)//constructor
	{
		array = new Object[size];
	}

	public void add(Object obj)
	{
		int code = obj.hashCode();
		int index = Math.abs(code % array.length);
		if(array[index] == null)  //empty
		{
			//insert it
			array[index] = obj;
			System.out.println(obj + "\t" + code + "\t" + index);
		}
		else //collision
		{
			System.out.println(obj + "\t" + code + "\tCollision at "+ index);
			index = linearProbe(index);
			array[index] = obj;
			System.out.println(obj + "\t" + code + "\t" + index);
		}
	}  

	public int linearProbe(int index)
	{      
		for(int i = 1; i < array.length; i++) {
			if(array[(index+i)%array.length] == null)
				return (index+i)%array.length;
		}
		return index;
	}

	public int indexOf(Object obj)     
	{
		int index = Math.abs(obj.hashCode() % array.length);
		while(array[index] != null)
		{
			if(array[index].equals(obj))  //found it
			{
				return index;
			}
			else //search for it in a linear probe manner
			{
				index = (index + 1)% array.length;
				System.out.println("Looking at index " + index);
			}
		}
		//not found
		return -1;
	}
}

/*****************************************************/
class HashtableRehash implements Hashtable
{
	private Object[] array;
	private int constant;  

	public HashtableRehash(int size) //constructor
	{
		array = new Object[size];
		constant = fRP(size);
		System.out.println("Constant is" + constant);
	}

	public static int gcd(int n, int n1) {
		if (n1 != 0){
			if(n < n1)
				return gcd(n1, n);
			return gcd(n1, n % n1);
		} else{
			return n;
		}
	}

	public static int fRP(int size) {
		for(int i = 2; i <= size; i++) {
			if(gcd(size, i) == 1) {
				return i;
			}
		}
		return 0;
	}

	public void add(Object obj)
	{
		int code = obj.hashCode();
		int index = Math.abs(code % array.length);
		if(array[index] == null)  //empty
		{
			//insert it
			array[index] = obj;
			System.out.println(obj + "\t" + code + "\t" + index);
		}
		else //collision
		{
			System.out.println(obj + "\t" + code + "\tCollision at "+ index);
			index = rehash(index);
			array[index] = obj;
			System.out.println(obj + "\t" + code + "\t" + index);
		}
	}  

	public int rehash(int index)
	{
		while(array[index] != null) {
			index = (index+constant) % array.length;
			if(array[index] == null) {
				//				System.out.println("returning index");
				return index;
			}
		}
		return -1;
	}

	public  int indexOf(Object obj)
	{
		int index = Math.abs(obj.hashCode() % array.length);
		while(array[index] != null)
		{
			if(array[index].equals(obj))  //found it
			{
				return index;
			}
			else //search for it in a rehashing manner
			{
				index = (index+constant) % array.length;
				System.out.println("Looking at index " + index);
			}
		}
		//not found
		return -1;
	}
}

/********************************************************/
class HashtableChaining implements Hashtable
{
	private LinkedList[] array;

	public HashtableChaining(int size)
	{
		//instantiate the array
		//instantiate the LinkedLists
		array = new LinkedList[size];
		for(int i = 0; i< size; i++) {
			array[i] = new LinkedList();
		}
	}
	public void add(Object obj)
	{
		int code = obj.hashCode();
		int index = Math.abs(code % array.length);
		array[index].addFirst(obj);
		System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
	}  

	public int indexOf(Object obj)
	{
		int index = Math.abs(obj.hashCode() % array.length);
		if( !array[index].isEmpty() )
		{
			if(array[index].getFirst().equals(obj))  //found it
			{
				return index;
			}
			else //search for it in a chaining manner
			{
				for (int i = 0; i < array[index].size(); i++) {
					if(array[index].get(i).equals(obj)) {
						return index;
					}
				}
			}
		}
		//not found
		return -1;
	}
}