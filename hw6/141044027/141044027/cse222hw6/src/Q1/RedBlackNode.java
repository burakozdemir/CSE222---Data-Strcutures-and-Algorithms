package Q1;

/**
 * RedBlackNode sınıfı . RedBlackTree sınıfı ıcın kullanılacak.
 * @param <T>
 */
class RedBlackNode<T extends Comparable<T>> {

    /**
     * Datafields
     */
    RedBlackNode<T> parent;
    RedBlackNode<T> left;
    RedBlackNode<T> right;
    public int numLeft = 0;
    public int numRight = 0;
    public int color;
    public static final int BLACK = 0;
    public static final int RED = 1;
	public T key;

    /**
     * Constructor
     */
    RedBlackNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    /**
     * Csontructor
     * @param key
     */
	RedBlackNode(T key){
        this();
        this.key = key;
	}
}
