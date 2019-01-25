package Q2;

/**
 * Bnode sınıfı. BTree ıcın node sınıfı
 */
public class BNode
{
	/**
	 * Datafields
	 */
	static int t;
	int count;
	int key[];
	BNode child[];
	boolean leaf;
	BNode parent;

	/**
	 * Constructor
	 */
	public BNode()
	{}

	/**
	 * Constructor
	 * @param t
	 * @param parent
	 */
	public BNode(int t, BNode parent)
	{
		this.t = t;
		this.parent = parent;
		key = new int[2*t - 1];
		child = new BNode[2*t];
		leaf = true;
		count = 0;
	}

	/**
	 * getter for index
	 * @param index
	 * @return int
	 */
	public int getValue(int index)
	{
		return key[index];
	}

	/**
	 * getter for childs
	 * @param index
	 * @return Bnode
	 */
	public BNode getChild(int index)
	{
		return child[index];
	}


}



























