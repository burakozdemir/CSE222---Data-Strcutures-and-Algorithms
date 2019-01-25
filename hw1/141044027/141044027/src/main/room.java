package main;

/**
 * ---Simple Hotel Automation----
 * Room Class
 *
 * @author Burak Ozdemir 141044027
 */
public class room {

    private Integer roomId;
    private boolean stat;
    private Integer owner=0;
    private Integer[] friends =new Integer[3];//Burda kalan kisilerin IDlerı tutuluyor.

    /********************CONSTRUCTORS********************
    /**
     * Default Constructor
     */
    public room(){this(0,true);}

    /**
     * Constructor
     * @param roomID
     * @param status
     */
    public room(int roomID,boolean status){
        this(roomID,status,0);
    }


    /**
     * Constructor
     * @param roomID
     * @param status
     * @param ownerID
     */
    public room(int roomID,boolean status,int ownerID){
        this.roomId=roomID;
        this.stat=status;
        this.owner=ownerID;
        for (int i=0;i<this.friends.length;i++) this.friends[i]=-1;
    }

    /**********************SETTER GETTER***************

     /*
     * getter for roomid
     * @return Integer
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * setter for roomid
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * getter for stat
     * @return true or false
     */
    public boolean isStat() {
        return stat;
    }

    /**
     * setter for stat
     * @param stat
     */
    public void setStat(boolean stat) {
        this.stat = stat;
    }

    /**
     * getter for owner
     * @return Integer
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * setter for owner
     * @param owner
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * getter for Friends
     * @return Integer[]
     */
    public Integer[] getFriends(){return this.friends;}

    /**
     * getter for a friend
     * @param indis
     * @return Integer
     */
    public Integer getFriend(int indis) {return friends[indis];}

    /*************************HELPER METHODS*************

    /*
     * Odadaki tum userları cıkartır(-+1 yapar)
     */
    public void deleteAllFriend(){ for(int i=0;i<this.friends.length;i++) this.friends[i]=-1;}

    /**
     * Parametreye gore odadan uer cıkartır(-1 yapar)
     * @param userID
     */
    public void deleteFriend(int userID){
        for(int i=0;i<this.friends.length;i++)
            if(this.friends[i]==userID)
                this.friends[i]=-1;}

    /**
     * Odada yer varmı kontrol eder
     * @return true or false
     */
    public boolean isEmpty(){
        for (int i = 0; i<this.friends.length; i++){
            if(this.friends[i]==-1)
                return true;
        }
        return false;
    }

    /**
     * Oda full mu kontrol eder.
     * @return true or false
     */
    public boolean isFull(){
        int count=0;
        for(int i=0;i<this.friends.length;i++){
            if(!this.friends[i].equals(-1)) count++;
        }
        if(count==this.friends.length) return true;else return false;
    }

    /**
     * Paramtreye gore odanın ıcındekılerı ayarlar.
     * @param val
     * @param indis
     */
    public void setHuman(int val,int indis){
        this.friends[indis]=val;
    }

    /**
     * verilen id yi friendslere sırasıyla koyuyor
     * @param ID
     */
    public void setFriend(int ID) {
        for (int i=0;i<this.friends.length;i++){
            if(this.friends[i]==-1){
                this.friends[i]=ID;
                return;
            }
        }
    }
}
