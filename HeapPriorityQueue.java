 //Name: Amy Wang
 //Date: 3/26/2021
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
//	   myHeap.add(1, obj);
//	   heapDown(1, myHeap.size()-1);
	   myHeap.add(obj);
	   heapUp(myHeap.size()-1);
	   return true;
   }
   
   public E remove()
   {
	   E ret = myHeap.get(1);
	   swap(1, myHeap.size()-1);
	   myHeap.remove(myHeap.size()-1);
	   heapDown(1, myHeap.size()-1);
	   return ret;
   }
   
   public E peek()
   {
	   if(myHeap.size() > 1) {
		   return myHeap.get(1);
	   }
	   return null;
   }
   
   public boolean isEmpty()
   {
	   if(myHeap.size() <= 1) {
		   return true;
	   }
	   return false;
   }
   
   private void heapUp(int k)
   {
	   if(k == 1) {
		   return;
	   }
	   if(myHeap.size() <= 2) {
		   return;
	   }
	   int parent = k/2;
       if(myHeap.get(k).compareTo(myHeap.get(parent)) < 0) {
        	 swap(k, parent);
        	 heapUp(parent);
       }
   }
   
   private void swap(int a, int b)
   {
	   E temp = myHeap.get(a);
	   myHeap.set(a, myHeap.get(b));
	   myHeap.set(b, temp);
   }
   
   private void heapDown(int k, int size)
   {
	   int left = 0;
		int right = 0;
		if(k > size/2 && k <= size) {
			return;
		}
		if(k*2 <= size) {
			left = 2*k;
			right = 2*k+1;
			if(right > size) {
				if(myHeap.get(k).compareTo(myHeap.get(left)) > 0) {
					swap(k, left);
					heapDown(left, size);
				}
			} else {
				if(myHeap.get(k).compareTo(myHeap.get(left)) > 0 || myHeap.get(k).compareTo(myHeap.get(right)) > 0) {
					if(myHeap.get(left).compareTo(myHeap.get(right)) < 0) {
						swap(k, left);
						heapDown(left, size);
					} else {
						swap(k, right);
						heapDown(right, size);
					}
				}
			}
		}
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
