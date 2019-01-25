package main;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Room Unit Test Class
 */

public class roomTest {
    @Test
    public void deleteAllFriend() throws Exception {
        room r=new room(1,false,2);
        r.setFriend(5);
        r.setFriend(6);

        r.deleteAllFriend();
        Assert.assertEquals(new Integer(-1),r.getFriend(0));
    }

    @Test
    public void deleteFriend() throws Exception {
        room r=new room(1,false,2);
        r.setFriend(5);
        r.setFriend(6);

        r.deleteFriend(6);
        Assert.assertEquals(new Integer(-1),r.getFriend(1));

    }

    @Test
    public void isEmpty() throws Exception {
        room r=new room(1,false,2);
        r.setFriend(5);
        r.setFriend(6);
        r.setFriend(8);

        Assert.assertEquals(false,r.isEmpty());
    }

    @Test
    public void isFull() throws Exception {
        room r=new room(1,false,2);
        r.setFriend(5);
        r.setFriend(6);
        r.setFriend(8);

        Assert.assertEquals(true,r.isFull());
    }

    @Test
    public void setFriend() throws Exception {
        room r=new room(1,false,2);
        r.setFriend(5);
        r.setFriend(6);
        r.setFriend(12345);

        Assert.assertEquals(new Integer(12345),r.getFriend(2));
    }

}