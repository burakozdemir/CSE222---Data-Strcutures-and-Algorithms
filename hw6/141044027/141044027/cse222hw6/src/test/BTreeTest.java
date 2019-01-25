package test;

import Q2.BTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BTreeTest {
    BTree tree=new BTree(4);
    @Before
    public void setUp() throws Exception {
        for (int i = 0; i <15; i++) {
            tree.insert(tree,i);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void binarySearch() {
        Assert.assertEquals(true,tree.binarySearch(14));
        Assert.assertEquals(false,tree.binarySearch(15));
    }
}