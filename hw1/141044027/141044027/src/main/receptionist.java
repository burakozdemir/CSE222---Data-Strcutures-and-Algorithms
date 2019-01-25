package main;

import main.guest;

import java.util.Scanner;

/**
 * ---Simple Hotel Automation----
 * receptionist Class
 *
 * @author Burak Ozdemir 141044027
 */
public class receptionist extends User implements receptionistSkills {
    private room[] rooms;
    private guest[] guests;

    /*************************CONSTRUCTORS****************
    /**
     * Constructor
     * @param name
     * @param password
     */
    public receptionist(String name,String password){
        super(0,name,password);
    }

    /*************************SETTER GETTER*********************
    /**
     * setter for rooms
     * @param rooms
     */
    public void setRooms(room[] rooms) {
        this.rooms = rooms;
    }

    /**
     * setter for guests
     * @param guests
     */
    public void setGuests(guest[] guests) {
        this.guests = guests;
    }

    /**
     * getter for guest
     * @return main.guest[]
     */
    public guest[] getGuest(){return this.guests;}

    /**
     * getter for rooms
     * @return main.room[]
     */
    public room[] getRooms(){return this.rooms;}

    /***************************HELPER METHODS*****************
     /**
     * Verilen ismin guestte olup olmadıgına bakar
     * @param name
     * @return true or false
     */
    public boolean searchName(String name){
        for (int i=1;i<this.guests.length;i++){
            if(this.guests[i]!=null){
                if(this.guests[i].getUserName().equals(name)) return true;
            }

        }
        return false;
    }

    /**
     * Userın odada varmı yokmu kontrol edıyor.
     * @param roomid
     * @param userid
     * @return true or false
     */
    public boolean isInRoom(int roomid,int userid){
        if(this.rooms[roomid].getOwner().equals(userid)) return true;
        for(int i=0;i<this.rooms[roomid].getFriends().length;i++){
            if(!this.rooms[roomid].getFriend(i).equals(-1) && this.rooms[roomid].getFriend(i).equals(userid)) return true;
        }
        return false;
    }

    /**
     * Parametreye gore user ıd dondurur.
     * @param name
     * @return int
     */
    public int getIDofGuest(String name){
       for(int i=0;i<this.guests.length;i++){
            if(this.guests[i]!=null){
                if(this.guests[i].getUserName().equals(name)) return this.guests[i].getUserID();
            }
       }
       return -1;
    }

