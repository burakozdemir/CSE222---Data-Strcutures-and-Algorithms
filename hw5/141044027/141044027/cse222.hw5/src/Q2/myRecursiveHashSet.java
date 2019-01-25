package Q2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Recursive HashSet sınıfı
 * Diger hash table yapıları multidimensional array ıle saglanmıstır
 * Set nterfacını ımplement eder
 * @param <E>
 */
public class myRecursiveHashSet<E> implements Set<E> {

    /**
     * Veri olarak kullanılan sınıf
     * @param <E>
     */
    private static class Entry<E>{
        E key;
        int indexX;
        int indexY;

        public Entry(E keyy,int X,int Y){
            this.key=keyy;
            this.indexX=X;
            this.indexY=Y;
        }
    }

    /**
     * Datafields
     */
    private Entry[][] buckets;
    private int size;

    /**
     * Constructor
     * @param capacity
     */
    public myRecursiveHashSet(int capacity){
        buckets= new Entry[capacity][10];
        size= 0;
    }

    /**
     * Objenın hash degerını return eder.
     * @param hashCode
     * @return int
     */
    private int hashFunction(int hashCode) {
        int index = hashCode;
        if (index < 0) { index = -index; }
        return index % buckets.length;
    }

    /**
     * Tablelardakı eleman sayısı
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * HashTableların dolu yada bos kontrolu
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * Objenın tablellarda mevcut olup olmadıgını kontrol eder.
     * @param element
     * @return true or false
     */
    @Override
    public boolean contains(Object element) {
        int indY = hashFunction(element.hashCode());
        return helperContains(0,indY,element);
    }

    /**
     * contains helper
     * @param X
     * @param Y
     * @param elem
     * @return true or false
     */
    private boolean helperContains(int X,int Y,Object elem){
        if(buckets[Y][X].key.equals(elem))
            return true;
        if(buckets[Y][X]==null)
            return false;
        if(buckets[Y][X].indexY==Y){
            return helperContains(X+1,Y,elem);
        }
        else{
            return helperContains(X,Y+1,elem);
        }
    }

    /**
     * Verilen objeyı varsa sıler true return eder aksı halde false return eder.
     * @param o
     * @return true or false
     */
    @Override
    public boolean remove(Object o) {
        int index = hashFunction(o.hashCode());
        return helperRemove(0,index,o);
    }

    /**
     * remove helper
     * @param X
     * @param Y
     * @param elem
     * @return true or false
     */
    private boolean helperRemove(int X,int Y,Object elem){
        if(buckets[Y][X]==null)
            return false;
        if(buckets[Y][X].key.equals(elem)){
            Entry[][] newBuckets= new Entry[buckets.length][10];
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < buckets[i].length; j++) {
                    if(buckets[i][j]!=null){
                        if(buckets[i][j].key.equals(elem)){

                        }
                        else{
                            newBuckets[i][j]=buckets[i][j];
                        }
                    }
                }
            }
            buckets=newBuckets;
            size--;
            return true;
        }
        if(buckets[Y][X].indexY==Y){
            return helperRemove(X+1,Y,elem);
        }
        else{
            return helperRemove(X,Y+1,elem);
        }
    }

    /**
     * hashtable a objeyı ekler true return eder aksı halde false return eder.
     * @param element
     * @return true or false
     */
    @Override
    public boolean add(Object element) {
        int indexY = hashFunction(element.hashCode());
        return helperAdd(0,indexY,element);
    }

    /**
     * add helper
     * @param X
     * @param Y
     * @param element
     * @return true or false
     */
    private boolean helperAdd(int X,int Y,Object element){
        if(buckets[Y][X]!=null){
            if(buckets[Y][X].key.equals(element))
                return false;
        }
        if(buckets[Y][X]==null){
            Entry entry = new Entry(element,X,Y);
            buckets[Y][X]=entry;
            size++;
            return true;
        }
        if(buckets[Y][X].indexY==Y){
            return helperAdd(X+1,Y,element);
        }
        else {
            return helperAdd(X,Y+1,element);
        }
    }

    /**
     * hash table ı temızler
     */
    @Override
    public void clear() {
        size=0;
        buckets=new Entry[10][10];
    }

    /**
     * tostring metod
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        for (int i = 0; i <buckets.length ; i++) {
            res.append(i+":");
            for (int j = 0; j <buckets[i].length ; j++) {
                if(buckets[i][j]!=null){
                    res.append(buckets[i][j].key.toString()+" ");
                }
            }
            res.append('\n');
        }
        return res.toString();
    }

    /**
     * Main
     * @param args
     */
    public static void main(String args[]){
        try{
            myRecursiveHashSet<String> obj1=new myRecursiveHashSet<>(3);
            obj1.add("b");System.out.println("obj1.add(b)");
            System.out.println(obj1.toString());
            obj1.add("c");System.out.println("obj1.add(c)");
            System.out.println(obj1.toString());
            obj1.add("d");System.out.println("obj1.add(d)");
            System.out.println(obj1.toString());
            obj1.add("c");System.out.println("obj1.add(c)");
            System.out.println(obj1.toString());
            obj1.add("x");System.out.println("obj1.add(x)");
            System.out.println(obj1.toString());
            obj1.add("y");System.out.println("obj1.add(y)");
            System.out.println(obj1.toString());
            obj1.add("h");System.out.println("obj1.add(h)");
            System.out.println(obj1.toString());
            obj1.add("g");System.out.println("obj1.add(g)");
            System.out.println(obj1.toString());
            obj1.remove("d");System.out.println("obj1.remove(g)");
            System.out.println(obj1.toString());
            myRecursiveHashSet<String> obj2=new myRecursiveHashSet<>(3);
            obj2.add(1);System.out.println("obj2.add(1)");
            System.out.println(obj2.toString());
            obj2.add(2);System.out.println("obj2.add(2)");
            System.out.println(obj2.toString());
            obj2.add(3);System.out.println("obj2.add(3)");
            System.out.println(obj2.toString());
            obj2.add(1);System.out.println("obj2.add(1)");
            System.out.println(obj2.toString());
            obj2.add(4);System.out.println("obj2.add(4)");
            System.out.println(obj2.toString());
            obj2.add(5);System.out.println("obj2.add(5)");
            System.out.println(obj2.toString());
            obj2.add(6);System.out.println("obj2.add(6)");
            System.out.println(obj2.toString());
            obj2.remove(2);System.out.println("obj2.remove(2)");
            System.out.println(obj2.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * İterator
     * @return Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /////////////////////////////////////////

    /**
     * --------
     * @return Object[]
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     *  ----
     * @param a
     * @param <T>
     * @return T[]
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * ---
     * @param c
     * @return true or false
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    /**
     * --------
     * @param c
     * @return true or false
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    /**
     * ---
     * @param c
     * @return true or false
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * ---
     * @param c
     * @return true or false
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
}
