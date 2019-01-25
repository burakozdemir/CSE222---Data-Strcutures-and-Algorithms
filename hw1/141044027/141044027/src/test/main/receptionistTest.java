package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Receptionist Unit Test Class
 */
public class receptionistTest {
    @Test
    public void searchName() throws Exception {
        guest[] guests=new guest[2];
        receptionist admin=new receptionist("fatih","654");
        guest temp=new guest(1,"burak","555");
        //guests[0]=temp;
        guests[1]=temp;

        admin.setGuests(guests);
        Assert.assertEquals(true,admin.searchName("burak"));
    }

    @Test
    public void isInRoom() throws Exception {
        receptionist admin=new receptionist("fatih","654");
        room[] r=new room[2];
        room temp=new room(1,false,2);
        r[1]=temp;
        r[1].setFriend(6);

        admin.setRooms(r);
        r=admin.getRooms();


        Assert.assertEquals(new Integer(6),r[1].getFriend(0));

    }

    @Test
    public void getIDofGuest() throws Exception {
        guest[] guests=new guest[2];
        receptionist admin=new receptionist("fatih","654");
        guest temp=new guest(3,"burak","555");
        //guests[0]=temp;
        guests[1]=temp;
        admin.setGuests(guests);
        Assert.assertEquals(3,admin.getIDofGuest("burak"));

    }

    @Test
    public void bookaRoom() throws Exception {
        Scanner s=new Scanner(System.in);
        guest[] guests=new guest[2];
        room[] rooms=new room[2];

        guest temp=new guest(1,"burak","555");
        room r=new room(1,true);
        receptionist admin=new receptionist("fatih","654");

        rooms[0]=r;
        rooms[1]=r;
        guests[1]=temp;

        admin.bookaRoom(1,"burak",s);

        rooms=admin.getRooms();
        guests=admin.getGuest();

        Assert.assertEquals(false,rooms[0].isStat());
    }

    @Test
    public void cancelReserv() throws Exception {
        guest[] guests=new guest[2];
        room[] rooms=new room[2];
        Scanner s=new Scanner(System.in);

        guest temp=new guest(1,"burak","555");
        room r=new room(1,true);
        receptionist admin=new receptionist("fatih","654");

        rooms[0]=r;
        rooms[1]=r;
        guests[1]=temp;

        admin.setRooms(rooms);
        admin.setGuests(guests);
        admin.bookaRoom(1,"burak",s);
        admin.cancelReserv(1,1);

        rooms=admin.getRooms();
        guests=admin.getGuest();

        Assert.assertEquals(new Integer(0),rooms[1].getOwner());
    }

    @Test
    public void checkOut() throws Exception {
        guest[] guests=new guest[2];

        Scanner s=new Scanner(System.in);

        guest temp=new guest(1,"burak","555");
        room r=new room(1,true);
        receptionist admin=new receptionist("fatih","654");

        guests[1]=temp;

        admin.setGuests(guests);
        admin.checkOut(1);
        guests=admin.getGuest();

        Assert.assertEquals(null,guests[1]);
    }

    @Test
    public void checkIn() throws Exception {
        guest[] guests=new guest[3];

        guest temp=new guest(1,"burak","555");
        guest temp2=new guest(2,"kadir","444");
        room r=new room(1,true);
        receptionist admin=new receptionist("fatih","654");

        guests[0]=temp;
        guests[1]=temp2;

        admin.setGuests(guests);

        admin.checkIn("heyo","546",-1);
        guests=admin.getGuest();
        Assert.assertEquals("546",guests[2].getUserPassword());
    }

}