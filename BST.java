// Name: Amy Wang
// Date: 2/24/2021

interface BSTinterface
{
	public int size();
	public boolean contains(String obj);
	public void add(String obj);   //does not balance
	public void addBalanced(String obj);
	public void remove(String obj);
	public String min();
	public String max();
	public String display();
	public String toString();
}

public class BST implements BSTinterface
{
	/*  copy your BST code  here  */
	private TreeNode root;
	private int size;
	public BST()
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

	/*  start the addBalanced methods */
	public void addBalanced(String value)  
	{
		if(root == null) {
			root = new TreeNode(value);
			size = 1;
		} else {
			add(root, value);
			root = balanceTree(value, root, true);   // for an AVL tree.  You may change this line
		}
	}

	public TreeNode balanceTree(String value, TreeNode t, boolean b)  
	{
		if(t == null)
			return null;
		TreeNode tr = t;
		if(value.compareTo(t.getValue().toString()) < 0) { //go left
			tr.setLeft(balanceTree(value, t.getLeft(), true));
		}
		if(value.compareTo(t.getValue().toString()) > 0) { //go right
			tr.setRight(balanceTree(value, t.getRight(), true));
		}
		int bal = balance(t);

		//right-heavy
		if(bal > 1) {
			int bal1 = balance(t.getRight());
			if(bal1 <= 0) { //subtree left-heavy
				tr = LR(t);
			} else { //subtree right-heavy
				tr = LL(t);
			}
		} else { //left-heavy
			if(bal < -1) {
				int bal1 = balance(t.getLeft());
				if(bal1 >= 0) { //subtree left-heavy
					tr = RL(t);
				} else { //subtree right-heavy
					tr = RR(t);
				}
			}
		}
		return tr;
	}

	private int balance(TreeNode t) {
		int left = height(t.getLeft());
		int right = height(t.getRight());
		return right-left;
	}

	private TreeNode LL(TreeNode t) {
		TreeNode tr = t.getRight();
		t.setRight(tr.getLeft());
		tr.setLeft(t);
		return tr;
	}

	private TreeNode RR(TreeNode t) {
		TreeNode tr = t.getLeft();
		t.setLeft(tr.getRight());
		tr.setRight(t);
		return tr;
	}

	private TreeNode LR(TreeNode t) {
		TreeNode tr = RR(t.getRight());
		t.setRight(tr);
		tr = LL(t);
		return tr;
	}

	private TreeNode RL(TreeNode t) {
		TreeNode tr = LL(t.getLeft());
		t.setLeft(tr);
		tr = RR(t);
		return tr;
	}

	private int height(TreeNode t)
	{
		int count = 0;
		if(t == null)
			return -1;       
		int h1 = height(t.getLeft()); 
		int h2 = height(t.getRight());
		if(h1 > h2)
			count = h1+1;
		else
			count = h2+1;
		return count;
	}

}