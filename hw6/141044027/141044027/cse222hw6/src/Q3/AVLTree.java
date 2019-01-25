package Q3;

import java.util.Vector;

/**
 * AVLTree sınıfı
 * add ve remove metodları test edilmistir
 * @param <E>
 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    /**
     * Referance icin node sınıfı
     * @param <E>
     */
    private static class AVLNode<E extends Comparable<E>> extends Node<E> {

        public static final int LEFT_HEAVY = -1;
        public static final int BALANCED = 0;

        public static final int RIGHT_HEAVY = 1;
        private int balance;

        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        public E getElement(){return data;}
        public Node<E> getLeft(){return left;}
        public Node<E> getRight(){return right;}

        public void setLeftNode(Node<E> nod){left=nod;}
        public void setRightNode(Node<E> nod){right=nod;}

        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
    private boolean increase;
    private boolean decrease;

    /**
     * Constructor
     */
    public AVLTree(){

    }

    /**
     * Constructor
     * @param tree
     */
    public AVLTree(AVLTree<E> tree){

        if (isBST(tree.root) && isBalanced(tree.root))
            System.out.println("Tree is AVL tree");
        else
            System.out.println("Tree is not AVL tree");

        root= refactor(tree.root);
    }

    /**
     * Roottan ıtıbaren nodeları vector sınıfına yedekler
     * @param root
     * @param nodes
     */
    void NodeToVector(Node root, Vector<Node> nodes)
    {
        if (root == null)
            return;
        NodeToVector(root.left, nodes);
        nodes.add(root);
        NodeToVector(root.right, nodes);
    }

    /**
     * Vectordekı nodelar ıle yeni bir avltree olusturur
     * @param nodes
     * @param start
     * @param end
     * @return Node
     */
    Node newTree(Vector<Node> nodes, int start,
                 int end)
    {
        if (start > end)
            return null;
       int mid = (start + end) / 2;
        Node node = nodes.get(mid);
        node.left = newTree(nodes, start, mid - 1);
        node.right = newTree(nodes, mid + 1, end);

        return node;
    }

    /**
     * Parametreli constructorda caıgırlır.
     * @param root
     * @return Node
     */
    Node refactor(Node root)
    {
        Vector<Node> nodes = new Vector<Node>();
        NodeToVector(root, nodes);
        int n = nodes.size();
        return newTree(nodes, 0, n - 1);
    }

    /**
     * Girililen parametre elemanı siler ve treeyi tekrar duzenler
     * @param target The object to be deleted
     * @return E
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Public metodu icin helper
     * @param localRoot
     * @param item
     * @return Node
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else {
                if (localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    /**
     * Girilen parametre icin predecessorunu bulur
     * @param parent
     * @return E
     */
    private E findLargestChild(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * Treeye dengeyi koruyacak sekılde eleman ekler
     * @param item The object being inserted
     * @return true or false
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Public metodu icin helper metod
     * @param localRoot
     * @param item
     * @return AVLNode
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }
        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = add((AVLNode<E>) localRoot.left, item);
            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        } else {
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRight(localRoot);
                }
                else{
                    return localRoot;
                }
            }else {
                return localRoot;
            }
        }
    }

    /**
     * verilen noddan itibaren sol tarafı dengeler
     * @param localRoot
     * @return AVLNode
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        AVLNode < E > leftChild = (AVLNode < E > ) localRoot.left;
        if (leftChild.balance > AVLNode.BALANCED) {
            AVLNode < E > leftRightChild = (AVLNode < E > ) leftChild.right;
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.left = rotateLeft(leftChild);
        }
        else {
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        return (AVLNode < E > ) rotateRight(localRoot);
    }

    /**
     * verilen noddan itibaren sag tarafı dengeler
     * @param localRoot
     * @return AVLNode
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot){
        AVLNode < E > rightChild = (AVLNode < E > ) localRoot.right;
        if (rightChild.balance < AVLNode.BALANCED) {
            AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            increase = false;
            decrease = true;
            localRoot.right = rotateRight(rightChild);
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
        else {
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
    }

    /**
     * Denge ıcın degısken degerını azaltır
     * @param node
     */
    private void decrementBalance(AVLNode<E> node) {
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            increase = false;
        }
    }

    /**
     * Denge ıcın degısken degerını artırır
     * @param node
     */
    private void incrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance++;
        if (node.balance > AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = true;
            decrease = true;
        }else
        {
            increase = false;
            decrease = true;
        }
    }

    /**
     * binarysearchtree kontrol
     * @param node
     * @return true or false
     */
    public boolean isBST(Node node)
    {
        return (helper(node, 0, 100));
    }

    /**
     * helper method
     * @param node
     * @param min
     * @param max
     * @return true or false
     */
    private static boolean helper(Node node, int min, int max)
    {

        if (node == null)
            return true;

        if (node.data.compareTo(min) < 0 || node.data.compareTo(max) > 0)
            return false;

        return (helper(node.left, min, max-1) && helper(node.right, min+1, max));
    }

    /**
     *
     * @param root
     * @return true or false
     */
    public boolean isBalanced(Node root)
    {
        int lh; /* for height of left subtree */
        int rh; /* for height of right subtree */

        if (root == null)
            return true;

        lh = height(root.left);
        rh = height(root.right);

        if (Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        return false;
    }

    /**
     * max value
     * @param a
     * @param b
     * @return int
     */
    private static int max(int a, int b)
    {
        return (a >= b) ? a : b;
    }

    /**
     * height of node
     * @param node
     * @return int
     */
    public int height(Node node)
    {
        if (node == null)
            return 0;

        return 1 + max(height(node.left), height(node.right));
    }

    /**
     * Print level order
     */
    void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++){
            printGivenLevel(root, i);
        }
    }

    /**
     * level icin hegiht degerini dondurur
     * @param root
     * @return int
     */
    private int height(AVLNode<E> root)
    {
        if (root==null || root.data == null)
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
     * Parametreye gore levelı basar
     * @param root
     * @param level
     */
    private void printGivenLevel (Node<E> root ,int level)
    {
        char b='B';
        char r='R';
        if (root == null)
            return;
        if (level == 1){
            System.out.print(root.data+" ");
        }
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String args[]){
        AVLNode<Integer> binNode=new AVLNode<>(15);
        binNode.left=new Node<>(14);
        binNode.left.left=new Node<>(13);
        binNode.left.left.left=new Node<>(12);
        binNode.left.left.left.left=new Node<>(11);
        binNode.left.left.left.left.left=new Node<>(10);
        binNode.left.left.left.left.left.left=new Node<>(9);
        binNode.left.left.left.left.left.left.left=new Node<>(8);
        binNode.left.left.left.left.left.left.left.left=new Node<>(7);
        binNode.left.left.left.left.left.left.left.left.left=new Node<>(6);
        binNode.left.left.left.left.left.left.left.left.left.left=new Node<>(5);
        binNode.left.left.left.left.left.left.left.left.left.left.left=new Node<>(4);
        binNode.left.left.left.left.left.left.left.left.left.left.left.left=new Node<>(3);
        binNode.left.left.left.left.left.left.left.left.left.left.left.left.left=new Node<>(2);
        binNode.left.left.left.left.left.left.left.left.left.left.left.left.left.left=new Node<>(1);
        System.out.println("BinNode is creating to 15 to 1");
        AVLTree<Integer> tree=new AVLTree<>();
        tree.root=binNode;
        System.out.println("tree.root=binNode");
        System.out.println("Control of 'tree' is AVL OR NOT:");
        System.out.println("tree2(tree) is initialize");
        AVLTree<Integer> tree2=new AVLTree<>(tree);
        System.out.println("tree2.levelOrder:");
        tree2.printLevelOrder();
        System.out.println();

        AVLTree<Integer> tree3=new AVLTree<>();
        for (int i = 1; i <16 ; i++) {
            tree3.add(i);
        }
        System.out.println("tree3 initialize and 1 to 15 added");
        System.out.println("tree3.levelOrder");
        tree3.printLevelOrder();
        System.out.println();
        System.out.println("tree3.remove(8)");
        tree3.remove(8);
        System.out.println("tree3.printlevelOrder");
        tree3.printLevelOrder();
        System.out.println();
        System.out.println("tree3.remove(7)");
        tree3.remove(7);
        System.out.println("tree3.printlevelOrder");
        tree3.printLevelOrder();
        System.out.println();
        tree3.add(16);
        tree3.add(17);
        tree3.add(18);
        tree3.add(19);
        tree3.add(20);
        System.out.println("tree3 adds 16,17,18,19,20 and delete 16");
        tree3.remove(16);
        System.out.println("tree3.printlevelorder");
        tree3.printLevelOrder();
        System.out.println();

    }

}
