// Name: Amy Wang
// Date: 12/15/2020
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
	public static void main(String[] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nEnter input file name: ");
		String inFileName = keyboard.nextLine().trim();
		Scanner inputFile = new Scanner(new File(inFileName));
		String outFileName = "fishIndex.txt";
		PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
		indexDocument(inputFile, outputFile);
		inputFile.close(); 						
		outputFile.close();
		System.out.println("Done.");
	}
	public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
	{
		DocumentIndex1 index = new DocumentIndex1();
		String line = null;
		int lineNum = 0;
		while(inputFile.hasNextLine())
		{
			lineNum++;
			index.addAllWords(inputFile.nextLine(), lineNum);
		}
		for(IndexEntry entry : index)
			outputFile.println(entry);
	}   
}
class DocumentIndex1 extends ArrayList<IndexEntry>
{
	//constructors
	public DocumentIndex1() {
		super();
	}

	public DocumentIndex1(int n) {
		super(n);
	}
	/** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
	public void addAllWords(String str, int lineNum) 
	{
		if(str.isEmpty()) {
			return;
		}
		String[] s = str.split("[., \"!?]");
		for(int i = 0; i < s.length; i++) {
			if(!s[i].isEmpty()) 
				addWord(s[i], lineNum);
		}
	}

	/** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
	public void addWord(String word, int lineNum)
	{
		if(this.size() == 0) {
			IndexEntry a = new IndexEntry(word);
			a.add(lineNum);
			this.add(a);
			return;
		}
		int n = foundOrInserted(word);
		IndexEntry a = this.get(n);
		a.add(lineNum);
	}

	/** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
	private int foundOrInserted(String word)
	{
		ListIterator<IndexEntry> it = this.listIterator();
//		int pos = 0;
//		while(it.hasNext()) {
//			int c = word.toUpperCase().compareTo(it.next().getWord());
//			if(c == 0) {
//				return pos;
//			}
//			if(c < 0) {
//				IndexEntry b = new IndexEntry(word);
//				this.add(pos, b);
//				return pos;
//			}
//			pos++;
//		}
//
//		IndexEntry b = new IndexEntry(word);
//		this.add(b);
		int pos = binaryhelper(word.toUpperCase(), 0, this.size()-1);
		if(pos > this.size()-1) {
			IndexEntry b = new IndexEntry(word);
			this.add(b);
		}
		else {
			if(word.toUpperCase().compareTo(this.get(pos).getWord()) != 0) {
				IndexEntry b = new IndexEntry(word);
				this.add(pos, b);
			}
		}
		return pos;
	}
	private int binaryhelper(String word, int start, int end)
	{
		if(start > end )
			return end;
		if(start == end) {
			if(word.compareTo(this.get(start).getWord()) <= 0) {
				return start;
			}
			else
				return start+1;
		}
		if(end-start == 1) {
			if(word.compareTo(this.get(start).getWord()) == 0) {
				return start;
			}
			else if(word.compareTo(this.get(end).getWord()) == 0) {
				return end;
			}
			else {
				if(word.compareTo(this.get(end).getWord()) > 0) {
					return end+1;
				}
				return end;
			}
		}
		int middle = (end-start)/2 + start;
		if(word.compareTo(this.get(middle).getWord()) == 0) {
			return middle;
		}
		else {
			if(word.compareTo(this.get(middle).getWord()) > 0) {
				return binaryhelper(word, middle + 1, end);
			}
			else {
				return binaryhelper(word, start, middle - 1);
			}
		}
	}
}


class IndexEntry extends ArrayList<Integer> implements Comparable<IndexEntry>
{
	//fields
	private String word;
	private ArrayList<Integer> numsList;

	//constructors
	public IndexEntry(String w){
		word = w.toUpperCase();
		numsList = new ArrayList<Integer>();
	}


	/**  appends num to numsList, but only if it is not already in that list.    
	 */
	public void add(int num)
	{
		ListIterator<Integer> it = numsList.listIterator();
		while(it.hasNext()) {
			if(it.next() == num)
				return;
		}
		numsList.add(num);
	}

	/** this is a standard accessor method  */
	public String getWord()
	{
		return word;
	}

	/**  returns a string representation of this Index Entry.  */
	public String toString()
	{
		String s = word + " ";
		for(int i = 0; i < numsList.size(); i++) {
			s += "" + numsList.get(i);
			if(i != numsList.size()-1)
				s += ", ";
		}
		return s;
	}

	@Override
	public int compareTo(IndexEntry o) {
		return this.getWord().compareTo(o.getWord());
	}
}