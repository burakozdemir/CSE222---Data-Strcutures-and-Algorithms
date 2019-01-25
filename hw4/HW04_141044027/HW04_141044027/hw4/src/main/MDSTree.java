package main;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * MuitiDimensionalTree class
 * Comparable sınıfından extend edilip SearchTree interfaceınden implement edilmistir
 * @param <E>
 */
public class MDSTree<E extends Comparable<E>> implements SearchTree<E>{
    /**
     * MultiDimensinal özelliğini saglamak ıcın icerisindeki data vector tipindedir
     * @param <E>
     */
    protected static class Node<E extends Comparable<E>> implements Serializable{
        /**
         * DataField
         */
        protected Vector<E> data;
        /**
         * DataField. sol node
         */
        protected Node<E> left;
        /**
         * DataField. sag node
         */
        protected Node<E> right;

        /**
         * Constructor
         * @param data
         */
        public Node(Vector<E> data) {
            this.data  = data;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructor
         * @param nod
         */
        public Node(Node<E> nod){
            this.data = nod.data;
            left = nod.left;
            right = nod.right;
        }

        /**
         * Constructor . default
         */
        public Node() {
            left = null;
            right = null;
        }
        public String toString() {
            return data.toString();
        }

    }

    /**
     * Datafield . MDT özelliği icin indis tutulur
     */
    protected Integer indis=new Integer(0);
    /**
     * Datafield
     */
    protected Node<E> root;

    /** Return value from the public delete method. */
    protected Vector<E> deleteReturn;

    /**
     * Main Method
     * @param args
     */
    public static void main(String args[]){
        Vector<Integer> tempA=new Vector<>();Vector<Integer> tempB=new Vector<>();
        Vector<Integer> tempC=new Vector<>();Vector<Integer> tempD=new Vector<>();
        Vector<Integer> tempE=new Vector<>();Vector<Integer> tempF=new Vector<>();
        Vector<Integer> tempG=new Vector<>();Vector<Integer> tempH=new Vector<>();
        Vector<Integer> tempI=new Vector<>();Vector<Integer> tempJ=new Vector<>();
        Vector<Integer> tempK=new Vector<>();MDSTree<Integer> tree=new MDSTree<>();
        tempA.add(new Integer(40));tempA.add(new Integer(45));tempA.add(new Integer(50));
        tempB.add(new Integer(15));tempB.add(new Integer(70));tempB.add(new Integer(80));
        tempC.add(new Integer(70));tempC.add(new Integer(10));tempC.add(new Integer(90));
        tempD.add(new Integer(69));tempD.add(new Integer(50));tempD.add(new Integer(10));
        tempE.add(new Integer(66));tempE.add(new Integer(85));tempE.add(new Integer(40));
        tempF.add(new Integer(85));tempF.add(new Integer(90));tempF.add(new Integer(5));
        tempG.add(new Integer(10));tempG.add(new Integer(65));tempG.add(new Integer(30));
        tempH.add(new Integer(90));tempH.add(new Integer(80));tempH.add(new Integer(8));
        tempI.add(new Integer(95));tempI.add(new Integer(100));tempI.add(new Integer(5));
        tempJ.add(new Integer(90));tempJ.add(new Integer(75));tempJ.add(new Integer(3));
        tempK.add(new Integer(90));tempK.add(new Integer(70));tempK.add(new Integer(3));
        tree.add(tempA);tree.add(tempB);tree.add(tempC);tree.add(tempD);
        tree.add(tempE);tree.add(tempF);tree.add(tempG);tree.add(tempH);
        tree.add(tempI);tree.add(tempJ);tree.add(tempK);
        System.out.println(":::::::::::::add method:::::::::");
        System.out.println("tree.add(40,45,50)");System.out.println("tree.add(15,70,80)");
        System.out.println("tree.add(70,10,90)");System.out.println("tree.add(69,50,10)");
        System.out.println("tree.add(66,85,40)");System.out.println("tree.add(85,90,5)");
        System.out.println("tree.add(10,65,30)");System.out.println("tree.add(90,80,8)");
        System.out.println("tree.add(95,100,5)");System.out.println("tree.add(90,75,3)");
        System.out.println("tree.add(90,70,3)");
        System.out.println("::::::::::::contain method::::::::");
        Vector<Integer> asd=new Vector<Integer>();
        asd.add(new Integer(85));asd.add(new Integer(90));asd.add(new Integer(6));
        System.out.println("tree.contains(Vector<Integer>(85,90,5)): "+tree.contains(tempF));
        System.out.println("tree.contains(Vector<Integer>(85,90,6)): "+tree.contains(asd));
        System.out.println("::::::::::::::find method:::::::::");
        System.out.println("tree.find(Vector<Integer>(90,75,3)): "+tree.find(tempJ).get(0)+"-"+tree.find(tempJ).get(1)+
                "-"+tree.find(tempJ).get(2));
        System.out.println(":::::::::::::delete method:::::::::::::");
        Queue<Vector<Integer>> sıra=new LinkedList<Vector<Integer>>();
        System.out.println("levelorder:");
        sıra=tree.levelOrder(tree.root);
        while (!sıra.isEmpty()){
            System.out.println(sıra.poll());
        }
        System.out.println("tree.delete(Vector<Integer>(90,80,8)) :");
        tree.delete(tempH);
        sıra=tree.levelOrder(tree.root);
        while (!sıra.isEmpty()){
            System.out.println(sıra.poll());
        }

    }

