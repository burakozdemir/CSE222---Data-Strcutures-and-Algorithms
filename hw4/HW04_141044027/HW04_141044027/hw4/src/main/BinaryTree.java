package main;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


/**
 * Binary Tree
 * Genel agac yapısı Binary agac yapısı seklınde represent edilmiştir
 * İlk cocuk sol nodda diger cocuklar ılk cocugun sag noduna baglıdır.
 * @param <E>
 */
public class BinaryTree<E> implements Serializable{
    /**
     * Node class
     * @param <E>
     */
    protected static class Node<E>
            implements Serializable {
        protected int high=0;//postorder search icin gecerli
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public E getData(){
            return data;
        }

        public Node<E> getRight(){
            return right;
        }

        public Node<E> getLeft(){
            return left;
        }

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        public Node(Node<E> nod){
            this.data = nod.data;
            left = nod.left;
            right = nod.right;
        }
        public Node() {
            this.data = null;
            left = null;
            right = null;
        }
        public String toString() {
            return data.toString();
        }
    }

    /**
     * Data field
     */
    protected Node<E> root;
    /**
     * Data field . Level order icin
     */
    protected Queue<Node<E>> levelOrderQueue=new LinkedList<Node<E>>();
    /**
     * Data field . POstorder icin
     */
    protected Stack<Queue<Node<E>>> postOrderStack=new Stack<>();
    /**
     * Data field .Postorder icin
     */
    protected Queue<Node<E>> hafizaQueue=new LinkedList<Node<E>>();

