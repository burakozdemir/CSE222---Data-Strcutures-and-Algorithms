package test.part2;

import main.part2.myLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;


public class myLinkedListTest {
    myLinkedList<String> test=new myLinkedList<String>();
    @Before
    public void setUp() throws Exception {
        test.add("a");
        test.add("b");
        test.add("c");
    }

    @Test
    public void disable() throws Exception {
        myLinkedList<String> test2=new myLinkedList<String>();
        test2.add("a");
        test2.add("b");
        test2.add("c");
        test2.add("d");
        test2.add("e");
        test2.add("f");

        test2.disable("a");
        Assert.assertEquals(new String("b"),test2.get(0));
        test2.disable("c");
        Assert.assertEquals(new String("d"),test2.get(1));
        System.out.println(test2.toString());


    }

    @Test
    public void enable() throws Exception {
        myLinkedList<String> test2=new myLinkedList<String>();
        test2.add("a");
        test2.add("b");
        test2.add("c");
        test2.add("d");
        test2.add("e");
        test2.add("f");

        test2.disable("a");
        test2.disable("c");
        test2.enable();
        Assert.assertEquals(new String("a"),test2.get(0));
        Assert.assertEquals(new String("c"),test2.get(2));

    }

    @Test
    public void showDisabled() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(new String("a"),test.get(0));
        Assert.assertEquals(new String("b"),test.get(1));
        Assert.assertEquals(new String("c"),test.get(2));
    }

    @Test
    public void set() throws Exception {
        test.set(0,"d");
        test.set(2,"f");

        Assert.assertEquals(new String("d"),test.get(0));
        Assert.assertEquals(new String("f"),test.get(2));

    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(new Integer(3).intValue(),test.size());
    }

    @Test
    public void remove() throws Exception {
        test.remove(0);
        Assert.assertEquals(new String("b"),test.get(0));
        test.remove(0);

        Assert.assertEquals(new String("c"),test.get(0));
    }

    @Test
    public void listIterator() throws Exception {
        ListIterator<String> iter=test.listIterator(0);
        Assert.assertEquals(true,iter.hasNext());
        Assert.assertEquals(new String("a"),iter.next());
        Assert.assertEquals(new String("b"),iter.next());
        Assert.assertEquals(new String("c"),iter.next());
        /*
        test.add("d");
        test.add("e");
        test.disable("c");
        ListIterator<String> iter2=test.listIterator(3);
        Assert.assertEquals(new String("e"),iter2.previous());
        */
    }

    @Test
    public void isEmpty() throws Exception {
        myLinkedList<String> test2=new myLinkedList<>();
        Assert.assertEquals(false,test.isEmpty());
        Assert.assertEquals(true,test2.isEmpty());
    }

    /**
     * add(E element)
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        test.add("asd");
        Assert.assertEquals(new String("asd"),test.get(3));
    }

    /**
     * add(int index,E element)
     * @throws Exception
     */
    @Test
    public void add1() throws Exception {
        myLinkedList<String> test2=new myLinkedList<String>();
        test2.addToFirst("kk");
        Assert.assertEquals(new String("kk"),test2.get(0));

        test.add(1,"asd");
        test.add(0,"1.nci");
        test.add(4,"son");
        Assert.assertEquals(new String("1.nci"),test.get(0));
        Assert.assertEquals(new String("a"),test.get(1));
        Assert.assertEquals(new String("asd"),test.get(2));
        Assert.assertEquals(new String("b"),test.get(3));
        Assert.assertEquals(new String("son"),test.get(4));
        Assert.assertEquals(new String("c"),test.get(5));
        //System.out.println(test.toString());
    }

    @Test
    public void addToFirst() throws Exception {
        test.addToFirst("ilk");
        Assert.assertEquals(new String("ilk"),test.get(0));
        Assert.assertEquals(new String("a"),test.get(1));
        Assert.assertEquals(new String("b"),test.get(2));
        Assert.assertEquals(new String("c"),test.get(3));
        //Assert.assertEquals(new String("ilk"),test.get(0));
    }

}