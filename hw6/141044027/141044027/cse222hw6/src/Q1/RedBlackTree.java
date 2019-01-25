package Q1;
/**
 * RedBlackTree sınıfı
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<T>> {

	/**
	 * Datafields
	 */
	private RedBlackNode<T> nil = new RedBlackNode<T>();
	private RedBlackNode<T> root = nil;

	/**
	 * Constructor
	 */
    public RedBlackTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

	/**
	 * Node dan ıtıbaren sol tarafı dondurur
	 * @param node
	 */
	private void solaDondur(RedBlackNode<T> node){
		solaDondurDuzenle(node);
		RedBlackNode<T> temp;
		temp = node.right;
		node.right = temp.left;
		if (!isNil(temp.left))
			temp.left.parent = node;
		temp.parent = node.parent;
		if (isNil(node.parent))
			root = temp;
		else if (node.parent.left == node)
			node.parent.left = temp;
		else
			node.parent.right = temp;
		temp.left = node;
		node.parent = temp;
	}

	/**
	 * Nodedan ıtıbaren sol tarafı duzenler
	 * @param node
	 */
	private void solaDondurDuzenle(RedBlackNode node){
		//right,rightright
		if (isNil(node.left) && isNil(node.right.left)){
			node.numLeft = 0;
			node.numRight = 0;
			node.right.numLeft = 1;
		}
		//rightleft
		else if (isNil(node.left) && !isNil(node.right.left)){
			node.numLeft = 0;
			node.numRight = 1 + node.right.left.numLeft + node.right.left.numRight;
			node.right.numLeft = 2 + node.right.left.numLeft + node.right.left.numRight;
		}
		//leftleft
		else if (!isNil(node.left) && isNil(node.right.left)){
			node.numRight = 0;
			node.right.numLeft = 2 + node.left.numLeft + node.left.numRight;
		}
		//leftright
		else{
			node.numRight = 1 + node.right.left.numLeft + node.right.left.numRight;
			node.right.numLeft = 3 + node.left.numLeft + node.left.numRight +
			node.right.left.numLeft + node.right.left.numRight;
		}
	}

	/**
	 * Node dan ıtıbaren saga dondurme yapar
	 * @param node
	 */
	private void sagaDondur(RedBlackNode<T> node){
		sagaDondurDuzenle(node);
        RedBlackNode<T> temp = node.left;
        node.left = temp.right;
		if (!isNil(temp.right))
            temp.right.parent = node;
        temp.parent = node.parent;
		if (isNil(node.parent))
            root = temp;
		else if (node.parent.right == node)
            node.parent.right = temp;
		else
            node.parent.left = temp;
        temp.right = node;
		node.parent = temp;
	}

	/**
	 * Rotate oncesi düzenleme yapar
	 * @param node
	 */
	private void sagaDondurDuzenle(RedBlackNode node){
		//left
		if (isNil(node.right) && isNil(node.left.right)){
			node.numRight = 0;
			node.numLeft = 0;
			node.left.numRight = 1;
		}
		//leftrighr
		else if (isNil(node.right) && !isNil(node.left.right)){
			node.numRight = 0;
			node.numLeft = 1 + node.left.right.numRight + node.left.right.numLeft;
			node.left.numRight = 2 + node.left.right.numRight + node.left.right.numLeft;
		}
		//right
		else if (!isNil(node.right) && isNil(node.left.right)){
			node.numLeft = 0;
			node.left.numRight = 2 + node.right.numRight +node.right.numLeft;
		}
		//rightleft
		else{
			node.numLeft = 1 + node.left.right.numRight + node.left.right.numLeft;
			node.left.numRight = 3 + node.right.numRight + node.right.numLeft +
			node.left.right.numRight + node.left.right.numLeft;
		}
	}

	/**
	 * Treeye ekleme yapar . Kuralları bozmadan ekleme yapar
	 * @param key
	 */
	public void insert(T key) {
        insert(new RedBlackNode<T>(key));
    }

	/**
	 * Public method ıcın helper
	 * @param node
	 */
	private void insert(RedBlackNode<T> node) {
			RedBlackNode<T> temp = nil;
			RedBlackNode<T> res = root;
			while (!isNil(res)){
				temp = res;
					if (node.key.compareTo(res.key) < 0){
					res.numLeft++;
					res = res.left;
				}else{
					res.numRight++;
					res = res.right;
				}
			}
			node.parent = temp;
			if (isNil(temp))
				root = node;
			else if (node.key.compareTo(temp.key) < 0)
				temp.left = node;
			else
				temp.right = node;
			node.left = nil;
			node.right = nil;
			node.color = RedBlackNode.RED;
			insertAndRefactor(node);

	}

	/**
	 * Ekleme sonrası düzenleme islemını yapan method
	 * @param node
	 */
	private void insertAndRefactor(RedBlackNode<T> node){
		RedBlackNode<T> temp = nil;
		while (node.parent.color == RedBlackNode.RED){
			if (node.parent == node.parent.parent.left){
				temp = node.parent.parent.right;
				//red olma durumu
				if (temp.color == RedBlackNode.RED){
					node.parent.color = RedBlackNode.BLACK;
					temp.color = RedBlackNode.BLACK;
					node.parent.parent.color = RedBlackNode.RED;
					node = node.parent.parent;
				}
				//temp black ise
				else if (node == node.parent.right){
					node = node.parent;
					solaDondur(node);
				}
				//temp black node left node da ise
				else{
					node.parent.color = RedBlackNode.BLACK;
					node.parent.parent.color = RedBlackNode.RED;
					sagaDondur(node.parent.parent);
				}
			}
			else{
				temp = node.parent.parent.left;
				//temp red ise
				if (temp.color == RedBlackNode.RED){
					node.parent.color = RedBlackNode.BLACK;
					temp.color = RedBlackNode.BLACK;
					node.parent.parent.color = RedBlackNode.RED;
					node = node.parent.parent;
				}
				//temp black node left child ise
				else if (node == node.parent.left){
					node = node.parent;
					sagaDondur(node);
				}
				// temp black node right ise
				else{node.parent.color = RedBlackNode.BLACK;
					node.parent.parent.color = RedBlackNode.RED;
					solaDondur(node.parent.parent);
				}
			}
		}
	root.color = RedBlackNode.BLACK;
	}

	/**
	 * Bos nod kontrolu
	 * @param node
	 * @return true or false
	 */
	private boolean isNil(RedBlackNode node){
		return node == nil;
	}

	/**
	 * Tree size getter
	 * @return int
	 */
	public int size(){
		return root.numLeft + root.numRight + 1;
	}

	/**
	 * Print level order
	 */
	void printLevelOrder(){
		int h = height(root);
		int i;
		for (i=1; i<=h; i++)
			printGivenLevel(root, i);
	}

	/**
	 * HEİGHT fpr printlevelorder
	 * @param root
	 * @return int
	 */
	int height(RedBlackNode root)
	{
		if (root.key == null)
			return 0;
		else
		{
			int lheight = height(root.left);
			int rheight = height(root.right);
			if (lheight > rheight)
				return(lheight+1);
			else return(rheight+1);
		}
	}

	/**
	 * Verilen leveldekı nodeları basar.
	 * @param root
	 * @param level
	 */
	void printGivenLevel (RedBlackNode root ,int level)
	{
		char b='B';
		char r='R';
		if (root == null)
			return;
		if (level == 1){
			if(root.key==null){
			}else
			{
				char renk;
				if(root.color==0)
					renk=b;
				else
					renk=r;
				System.out.print(root.key+"("+renk+")"+ " ");

			}
		}
		else if (level > 1)
		{
			printGivenLevel(root.left, level-1);
			printGivenLevel(root.right, level-1);
		}
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String args[]){
    	RedBlackTree<Integer> tree = new RedBlackTree<>();
		for (int i = 14; i >0 ; i--) {
			tree.insert(i);
		}
		tree.printLevelOrder();
	}
}
