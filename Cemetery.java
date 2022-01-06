// Name:	Amy Wang
// Date:	9/23/2020
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need

public class Cemetery
{
	private static final DecimalFormat df = new DecimalFormat("0.0###");
	public static void main (String [] args)
	{
		//File file = new File("cemetery_short.txt");
		File file = new File("cemetery.txt");
		int numEntries = countEntries(file);
		Person[] cemetery = readIntoArray(file, numEntries); 
		//see what you have
		for (int i = 0; i < cemetery.length; i++) 
			System.out.println(cemetery[i]);

		int min = locateMinAgePerson(cemetery);
		int max = locateMaxAgePerson(cemetery); 
		System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
		System.out.println("Name of youngest person: " + cemetery[min].getName());
		System.out.println("Age of youngest person: " + df.format(cemetery[min].getAge()));    
		System.out.println("Name of oldest person: " + cemetery[max].getName());
		System.out.println("Age of oldest person: " + df.format(cemetery[max].getAge()));
		System.out.println("The average age is: " + df.format(averageAge(cemetery)));
		//you may create other testing cases here
		//comment them out when you submit your file to Grade-It


	}

	/* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
	 */
	public static int countEntries(File f)
	{
		Scanner file;
		int count = 0;
		try {
			file = new Scanner(f);

			while (file.hasNextLine()) 
			{
				count++;
				file.nextLine();
			}
			file.close();
		} catch (FileNotFoundException e) {
			System.out.print("File not found");
			return 0;
		}

		return count;
	}

	/* Reads the data from file f (you may assume each line has same alignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
	 */
	public static Person[] readIntoArray (File f, int num)
	{
		Scanner infile = null;
		Person[] personArray = new Person[num];
		int count = 0;
		try
		{
			infile = new Scanner(f);  
			while( infile.hasNext() )
			{
				String s = infile.nextLine();
				personArray[count] = makeObjects(s);
				count++;
			}
			infile.close();
		}
		catch(IOException e)
		{
			System.out.println("File is not valid");
			return null;   
		}
		return personArray;
	}

	/* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
	 */
	public static Person makeObjects(String entry)
	{
		//String str = entry.replaceAll(" +", " ");
		String[] splitArray = entry.split(" ");
		int index = 0;
		String name = "";
		String burialDate = "";
		String age = "";

		for(int i = 0; i < entry.length(); i++)
		{
			if(!splitArray[i].equals(""))
			{
				//Character.isDigit(splitArray[i].charAt(0))
				if((splitArray[i].charAt(0) <= '9' && splitArray[i].charAt(0) >='0') 
						|| (splitArray[i].charAt(0)=='x' && splitArray[i].charAt(0)=='x'))
				{
					index = i;
					break;
				}

				else
				{
					name = name + splitArray[i] + " ";
				}
			}
		}
		name = name.substring(0, name.length()-1);
		burialDate = splitArray[index] + " " + splitArray[index+1] + " " + splitArray[index+2];
		age = splitArray[index+3];

		Person person = new Person(name, burialDate, age);

		return person;
	}  

	/* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
	 */
	public static int locateMinAgePerson(Person[] arg)
	{
		int minPos = 0;
		double min = arg[0].getAge();
		if(arg.length == 0)
		{
			return -1;
		}
		for(int i = 0; i < arg.length; i++)
		{
			if(arg[i].getAge() < min)
			{
				minPos = i;
				min = arg[i].getAge();
			}
		}
		return minPos;
	}   

	/* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
	 */
	public static int locateMaxAgePerson(Person[] arg)
	{
		int maxPos = 0;
		double max = arg[0].getAge();
		if(arg.length == 0)
		{
			return -1;
		}
		for(int i = 0; i < arg.length; i++)
		{
			if(arg[i].getAge() > max)
			{
				maxPos = i;
				max = arg[i].getAge();
			}
		}
		return maxPos;
	}
	
	public static double averageAge(Person[] arg)
	{
		double sum = 0;
		double average = 0;
		for(int i = 0; i< arg.length; i++)
		{
			sum += arg[i].getAge();
		}
		average = sum/arg.length;
		return average;
	}
} 

class Person
{
	//constant that can be used for formatting purposes
	private static final DecimalFormat df = new DecimalFormat("0.0###");
	/* private fields */
	String name = "";
	String burialDate = "";
	double age = 0.0;
	/* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
	public Person(String name, String burialDate, String age)
	{
		this.name = name;
		this.burialDate = burialDate;
		//System.out.println("**********************"+name);
		this.age = calculateAge(age);
	}
	public Person() {
		name = "";
		burialDate = "";
		age = 0;
	}

	public String toString() {
		return name + ", " + burialDate + ", " + df.format(age);
	}  

	/* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBurialDate() {
		return burialDate;
	}


	public void setBurialDate(String burialDate) {
		this.burialDate = burialDate;
	}


	public double getAge() {
		return age;
	}


	public void setAge(double age) {
		this.age = age;
	}

	/*handles the inconsistencies regarding age, using calculateAge
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
	 */
	public double calculateAge(String a)
	{
		double age = 0;

		if(a.contains("w"))
		{
			String b = a.substring(0,a.length()-1);
			age = Double.parseDouble(b);
			age = (age*7)/365;
			//*7 and then /365
		} else { 

			if(a.contains("d"))
			{
				String b = a.substring(0,a.length()-1);
				age = Double.parseDouble(b);
				age = age/365;
				// divide 365
			} else {
				age = Double.parseDouble(a);
			}
		}
		
		return age;
	}
}

/******************************************

 John William ALLARDYCE, 17 Mar 1844, 2.9
 Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 Philip AMIS, 03 Aug 1848, 1.0
 Thomas ANDERSON, 06 Jul 1845, 27.0
 Edward ANGEL, 20 Nov 1842, 22.0
 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 Thomas William COLLEY, 08 Aug 1833, 0.011
 Joseph COLLIER, 03 Apr 1831, 58.0

 In the St. Mary Magdelene Old Fish Cemetery --> 
 Name of youngest person: Thomas William COLLEY
 Age of youngest person: 0.011
 Name of oldest person: Joseph COLLIER
 Age of oldest person: 58.0

 **************************************/