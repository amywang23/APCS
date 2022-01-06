// Name: Amy Wang
// Date: 10/1/2020

import java.util.*;
public class Permutations
{
	public static int count = 0;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("\nHow many digits? ");
		int n = sc.nextInt();
		leftRight("", n);  

		oddDigits("", 3);

		superprime(n);
		if(count == 0)
			System.out.println("no superprimes");
		else
			System.out.println("Count is "+count);
	}

	/**
	 * Builds all the permutations of a string of length n containing Ls and Rs
	 * @param s A string 
	 * @param n An postive int representing the length of the string
	 */
	public static void leftRight(String s, int n)
	{
		if(s.length() == n) {
			System.out.println(s);
		} 
		else {
			leftRight(s+"L", n);
			leftRight(s+"R", n);
		}
	}

	/**
	 * Builds all the permutations of a string of length n containing odd digits
	 * @param s A string 
	 * @param n A postive int representing the length of the string
	 */
	public static void oddDigits(String s, int n)
	{
		if(s.length() == n) {
			System.out.println(s);
		} 
		else {
			oddDigits(s+"1", n);
			oddDigits(s+"3", n);
			oddDigits(s+"5", n);
			oddDigits(s+"7", n);
			oddDigits(s+"9", n);
		}
	}

	/**
	 * Builds all combinations of a n-digit number whose value is a superprime
	 * @param n A positive int representing the desired length of superprimes  
	 */
	public static void superprime(int n)
	{
		recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
		recur(3, n); 
		recur(5, n);
		recur(7, n);
	}

	/**
	 * Recursive helper method for superprime
	 * @param k The possible superprime
	 * @param n A positive int representing the desired length of superprimes
	 */
	private static void recur(int k, int n)
	{
		String str = "" + k;
		if(str.length() == n) {
			boolean flag = true;
			for(int i = 0; i< str.length(); i++) {
				int p = Integer.parseInt(str.substring(0, str.length() - i));
				if(!isPrime(p)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println(str);
				count ++;
			}
		} 
		else {
			recur(Integer.parseInt(str+"1"), n);
			recur(Integer.parseInt(str+"3"), n);
			recur(Integer.parseInt(str+"5"), n);
			recur(Integer.parseInt(str+"7"), n);
			recur(Integer.parseInt(str+"9"), n);
		}
	}

	/**
	 * Determines if the parameter is a prime number.
	 * @param n An int.
	 * @return true if prime, false otherwise.
	 */
	public static boolean isPrime(int n)
	{
		int numfactors = 0;
		int divisor = 2;

		while (divisor < n) {
			if (n % divisor == 0) {
				numfactors ++;
			}
			divisor++;
			if (numfactors >= 1) {
				return false;
			}
		}
		return true;
	}
}