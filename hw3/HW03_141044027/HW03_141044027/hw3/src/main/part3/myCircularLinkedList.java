package main.part3;

import main.part2.myLinkedList;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Part 3 - class myCircularLinkedList
 *
 * Nodelar singleLinkedList seklinde brbirine bagli.
 * Aynı zamanda semester degelerine görede circular olarak birbirine bagli.
 *
 * INPUT FILE:Courses(CSV)(Updated).csv
 * @author Burak Ozdemir 141044027
 */
public class myCircularLinkedList<E> extends LinkedList<E> {
    private static class Node<E>{
        private E data;
        private E key;
        private Node<E> nextInSemester =null;
        private Node<E> next =null;

        public Node(E data,E key){
            this.data=data;
            this.key=key;
        }

        public Node(){
            this.data=null;
            this.key=null;
        }

        public E getKey(){
            return this.key;
        }

        public E getData(){
            return this.data;
        }

        public Node<E> next(){
            return next;
        }

        public Node<E> nextInSemester(){
            return nextInSemester;
        }

    }

    /**
     * Data Fields
     */
    private int size=0;
    private Node<E> head=null;
    private Node<E> tail=null;
    private Node<E> current=head;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args){
        try{
            myCircularLinkedList<String> test=new myCircularLinkedList<>();
            test.add("mat","1");
            test.add("kim","1");
            test.add("mat2","2");
            test.add("kim2","3");
            test.add("cog","1");
            test.add("tarih","3");
            Node<String> a=test.getNode(0);
            System.out.println(":::::::next() and nextInSemester():::::::::");
            int i=0;
            System.out.print("next(): ");
            while(i<6){
                System.out.print(" "+a.getData());
                i++;
                a=a.next();
            }
            System.out.println();
            System.out.println("nextInSemester(): ");
            i=0;
            a=test.getNode(0);
            while(i<10){
                System.out.println("data: "+a.getData()+" semester: "+a.getKey());
                i++;
                a=a.nextInSemester();
            }
            System.out.println("");
            System.out.println("Size:"+test.size());
            test.add("fel","2");
            System.out.println("fel(semester2) is added.");
            test.remove(2);
            System.out.println("test.remove(2):mat2 is deleted.");
            a=test.getNode(0);
            i=0;
            System.out.println("next(): ");
            while(i<6){
                System.out.println("data: "+a.getData()+" semester: "+a.getKey());
                i++;
                a=a.next();
            }

        }catch (Exception a){
            System.out.println(a.getMessage().toString());
        }
    }

    /**
     *
     * @param data
     * @param key
     */
    public void addFirst(E data,E key) {
        Node<E> temp=new Node<>(data,key);
        if(this.size==0){
            this.head=temp;
            this.tail=temp;
        }else{
            Node<E> temp2=this.head;
            temp.next=temp2;
            this.head=temp;

            Node<E> secondNode=secondNode(key);
            Node<E> firstNode=firsNode(key);
            if(secondNode!=null){
                firstNode.nextInSemester=temp;
                temp.nextInSemester=secondNode;
            }
            //this.tail.next=this.head;
        }
        size++;
    }

    /**
     * semesterlinklerini birlesitirken kullaniliyor.
     * @param key
     * @return Node<E>
     */
    private Node<E> secondNode(E key){
        Node<E> node=this.head;
        int i=0;
        Node<E> res=null;
        while(node.next!=null){
            if(node.key.equals(key)){
                i++;
                res=node;
                if(i==2) return  res;
            }
            node=node.next;
        }
        return null;
    }
    /**
     * semesterlinklerini birlesitirken kullaniliyor.
     * @param key
     * @return Node<E>
     */
    private Node<E> firsNode(E key){
        Node<E> node=this.head;
        while(node.next!=null){
            if(node.key.equals(key)){
                return node;
            }
            node=node.next;
        }
        return node;
    }

    /**
     * semesterlinklerini birlesitirken kullaniliyor.
     * @param key
     * @return Node<E>
     */
    private Node<E> lastNode(E key){
        Node<E> node=this.head;
        Node<E> res=null;
        while(node.next!=null){
            if(node.key.equals(key)){
                res=node;
            }
            node=node.next;
        }
        if(node.key.equals(key))
            return node;
        else
            return res;
    }

    /**
     * parametlerle birlikte listenin sonuna node ekler.
     * @param data
     * @param key
     * @return true or false
     */
    public boolean add(E data,E key){
        if(this.size==0){
            addFirst(data,key);
            return true;
        }else if(this.size>0){
            Node<E> lastNode=lastNode(key);//mat
            Node<E> firstNode=firsNode(key);//kim
            Node<E> temp=new Node<>(data,key);

            this.tail.next =temp;
            this.tail=temp;

            if(lastNode!=null){
                lastNode.nextInSemester=temp;
                temp.nextInSemester=firstNode;
            }

            //this.tail.next=this.head;
            this.size++;
            return true;
        }else
            return false;
    }

    /**
     * listenin basindan node siler.
     */
    private void deleteStart(){
        if(size==0){
            System.out.println("\nList is Empty");
        }else{
            this.head = this.head.next;
            this.tail.next=this.head;
            size--;
        }
    }

    /**
     * Bu kod parcası internetten alınmıstır.
     *
     * parametreye denk gelen node u siler.
     * Semester linklerinde kullanılır.(circular list)
     *  @param data
     */
    private void deleteInSemester(E data){
        if(head == null) return;

        if(head.data == data && head.nextInSemester.data == head.data) {
            head.nextInSemester = null;
            head = null;
            return;
        }
        if(head.data == data) {
           Node end = head;
            while(end.nextInSemester.data != head.data) {
                end = end.nextInSemester;
            }
            Node temp = head;
            head = head.nextInSemester;
            temp.nextInSemester = null;
            end.nextInSemester = head;
            return;
        }
        Node temp = head;
        while(temp.nextInSemester.data != data && temp.nextInSemester.data != head.data) {
            temp = temp.nextInSemester;
        }
        if(temp.nextInSemester.data == head.data) return;
    Node del = temp.nextInSemester;
        temp.nextInSemester = temp.nextInSemester.nextInSemester;
        del.nextInSemester = null;
    }

    /**
     * indexteki node u siler.
     * @param index
     * @return E
     */
    public E remove(int index){
        E data=getNode(index).data;
        deleteInSemester(data);
        if(head.data.equals(data)){
            deleteStart();
            return data;
        }
        Node<E> current = head.next;
        Node<E> previous = null;
        while (current != null) {
            E dataOld = current.data;
            if ((dataOld == null && data == null) || (dataOld != null && dataOld.equals(data))) {
                Node afterRemoved = current.next;
                if (previous == null) {
                    head.next =afterRemoved;
                } else {
                    previous.next =afterRemoved;
                }
                if (afterRemoved.next == null) {
                    tail = afterRemoved;
                }
                size--;
                return data;
            } else {
                previous = current;
                current = current.next;
            }
        }
        return data;
    }

    /**
     * Listenin size ını return eder.
     * @return int
     */
    public int size(){
        return this.size;
    }

    /**
     * İndex in bound unu kontrol eder.
     * @param index
     * @throws IndexOutOfBoundsException
     */
    private void controlIndex(final int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Bound Exception");
        }
    }

    /**
     * İndex teki node u return eder.
     * @param index
     * @return Node<E></>
     */
    public Node<E> getNode(int index){
        controlIndex(index);
        Node<E> node=head;
        int i=0;
        while (node!=null){
            if(i==index) return node;
            i++;
            node=node.next;
        }
        return node;
    }

    /**
     * parametre index i ile baslayan iterator return eder.
     * iterator semester linklerinde ilerler.
     * @param index
     * @return ListIterator<E>
     */
    public ListIterator<E> listIteratorInSemester(int index){
        controlIndex(index);
        ListIterator<E> res=new ListIterator<E>() {
            private Node<E> prev=null;
            private Node<E> next=head.nextInSemester;
            private int pre=-1;
            private int nextInd=0;
            @Override
            public boolean hasNext() {
                return next!=tail;
            }

            @Override
            public E next() {
                prev = next;
                next = next.nextInSemester;
                pre++;
                nextInd++;
                return prev.data;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        };
        return res;
    }

    /**
     * parametre index i ile baslayan iterator return eder.
     * iterator normal ilerler ilerler.
     * @param index
     * @return ListIterator<E>
     */
    public ListIterator<E> listIterator(int index){
        controlIndex(index);
        ListIterator<E> res=new ListIterator<E>() {
            private Node<E> prev=null;
            private Node<E> next=head;
            private int pre=-1;
            private int nextInd=0;
            @Override
            public boolean hasNext() {
                return next!=tail;
            }

            @Override
            public E next() {
                prev = next;
                next = next.next;
                pre++;
                nextInd++;
                return prev.data;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        };
        return res;
    }

    /**
     * indexteki nodun datasini return eder.
     * @param index
     * @return E
     */
    public E get(int index){
        controlIndex(index);
        Node<E> node=getNode(index);
            return node.data;
    }

    /**
     * İndexteki node un semester degerini return eder.
     * @param index
     * @return E
     */
    public E getKey(int index){
        controlIndex(index);
        Node<E> node=getNode(index);
        return node.key;
    }

}
