// Name: Amy Wang
// Date: 4/22/2021

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
	String toString(); // Don't use commas in the list.  Example: "C [C D]"
	String getName();
	ArrayList<Vertex> getAdjacencies();
	void addAdjacent(Vertex v);
} 

class Vertex implements VertexInterface 
{
	private final String name;
	private ArrayList<Vertex> adjacencies;

	/* enter your code here  */

	public Vertex(String name) {
		this.name = name;
		adjacencies = new ArrayList<Vertex>();
	}

	public void addAdjacent(Vertex v) {
		adjacencies.add(v);
	}


	public String getName() {
		return name;
	}

	public ArrayList<Vertex> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(ArrayList<Vertex> adjacencies) {
		this.adjacencies = adjacencies;
	}

	public String toString() {
		String s = name + " [";
		for(int i = 0; i < adjacencies.size(); i++) {
			s = s + adjacencies.get(i).getName() + " ";
		}
		return s.trim() + "]";
	}
}   

interface AdjListInterface 
{ 
	List<Vertex> getVertices();
	Vertex getVertex(int i) ;
	Vertex getVertex(String vertexName);
	Map<String, Integer> getVertexMap();
	void addVertex(String v);
	void addEdge(String source, String target);
	String toString();  //returns all vertices with their edges (omit commas)
}


/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
	List<Vertex> depthFirstSearch(String name);
	List<Vertex> depthFirstSearch(Vertex v);
	List<Vertex> breadthFirstSearch(String name);
	List<Vertex> breadthFirstSearch(Vertex v);
	/*  three extra credit methods */
	List<Vertex> depthFirstRecur(String name);
	List<Vertex> depthFirstRecur(Vertex v);
	void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
	void graphFromEdgeListData(String fileName) throws FileNotFoundException;
	int edgeCount();
	int vertexCount(); //count the vertices in the object
	boolean isReachable(String source, String target);
	boolean isConnected();
}


//public class AdjList implements AdjListInterface// , DFS_BFS , EdgeListWithCities
//{
//	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
//	private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
//
//	/* enter your code here  */
//
//	public AdjList() {
//		
//	}
//	
//	public List<Vertex> getVertices() {
//		return vertices;
//	}
//
//	public Vertex getVertex(int i) {
//		return vertices.get(i);
//	}
//
//	public Vertex getVertex(String vertexName) {
//		Integer i = nameToIndex.get(vertexName);
//		if(i == null) {
//			return null;
//		}
//		return vertices.get(i);
//	}
//
//	public Map<String, Integer> getVertexMap() {
//		return nameToIndex;
//	}
//
//	public void addVertex(String v) {
//		Vertex temp = getVertex(v);
//		if(temp == null) {
//			temp = new Vertex(v);
//			vertices.add(temp);
//			nameToIndex.put(v, vertices.size()-1);
//		}
//	}
//
//	public void addEdge(String source, String target) {
//		Vertex sourceVertex = getVertex(source);
//		Vertex targetVertex = getVertex(target);
//		if(sourceVertex == null) {
//			addVertex(source);
//			sourceVertex = getVertex(source);
//		}
//		if(targetVertex == null) {
//			addVertex(target);
//			targetVertex = getVertex(target);
//		}
//		sourceVertex.addAdjacent(targetVertex);
//	}
//
//	public Map<String, Integer> getNameToIndex() {
//		return nameToIndex;
//	}
//
//
//	public void setNameToIndex(Map<String, Integer> nameToIndex) {
//		this.nameToIndex = nameToIndex;
//	}
//
//	public void setVertices(ArrayList<Vertex> vertices) {
//		this.vertices = vertices;
//	}   
//	
//	/*  three extra credit methods, recursive version  */
//	List<Vertex> depthFirstRecur(String name)
//	{
//		return null;
//	}
//
//	List<Vertex> depthFirstRecur(Vertex v)
//	{
//		return null;
//	}
//
//	void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
//	{
//
//	}
//
//	@Override
//	public String toString() {
//		String s = "";
//		for(Vertex v: vertices) {
//			s = s + v.toString() + "\n";
//		}
//		return s;
//	}
//}

