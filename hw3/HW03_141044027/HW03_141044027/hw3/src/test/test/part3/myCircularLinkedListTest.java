package test.part3;

import main.part3.myCircularLinkedList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;

import static org.junit.Assert.*;

public class myCircularLinkedListTest {

    myCircularLinkedList<String> test;
    @Before
    public void setUp() throws Exception {
        test=new myCircularLinkedList<>();
        test.add("mat","1");
        test.add("kim","1");
        test.add("mat2","2");
        test.add("kim2","3");
        test.add("cog","1");
        test.add("tarih","3");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void listIteratorInSemester() throws Exception {
        ListIterator<String> iter=test.listIteratorInSemester(0);
        Assert.assertEquals(new String("kim"),iter.next());
        Assert.assertEquals(new String("cog"),iter.next());
        Assert.assertEquals(new String("mat"),iter.next());
        Assert.assertEquals(new String("kim"),iter.next());
        Assert.assertEquals(new String("cog"),iter.next());
        Assert.assertEquals(new String("mat"),iter.next());

    }

    @Test
    public void listIterator() throws Exception {
        ListIterator<String> iter=test.listIterator(0);
        Assert.assertEquals(new String("mat"),iter.next());
        Assert.assertEquals(new String("kim"),iter.next());
        Assert.assertEquals(new String("mat2"),iter.next());
        Assert.assertEquals(new String("kim2"),iter.next());
        Assert.assertEquals(new String("cog"),iter.next());
        Assert.assertEquals(new String("tarih"),iter.next());

    }

    @Test
    public void getKey() throws Exception {
        Assert.assertEquals(new String("1"),test.getKey(0));
    }

    @Test
    public void addFirst() throws Exception {
        test.addFirst("LAB","2");
        Assert.assertEquals(new String("LAB"),test.get(0));
    }

    @Test
    public void add() throws Exception {
        test.add("LAB","2");
        Assert.assertEquals(new String("LAB"),test.get(6));
    }

    @Test
    public void remove() throws Exception {
        test.remove(1);
        Assert.assertEquals(new String("mat2"),test.get(1));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(6,test.size());
    }

    @Test
    public void get(){
        Assert.assertEquals(new String("mat"),test.get(0));
    }
}