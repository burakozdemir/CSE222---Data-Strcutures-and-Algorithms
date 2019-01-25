package Q2;

/**
 * Btree sınıfı.
 */
public class BTree{

	/**
	 * Datafields
	 */
	static int order;
	BNode root;

	/**
	 * Constructor
	 * @param order
	 */
	public BTree(int order) {
		this.order = order;
		root = new BNode(order, null);
	}

	/**
	 * BinarySearch method
	 * @param data
	 * @return true or false
	 */
    public boolean binarySearch(int data){
	    return  binarySearchInChilds(root,data);
    }

	/**
	 * binarySearch mantıgında keys yapısında arama yapar
	 * @param node
	 * @param low
	 * @param high
	 * @param data
	 * @return true or false
	 */
    private boolean binarySearchInKeys(BNode node, int low, int high, int data){
	    if(high>=low){
            int mid=low+(high-low)/2;
            if(node.key[mid]==data)
                return true;
            if(node.key[mid]<data)
                return binarySearchInKeys(node,mid+1,high,data);
            else
                return binarySearchInKeys(node,low,mid-1,data);
        }
        return false;
    }

	/**
	 * BinarySearch mantıgında childslarda arama yapar.
	 * @param root
	 * @param data
	 * @return true or false
	 */
    private boolean binarySearchInChilds(BNode root, int data){
        boolean res= binarySearchInKeys(root,0,root.count-1,data);
        if(res==true)
            return true;
        int newChildNode=-1;
        int i=0;
        BNode temp=root;
        while(temp.child[i]!=null){
            if(temp.child[i]!=null){
                if(temp.child[i+1]!=null){
                    if(temp.child[i].key[0]<=data && temp.child[i+1].key[0]>data){
                        newChildNode=i;
                        break;
                    }
                }else
                    newChildNode=i;
            }
            i++;
        }
        if(newChildNode==-1)
            return false;
        return binarySearchInChilds(root.getChild(newChildNode),data);

    }

	/**
	 * Keys yapısı sınıra geldgnde metod ile alt cocuklara ayrılır nodelar
	 * @param firstChild
	 * @param i
	 * @param lastChild
	 */
	public void ayirma(BNode firstChild, int i, BNode lastChild)
	{
		BNode temp = new BNode(order,null);

		temp.leaf = lastChild.leaf;
		temp.count = order - 1;
		for(int j = 0; j < order - 1; j++) {
			temp.key[j] = lastChild.key[j+order];
		}
		if(!lastChild.leaf){
			for(int k = 0; k < order; k++) {
				temp.child[k] = lastChild.child[k+order];}
		}
		lastChild.count = order - 1;
		for(int j = firstChild.count ; j> i ; j--){
			firstChild.child[j+1] = firstChild.child[j];
		}
		firstChild.child[i+1] = temp;
		for(int j = firstChild.count; j> i; j--) {
			firstChild.key[j + 1] = firstChild.key[j]; }
		firstChild.key[i] = lastChild.key[order-1];
		lastChild.key[order-1 ] = 0;
		for(int j = 0; j < order - 1; j++) {
			lastChild.key[j + order] = 0;}
		firstChild.count ++;
	}

	/**
	 * Ayrılan cocuk nodeları tekrak duzgun bır skeılde yerıne koyar
	 * @param node
	 * @param key
	 */
	public void insertAgain(BNode node, int key) {
		int i = node.count;
		if(node.leaf) {
			while(i >= 1 && key < node.key[i-1]){
				node.key[i] = node.key[i-1];
				i--;//decrement
			}
			node.key[i] = key;node.count ++; //increment count of keys in this node now.
		} else {
			int j = 0;
			while(j < node.count  && key > node.key[j]){
				j++; }
			if(node.child[j].count == order*2 - 1) {
				ayirma(node,j,node.child[j]);
				if(key > node.key[j]) {
					j++;
				}
			}
			insertAgain(node.child[j],key);//call again
		}
	}

	/**
	 * Verilen nodedan ıtıbaren uygun yere ekleme yapar
	 * @param node
	 * @param key
	 */
	public void insert(BTree node, int key)
	{
		BNode temp = node.root;
		if(temp.count == 2*order - 1){
			BNode temp2 = new BNode(order,null);
			node.root = temp2;
			temp2.leaf = false;
			temp2.count = 0;
			temp2.child[0] = temp;
			ayirma(temp2,0,temp);
			insertAgain(temp2, key);
		}
		else
			insertAgain(temp,key);}
	/**
	 * Main method
	 * @param args
	 */
       public static void main(String args[]){
		   BTree tree = new BTree(4);
		   BTree tree2 = new BTree(4);
		   tree.insert(tree,1);
           tree.insert(tree,2);
           tree.insert(tree,3);
           tree.insert(tree,4);
           tree.insert(tree,5);
           tree.insert(tree,6);
           tree.insert(tree,7);
           tree.insert(tree,8);
           tree.insert(tree,9);
           tree.insert(tree,10);
           tree.insert(tree,11);
           tree.insert(tree,12);
           tree.insert(tree,13);
           tree.insert(tree,14);

		   tree2.insert(tree2,14);
		   tree2.insert(tree2,13);
		   tree2.insert(tree2,12);
		   tree2.insert(tree2,11);
		   tree2.insert(tree2,10);
		   tree2.insert(tree2,9);
		   tree2.insert(tree2,8);
		   tree2.insert(tree2,7);
		   tree2.insert(tree2,6);
		   tree2.insert(tree2,5);
		   tree2.insert(tree2,4);
		   tree2.insert(tree2,3);
		   tree2.insert(tree2,2);
		   tree2.insert(tree2,1);

		   System.out.println("tree.search(1):"+tree.binarySearch(1));
		   System.out.println("tree.search(7):"+tree.binarySearch(7));
		   System.out.println("tree.search(14):"+tree.binarySearch(14));
		   System.out.println("tree.search(25):"+tree.binarySearch(25));

		   System.out.println("tree2.search(2):"+tree2.binarySearch(2));
		   System.out.println("tree2.search(9):"+tree2.binarySearch(9));
		   System.out.println("tree2.search(12):"+tree2.binarySearch(12));
		   System.out.println("tree2.search(17):"+tree2.binarySearch(17));



	   }

}