    /**
     * BST için valuenin degerine gore uygun yere nod yerlestirir
     * @param value
     * @return true or false
     */
    @Override
    public boolean add(Vector<E> value) {
        //root = addRecursive(root, value);
        this.indis=new Integer(0);
        root = addDimensional(root,value);
        this.indis=new Integer(0);
        return true;
    }

    /**
     * public boolean add(Vector<E></> value) metodu ıcın gereklı ıslemlerı yapar.
     * @param current
     * @param value
     * @return Node<E></>
     */
    private Node<E> addDimensional(Node<E> current,Vector<E> value){
        if (current == null) {
            current = new Node<E>(value);
            return current;
        }
        //Node<E> temp=current;
        if(this.indis==value.size())
            this.indis=new Integer(0);
        //System.out.println(current.data.get(this.indis.intValue())+"vs"+value.get(this.indis.intValue()));
        if(current.data.get(this.indis.intValue()).compareTo(value.get(this.indis.intValue()))>0){
            this.indis++;
            current.left=addDimensional(current.left,value);
        }
        else if(current.data.get(this.indis.intValue()).compareTo(value.get(this.indis.intValue()))<0){
            this.indis++;
            current.right=addDimensional(current.right,value);
        }else if(current.data.get(this.indis.intValue()).compareTo(value.get(this.indis.intValue()))==0){
            this.indis++;
            current.left=addDimensional(current.left,value);
        }
        return current;
    }


    /**
     * Verilen parametrenin agac icerisinde olup olmadıgını kontrol eder.
     * @param item
     * @return true or false
     */
    @Override
    public boolean contains(Vector<E> item) {
        return contains(root,item);
    }

    /**
     * public boolean contains(Vector<E> item) metodu ıcın gereklı ıslemlerı yapar.
     * @param node
     * @param value
     * @return true or false
     */
    private boolean contains(Node<E> node ,Vector<E> value){
        if (node.data.equals(value)) {
            return true;
        }

        boolean contains = false;
        if (node.left!=null) {
            contains = contains(node.left, value);
        }
        if (!contains && node.right!=null) {
            contains = contains(node.right, value);
        }
        return contains;
    }

    /**
     *
     * @param target
     * @return Vector<E></>
     */
    @Override
    public Vector<E> find(Vector<E> target){
        Vector<E> result;
        this.indis=new Integer(0);
        result=findDimensional(root,target);
        this.indis=new Integer(0);
        return result;
    }

    /**
     * public Vector<E> find(Vector<E> target) metodu icin gerekli islemleri yapar
     * @param localRoot
     * @param target
     * @return Vector<E></>
     */
    private Vector<E> findDimensional(Node<E> localRoot,Vector<E> target){
        if(localRoot==null)
            return null;
        if(this.indis==target.size())
            this.indis=new Integer(0);
        int compResult=target.get(this.indis.intValue()).compareTo(localRoot.data.get(this.indis.intValue()));
        if(compResult==0)
            return localRoot.data;
        else if(compResult<0){
            this.indis++;
            return findDimensional(localRoot.left,target);
        }
        else{
            this.indis++;
            return findDimensional(localRoot.right,target);
        }
    }

