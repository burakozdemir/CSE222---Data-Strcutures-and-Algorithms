package main.part2;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Part 2 - class myLinkedList extends LinkedList
 * NOTE==>iteratorlist metodunun hasprevious ve previous metodlarnda azcık skntı var.
 * @param <E>
 */
public class myLinkedList<E> extends LinkedList<E> {

    /**
     * Node lari tutmak icin class Node
     * Kitaptan alinmistir.
     * @param <E>
     */
    private static class Node<E> {
        private boolean status=true;
        private E data;
        private Node<E> next=null;
        private Node<E> prev=null;
        /** Creates a new node with a null next field
         @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
        }
        private Node( Node<E> nodeNext,Node<E> nodeprev){
            this.data=null;
            next = nodeNext;
            prev = nodeprev;
        }
        /**
         *
         * @param dataItem
         * @param nodeNext
         * @param nodeprev
         */
        private Node(E dataItem, Node<E> nodeNext,Node<E> nodeprev) {
            data = dataItem;
            next = nodeNext;
            prev = nodeprev;
        }

        /**
         * getter for status
         * @return true or false
         */
        public boolean isStatus() {
            return status;
        }

        /**
         * setter for status
         * @param status
         */
        public void setStatus(boolean status) {
            this.status = status;
        }
    }

    /**
     * Data Fields
     */
    private Node<E> head=null;
    private Node<E> tail=null;
    private int size=0;

    /**
     * Main method
     * @param argv
     */
    public static void main(String[] argv){
        try{

            myLinkedList<String> test1=new myLinkedList<String>();

            System.out.println("TEST1------------");
            test1.add("cagla");
            System.out.println("Added:"+test1.get(0));
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            test1.add("burak");
            System.out.println("Added:"+test1.get(1));
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            test1.set(0,"tuana");
            System.out.println("Set (0):"+test1.get(0));
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            test1.add("kamil");
            System.out.println("Added:"+test1.get(2));
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            test1.add("bahar");
            System.out.println("Added:"+test1.get(3));
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            test1.add("selika");
            System.out.println("Added:"+test1.get(4));
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            test1.remove(0);
            System.out.println("==>remove(0)"   );
            System.out.println("Size:"+test1.size());
            System.out.println(test1.toString());

            ListIterator<String> iter=test1.listIterator(0);
            System.out.print("iter(next): ");
            while (iter.hasNext()){
                System.out.print(iter.next()+" ");
            }
            System.out.println();
            System.out.println("Testing Methods:::::::::::::");
            System.out.println(test1.toString());
                System.out.println("kamil disabled.");
            test1.disable("kamil");
            //System.out.println(test1.toString());
            System.out.println("Size: "+test1.size()+" ");
            System.out.println("get(1): "+test1.get(1));
            test1.set(1,"cagla");
            System.out.println("set(1): "+test1.get(1));
            System.out.println(test1.toString());
            System.out.println("TEST2-----------------");
            myLinkedList<String> test2=new myLinkedList<String>();
            test2.add("a");
            test2.add("b");
            test2.add("c");
            test2.add("d");
            test2.add("e");
            test2.add("f");
            //System.out.println("test2:"+test2.toString());
            System.out.println("Disabled 'c' and 'b' ");
            test2.disable("b");
            test2.disable("c");

            ListIterator<String> iter2=test2.listIterator(0);

            System.out.print("Iter.next():");
            while(iter2.hasNext()){
                System.out.print(" "+iter2.next());
            }
            System.out.println();

            System.out.println("Showing all disabled elements:");
            test2.showDisabled();
            System.out.println();

            System.out.println("Enabled to all elements.");
            test2.enable();
            iter2=test2.listIterator(0);
            while(iter2.hasNext()){
                System.out.println(iter2.next());
            }

            System.out.println("----------------------");
            /*
            iter2=test2.listIterator(5);
            System.out.print("iter.previous():");
            while (iter2.hasPrevious()){
                System.out.print(" "+iter2.previous());
            }
            System.out.println();
        */
        }catch (Exception except){
            System.out.println(except.getMessage().toString());
        }
    }
    /////////// Methods ///////////////