//public class AdjList implements DFS_BFS// , EdgeListWithCities
//{
//	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
//	private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
//
//	/* enter your code here  */
//
//	public AdjList() {
//
//	}
//
//	public List<Vertex> getVertices() {
//		return vertices;
//	}
//
//	public Vertex getVertex(int i) {
//		return vertices.get(i);
//	}
//
//	public Vertex getVertex(String vertexName) {
//		Integer i = nameToIndex.get(vertexName);
//		if(i == null) {
//			return null;
//		}
//		return vertices.get(i);
//	}
//
//	public Map<String, Integer> getVertexMap() {
//		return nameToIndex;
//	}
//
//	public void addVertex(String v) {
//		Vertex temp = getVertex(v);
//		if(temp == null) {
//			temp = new Vertex(v);
//			vertices.add(temp);
//			nameToIndex.put(v, vertices.size()-1);
//		}
//	}
//
//	public void addEdge(String source, String target) {
//		Vertex sourceVertex = getVertex(source);
//		Vertex targetVertex = getVertex(target);
//		if(sourceVertex == null) {
//			addVertex(source);
//			sourceVertex = getVertex(source);
//		}
//		if(targetVertex == null) {
//			addVertex(target);
//			targetVertex = getVertex(target);
//		}
//		sourceVertex.addAdjacent(targetVertex);
//	}
//
//	public Map<String, Integer> getNameToIndex() {
//		return nameToIndex;
//	}
//
//
//	public void setNameToIndex(Map<String, Integer> nameToIndex) {
//		this.nameToIndex = nameToIndex;
//	}
//
//	public void setVertices(ArrayList<Vertex> vertices) {
//		this.vertices = vertices;
//	}   
//
//	public List<Vertex> depthFirstSearch(String name) {
//		return depthFirstSearch(this.getVertex(name));
//	}
//
//	public List<Vertex> depthFirstSearch(Vertex v) {
//		Stack<Vertex> stack = new Stack<Vertex>();
//		ArrayList<Vertex> reach = new ArrayList<Vertex>();
//
//		stack.push(v);
//		while(!stack.isEmpty()) {
//			Vertex vr = stack.pop();
//
//			if(!reach.contains(vr)) {
//				reach.add(vr);
//				for(Vertex vert: vr.getAdjacencies()) {
//					stack.push(vert);
//				}
//			}
//		}
//		return reach;
//	}
//
//	public List<Vertex> breadthFirstSearch(String name) {
//		return breadthFirstSearch(this.getVertex(name));
//	}
//
//	public List<Vertex> breadthFirstSearch(Vertex v) {
//		Queue<Vertex> q = new LinkedList<Vertex>();
//		ArrayList<Vertex> reach = new ArrayList<Vertex>();
//		
//		q.add(v);
//		while(!q.isEmpty()) {
//			Vertex vr = q.remove();
//
//			if(!reach.contains(vr)) {
//				reach.add(vr);
//				for(Vertex vert: vr.getAdjacencies()) {
//					q.add(vert);
//				}
//			}
//		}
//		return reach;
//	}
//
//	/*  three extra credit methods, recursive version  */
//	public List<Vertex> depthFirstRecur(String name)
//	{
//		return depthFirstRecur(this.getVertex(name));
//	}
//
//	public List<Vertex> depthFirstRecur(Vertex v)
//	{
//		ArrayList<Vertex> reachable = new ArrayList<Vertex>();
//		depthFirstRecurHelper(v, reachable);
//		return reachable;
//	}
//
//	public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
//	{
//		if(!reachable.contains(v)) {
//			reachable.add(v);
//		}
////		for(Vertex vv: v.getAdjacencies()) {
////			if(!reachable.contains(vv)) {
////				depthFirstRecurHelper(vv, reachable);
////			}
////		}
//		for(int i = v.getAdjacencies().size()-1; i >=0 ; i--) {
//			Vertex vv = v.getAdjacencies().get(i);
//			if(!reachable.contains(vv)) {
//				depthFirstRecurHelper(vv, reachable);
//			}
//		}
//	}
//
//
//	public String toString() {
//		String s = "";
//		for(Vertex v: vertices) {
//			s = s + v.toString() + "\n";
//		}
//		return s;
//	}
//}

