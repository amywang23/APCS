// Name: Amy Wang
// Date: 5/21/2021
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Edge 
{
   public final wVertex target;  //if it's public, you don't need accessor methods
   public final double weight;   //if it's public, you don't need accessor methods
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
   
   public wVertex(String name) {
		this.name = name;
		adjacencies = new ArrayList<Edge>();
	}

public String getName() {
	
	return name;
}

public double getMinDistance() {
	
	return minDistance;
}

public void setMinDistance(double m) {
	
	minDistance = m;
}

public ArrayList<Edge> getAdjacencies() {
	
	return adjacencies;
}

public void addEdge(wVertex v, double weight) {
	adjacencies.add(new Edge(v, weight));
}

public int compareTo(wVertex other) {
	return (int)(minDistance-other.minDistance);
}

public wVertex getPrevious() {
	return previous;
}

public void setPrevious(wVertex v) {
	previous = v;
}
    
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 

//public class AdjListWeighted implements AdjListWeightedInterface //,AdjListWeightedInterfaceWithCities
//{
//   private List<wVertex> vertices = new ArrayList<wVertex>();
//   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
//   //the constructor is a no-arg constructor 
//public AdjListWeighted() {
//	
//}
//   
//   /*  enter your code for Graphs 6 */ 
//
//public List<wVertex> getVertices() {
//	
//	return vertices;
//}
//
//public Map<String, Integer> getNameToIndex() {
//	
//	return nameToIndex;
//}
//
//public wVertex getVertex(int i) {
//	
//	return vertices.get(i);
//}
//
//public wVertex getVertex(String vertexName) {
//	Integer i = nameToIndex.get(vertexName);
//	if(i == null) {
//		return null;
//	}
//	return vertices.get(i);
//}
//
//public void addVertex(String v) {
//	wVertex temp = getVertex(v);
//	if(temp == null) {
//		temp = new wVertex(v);
//		vertices.add(temp);
//		nameToIndex.put(v, vertices.size()-1);
//	}
//}
//
//public void addEdge(String source, String target, double weight) {
//	wVertex sourcev = getVertex(source);
//	wVertex targetv = getVertex(target);
//	sourcev.addEdge(targetv, weight);
//}
//
//public void minimumWeightPath(String vertexName) {
//	PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
//	for(wVertex v : vertices) {
//		v.setMinDistance(Double.POSITIVE_INFINITY);
//	}
//	getVertex(vertexName).setMinDistance(0);
//	pq.addAll(vertices);
//	while(!pq.isEmpty()) {
//		wVertex first = pq.peek();
//		for(Edge e: first.getAdjacencies()) {
//			double newDistance = first.getMinDistance()+e.weight;
//			if(newDistance < e.target.getMinDistance()) {
//				e.target.setMinDistance(newDistance);
//			}
//		}
//		pq.remove();
//	}
//}  
//   
//   
//   /*  enter your code for two new methods in Graphs 7 */
//   
//   
//}   

public class AdjListWeighted implements AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
public AdjListWeighted() {
	
}
   
   /*  enter your code for Graphs 6 */ 

public List<wVertex> getVertices() {
	
	return vertices;
}

public Map<String, Integer> getNameToIndex() {
	
	return nameToIndex;
}

public wVertex getVertex(int i) {
	
	return vertices.get(i);
}

public wVertex getVertex(String vertexName) {
	Integer i = nameToIndex.get(vertexName);
	if(i == null) {
		return null;
	}
	return vertices.get(i);
}

public void addVertex(String v) {
	wVertex temp = getVertex(v);
	if(temp == null) {
		temp = new wVertex(v);
		vertices.add(temp);
		nameToIndex.put(v, vertices.size()-1);
	}
}

public void addEdge(String source, String target, double weight) {
	wVertex sourcev = getVertex(source);
	wVertex targetv = getVertex(target);
	sourcev.addEdge(targetv, weight);
}

public void minimumWeightPath(String vertexName) {
	PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
	for(wVertex v : vertices) {
		v.setMinDistance(Double.POSITIVE_INFINITY);
		v.setPrevious(null);
	}
	getVertex(vertexName).setMinDistance(0);
	pq.addAll(vertices);
	while(!pq.isEmpty()) {
		wVertex first = pq.peek();
		for(Edge e: first.getAdjacencies()) {
			double newDistance = first.getMinDistance()+e.weight;
			if(newDistance < e.target.getMinDistance()) {
				e.target.setMinDistance(newDistance);
				e.target.setPrevious(first);
				pq.add(e.target);
			}
		}
		pq.remove();
	}
}
/*  enter your code for two new methods in Graphs 7 */

public List<String> getShortestPathTo(wVertex v) {
	List<String> path = new ArrayList<String>();
	 wVertex prev = v;
	 
	 while (prev!=null) {
		 path.add(0, prev.getName());
		 prev = prev.getPrevious();
	 }
	 return path;

}


public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException {
	Scanner s = new Scanner(vertexNames);
	int size = Integer.parseInt(s.nextLine());
	for(int i = 0; i < size; i++) {
		String str = s.nextLine();
		this.addVertex(str);
	}

	s.close();
	s = new Scanner(edgeListData);
	while(s.hasNextLine()) {
		String[] pair = s.nextLine().split(" ");
		this.addEdge(pair[0], pair[1], Double.parseDouble(pair[2]));
	}

	s.close();
	return this;
}

}  


