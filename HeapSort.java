// Name: Amy Wang
// Date: 3/24/2021

public class HeapSort
{
	public static int SIZE;  //9 or 100

	public static void main(String[] args)
	{
		//Part 1: Given a heap, sort it. Do this part first. 
		SIZE = 9;  
		double heap[] = {-1,99,80,85,17,30,84,2,16,1};

		display(heap);
		sort(heap);
		display(heap);
		System.out.println(isSorted(heap));

		//Part 2:  Generate 100 random numbers, make a heap, sort it.
		 SIZE = 100;
		 heap = new double[SIZE + 1];
		 heap = createRandom(heap);
		 display(heap);
		 makeHeap(heap, SIZE);
		 display(heap); 
		 sort(heap);
		 display(heap);
		 System.out.println(isSorted(heap));
	}

	//******* Part 1 ******************************************
	public static void display(double[] array)
	{
		for(int k = 1; k < array.length; k++)
			System.out.print(array[k] + "    ");
		System.out.println("\n");	
	}

	public static void sort(double[] array)
	{
		/* enter your code here */
		for(int i = 0; i < SIZE-2; i++) {
//			System.out.println("i="+ i + ", swap = 1,"+(SIZE-i) + ", " + array[1] + ", " + array[SIZE-i]);
			swap(array, 1, SIZE-i);
//			display(array);
			heapDown(array, 1, SIZE-i-1);
//			display(array);
		}


		if(array[1] > array[2])   //just an extra swap, if needed.
			swap(array, 1, 2);
	}

	public static void swap(double[] array, int a, int b)
	{
		double temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void heapDown(double[] array, int k, int size)
	{
		int left = 0;
		int right = 0;
		if(k*2 <= size) {
			left = 2*k;
			right = 2*k+1;
			if(right > size) {
				if(array[k] < array[left]) {
					swap(array, k, left);
					heapDown(array, left, size);
				}
			} else {
				if(array[k] < array[left] || array[k] < array[right]) {
					if(array[left] > array[right]) {
						swap(array, k, left);
						heapDown(array, left, size);
					} else {
						swap(array, k, right);
						heapDown(array, right, size);
					}
				}
			}
		}
	}

	public static boolean isSorted(double[] arr)
	{
		for(int i = 1; i < arr.length-1; i++) {
			if(arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}

	//****** Part 2 *******************************************

	//Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
	public static double[] createRandom(double[] array)
	{  
		array[0] = -1;   //because it will become a heap
		for(int i = 1; i <= 100; i++) {
			int random = 100+(int)(Math.random()*(9999-100)+1);
			array[i] = random/100.00;
		}
		return array;
	}

	//turn the random array into a heap
	public static void makeHeap(double[] array, int size)
	{
		int middle = size/2;
		for(int i = middle; i > 0; i--) {
			heapDown(array, i, size);
		}
	}
}