public class AdjList implements EdgeListWithCities
{
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();

	/* enter your code here  */

	public AdjList() {

	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public Vertex getVertex(int i) {
		return vertices.get(i);
	}

	public Vertex getVertex(String vertexName) {
		Integer i = nameToIndex.get(vertexName);
		if(i == null) {
			return null;
		}
		return vertices.get(i);
	}

	public Map<String, Integer> getVertexMap() {
		return nameToIndex;
	}

	public void addVertex(String v) {
		Vertex temp = getVertex(v);
		if(temp == null) {
			temp = new Vertex(v);
			vertices.add(temp);
			nameToIndex.put(v, vertices.size()-1);
		}
	}

	public void addEdge(String source, String target) {
		Vertex sourceVertex = getVertex(source);
		Vertex targetVertex = getVertex(target);
		if(sourceVertex == null) {
			addVertex(source);
			sourceVertex = getVertex(source);
		}
		if(targetVertex == null) {
			addVertex(target);
			targetVertex = getVertex(target);
		}
		sourceVertex.addAdjacent(targetVertex);
	}

	public Map<String, Integer> getNameToIndex() {
		return nameToIndex;
	}


	public void setNameToIndex(Map<String, Integer> nameToIndex) {
		this.nameToIndex = nameToIndex;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}   

	public List<Vertex> depthFirstSearch(String name) {
		return depthFirstSearch(this.getVertex(name));
	}

	public List<Vertex> depthFirstSearch(Vertex v) {
		Stack<Vertex> stack = new Stack<Vertex>();
		ArrayList<Vertex> reach = new ArrayList<Vertex>();

		stack.push(v);
		while(!stack.isEmpty()) {
			Vertex vr = stack.pop();

			if(!reach.contains(vr)) {
				reach.add(vr);
				for(Vertex vert: vr.getAdjacencies()) {
					stack.push(vert);
				}
			}
		}
		return reach;
	}

	public List<Vertex> breadthFirstSearch(String name) {
		return breadthFirstSearch(this.getVertex(name));
	}

	public List<Vertex> breadthFirstSearch(Vertex v) {
		Queue<Vertex> q = new LinkedList<Vertex>();
		ArrayList<Vertex> reach = new ArrayList<Vertex>();
		
		q.add(v);
		while(!q.isEmpty()) {
			Vertex vr = q.remove();

			if(!reach.contains(vr)) {
				reach.add(vr);
				for(Vertex vert: vr.getAdjacencies()) {
					q.add(vert);
				}
			}
		}
		return reach;
	}

	/*  three extra credit methods, recursive version  */
	public List<Vertex> depthFirstRecur(String name)
	{
		return null;
	}

	public List<Vertex> depthFirstRecur(Vertex v)
	{
		return null;
	}

	public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
	{

	}


	public String toString() {
		String s = "";
		for(Vertex v: vertices) {
			s = s + v.toString() + "\n";
		}
		return s;
	}

	public void graphFromEdgeListData(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		
		while(sc.hasNextLine()) {
			String[] pair = sc.nextLine().split(" ");
			this.addEdge(pair[0], pair[1]);
		}
	}

	public int edgeCount() {
		int count = 0;
		for(Vertex v: vertices) {
			count += v.getAdjacencies().size();
		}
		return count;
	}

	public int vertexCount() {
		return vertices.size();
	}

	public boolean isReachable(String source, String target) {
		List<Vertex> reach = depthFirstSearch(source);
		for(Vertex v: reach) {
			if(v.getName().equals(target)) {
				return true;
			}
		}
		return false;
	}

	public boolean isConnected() {
		for(Vertex target: vertices) {
			boolean reach = true;
			for(Vertex source: vertices) {
				if(source != target) {
					if(!isReachable(source.getName(), target.getName())) {
						reach = false;
						break;
					}
				}
			}
			if(!reach) {
				return false;
			}
		}
		return true;
	}
}