    /**
     * Odaların oteldeki durumlarını gosterir
     * True musait()
     * False kullanılıyor()
     */
    public void listRooms(){
        System.out.println("Room ID - Status - Owner - Friends ...");
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i]!=null){
                if(rooms[i].isStat()==true){
                    System.out.println(rooms[i].getRoomId()+" - "+rooms[i].isStat());
                }
                else{
                    System.out.print(rooms[i].getRoomId()+" - "+rooms[i].isStat()+" - "+rooms[i].getOwner().toString()+"(Owner) - ");
                    for ( int k=0;k<rooms[i].getFriends().length;k++){
                        if(!rooms[i].getFriend(k).equals(-1))
                            System.out.print(rooms[i].getFriend(k)+" - ");
                    }
                    System.out.println();
                }
            }

        }
    }

    /**
     * gGuestlerin durumunu ekrana basar.
     */
    public void listGuests(){
        System.out.println("Guest ID - Guest Name - Guest Rooms...");
        for (int i = 0; i < this.guests.length; i++) {
            if(this.guests[i]!=null){
                System.out.print(this.guests[i].getUserID()+" - "+this.guests[i].getUserName()+" - ");
                for (int k=0;k<this.guests[i].getReservedRooms().length;k++){
                    if(!this.guests[i].getReservedRoom(k).equals(-1))
                    System.out.print(this.guests[i].getReservedRoom(k)+" - ");
                }
                System.out.println();
            }
        }
    }

    /*******************METHODS****************
    /**
     * Recepsionist menu
     */
    @Override
    public void userMenu(){
        System.out.println();
        System.out.println("------------- MENU -------------");
        System.out.println("1- Check In Guest     ");
        System.out.println("2- Check Out Guest    ");
        System.out.println("3- Book Room          ");
        System.out.println("4- Cancel Reservation ");
        System.out.println("5- List all guests    ");
        System.out.println("6- List all rooms     ");
        System.out.println("7- Exit               ");
    }

    /**
     * Recepsionistin guest icin oda reserv etmesını saglar
     * oda false ise oda sorumlusunun ıd ve sıfresını ısteyerek dogrulama yapar. eklenen sıfresız main.guest olur
     * oda true ıse yenı bır check ın yapar .
     * @param Roomid
     * @param name
     * @return true or false
     */
    @Override
    public boolean bookaRoom(int Roomid,String name,Scanner scan) {
        if(this.rooms[Roomid].isStat()==true){
            if(searchName(name)){
                int userID=getIDofGuest(name);
                this.rooms[Roomid].setStat(false);
                this.rooms[Roomid].setOwner(userID);
                this.guests[userID].addRoomtoMe(Roomid);
            }else{
                System.out.print("Please create new password:");
                String tempname=scan.next();
                System.out.println(tempname);
                checkIn(name,tempname,-1);
                int userID=getIDofGuest(name);
                this.rooms[Roomid].setStat(false);
                this.rooms[Roomid].setOwner(userID);
                this.guests[userID].addRoomtoMe(Roomid);
            }
        }
        else if(this.rooms[Roomid].isStat()==false){
            System.out.print("PLease enter Owner ID for this main.room:");
            int tempID=scan.nextInt();
            System.out.println(tempID);
            System.out.print("PLease enter Owner Password for this main.room:");
            String tempPassword=scan.next();
            System.out.println(tempPassword);
            if(guests[tempID]!= null && guests[tempID].getUserID()==tempID && guests[tempID].getUserPassword().equals(tempPassword)){
                if(this.rooms[Roomid].isEmpty()){
                    checkIn(name,"r",-1);
                    int userid=getIDofGuest(name);
                    this.rooms[Roomid].setFriend(userid);
                    this.guests[getIDofGuest(name)].addRoomtoMe(Roomid);
                    System.out.println("Done");
                    return true;
                }else{System.out.println("Room is Full.");}
            }
            else{ System.out.println("Acces Denied!"); return false;}

        }
        return false;
    }

    /**
     * Recepsionistin main.guest ıcın reserv ıptal eder
     * Iptal edılen reservasyon islemi sifresiz main.guest ıcın ıse sadece o main.guest cıkar.
     * Iptal edılen reservasyon ıslemı oda sorumlusu ise odadakı herkes cıkartılır.
     * @param roomID
     * @param guestID
     * @return true or false
     */
    public boolean cancelReserv(int roomID,Integer guestID){
        for (int i =0 ; i< guests.length;i++){
            if(this.guests[i]!=null && guestID.equals(guests[i].getUserID())){
                if(this.guests[i].getUserPassword().equals("r")){
                    for(int k=2;k>=0;k--){
                        if(this.rooms[roomID].getFriend(k)!=-1 &&
                                this.guests[guestID].getUserID()==this.rooms[roomID].getFriend(k)){
                            this.rooms[roomID].setHuman(-1,k);
                            break;
                        }
                    }
                    this.guests[i]=null;
                    return true;
                }
                else {
                    for (int k=0;k<rooms[roomID].getFriends().length;k++){
                        if(!this.rooms[roomID].getFriend(k).equals(-1)){
                            this.guests[rooms[roomID].getFriend(k)]=null;
                            this.rooms[roomID].setHuman(-1,k);
                        }
                    }
                    this.guests[guestID].deleteARoom(roomID);
                    this.rooms[roomID].setOwner(0);this.rooms[roomID].setStat(true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Musteriyi sistemden cikarir.
     * Oda reserv etti ise o oda bosaltılır.
     * @param guestID
     * @return true or false
     */
    @Override
    public boolean checkOut(int guestID){
        for (int i =0 ; i< this.guests.length;i++){
            if(this.guests[i]!=null && this.guests[i].getUserID()==guestID){
                if(this.guests[i].getUserPassword().equals("r")){
                    this.rooms[this.guests[i].getReservedRoom(0)].deleteFriend(guestID);
                    this.guests[i]=null;
                    return true;
                }
                else {
                    for(int k=0;k<this.guests[i].getReservedRooms().length;k++){
                        if(!this.guests[i].getReservedRoom(k).equals(-1)){
                            for(int j=0;j<this.rooms[this.guests[i].reservedRooms[k]].getFriends().length;j++){
                                int t = this.rooms[this.guests[i].reservedRooms[k]].getFriend(j);
                                if(t!=-1)
                                    this.guests[t]=null;
                            }
                            this.rooms[this.guests[i].getReservedRoom(k)].deleteAllFriend();
                            this.rooms[this.guests[i].getReservedRoom(k)].setStat(true);
                            this.rooms[this.guests[i].getReservedRoom(k)].setOwner(0);
                        }
                    }

                    this.guests[i]=null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sisteme main.guest ekler
     * @param name
     * @param password
     * @return true or false
     */
    @Override
    public boolean checkIn(String name,String password,int UserID){
        int lastId=0;
        int lastindex=0;
        if(UserID==-1){
            for (int i = 1; i < this.guests.length; i++) {
                if(guests[i]!=null){
                    lastId=guests[i].getUserID();
                    lastindex=i;
                }
            }
            if(lastindex==49){
                return false;
            }
            guests[lastindex+1]=new guest(lastId+1,name,password);
            return true;

        }
        return false;
    }

}
