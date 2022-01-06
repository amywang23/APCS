import java.util.LinkedList;

public class LLPriorityQueue<E extends Comparable<E> >{
	private LinkedList<E> ll;
	
	public LLPriorityQueue () {
		ll = new LinkedList<E>();
	}
	
	
	public LLPriorityQueue(LinkedList<E> ll) {
		this.ll = ll;
	}


	public boolean add(E obj) {
		if (ll.isEmpty()) {
			ll.add(obj);
			return true;
		} else {
			int index = -1;
			for (int i=0;i<ll.size();i++) {
				if (obj.compareTo(ll.get(i))<0) {
					index = i;
					break;
				}
			}
			if (index == -1)
				ll.addLast(obj);
			else
				ll.add(index, obj);
			return true;
		}
	}

	public E remove() {
		if (ll.isEmpty())
			return null;
		else {
			E obj = ll.getFirst();
			ll.remove();
			return obj;
		}
	}
	
	public E peek(){
		if (ll.isEmpty())
			return null;
		else
			return ll.getFirst();
	}
	
	public boolean isEmpty(){
		return ll.isEmpty();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		for(Object obj : ll) {
			sb.append(obj+", ");
			}
		if (sb.length()>1)
			sb.setLength(sb.length() - 2);
		sb.append("]");
		return sb.toString();
	}
}
