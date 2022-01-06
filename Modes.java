// Name:  Amy Wang
// Date:  9/8/2020

public class Modes
{
	public static void main(String[] args)
	{
		int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
		//int[] tally = {5, 5, 5};
		display(tally);
		int[] modes = calculateModes(tally);
		display(modes);
		int sum = 0;
		for(int k = 0; k < tally.length; k++)
			sum += tally[k];
		System.out.println("kth \tindex"); 
		for(int k = 1; k <= sum; k++)
			System.out.println(k + "\t\t" + kthDataValue(tally, k));
	}

	/**
	 * precondition: tally.length > 0
	 * postcondition: returns an int array that contains the modes(s);
	 *                the array's length equals the number of modes.
	 */
	public static int[] calculateModes(int[] tally)
	{
		if(tally.length == 0)
		{
			System.out.println("You gave me an empty array, please try again");
			return new int[] {};
		}
//		boolean a = true;
//		for(int i = 0; i < tally.length-1; i++)
//		{
//			if(tally[i] != tally[i+1])
//			{
//				a = false;
//				break;
//			}
//		}
//		if(a) 
//		{
//			System.out.println("There is no mode in the array, please try again");
//			return new int[] {};
//		}
		int number = 0;
		int max = findMax(tally);
		System.out.println("The maximum value is: " + max);
		for(int k = 0; k < tally.length; k++)
		{
			if(tally[k] == max)
			{
				number++;
			}
		}
		if(number == tally.length)
		{
			System.out.println("There is no mode in the array, please try again");
			return new int[] {};
		}
		int[] modes = new int[number];
		int index = 0;
		for(int k = 0; k < tally.length; k++)
		{
			if(tally[k] == max)
			{
				modes[index] = k;
				index++;
			}
		}
		return modes;
	}

	/**  
	 * precondition:  tally.length > 0
	 * 	             0 < k <= total number of values in data collection
	 * postcondition: returns the kth value in the data collection
	 *                represented by tally
	 */
	public static int kthDataValue(int[] tally, int k)
	{
		if(tally.length == 0)
		{
			System.out.println("You gave me an empty array, please try again");
			return -1;
		}
		int sum = 0;
		for(int i = 0; i < tally.length; i++)
		{
			sum += tally[i];
			if(sum >= k)
			{
				return i;
			}
		}
		
		return -1;
	}

	/**
	 * precondition:  nums.length > 0
	 * postcondition: returns the maximal value in nums
	 */
	public static int findMax(int[] nums)
	{
		int pos = 0;
		for(int k = 1; k < nums.length; k++)
			if(nums[k] > nums[pos])
				pos = k;
		return nums[pos];
	}

	public static void display(int[] args)
	{
		for(int k = 0; k < args.length; k++)
			System.out.print(args[k] + " ");
		System.out.println();
		System.out.println();
	}
}