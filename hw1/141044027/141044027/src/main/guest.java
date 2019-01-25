package main;

import java.util.Scanner;

/**
 * ---Simple Hotel Automation----
 * guest Class
 *
 * @author Burak Ozdemir 141044027
 */
public class guest extends User implements guestSkills {

    private room[] rooms;///bookaromm ve cancel reservation yaparken
    protected Integer[] reservedRooms= new Integer[5] ; ///Musterinin rezerv ettigi oda.
    protected boolean isGuest=true;

    /********************* CONSTRUCTORS ***********************************
    /**
     * Constructor
     * @param ID
     * @param name
     * @param password
     * */
    public guest(int ID,String name,String password){

        super(ID,name,password);
        if(password.equals("r")) this.isGuest=false;
        for (int i=0;i<this.reservedRooms.length;i++){ this.reservedRooms[i]=-1;}
    }

    /**********************SETTER GETTERS *************************************

    /**
     * Setter for isGuest
     * @param val
     */
    public void setGuest(boolean val){this.isGuest=val;}

    /**
     * getter for isGuest
     * @return true or false
     */
    public boolean getGuest(){return this.isGuest;}

    /**
     * setter for reservedRoom
     * @param val
     * @param indis
     */
    public void setReservedRoom(Integer val,int indis){ this.reservedRooms[indis]=val;}

    /**
     * getter for reservedroom
     * @param indis
     * @return Integer
     */
    public Integer getReservedRoom(int indis){return this.reservedRooms[indis];}

    /**
     * Getter for reservedRooms
     * @return Integer[]
     */
    public Integer[] getReservedRooms(){return  this.reservedRooms;}

    /**
     * getter for rooms
     * @return main.room[]
     */
    public room[] getRooms() {return rooms;}

    /**
     * Setter for rooms
     * @param rooms
     */
    public void setRooms(room[] rooms) {this.rooms = rooms;}

    /*********************HELPER METHODS*****************************************

    /**
     * Guest in reservedRooms'u na  bir tane oda ekler.
     * @param RoomID
     */
    public void addRoomtoMe(int RoomID){
        for (int i=0;i<reservedRooms.length;i++){
            if(this.reservedRooms[i].equals(-1)){
                this.reservedRooms[i]=RoomID;
                return;
            }
        }
    }

    /**
     * Guestin reservedRooms larını ekrana print eder.
     */
    public void listReservedRooms(){
        for(int i=0;i<this.reservedRooms.length;i++){
            if(this.reservedRooms[i]!=null && !this.reservedRooms[i].equals(-1))
            System.out.print(this.reservedRooms[i].toString() + " ");
        }
        System.out.println();
    }

    /**
     * Guest in reservedRooms fieldından parametre degerini siler(-1 yapar)
     * @param val
     */
    public void deleteARoom(int val) {
        for(int i=0;i<this.reservedRooms.length;i++){
            if(this.reservedRooms[i].equals(val)) {
                this.reservedRooms[i]=-1;break;
            }
        }
    }

    /**
     * Guestin tüm odalarını siler(-1 yapar)
     * @param Roomid
     */
    public void deleteReservedRoom(int Roomid){
        for(int i=0;i<this.reservedRooms.length;i++){
            if(Roomid==this.reservedRooms[i]) this.reservedRooms[i]=-1;
        }
    }

    /***********************METHODS**********************
    /**
     * Guest Menusunu ekrana basar.
     */
    @Override
    public void userMenu(){
        System.out.println("------------- MENU --------------");
        System.out.println("1- Book a Room        ");
        System.out.println("2- Cancel Reservation ");
        System.out.println("3- List all my rooms  ");
        System.out.println("4- Exit               ");
    }

    /**
     * Guest oda rezerv eder.
     * Donen string odaya dahil edilen insanın ismi . Passwordu otamatık "r" olucak.
     * @param Roomid
     * @return String
     */
    @Override
    public String bookRoom(int Roomid,Scanner scan) {
        String name="";
        for(int i =0;i<this.rooms.length;i++){
            if(this.rooms[i]!=null && this.rooms[i].getRoomId()==Roomid){
                if(this.rooms[i].isStat()==true){
                    this.rooms[i].setStat(false);
                    this.rooms[i].setOwner(this.getUserID());
                }
                else{
                    if(this.rooms[i].isEmpty()){
                        if(this.rooms[i].getOwner().equals(this.getUserID())){
                            System.out.print("PLease enter name:");
                            name=scan.next();
                            System.out.println(name);
                            return name;
                        }
                    }else{
                        System.out.println("Room is Full");
                    }
                }
            }
        }
        return name;
    }

    /**
     * Guest rezervli odayı iptal eder .
     * @param RoomID
     * @return true or false
     */
    @Override
    public Integer[] cancelReservation(int RoomID) {
        Integer[] res=new Integer[3];
        for(int i=0;i<this.rooms.length;i++){
            if(this.rooms[i]!=null && RoomID==this.rooms[i].getRoomId()){
                res=this.rooms[i].getFriends();
                deleteReservedRoom(RoomID);
                this.rooms[RoomID].setOwner(0);
                this.rooms[RoomID].setStat(true);
                return res;
            }
        }
        return res;
    }

}
