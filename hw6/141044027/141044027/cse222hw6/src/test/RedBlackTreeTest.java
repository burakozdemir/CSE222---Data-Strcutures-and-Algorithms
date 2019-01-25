package test;

import Q1.RedBlackTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedBlackTreeTest {
    RedBlackTree<Integer> tree=new RedBlackTree<>();
    @Before
    public void setUp() throws Exception {
        tree.insert(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() {
        Assert.assertEquals(1,tree.size());
        tree.insert(2);
        Assert.assertEquals(2,tree.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(1,tree.size());
    }
}