// Name: Amy Wang
// Date: 2/17/21

//interface BSTinterface
//{
//	public int size();
//	public boolean contains(String obj);
//	public void add(String obj);
//	public void addBalanced(String obj);
//	public void remove(String obj);
//	public String min();
//	public String max();
//	public String display();
//	public String toString();
//}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
 **********************/
public class BSTRemove implements BSTinterface
{
	private TreeNode root;
	private int size;
	public BSTRemove()
	{
		root = null;
		size = 0;
	}
	public int size()
	{
		return size;
	}
	public TreeNode getRoot()   //for Grade-It
	{
		return root;
	}
	/***************************************
   @param s -- one string to be inserted
	 ****************************************/
	public void add(String s) 
	{
		if(root == null) {
			root = new TreeNode(s);
			size++;
			return;
		}
		add(root, s);
	}
	private TreeNode add(TreeNode t, String s) //recursive helper method
	{
		if(t == null) 
			return null;
		TreeNode tr = new TreeNode(s);
		String str = t.getValue().toString();
		if(s.compareTo(str) <= 0) {
			if(t.getLeft() == null) {
				t.setLeft(tr);
				size++;
				return tr;
			} else {
				return add(t.getLeft(), s);
			}
		} else {
			if(t.getRight() == null) {
				t.setRight(tr);
				size++;
				return tr;
			} else {
				return add(t.getRight(), s);   
			}
		}
	}

	public String display()
	{
		return display(root, 0);
	}
	private String display(TreeNode t, int level) //recursive helper method
	{
		String toRet = "";
		if(t == null)
			return "";
		toRet += display(t.getRight(), level + 1); //recurse right
		for(int k = 0; k < level; k++)
			toRet += "\t";
		toRet += t.getValue() + "\n";
		toRet += display(t.getLeft(), level + 1); //recurse left
		return toRet;
	}

	public boolean contains( String obj)
	{
		return contains(root, obj);
	}
	private boolean contains(TreeNode t, String x) //recursive helper method
	{
		if(t == null) 
			return false;
		String s = t.getValue().toString();
		if(s.equals(x)) {
			return true;
		} else {
			if(x.compareTo(s) < 0) {
				return contains(t.getLeft(), x);
			} else
				return contains(t.getRight(), x);
		}
	}

	public String min()
	{
		return min(root);
	}
	private String min(TreeNode t)  //use iteration
	{
		if(t == null)
			return null;
		TreeNode n = t;

		while(n.getLeft() != null) {
			n = n.getLeft();
		}
		return n.getValue().toString();
	}

	public String max()
	{
		return max(root);
	}
	private String max(TreeNode t)  //recursive helper method
	{
		String s = "";
		if(t == null)
			return null;
		if(t.getRight() == null) {
			return t.getValue().toString();
		}
		s= max(t.getRight());

		return s;
	}

	public String toString()
	{
		return toString(root);
	}
	private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
	{
		String toReturn = "";
		if(t == null)
			return "";
		toReturn += toString(t.getLeft());            //recurse left
		toReturn += t.getValue() + " ";    //inorder visit
		toReturn += toString(t.getRight());  //recurse right
		return toReturn;
	}
	public void remove(String target)
	{
		root = remove(root, target);
		size--;
	}
	private TreeNode remove(TreeNode current, String target) {
		if(current == null) {
			return null;
		}
		String s = current.getValue().toString();
		if(target.compareTo(s) < 0) {
			current.setLeft(remove(current.getLeft(), target));
		}
		if(target.compareTo(s) > 0) {
			current.setRight(remove(current.getRight(), target));
		}
		if(target.compareTo(s) == 0) {
			if(current.getLeft() == null) { //no left child
				return current.getRight();
			}
			if(current.getRight() == null) { //no right child
				return current.getLeft();
			}
			String m = max(current.getLeft()); //two children
			current.setValue(m);
			current.setLeft(remove(current.getLeft(), m));
		}
		return current;
	}
	
	private TreeNode removeIt(TreeNode current, String target)
	{   
		TreeNode pointer = current;
		TreeNode previous = current;
		while(pointer != null) {
			if(target.compareTo(pointer.getValue().toString()) > 0) {
				previous = pointer;
				pointer = pointer.getRight();
			}
			if(target.compareTo(pointer.getValue().toString()) < 0) {
				previous = pointer;
				pointer = pointer.getLeft();
			}
			if(target.compareTo(pointer.getValue().toString()) == 0) { //found match
				if(pointer == current) { //root
					if(pointer.getLeft() == null)
						return pointer.getRight();
					if(pointer.getRight() == null)
						return pointer.getLeft();
				}
				if(pointer == previous.getLeft()) { //from left branch
					if(pointer.getLeft() == null) { //no left child
						previous.setLeft(pointer.getRight());
						break;
					}
					if(pointer.getRight() == null) { //no right child
						previous.setLeft(pointer.getLeft());
						break;
					}
					//has two children
					String m = max(pointer.getLeft());
					pointer.setValue(m);
					if(pointer.getLeft().getValue().toString().equals(m)) {
						pointer.setLeft(pointer.getLeft().getLeft());
						break;
					}
					TreeNode parent = pointer.getLeft();
					while(parent != null) {
						if(parent.getRight() != null && parent.getRight().getValue().toString().equals(m)) {
							parent.setRight(parent.getRight().getLeft());
							break;
						}
						parent = parent.getRight();
					}
					break;
				}
				if(pointer == previous.getRight()) { //from right branch
					if(pointer.getLeft() == null) { //no left child
						previous.setRight(pointer.getRight());
						break;
					}
					if(pointer.getRight() == null) { //no right child
						previous.setRight(pointer.getLeft());
						break;
					}
					//has two children
					String m = max(pointer.getLeft());
					pointer.setValue(m);
					if(pointer.getLeft().getValue().toString().equals(m)) {
						pointer.setLeft(pointer.getLeft().getLeft());
						break;
					}
					TreeNode parent = pointer.getLeft();
					while(parent != null) {
						if(parent.getRight() != null && parent.getRight().getValue().toString().equals(m)) {
							parent.setRight(parent.getRight().getLeft());
							break;
						}
						parent = parent.getRight();
					}
					break;
				}
			}
		}
		return current;
	}
}