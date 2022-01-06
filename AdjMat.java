// Name: Amy Wang
// Date: 4/15/2021

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
	void addEdge(int source, int target);
	void removeEdge(int source, int target);
	boolean isEdge(int from, int to);
	String toString();   //returns the grid as a String
	int edgeCount();
	List<Integer> getNeighbors(int source);
	//public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
	boolean isEdge(String from, String to);
	Map<String, Integer> getVertices();     
	void readNames(String fileName) throws FileNotFoundException;
	void readGrid(String fileName) throws FileNotFoundException;
	void displayVertices();
	void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
	int getCost(int from, int to);
	int getCost(String from, String to);
	void allPairsWeighted(); 
}

//public class AdjMat implements AdjacencyMatrix//,Warshall//,Floyd 
//{
//	private int[][] grid = null;   //adjacency matrix representation
//	private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
//	/*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
//
//	/*  enter your code here  */  
//	public AdjMat() {
//		grid = null;
//		vertices = null;
//	}
//
//	public AdjMat(int size) {
//		grid = new int[size][size];
//		vertices = null;
//	}
//
//	@Override
//	public void addEdge(int source, int target) {
//		grid[source][target] = 1;
//	}
//
//	@Override
//	public void removeEdge(int source, int target) {
//		grid[source][target] = 0;
//	}
//
//	@Override
//	public boolean isEdge(int from, int to) {
//		if(grid[from][to] ==1)
//			return true;
//		return false;
//	}
//
//	@Override
//	public int edgeCount() {
//		int count = 0;
//		for(int i = 0; i< grid.length; i++) {
//			for(int j = 0; j < grid[i].length; j++) {
//				if(grid[i][j] == 1) {
//					count++;
//				}
//			}
//		}
//		return count;
//	}
//
//	@Override
//	public List<Integer> getNeighbors(int source) {
//		List<Integer> list = new ArrayList<Integer>();
//		for(int i = 0; i< grid[source].length; i++) {
//			if(grid[source][i] == 1) {
//				list.add(i);
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public String toString() {
//		String s = "";
//		for(int i = 0; i< grid.length; i++) {
//			for(int j = 0; j < grid[0].length; j++) {
//				s = s + grid[i][j] + "  ";
//			}
//			s = s.trim() + "\n";
//		}
//		return s;
//	}
//
//	
//
//}

//public class AdjMat implements Warshall//,Floyd 
//{
//	private int[][] grid = null;   //adjacency matrix representation
//	private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
//	/*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
//
//	/*  enter your code here  */  
//
//	public AdjMat(int size) {
//		grid = new int[size][size];
//		vertices = new HashMap<String, Integer>();
//		nameList = new ArrayList<String>();
//	}
//
//	public boolean isEdge(int from, int to) {
//		if(grid[from][to] ==1)
//			return true;
//		return false;
//	}
//
//	public int edgeCount() {
//		int count = 0;
//		for(int i = 0; i< grid.length; i++) {
//			for(int j = 0; j < grid[i].length; j++) {
//				if(grid[i][j] == 1) {
//					count++;
//				}
//			}
//		}
//		return count;
//	}
//
//	public String toString() {
//		String s = "";
//		for(int i = 0; i< grid.length; i++) {
//			for(int j = 0; j < grid[0].length; j++) {
//				s = s + grid[i][j] + "  ";
//			}
//			s = s.trim() + "\n";
//		}
//		return s;
//	}
//
//	@Override
//	public boolean isEdge(String from, String to) {
//		int n1 = vertices.get(from);
//		int n2 = vertices.get(to);
////		System.out.println("n1 = " + n1 + " n2 = " + n2);
//		return isEdge(n1, n2);
//	}
//
//	@Override
//	public Map<String, Integer> getVertices() {
//		return vertices;
//	}
//
//	@Override
//	public void readNames(String fileName) throws FileNotFoundException {
//		Scanner s = new Scanner(new File(fileName));
//		int size = Integer.parseInt(s.nextLine());
//		for(int i = 0; i < size; i++) {
//			String str = s.nextLine();
//			nameList.add(str);
//			vertices.put(str, i);
//		}
//	}
//
//	@Override
//	public void readGrid(String fileName) throws FileNotFoundException {
//		Scanner s = new Scanner(new File(fileName));
//		int size = Integer.parseInt(s.nextLine());
//		grid = new int[size][size];
//		for(int i = 0; i < grid.length; i++) {
//				String str = s.nextLine();
////				System.out.println("i =" + i + " " + str);
//				String[] split = str.split(" ");
//				for(int j = 0; j < split.length; j++) {
//					grid[i][j] = Integer.parseInt(split[j]);
//				}
//		}
//	}
//
//	@Override
//	public void displayVertices() {
//		for(int i = 0; i < nameList.size(); i++) {
//			System.out.println(i + "-" + nameList.get(i));
//		}
//	}
//
//	@Override
//	public void allPairsReachability() {
//		int size = grid.length;
//		for(int v = 0; v < size; v++) {
//			for(int i = 0; i < size; i++) {
//				for(int j = 0; j < size; j++) {
//					if(isEdge(i, v) && isEdge(v, j)) {
//						grid[i][j] = 1;
//					}
//				}
//			}
//		}
//	}
//	
//	public ArrayList<String> getReachables(String city) {
//		ArrayList<String> array = new ArrayList<String>();
//		int row = vertices.get(city);
//		for(int i = 0; i< grid[row].length; i++) {
//			if(grid[row][i] == 1) {
//				array.add(nameList.get(i));
//			}
//		}
//		return array;
//	}
//}

public class AdjMat implements Floyd 
{
	private int[][] grid = null;   //adjacency matrix representation
	private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
	/*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name

	/*  enter your code here  */  

	public AdjMat(int size) {
		grid = new int[size][size];
		vertices = new HashMap<String, Integer>();
		nameList = new ArrayList<String>();
	}

	public boolean isEdge(int from, int to) {
		if(grid[from][to] > 0 && grid[from][to] < 9999)
			return true;
		return false;
	}

	public int edgeCount() {
		int count = 0;
		for(int i = 0; i< grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] > 0 && grid[i][j] < 9999) {
					count++;
				}
			}
		}
		return count;
	}

	public String toString() {
		String s = "";
		for(int i = 0; i< grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				s = s + grid[i][j] + "  ";
			}
			s = s.trim() + "\n";
		}
		return s;
	}


	public boolean isEdge(String from, String to) {
		int n1 = vertices.get(from);
		int n2 = vertices.get(to);
		//		System.out.println("n1 = " + n1 + " n2 = " + n2);
		return isEdge(n1, n2);
	}


	public Map<String, Integer> getVertices() {
		return vertices;
	}

	public void readNames(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		int size = Integer.parseInt(s.nextLine());
		for(int i = 0; i < size; i++) {
			String str = s.nextLine();
			nameList.add(str);
			vertices.put(str, i);
		}
	}


	public void readGrid(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		int size = Integer.parseInt(s.nextLine());
		grid = new int[size][size];
		for(int i = 0; i < grid.length; i++) {
			String str = s.nextLine();
			//				System.out.println("i =" + i + " " + str);
			String[] split = str.split(" ");
			for(int j = 0; j < split.length; j++) {
				grid[i][j] = Integer.parseInt(split[j]);
			}
		}
	}


	public void displayVertices() {
		for(int i = 0; i < nameList.size(); i++) {
			System.out.println(i + "-" + nameList.get(i));
		}
	}

	public void allPairsWeighted() {
		int size = grid.length;
		for(int v = 0; v < size; v++) {
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(isEdge(i, v) && isEdge(v, j)) {
						if(grid[i][v] + grid[v][j] < grid[i][j])
							grid[i][j] = grid[i][v] + grid[v][j];
					}
				}
			}
		}
	}

	public ArrayList<String> getReachables(String city) {
		ArrayList<String> array = new ArrayList<String>();
		int row = vertices.get(city);
		for(int i = 0; i< grid[row].length; i++) {
			if(grid[row][i] == 1) {
				array.add(nameList.get(i));
			}
		}
		return array;
	}

	@Override
	public int getCost(int from, int to) {
		return grid[from][to];
	}

	@Override
	public int getCost(String from, String to) {
		int n1 = vertices.get(from);
		int n2 = vertices.get(to);
		return getCost(n1, n2);
	}
}

