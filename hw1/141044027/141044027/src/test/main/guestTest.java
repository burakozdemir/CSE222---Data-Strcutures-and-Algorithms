package main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * guest Unit Test Class
 */
public class guestTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void deleteReservedRoom() throws Exception{

    }

    @Test
    public void addRoomtoMe() throws Exception {
        guest user=new guest(1,"burak","555");
        room roomm=new room(0,true,0);
        user.addRoomtoMe(roomm.getRoomId());
        Assert.assertEquals(new Integer(0),user.getReservedRoom(0));
    }

    @Test
    public void deleteARoom() throws Exception {
        guest user=new guest(1,"burak","555");
        room roomm=new room(0,true,0);
        user.addRoomtoMe(roomm.getRoomId());
        user.deleteARoom(0);
        Assert.assertEquals(new Integer(-1),user.getReservedRoom(0));
    }

    @Test
    public void bookRoom() throws Exception {
        Scanner scan=new Scanner(System.in);
        guest user=new guest(1,"burak","555");
        room[] roomm=new room[2];
        room a=new room(0,true);
        roomm[0]=a;

        user.setRooms(roomm);

        user.bookRoom(0,scan);

        roomm=user.getRooms();

        Assert.assertEquals(false,roomm[0].isStat());
    }

    @Test
    public void cancelReservation() throws Exception {
        Scanner scan=new Scanner(System.in);
        guest user=new guest(1,"burak","555");
        room[] roomm=new room[2];
        room a=new room(0,true);
        roomm[0]=a;

        user.setRooms(roomm);
        roomm[0].setOwner(1);
        roomm[0].setStat(false);

        user.setRooms(roomm);

        user.cancelReservation(0);

        roomm=user.getRooms();

        Assert.assertEquals(new Integer(0),roomm[0].getOwner());
    }

}