    /**
     * Parametredeki value e denk gelen nodun referansını return eder.
     * @param localRoot
     * @param target
     * @return Node<E></>
     */
    private Node<E> findNode(Node<E> localRoot,Vector<E> target){
        if(localRoot==null)
            return null;
        if(this.indis==target.size())
            this.indis=new Integer(0);
        int compResult=localRoot.data.get(this.indis.intValue()).compareTo(target.get(this.indis.intValue()));
        if(compResult==0){
            return localRoot;
        }
        else if(compResult>0){
            this.indis++;
            return findNode(localRoot.left,target);
        }
        else{
            this.indis++;
            return findNode(localRoot.right,target);
        }
    }


    /**
     * target ı agac yapısından siler.
     * Silme isleminde altta kalan agac yapısını tekrar add metodu ile ekler.
     * @param target
     * @return Vector<E></>
     */
    @Override
    public Vector<E> delete(Vector<E> target) {
        root = deleteDimensional(root,target);
        return deleteReturn;
    }

    /**
     * public Vector<E> delete(Vector<E> target) metodu icin gerekli islemleri yapar
     * @param root
     * @param item
     * @return Node<E></>
     */
    private Node<E> deleteDimensional(Node<E> root,Vector<E> item){
        if (root == null) {
            deleteReturn = null;
            return root;
        }
        Queue<Vector<E>> quu=new LinkedList<Vector<E>>();
        Node<E> deletedNode=findNode(root,item);
        Node<E> parent=findParent(root,item);
        Vector<E> temp=new Vector<E>();
        if(deletedNode!=null){
            deleteReturn=deletedNode.data;
            quu=levelOrder(deletedNode);
            if (parent.right != null) {
                if(parent.right.data.equals(item)){
                    parent.right=null;
                }
            }
            if(parent.left !=null){
                if(parent.left.data.equals(item)){
                    parent.left=null;
                }
            }
            quu.poll();
            while(!quu.isEmpty()){
                temp=quu.poll();
                add(temp);
            }
            return root;
        }else{
            deleteReturn=null;
            return root;
        }

    }

    /**
     * Levelorder seklinde nodları Queue yapısına atar.
     * @param root
     * @return Vector<E></>
     */
    private Queue<Vector<E>> levelOrder(Node<E> root) {
        Queue<Vector<E>> result=new LinkedList<Vector<E>>();
        if(root==null) return result;
        ArrayList<Node<E>> list=new ArrayList<Node<E>>();
        list.add(root);
        while(!list.isEmpty())
        {
            ArrayList<Node<E>> Tplist=new ArrayList<Node<E>>();
            ArrayList<Vector<E>> level=new ArrayList<Vector<E>>();
            while(!list.isEmpty())
            {
                Node<E> node=list.remove(0);
                result.add(node.data);
                if(node.left!=null) Tplist.add(node.left);
                if(node.right!=null) Tplist.add(node.right);
            }
            list=Tplist;
        }
        return result;
    }

    /**
     * verilen value degerıne denk gelen nodun parent nodunun referansını return eder
     * @param localroot
     * @param value
     * @return Node<E></>
     */
    private Node<E> findParent(Node<E> localroot,Vector<E> value){
        Node<E> result=new Node<E>();
        if(localroot==null) return result;
        ArrayList<Node<E>> list=new ArrayList<Node<E>>();
        list.add(root);
        while(!list.isEmpty())
        {
            ArrayList<Node<E>> Tplist=new ArrayList<Node<E>>();
            while(!list.isEmpty())
            {
                Node<E> node=list.remove(0);
                int i=0;
                int count=0;
                if(node.left!=null) {
                    while (i<node.left.data.size()){
                        if(node.left.data.get(i).equals(value.get(i))){
                            count++; }
                        i++;
                    }
                    if(count==value.size()){
                        return node;
                    }
                    Tplist.add(node.left);
                }
                i=0;
                count=0;
                if(node.right!=null){
                    while (i<node.right.data.size()){
                        if(node.right.data.get(i).equals(value.get(i))){
                            count++;}
                        i++;
                    }
                    if(count==value.size()){
                        return node;
                    }
                    Tplist.add(node.right);}
            }
            list=Tplist;
        }
        return result;
    }

    /**
     * İcerisinde delete metodunu cagırır. Sİlme islemı dogru ıse true yanlıssa false return eder.
     * @param target
     * @return true or false
     */
    @Override
    public boolean remove(Vector<E> target) {
        if(target.equals(delete(target)))
            return true;
        else
            return false;
    }

}
