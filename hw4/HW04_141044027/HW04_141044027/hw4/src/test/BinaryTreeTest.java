package test;


import main.BinaryTree;
import main.BinaryTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BinaryTreeTest {
    BinaryTree<Integer> tree1=new BinaryTree<>();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {
        tree1.add(1);
        tree1.add(1,3);
        tree1.add(1,5);
        tree1.add(3,4);
        Assert.assertEquals(false, tree1.isLeaf());

    }

    @Test
    public void isLeaf() throws Exception {
        tree1.add(1);
        Assert.assertEquals(true,tree1.isLeaf());
    }

}