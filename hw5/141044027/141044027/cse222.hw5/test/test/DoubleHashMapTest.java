package test;

import Q1.DoubleHashMap;
import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleHashMapTest {

    DoubleHashMap<String,Integer> obj1=new DoubleHashMap<>(5);

    @Before
    public void setUp() throws Exception {
        obj1.put("burak",22);
        obj1.printHashTable();
        obj1.put("cagla",21);
        obj1.printHashTable();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void size() {
        Assert.assertEquals(2,obj1.size());
    }

    @Test
    public void isEmpty() {
        Assert.assertEquals(false,obj1.isEmpty());
    }

    @Test
    public void get() {
        assertEquals(22,obj1.get("burak").intValue());
    }

    @Test
    public void put() {
        assertEquals(1,obj1.put("a",1).intValue());
    }

    @Test
    public void remove() {
        assertEquals(21,obj1.remove("cagla").intValue());
        obj1.printHashTable();
    }

    @Test
    public void clear() {
        obj1.clear();
        obj1.printHashTable();
        assertEquals(true,obj1.isEmpty());
    }
}