// Name: Amy Wang
// Date: 10/20/2020

import java.io.File;
import java.util.Scanner;

public class MazeMaster
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the maze's filename (no .txt): ");
		Maze m = new Maze(sc.next()+".txt");
		//Maze m = new Maze();    //extension
		m.display();      
		System.out.println("Options: ");
		System.out.println("1: Mark all dots.");
		System.out.println("2: Mark all dots and display the number of recursive calls.");
		System.out.println("3: Mark only the correct path.");
		System.out.println("4: Mark only the correct path. If no path exists, say so.");
		System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
		System.out.print("Please make a selection: ");
		m.solve(sc.nextInt());
		m.display();      //display solved maze
		sc.close();
	} 
}

class Maze
{
	//constants
	private final char WALL = 'W';
	private final char DOT = '.';
	private final char START = 'S';
	private final char EXIT = 'E';
	private final char TEMP = 'o';
	private final char PATH = '*';
	//instance fields
	private char[][] maze;
	private int startRow, startCol;
	private final int max = 10;
	private final int min = 3;

	//constructors

	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
	public Maze()
	{
		int row = (int)(Math.random()*(max-min+1) + min);
		int col = (int)(Math.random()*(max-min+1) + min);
		maze = new char[row][col];

		startRow = (int)(Math.random()*row);
		startCol = (int)(Math.random()*col);
		maze[startRow][startCol] = START;

		int r = (int)(Math.random()*row);
		int c = (int)(Math.random()*col);
		if(startRow == r && startCol == c) {
			if(startRow > 0) 
				r--;
			else
				r++;
		}
		maze[r][c] = EXIT;

		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze[0].length; j++)
			{ 
				if(maze[i][j] != START && maze[i][j] != EXIT) {
					int k = (int)(Math.random()*100);
					if(k%2 ==0)
						maze[i][j] = DOT;
					else
						maze[i][j] = WALL;
				}
			}
		}
	}

	/* 
	 * Copy Constructor  
	 */
	public Maze(char[][] m)  
	{
		maze = m;
		for(int r = 0; r < maze.length; r++)
		{
			for(int c = 0; c < maze[0].length; c++)
			{ 
				if(maze[r][c] == START)      //identify start location
				{
					startRow = r;
					startCol = c;
				}
			}
		}
	} 

	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
	public Maze(String filename)    
	{
		Scanner infile = null;
		try
		{
			infile = new Scanner(new File(filename));
		}
		catch (Exception e)
		{
			System.out.println("File not found");
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
		}
		maze = new char[row][col];
		while(infile.hasNext()) {
			String line = infile.nextLine();
			if(!line.equals("")) {
				maze[count] = line.toCharArray();
				count++;
			}
		}
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[0].length; j++) {
				if(maze[i][j] == START)      //identify start location
				{
					startRow = i;
					startCol = j;
					break;
				}
			}
		}
		infile.close();
	}

	public char[][] getMaze()
	{
		return maze;
	}

	public void display()
	{
		if(maze==null) 
			return;
		for(int a = 0; a<maze.length; a++)
		{
			for(int b = 0; b<maze[0].length; b++)
			{
				System.out.print(maze[a][b]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void solve(int n)
	{
		switch(n)
		{
		case 1:
		{
			markAll(startRow, startCol);
			break;
		}
		case 2:
		{
			int count = markAllAndCountRecursions(startRow, startCol);
			System.out.println("Number of recursions = " + count);
			break;
		}
		case 3:
		{
			markTheCorrectPath(startRow, startCol);
			break;
		}
		case 4:         //use mazeNoPath.txt 
		{
			if( !markTheCorrectPath(startRow, startCol) )
				System.out.println("No path exists."); 
			break;
		}
		case 5:
		{
			if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
				System.out.println("No path exists."); 
			break;
		}
		default:
			System.out.println("File not found");   
		}
	}

	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
	public void markAll(int r, int c)
	{
		if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == PATH 
				|| maze[r][c] == WALL || maze[r][c] == EXIT) {
			return;
		}
		else{
			if(maze[r][c] != DOT && maze[r][c] != START) {
				return;
			}
			else if (maze[r][c] != START) {
				maze[r][c] = PATH;
			}
			if(r > 0)
				markAll(r-1, c);
			if(r < maze.length-1)
				markAll(r+1, c);
			if(c < maze[0].length-1)
				markAll(r, c+1);
			if(c > 0)
				markAll(r, c-1);
		}
	}

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
	public int markAllAndCountRecursions(int r, int c)
	{
		int count = 0;
		count++;
		if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == PATH 
				|| maze[r][c] == WALL || maze[r][c] == EXIT) {
			return 1;
		}
		else{
			if(maze[r][c] != DOT && maze[r][c] != START) {
				return 1;
			}
			else if (maze[r][c] != START) {
				maze[r][c] = PATH;
			}
			if(r > 0)
				count += markAllAndCountRecursions(r-1, c);
			if(r < maze.length-1)
				count += markAllAndCountRecursions(r+1, c);
			if(c < maze[0].length-1)
				count += markAllAndCountRecursions(r, c+1);
			if(c > 0)
				count += markAllAndCountRecursions(r, c-1);
		}
		return count;
	}

	/* 
	 * From handout, #3.
	 * Solve the maze, OR the booleans, and mark the path through it with a “*” 
	 * Recur until you find E, then mark the True path.
	 */ 	
	public boolean markTheCorrectPath(int r, int c)
	{
		if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == PATH 
				|| maze[r][c] == WALL) {
			return false;
		}
		else{
			if(maze[r][c] == EXIT) {
				return true;
			}
			if(maze[r][c] != DOT && maze[r][c] != START) {
				return false;
			}
			else if (maze[r][c] != START) {
				maze[r][c] = TEMP;
			}
			if(markTheCorrectPath(r-1, c) || markTheCorrectPath(r+1, c) 
					|| markTheCorrectPath(r, c+1) || markTheCorrectPath(r, c-1)) {
				if(maze[r][c] == TEMP)
					maze[r][c] = PATH;
				return true;
			}
			else {
				if(maze[r][c] == TEMP)
					maze[r][c] = DOT;
				return false;
			}
		}
	}


	/*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need. */


	/* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
	public boolean markCorrectPathAndCountSteps(int r, int c, int count)
	{
		if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == PATH 
				|| maze[r][c] == WALL) {
			return false;
		}
		else{
			if(maze[r][c] == EXIT) {
				System.out.println("Number of steps = " + count);
				return true;
			}
			if(maze[r][c] != DOT && maze[r][c] != START) {
				return false;
			}
			else if (maze[r][c] != START) {
				maze[r][c] = TEMP;
			}
			if(markCorrectPathAndCountSteps(r-1, c, count + 1) || markCorrectPathAndCountSteps(r+1, c, count + 1) 
					|| markCorrectPathAndCountSteps(r, c+1, count + 1) || markCorrectPathAndCountSteps(r, c-1, count + 1)) {
				if(maze[r][c] == TEMP)
					maze[r][c] = PATH;
				return true;
			}
			else {
				if(maze[r][c] == TEMP)
					maze[r][c] = DOT;
				return false;
			}
		}
	}
}

/*****************************************

 ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW

 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW


  ----jGRASP: operation complete.

  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW

 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW


  ----jGRASP: operation complete.

  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW

 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 3
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW


  ----jGRASP: operation complete.


  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW

 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW


  ----jGRASP: operation complete.

  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW

 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 5
 Number of steps = 14
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW


  ----jGRASP: operation complete.
 ********************************************/