    /**
     * Ders kodu girilen Nodu un durumunu false yapıp gorunmemesını saglar.
     * @param code
     */
    public void disable(E code){
        Node<E> temp=head;
        for (int i = 0; i <this.size ; i++) {
            if(temp.data.equals(code) && temp.isStatus()!=false){
                temp.setStatus(false);
                size--;
            }
            temp=temp.next;
        }
    }

    /**
     * Tüm disable olan dersleri enable yapar.
     */
    public void enable(){
        Node<E> temp=head;
        for (int i = 0; i <this.size ; i++) {
            if(temp.status==false){
                temp.setStatus(true);
                size++;
            }
            temp=temp.next;
        }
    }

    /**
     * Disable olan dersleri ekrana bastirir.
     */
    public void showDisabled(){
        Node<E> temp=head;
        for (int i = 0; i < this.size; i++) {
            if(temp.status==false){
                System.out.print(" "+temp.data);
            }
            temp=temp.next;
        }
    }

    /**
     * Parametre ile denk gelen indisteki nodu return eder.
     * @param index
     * @return E
     */
    @Override
    public E get(int index){
        controlIndex(index);
        Node<E> node=getNode(index);
        if(node.status==true){
            return node.data;
        }else{
            //System.out.println("This node is disable");
            return null;
        }
    }

    /**
     * Parametredki indis ile denk gelen Nodun datasını set eder.
     * @param index
     * @param element
     * @return E
     */
    @Override
    public E set(int index,E element) {
        if (this==element) throw new IllegalArgumentException("Valuea are equal each other. ");
        controlIndex(index);
        Node<E> node=getNode(index);
        if(node.status==true){
            E result=node.data;
            node.data=element;
            return result;
        }else{
            System.out.println("This node is disable");
            E res=node.data;
            return res;
        }
    }

    /**
     * Listenin size ını return eder.
     * @return int
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Parametre indisi ile denk gelen Nodu listeden cıkartır.
     * @param index
     * @return previous E
     */
    @Override
    public E remove(int index){
        controlIndex(index);
        Node<E> curr=getNode(index);
        E result=curr.data;
        if(curr.status==true){
            if (index == 0) {
                head = head.next;
                head.prev = null;
                size--;
            } else if (index == size - 1) {
                tail = tail.prev;
                tail.next = null;
                size--;
            } else {
                Node<E> current = getNode(index);
                Node<E> previous = current.prev;
                Node<E> next = current.next;
                previous.next = current.next;
                next.prev = previous;
                size--;
            }
        }
        else{
            System.out.println("This node is disable");
            return null;
        }
        return result;
    }

