package test.part2;

import main.part1.GTUCECourses;
import main.part2.GTUCourses_myLinkedList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class GTUCourses_myLinkedListTest {
    GTUCourses_myLinkedList test;
    @Before
    public void setUp() throws Exception {
        test=new GTUCourses_myLinkedList("Courses(CSV)(Updated).csv");
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void getIndexOfCode(){
        Assert.assertEquals(1,test.getIndexOfCode("CSE 101"));
    }

    @Test
    public void getByCode() throws Exception {
        Assert.assertEquals(new String("Calculus I"),test.getByCode("MATH 101"));
        Assert.assertEquals(new String("Physics I"),test.getByCode("PHYS 121"));
        Assert.assertEquals(new String("Physics Laboratory I"),test.getByCode("PHYS 151"));
    }

    @Test
    public void listSemesterCourses() throws Exception {
        LinkedList<String> listInSemester = test.listSemesterCourses(1);
        Assert.assertEquals(new String("XXX XXX"),listInSemester.get(0));
        Assert.assertEquals(new String("CSE 101"),listInSemester.get(1));
        Assert.assertEquals(new String("CSE 107"),listInSemester.get(2));
        Assert.assertEquals(new String("MATH 101"),listInSemester.get(3));
        Assert.assertEquals(new String("PHYS 121"),listInSemester.get(4));
        Assert.assertEquals(new String("PHYS 151"),listInSemester.get(5));
        Assert.assertEquals(new String("SSTR 101"),listInSemester.get(6));
        Assert.assertEquals(new String("TUR 101"),listInSemester.get(7));

    }

    @Test
    public void getByRange() throws Exception {
        LinkedList<String> range=test.getByRange(0,5);
        Assert.assertEquals(new String("XXX XXX"),range.get(0));
        Assert.assertEquals(new String("CSE 101"),range.get(1));
        Assert.assertEquals(new String("CSE 107"),range.get(2));
        Assert.assertEquals(new String("MATH 101"),range.get(3));
        Assert.assertEquals(new String("PHYS 121"),range.get(4));

    }

}