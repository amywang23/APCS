// Name: Amy Wang
// Date: 12/4/2020

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
	private int size;
	private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code

	public int size()
	{
		return size;
	}

	/* appends obj to end of list; increases size;
   	  @return true  */
	public boolean add(Object obj)
	{
		addLast(obj);
		return true;   
	}

	/* inserts obj at position index.  increments size. 	*/
	public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
	{
		if( index > size || index < 0 )
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		/* enter your code below  */
		DLNode nd = new DLNode(obj, null, null);
		// checking if list is empty
		if(head.getNext() == head || size == 0) { 
			head.setNext(nd);
			head.setPrev(nd);
			nd.setNext(head);
			nd.setPrev(head);
			size = 1;
			return;
		}

		// list is not empty; add to index position
		DLNode p = head.getNext(); 
		for(int i = 0; i < index; i++) {
			p = p.getNext();
		}
		size++;
		nd.setNext(p);         
		nd.setPrev(p.getPrev());
		p.getPrev().setNext(nd);
		p.setPrev(nd);       
	}

	/* return obj at position index. 	*/
	public Object get(int index)
	{ 
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		if(head.getNext() == head || size == 0) // checking if list is empty
			return null;

		if(index == 0) {
			return getFirst();
		}

		DLNode p = head.getNext();
		for(int i = 0; i < index; i++) {
			p = p.getNext();
		}
		return p.getValue();
	}

	/* replaces obj at position index. 
        returns the obj that was replaced*/
	public Object set(int index, Object obj)
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		if(head.getNext() == head || size == 0) // checking if list is empty
			return null;

		Object ori = null;
		DLNode p = head.getNext();
		for(int i = 0; i < index; i++) {
			p = p.getNext();
		}
		ori = p.getValue();
		p.setValue(obj);
		return ori;
	}

	/*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
	public Object remove(int index)
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		/* enter your code below  */
		// checking if list is empty
		if(head.getNext() == head || size == 0) {
			return null;
		}

		// list is not empty; add to index position
		DLNode p = head.getNext(); 
		for(int i = 0; i < index; i++) {
			p = p.getNext();
		}
		p.getPrev().setNext(p.getNext());
		p.getNext().setPrev(p.getPrev());
		size--;
		return p.getValue();
	}

	/* inserts obj at front of list, increases size   */
	public void addFirst(Object obj)
	{
		add(0, obj);
	}

	/* appends obj to end of list, increases size    */
	public void addLast(Object obj)
	{
		add(size, obj);
	}

	/* returns the first element in this list  */
	public Object getFirst()
	{
		if(head.getNext() == head || size == 0)
			return null;
		return head.getNext().getValue();
	}

	/* returns the last element in this list  */
	public Object getLast()
	{
		return get(size-1);
	}

	/* returns and removes the first element in this list, or
       returns null if the list is empty  */
	public Object removeFirst()
	{
		return remove(0);
	}

	/* returns and removes the last element in this list, or
       returns null if the list is empty  */
	public Object removeLast()
	{
		return remove(size-1);
	}

	/*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
	 */
	public String toString()
	{
		String s = "[";
		DLNode p = head.getNext();
		for(int i = 0; i < size; i++) {
			s += p.getValue();
			p = p.getNext();
			if(i != size-1)
				s += ", ";
		}
		s += "]";
		return s;
	}
}