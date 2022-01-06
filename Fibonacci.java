// Name: Amy Wang
// Date: 10/7/2020
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int lastFibNumber = 43;
      int[] fibNumber = {1};
       System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
       for(int n = fibNumber[0]; n <= lastFibNumber; n++)
       { 
           start = System.nanoTime();
           fib = fibIterate(n);
           end = System.nanoTime();
           System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
           start = System.nanoTime();   	
           fib = fibRecur(n);      
           end = System.nanoTime();
           System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
       }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
       if(n < 0)
    	   throw new IllegalArgumentException();
       if(n == 1 || n == 2)
    	   return 1;
       int f = 1;
       int s = 1;
       int c = 0;
       for(int i = 3; i <= n; i++) {
    	   c = f+s;
    	   f = s;
    	   s = c;
       }
       return c;
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
	   if(n < 0)
    	   throw new IllegalArgumentException();
       if(n == 1 || n == 2)
    	   return 1;
       else {
    	   return fibRecur(n-1) + fibRecur(n-2);
       }
   }
}