    /**
     * Girilen index ile baslayan iterator return eder.
     * @param index
     * @return ListIterator<E>
     */
    @Override
    public ListIterator<E> listIterator(int index){
        controlIndex(index);
        ListIterator<E> res=new ListIterator<E>() {
            private Node<E> prev=null;
            private Node<E> next=head;
            private int pre=-1;
            private int nextInd=0;

            /**
             * Son elemanda item.next() ile birlkte hata cıkartıyor
             * @return
             */
            @Override
            public boolean hasNext() {
                Node<E> temp=new Node<E>(next,prev);
                for (int i = 0; i <size ; i++) {
                    if(temp.next!=null && temp.next.status==true)
                        return true;
                    if(temp.next!=null && temp.next.status==false){
                        temp.prev=temp.next;
                        temp.next=temp.next.next;
                    }
                    if(temp.next==null)
                        return false;
                    //return (next!=tail && tail!=null);
                }
                return false;
            }
            @Override
            public E next() {
                if(!hasNext()) throw new NoSuchElementException("There is no element for next()");
                Node<E> previous;
                for (int i = 0; i <size ; i++) {
                    previous=new Node<E>(next.data,next,prev);
                    if(next!=null && next.status==true){
                        prev=next;
                        next=next.next;
                        nextInd++;
                        pre++;
                        return previous.data;
                    }
                    /**
                     * null un nulluna bakmaya calısıyorsun
                     */
                    if(next==null){
                        return null;
                    }
                    if(next.next!=null && next.status==false){
                        prev=next.next;
                        next=next.next.next;
                        nextInd++;
                        pre++;
                    }

                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                Node<E> temp=new Node<E>(next.next,next);
                for (int i = 0; i <size ; i++) {
                    if(temp.prev!=null && temp.prev.status==true)
                        return true;
                    if(temp.prev!=null && temp.prev.status==false){
                        temp.prev=temp.prev.prev;
                        temp.next=temp.prev;
                    }
                    if(temp.prev==null)
                        return false;
                    //return (next!=tail && tail!=null);
                }
                return false;
            }

            @Override
            public E previous() {
                Node<E> curr;
                for (int i = 0; i <size ; i++) {
                    if(prev.prev!=null)
                        curr=new Node<E>(next.data,next.next,prev);
                    else
                        curr=new Node<E>(prev.data,next,prev);
                    if(prev!=null && prev.status==true){
                        prev=prev.prev;
                        next=prev;
                        nextInd--;
                        pre--;
                        return curr.data;
                    }
                    if(prev.prev!=null && prev.status==false){
                        prev=prev.prev.prev;
                        next=prev.prev;
                        nextInd--;
                        pre--;
                    }
                    if(prev==null){
                        return curr.data;
                    }
                }
                return null;
            }

            @Override
            public int nextIndex() {
                return nextInd;
            }

            @Override
            public int previousIndex() {
                return pre;
            }

            public Node<E> getData(){
                return prev;
            }
            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {
                prev.data=e;
            }

            public Node<E> getNode(E val){
                Node<E> res=head;
                while(res.next!=null){
                    if(res.data.equals(val)){
                        return res;
                    }
                    res=res.next;
                }
                return res;
            }

            public void setStat(boolean val,Node<E> node){
                node.status=val;
            }

            @Override
            public void add(E e) {

            }
        };
        while(res.hasNext()){
            if(res.nextIndex()==index){
                return res;
            }
            res.next();
        }
        return res;
    }

    /////////// Helper Methods ///////////////

    /**
     * Listenin bos olup olmadigini return eder.
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Listenin sonuna Node ekler
     * @param data
     * @return true or false
     */
    @Override
    public boolean add(E data) {
        Node temp = new Node(data);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        size++;
        return true;
    }

    /**
     * Belirtilen indexteki yere itemi insert eder.
     * @param index
     * @param item
     */
    @Override
    public void add(int index,E item){
        controlIndex(index);
        Node temp = new Node(item);
        if (index == 0) {
            addToFirst(item);
        } else if (index == size) {
            add(item);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node previous = current.prev;
            previous.next = temp;
            temp.prev = previous;
            temp.next = current;
            current.prev = temp;
            size++;
        }
    }

    /**
     * Listenin basina node ekler.
     * @param data
     */
    public void addToFirst(E data) {
        Node temp = new Node(data);
        if (head == null) {
            head=temp;
            tail=temp;
        } else {
            temp.next = head;
            head.prev = null;
            head = temp;
            temp.prev = head;
        }
        size++;
    }

    /**
     * index in bound durumunu kontrol eder . Aksi halde exception fırlatır.
     * @param index
     * @throws IndexOutOfBoundsException
     */
    private void controlIndex(final int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Bound Exception");
        }
    }

    /**
     * Girilen indexe denk gelen Nodu return eder.
     * @param index
     * @return Node<E>
     */
    private Node<E> getNode(int index){
        controlIndex(index);
        Node<E> node=head;
        int i=0;
        while (node!=null){
            if(node.isStatus()==true){
                i++;
                if(i==(index+1))
                    return node;
            }
            node=node.next;
        }
        return node;
    }

    /**
     * toString() method
     * @return String
     */
    @Override
    public String toString(){
        String result=new String("LinkedList:");
        Node<E> temp=head;
        while(temp!=null){
            if(temp.isStatus()==true)
                result+=temp.data.toString()+" ";
            temp=temp.next;
        }
        //result+=" "+temp.data.toString();
        return result;
    }
}
