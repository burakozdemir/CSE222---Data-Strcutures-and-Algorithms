package test;

import Q2.myRecursiveHashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class myRecursiveHashSetTest {

    myRecursiveHashSet<String> obj1=new myRecursiveHashSet<>(3);

    @Before
    public void setUp() throws Exception {
        obj1.add("b");System.out.println("obj1.add(b)");
        System.out.println(obj1.toString());
        obj1.add("c");System.out.println("obj1.add(c)");
        System.out.println(obj1.toString());

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void size() {
        assertEquals(2,obj1.size());
        obj1.add("b");
        assertEquals(2,obj1.size());

    }

    @Test
    public void isEmpty() {
        assertEquals(false,obj1.isEmpty());

    }

    @Test
    public void contains() {
        obj1.add("b");
        obj1.add("d");
        obj1.add("e");
        obj1.add("f");
        obj1.add("g");
        assertEquals(true,obj1.contains("e"));
    }

    @Test
    public void remove() {
        assertEquals(true,obj1.remove("b"));
        System.out.print(obj1.toString());
        assertEquals(false,obj1.remove("asd"));
    }

    @Test
    public void add() {
        assertEquals(true,obj1.add("g"));
        assertEquals(false,obj1.add("c"));
    }

    @Test
    public void clear() {
        obj1.clear();
        assertEquals(true,obj1.isEmpty());
    }
}