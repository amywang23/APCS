// Name: Amy Wang   
// Date: 9/15/2020
import java.util.*;
import java.io.*;
public class PigLatin
{
	public static void main(String[] args) 
	{
		//part_1_using_pig();
		//part_2_using_piglatenizeFile();
		/*  extension only    */
		String pigLatin = pig("What!?");
		System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
		pigLatin = pig("{(Hello!)}");
		System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
		pigLatin = pig("\"McDonald???\"");
		System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
	}

	public static void part_1_using_pig()	
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.print("\nWhat word? ");
			String s = sc.next();
			if(s.equals("-1"))
			{
				System.out.println("Goodbye!"); 
				System.exit(0);
			}
			String p = pig(s);
			System.out.println( p );
		}		
	}

	public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
	public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String vowels = "AEIOUaeiou";
	public static final String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String pig(String s)
	{
		if(s.length() == 0)
			return "";
		String result = "";
		String remix = new String(s);
		String prefix = "";
		String suffix = "";
		int start = 0;
		int end = s.length();

		//remove and store the beginning punctuation 
		if(punct.contains(s.charAt(0) + ""))
		{
			for(int i = 0; i < s.length(); i++)
			{
				if(punct.contains(s.charAt(i) + ""))
				{
					prefix = prefix + s.charAt(i);
				}
				else
				{
					start = i;
					break;
				}
			}
		}

		//remove and store the ending punctuation 
		if(punct.contains(s.charAt(s.length()-1) + ""))
		{
			for(int i = s.length()-1; i >= 0; i--)
			{
				if(punct.contains(s.charAt(i) + ""))
				{
					suffix = s.charAt(i) + suffix;
				}
				else
				{
					end = i+1;
					break;
				}
			}
		}
		remix = s.substring(start, end);
		//START HERE with the basic case:

		//find the index of the first vowel
		int indexOfFirstVowel = -1;
		for(int i = 0; i < remix.length(); i++)
		{
			if(vowels.contains(remix.charAt(i) + ""))
			{
				indexOfFirstVowel = i;
				break;
			}
		}
		//System.out.println(indexOfFirstVowel + "");

		//first letter is vowel
		if(indexOfFirstVowel == 0)
		{
			result = prefix + remix + "way" + suffix;
			//System.out.println(result);
			return result;
		}

		//if no vowel is found
		if(indexOfFirstVowel == -1)
		{
			result = "**** NO VOWEL ****";
			//System.out.println(result);
			return result;
		}

		//	     y is a vowel if it is not the first letter
		int yIndex = remix.indexOf("y");
		if(yIndex > 0 && yIndex < indexOfFirstVowel)
		{
			indexOfFirstVowel = yIndex;
		}

		//     qu
		int quIndex = remix.indexOf("qu");

		if(indexOfFirstVowel > quIndex)
		{
			if(vowels.contains("" + (remix.charAt(quIndex +2))))
			{
				indexOfFirstVowel = quIndex + 2;
			}
		}

		//is the first letter capitalized?
		boolean caps = false;
		if(capitalLetters.contains(remix.charAt(0) + ""))
		{
			caps = true;
		}

		String firstPart = remix.substring(0, indexOfFirstVowel);
		String secondPart = remix.substring(indexOfFirstVowel);

		if(caps)
		{
			String firstLetter = firstPart.substring(0,1);
			String restPart = firstPart.substring(1);
			firstPart = firstLetter.toLowerCase() + restPart;
			firstLetter = secondPart.substring(0,1);
			restPart = secondPart.substring(1);
			secondPart = firstLetter.toUpperCase() + restPart;
		}
		result = prefix + secondPart + firstPart + "ay" + suffix;
		//System.out.println(result);


		//return the piglatinized word 

		return result;


	}


	public static void part_2_using_piglatenizeFile() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("input filename including .txt: ");
		String fileNameIn = sc.next();
		System.out.print("output filename including .txt: ");
		String fileNameOut = sc.next();
		piglatenizeFile( fileNameIn, fileNameOut );
		System.out.println("Piglatin done!");
	}

	/****************************** 
	 *  piglatinizes each word in each line of the input file
	 *    precondition:  both fileNames include .txt
	 *    postcondition:  output a piglatinized .txt file 
	 ******************************/
	public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
	{
		Scanner infile = null;
		try
		{
			infile = new Scanner(new File(fileNameIn));  
		}
		catch(IOException e)
		{
			System.out.println("oops");
			System.exit(0);   
		}

		PrintWriter outfile = null;
		try
		{
			outfile = new PrintWriter(new FileWriter(fileNameOut));
		}
		catch(IOException e)
		{
			System.out.println("File not created");
			System.exit(0);
		}
		//process each word in each line
		while( infile.hasNext() )
		{
			String s = infile.nextLine();
			String[] splitArray = s.split(" ");
			for(int i = 0; i < splitArray.length; i++)
			{
				outfile.print(pig(splitArray[i]));
				if(i < splitArray.length-1)
				{
					outfile.print(" ");
				}
			}
			outfile.println();
		}
		outfile.close();
		infile.close();
	}

	/** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
	 */
	public static String pigReverse(String s)
	{
		if(s.length() == 0)
			return "";
		
		String remix = new String(s);
		String prefix = "";
		String suffix = "";
		int start = 0;
		int end = s.length();
		
		//remove and store the beginning punctuation 
		if(punct.contains(s.charAt(0) + ""))
		{
			for(int i = 0; i < s.length(); i++)
			{
				if(punct.contains(s.charAt(i) + ""))
				{
					prefix = prefix + s.charAt(i);
				}
				else
				{
					start = i;
					break;
				}
			}
		}

		//remove and store the ending punctuation 
		if(punct.contains(s.charAt(s.length()-1) + ""))
		{
			for(int i = s.length()-1; i >= 0; i--)
			{
				if(punct.contains(s.charAt(i) + ""))
				{
					suffix = s.charAt(i) + suffix;
				}
				else
				{
					end = i+1;
					break;
				}
			}
		}
		remix = s.substring(start, end);
		String reverse = "";
		
		for(int i = remix.length()-1; i >= 0; i--)
		{
			String charA = "" + remix.charAt(i);
			if(i == 0)
			{
				charA = charA.toLowerCase();			
			}
			if(i == remix.length()-1)
			{
				charA = charA.toUpperCase();	
			}
			reverse = reverse + charA;
		}
		
		return prefix + reverse + suffix;
	}
}