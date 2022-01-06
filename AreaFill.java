// Name: Amy Wang
// Date: 10/13/2020

import java.util.*;
import java.io.*;

public class AreaFill
{
	private static char[][] grid = null;
	private static String filename = null;

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		while(true)  // what does this do?
		{
			System.out.print("Fill the Area of (-1 to exit): ");
			filename = sc.next();
			if(filename.equals("-1"))
			{
				sc.close();
				System.out.println("Good-bye");
				//System.exit(0); 
				return;  
			}
			grid = read(filename);
			String theGrid = display(grid);
			System.out.println( theGrid );
			System.out.print( "1-Fill or 2-Fill-and-Count: ");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:
			{
				System.out.print("\nEnter ROW COL to fill from: ");
				int row = sc.nextInt();
				int col = sc.nextInt(); 
				fill(grid, row, col, grid[row][col]);
				System.out.println( display(grid) );
				break;
			}
			case 2:
			{
				System.out.print("\nEnter ROW COL to fill from: ");
				int row = sc.nextInt();
				int col = sc.nextInt();
				int count = fillAndCount(grid, row, col, grid[row][col]);
				System.out.println( display(grid) );
				System.out.println("count = " + count);
				System.out.println();
				break;
			}
			default:
				System.out.print("\nTry again! ");
			}
		}
	}

	/**
	 * Reads the contents of the file into a matrix.
	 * Uses try-catch.
	 * @param filename The string representing the filename.
	 * @returns A 2D array of chars.
	 */
	public static char[][] read(String filename)
	{
		Scanner infile = null;
		try
		{
			infile = new Scanner(new File(filename));
		}
		catch (Exception e)
		{
			System.out.println("File not found");
			return null;
		}
		/* enter your code here */
		int row = 0;
		int col = 0;
		int count = 0;
		if(infile.hasNext()) {
			row = infile.nextInt();
			col = infile.nextInt();
		}
		else {
			System.out.println("Nothing in file");
			infile.close();
			return null;
		}

		char[][] a = new char[row][col];
		while(infile.hasNext()) {
			String line = infile.nextLine();
			if(!line.equals("")) {
				a[count] = line.toCharArray();
				count++;
			}
		}
		infile.close();
		return a;

	}

	/**
	 * @param g A 2-D array of chars.
	 * @returns A string representing the 2D array.
	 */
	public static String display(char[][] g)
	{
		String ar = "";
		for(int i = 0; i < g.length; i++) {
			for(char entry : g[i]) {
				ar = ar + entry;
			}
			ar = ar + "\n";
		}
		return ar;
	}

	/**
	 * Fills part of the matrix with a different character.
	 * @param g A 2D char array.
	 * @param r An int representing the row of the cell to be filled.
	 * @param c An int representing the column of the cell to be filled.
	 * @param ch The char which is being replaced.
	 */
	public static void fill(char[][] g, int r, int c, char ch)
	{
		if(r < 0 || c < 0 || r >= g.length || c >= g[0].length || g[r][c] == '*') {
			return;
		}
		else{
			if(g[r][c] != ch)
				return;
			else {
				g[r][c] = '*';
			}
			if(r > 0)
				fill(g, r-1, c, ch);
			if(r < g.length-1)
				fill(g, r+1, c, ch);
			if(c < g[0].length-1)
				fill(g, r, c+1, ch);
			if(c > 0)
				fill(g, r, c-1, ch);
		}
	}

	/**
	 * Fills part of the matrix with a different character.
	 * Counts as you go.  Does not use a static variable.
	 * @param g A 2D char array.
	 * @param r An int representing the row of the cell to be filled.
	 * @param c An int representing the column of the cell to be filled.
	 * @param ch The char which is being replaced.
	 * @return An int representing the number of characters that were replaced.
	 */
	public static int fillAndCount(char[][] g, int r, int c, char ch)
	{
		int count = 0;
		if(r < 0 || c < 0 || r >= g.length || c >= g[0].length || g[r][c] == '*') {
			return 0;
		}
		else{
			if(g[r][c] != ch) {
				return 0;
			}
			else {
				g[r][c] = '*';
				count++;
			}
			if(r > 0)
				count += fillAndCount(g, r-1, c, ch);
			if(r < g.length-1)
				count += fillAndCount(g, r+1, c, ch);
			if(c < g[0].length-1)
				count += fillAndCount(g, r, c+1, ch);
			if(c > 0)
				count += fillAndCount(g, r, c-1, ch);
		}
		return count; //never reached
	}
}