    public Node<E> getNode(){
        return new Node<E>(root);
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String args[]){
        //Node<Integer> rootNode =new Node<Integer>(1);
        BinaryTree<Integer> tree1=new BinaryTree<>();
        //tree1.root=rootNode;
        tree1.add(new Integer(1));
        tree1.add(new Integer(1),new Integer(2));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(1),new Integer(4));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(1),new Integer(7));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(1),new Integer(11));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(2),new Integer(3));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(2),new Integer(6));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(2),new Integer(10));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(3),new Integer(5));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(3),new Integer(9));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(5),new Integer(8));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        tree1.add(new Integer(8),new Integer(12));System.out.print("post order traverse:");tree1.printPostOrder();System.out.println();
        System.out.println("---------------TREE1(postorder)");
        tree1.printPostOrder();
        System.out.println();

        BinaryTree<Integer> tree2=new BinaryTree<Integer>();
        Node<Integer> rootNode2=new Node<Integer>(new Integer(1));
        tree2.root=rootNode2;
        tree2.add(new Integer(1),new Integer(2));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(1),new Integer(4));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(1),new Integer(10));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(1),new Integer(11));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(1),new Integer(17));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(11),new Integer(12));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(12),new Integer(13));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(13),new Integer(14));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(17),new Integer(18));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(17),new Integer(19));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(19),new Integer(20));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(2),new Integer(3));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(2),new Integer(6));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(2),new Integer(7));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(3),new Integer(5));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(5),new Integer(9));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(6),new Integer(8));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(8),new Integer(15));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        tree2.add(new Integer(7),new Integer(16));System.out.print("levelorder traverse:");tree2.printLevelOrder();System.out.println();
        System.out.println("---------------TREE2(levelorder)");
        tree2.printLevelOrder();
    }


    /**
     * ParentItem im child Nodeları na childItem i ekler .
     * Soldakı ılk cocuktur.Ilk cocugun sagNodu seklınde dıger nodlar devam eder.
     * @param parentItem
     * @param childItem
     * @return true or false
     */
    public boolean add(E parentItem,E childItem){
        Node<E> temp=levelOrderSearch(parentItem);
        levelOrderQueue=new LinkedList<Node<E>>();
        if(temp==null)
            return false;
        if(temp.left==null){
            temp.left=new Node<E>(childItem);
            return true;
        }
        else if(temp.left.right==null){
            temp.left.right=new Node<E>(childItem);
            return true;
        }
        else{
            Node<E> tempRight=temp.left.right;
            if (tempRight==null){
                tempRight=new Node<E>(childItem);
                return true;
            }
            while(tempRight.right!=null){
                tempRight=tempRight.right;
            }
            tempRight.right=new Node<E>(childItem);
            return true;
        }
    }

    /**
     * Print to screen in levelorder
     */
    public void printLevelOrder(){
        levelOrderSearch(null);
    }

    /**
     * Parametreye gore agac yapısında levelorder search yapar.
     * Binary agac yapısında genel agac yapısı seklınde calısır.
     * @param item
     * @return Node<E></>
     */
    private Node<E> levelOrderSearch (E item){
        return levelOrder(root,item);
    }

    /**
     * Agac yapısını level order seklınde traverse eder. Binary
     * @param node
     * @param item
     * @return Node<E></>
     */
    private Node<E> levelOrder(Node<E> node ,E item) {
        if(node==null)
            return null;
        else{
            if(item==null)
                System.out.print(node.data+" ");
        }
        if(node.data.equals(item))
            return node;
        Node<E> tempLeft=node.left;
        if(tempLeft!=null)
            levelOrderQueue.add(tempLeft);
        if(node.right!=null){
            Node<E> tempRight=node.right;
            while (tempRight!=null){
                if(tempRight.data.equals(item))
                    return tempRight;
                if(tempRight.left!=null){
                    levelOrderQueue.add(tempRight.left);
                }
                if(item==null)
                    System.out.print(tempRight.data+" ");

                tempRight=tempRight.right;
            }
        }
        if(!levelOrderQueue.isEmpty()){
            Node<E> temp=levelOrderQueue.poll();
            return levelOrder(temp,item);
        }
        return null;
    }

    /**
     * Parametreye gore agac yapısında postorder search yapar.
     * Binary agac yapısında genel agac yapısı seklınde calısır.
     * @param item
     * @return Node<E></>
     */
    private Node<E> postOrderSearch(E item){
        int level=0;
        return postOrder(root,item,level);
    }

    /**
     * Agac yapısını post order seklınde traverse eder. Binary
     * @param node
     * @param item
     * @return Node<E>
     */
    private Node<E> postOrder(Node<E> node,E item,int level){
        Queue<Node<E>> temp=new LinkedList<Node<E>>();
        if(node==null)
            return null;
        else{
            if(item==null){
                temp.add(node);
            }
        }
        Node<E> tempLeft=node.left;
        if(tempLeft!=null)
            hafizaQueue.add(tempLeft);
        if(node.right!=null){
            Node<E> tempRight=node.right;
            while (tempRight!=null){
                if(tempRight.data.equals(item))
                    return tempRight;
                if(tempRight.left!=null){
                    hafizaQueue.add(tempRight.left);
                }
                if(item==null)
                {
                    temp.add(tempRight);
                }

                tempRight=tempRight.right;
            }
        }
        postOrderStack.push(temp);
        if(!hafizaQueue.isEmpty()){
            Node<E> temp2=hafizaQueue.poll();
            return postOrder(temp2,item,level+1);
        }
        return null;
    }

    /**
     *Print to screen postorder
     */
    public void printPostOrder(){

        postOrderSearch(null);
        printStack();
        postOrderStack=new Stack<>();
        hafizaQueue= new LinkedList<>();
    }

    /**
     * post order icin ekrana basar.
     */
    private void printStack(){
        while (!postOrderStack.isEmpty()){
            Queue<Node<E>> temp=postOrderStack.pop();
            while(!temp.isEmpty()){
                Node<E> temp2=temp.poll();
                System.out.print(temp2.data+" ");
            }
        }
    }

    /**
     * Agacı preoreder seklınde traverse eder.Genel agac yapısında calısır.
     */
    public void preOrderTraverse(){
        printPreorder(root);}

    /**
     * Print to screen in preOrder.Genel agac yapısında calısır.
     * @param node
     */
    private void printPreorder(Node node){
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    /**
     * Kök roottan ıtıbaren levelOrder tarzında agac yapısını dolasır.Genel agac yapısına gore
     * @param startNode
     */
    private void levelOrderTraverse(Node<E> startNode) {
        if(startNode==null)
            return ;
        Node<E> tempLeft=startNode;
        Node<E> tempRight;
        while(tempLeft!=null){
            if(tempLeft.data.equals(new Integer(-454)))
                return ;
            System.out.print(tempLeft.data+" ");
            tempRight=tempLeft.right;
            while(tempRight!=null){
                if(tempRight.data.equals(new Integer(-151)))
                    return ;
                System.out.print(tempRight.data+" ");
                tempRight=tempRight.right;
            }
            tempLeft=tempLeft.left;
        }
    }

    /**
     * Constructor
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructor
     * @param root
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructor
     * @param data
     * @param leftTree
     * @param rightTree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {

        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }

    }

    /**
     * Node un childlerinin bos olup olmadgı kontrolu
     * @return true or false
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * Treenin en sonuna recursive sekılde eklıyor.
     * @param current
     * @param value
     * @return Node<E>
     */
    private Node<E> addRecursive(Node current, E value) {
        if (current == null) {
            return new Node(value);
        }
        if (current.right!=null) {
            current.right = addRecursive(current.right, value);
        }else if(current.left!=null){
            current.left=addRecursive(current.left,value);
        }
        else {
            current.right =new Node(value) ;
        }

        return current;
    }

    /**
     * public add method
     * @param value
     */
    public void add(E value) {
        root = addRecursive(root, value);
    }

    /**
     * Tree yapısını hazır almak icin kullanılan method.
     * @param scan
     * @return BinaryTree<String>
     */
    public static BinaryTree<String>
    readBinaryTree(Scanner scan) {
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree,
                    rightTree);
        }
    }

    /**
     * Root un sol agacı yapısında yeni bir Node return eder.
     * @return BinaryTree<E>
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        }
        else {
            return null;
        }
    }

    /**
     * Root un sag agacı yapısında yeni bir Node return eder.
     * @return BinaryTree<E>
     */
    public BinaryTree<E> getRightSubtree(){
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        }
        else {
            return null;
        }
    }



}
