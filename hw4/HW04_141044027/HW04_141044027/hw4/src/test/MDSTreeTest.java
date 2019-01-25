package test;

import main.MDSTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by burki on 15.04.2018.
 */
public class MDSTreeTest {
    MDSTree<Integer> tree=new MDSTree<>();
    @Before
    public void setUp() throws Exception {
        Vector<Integer> tempA=new Vector<>();
        Vector<Integer> tempB=new Vector<>();
        Vector<Integer> tempC=new Vector<>();
        Vector<Integer> tempD=new Vector<>();
        Vector<Integer> tempE=new Vector<>();
        Vector<Integer> tempF=new Vector<>();

        tempA.add(40);tempA.add(45);tempA.add(50);
        tempB.add(15);tempB.add(70);tempB.add(80);
        tempC.add(70);tempC.add(10);tempC.add(90);
        tempD.add(69);tempD.add(50);tempD.add(10);
        tempE.add(66);tempE.add(85);tempE.add(40);
        tempF.add(85);tempF.add(90);tempF.add(5);

        tree.add(tempA);
        tree.add(tempB);
        tree.add(tempC);
        tree.add(tempD);
        tree.add(tempE);
        tree.add(tempF);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {
        Vector<Integer> temp=new Vector<>();
        temp.add(100);temp.add(100);temp.add(100);
        tree.add(temp);
        Assert.assertEquals(temp,tree.find(temp));

    }

    @Test
    public void contains() throws Exception {
        Vector<Integer> temp=new Vector<>();
        temp.add(100);temp.add(100);temp.add(100);
        tree.add(temp);
        Assert.assertEquals(true,tree.contains(temp));
        Vector<Integer> temp2=new Vector<>();
        temp2.add(1);temp2.add(11);temp2.add(111);
        Assert.assertEquals(false,tree.contains(temp2));

    }

    @Test
    public void find() throws Exception {
        Vector<Integer> temp=new Vector<>();
        temp.add(100);temp.add(100);temp.add(100);
        Assert.assertEquals(null,tree.find(temp));
        Vector<Integer> temp2=new Vector<>();
        temp2.add(40);temp2.add(45);temp2.add(50);
        Assert.assertEquals(temp2,tree.find(temp2));
    }

    @Test
    public void delete() throws Exception {
        Vector<Integer> temp=new Vector<>();
        temp.add(66);temp.add(85);temp.add(40);
        tree.delete(temp);
        Assert.assertEquals(false,tree.contains(temp));
    }

    @Test
    public void remove() throws Exception {
        Vector<Integer> temp2=new Vector<>();
        temp2.add(66);temp2.add(85);temp2.add(40);
        System.out.print(tree.contains(temp2));
        Assert.assertEquals(true,tree.remove(temp2));
        Assert.assertEquals(false,tree.remove(temp2));
    }

}