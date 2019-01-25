package test;

import Q3.AVLTree;
import org.junit.Assert;

import static org.junit.Assert.*;

public class AVLTreeTest {
    AVLTree<Integer> tree=new AVLTree<>();
    @org.junit.Before
    public void setUp() throws Exception {
        for (int i = 0; i <6 ; i++) {
            tree.add(i);
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void delete() {
        tree.delete(5);
        Assert.assertEquals(false,tree.contains(5));
    }

    @org.junit.Test
    public void add() {
        tree.add(6);
        tree.add(7);
        Assert.assertEquals(true,tree.contains(7));
        Assert.assertEquals(false,tree.contains(8));

    }
}