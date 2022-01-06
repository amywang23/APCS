 // Name: Amy Wang
 // Date: 12/12/2020
 // use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("\nArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> ar = new ArrayList<Integer> ();
      for(int i = 0; i < rawNumbers.length; i++) {
    	  ar.add(rawNumbers[i]);
      }
      return ar;
   }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList = new ArrayList<String>();
      for ( String str : rawWords )
         myList.add( str );
      return myList;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
	   int count = 0;
	   for(Integer i:a) {
		   if(i < 0)
			   count++;
	   }
	   return count;
   }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
	   int sum = 0;
	   for(Integer i:a) {
		   sum += i;
	   }
	   return sum/a.size();
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
	   int count = 0;
	   for(Integer i:a) {
		   if(i < 0)
			   a.set(count, 0);
		   count++;
	   }
	   return a;
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
       ListIterator<Integer> it = a.listIterator();
       while(it.hasNext()) {
    	   if(it.next() == 0)
    		   it.remove();
       }
       return a;
   }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
	   ListIterator<String> it = a.listIterator();
	   String[] ar = new String[a.size()];
	   int count = 0;
       while(it.hasNext()) {
    	   boolean flag = false;
    	   String str = it.next();
    	   for(int i = 0; i < ar.length; i++) {
    		   if(ar[i] == null) {
    			   break;
    		   }
    		   if(str.equals(ar[i])) {
    			   flag = true;
    		   }
    	   }
    	   if(flag)
    		   it.remove();
    	   else {
    		   ar[count] = str;
        	   count++;
    	   }
       }
       return a;
   }
   
}