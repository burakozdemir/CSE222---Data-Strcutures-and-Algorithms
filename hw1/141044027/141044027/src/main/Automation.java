package main;

import java.io.*;
import java.util.Scanner;

/**
 * ------ Hotel Automation ------
 *  Main class
 *  İNPUT FILES: users.csv , rooms.csv
 *  OUTPUT FILES: outputUsers.csv , outputRooms.csv
 *  adminID:0 ,adminPassword:1234
 * @author Burak Ozdemir 141044027
 */

public class Automation {

    static Scanner scan ;
    private static final String USERFILENAME="users.csv";
    private static final String ROOMSFILENAME ="rooms.csv";
    private static guest[] guests;
    private static receptionist admin;
    private static room[] rooms;


    /**
     * Default Constructor
     */
    public Automation(){
        guests=null;
        admin=null;
        rooms=null;
    }
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        Automation test=new Automation();
        String a;
        Scanner sc=new Scanner(System.in);
        boolean flagMain=true;
        while(flagMain){
            try {
                System.out.println("İf you start normally ,press '1'");
                System.out.println("İf you start with TEST SCENARIO , press '2'");
                System.out.print("Choose:");
                a=sc.next();
                System.out.println();
                if(a.equals("1")){
                    test.start("");
                    flagMain=false;
                }
                if(a.equals("2")){
                    boolean flag=true;
                    Scanner s=new Scanner(System.in);
                    while(flag){
                        test.seneryoMenu();
                        System.out.println("Choose senerio :");
                        //System.out.println("---------------------------");
                        int choose=s.nextInt();
                        switch (choose){
                            case 1:test.start("checkin-receptionist.txt");break;
                            case 2:test.start("checkout-receptionist.txt");break;
                            case 3:test.start("bookRoom-receptionist.txt");break;
                            case 4:test.start("cancelReservation-receptionist.txt");break;
                            case 5:test.start("bookRoom-guest.txt");break;
                            case 6:test.start("cancelReservation-guest.txt");break;
                            default :break;
                        }
                        System.out.println("Another Senerio ? Please press 'c' for continue:");
                        String res=s.next();
                        if(res.equals("c")) flag=true;else flag=false;
                        flagMain=false;
                    }
                }else
                    System.out.println("Please try again");
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Print eder senaryoyu ekrana.
     */
    private void seneryoMenu(){
        System.out.println();
        System.out.println("------------- Senaryo Menu -------------");
        System.out.println("1- Check-In for Receptionist ");
        System.out.println("2- Check-Out for Receptionist");
        System.out.println("3- BookRoom for Receptionist ");
        System.out.println("4- CancelReservation for Receptionist");
        System.out.println("5- BookRoom for Guest");
        System.out.println("6- CancelReservation for Guest");
        System.out.println("7- Exit");
    }

    /**
     * Otomasyonu baslatan method
     * Parametresi senaryo icin gerekli dosya adı
     * @param filename
     * @throws IOException
     * @return int
     */
    public int start(String filename) throws IOException{
        readUsersFile();
        readRoomsFile();

        if(!filename.equals("")) {
            setScanner(new FileReader(new File(filename)));
        }else
            scan=new Scanner(System.in);

        String userID = null;
        String password = null;
        String nextFlag = null;
        boolean flag=true;

        try{
            while(flag){
                System.out.println(" ~ Welcome to Simple Hotel Automation ~ ");
                System.out.println("If you guest(no password) , please enter 'r' for password.");
                System.out.print("Please enter User ID:");
                userID=scan.next();
                System.out.println(userID);
                System.out.print("Please enter Your Password:");
                password=scan.next();
                System.out.println(password);


                ///odasız guestin giris kontrolu mainde kontrol edilcek
                switch(findUser(Integer.parseInt(userID),password)){
                    case 1 :modADMIN();
                        break ;
                    case 2: modGUEST(userID);
                        break ;
                    case 3: modJustReserv(userID);
                        break;
                    case 0: System.out.println("Access Denied.");
                }
                System.out.print("If you want to exit please enter 'e' :");
                nextFlag=scan.next();
                System.out.println(nextFlag);
                if(nextFlag.equals("e"))
                    flag=false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage() +" (NO Integer)");
        }

        writeRoomsFile();
        writeUserFile();
        System.out.println("Good Byee.");
        return 1;
    }

    /**
     * Dosyadan(parametre) gerekli reservasyon icin inputları alır
     * @param fileReader
     */
    private void setScanner(FileReader fileReader){
        scan = new Scanner(fileReader);
    }


    /**
     * isim alıp userin idsini return eder.
     * @param name
     * @return userID
     */
    private int findUserID(String name){
        for(int i=1;i<guests.length;i++){
            if(guests[i]!=null){
                if(guests[i].getUserName()==name) return guests[i].getUserID();
            }

        }
        return -1;
    }

    /**
     * Guest olmayıp otelde olan user icin bildirme methodu
     * @param id
     */
    private  void modJustReserv(String id){
        System.out.println("You cannot log in");
        System.out.println("Your hotel main.room :"+guests[Integer.parseInt(id)].getReservedRoom(0));
        System.out.println();
    }

    /**
     * Receptionist icin ana method ve donguyu icerir.
     */
    private void modADMIN() throws Exception{

        try{
            boolean flag=true;
            admin.setGuests(guests);
            admin.setRooms(rooms);
            while(flag){
                System.out.println("User :"+admin.getUserName());
                int choo=0;
                admin.userMenu();
                System.out.print("Enter choice:");
                String choose=scan.next();
                System.out.println(choose);
                choo=Integer.parseInt(choose);
                switch(choo){
                    case 1:
                        System.out.print("Username:");
                        String name=scan.next();
                        System.out.println(name);
                        System.out.print("Password:");
                        String pass=scan.next();
                        System.out.println(pass);
                        if(admin.checkIn(name,pass,-1)){
                            guests=admin.getGuest();
                            rooms=admin.getRooms();
                            System.out.println("Done");
                        }
                        else
                            System.out.println("Error");
                        break ;
                    case 2:
                        admin.listGuests();
                        System.out.print("Enter Guest ID:");
                        int id=scan.nextInt();
                        System.out.println(id);
                        if(admin.checkOut(id)){
                            guests=admin.getGuest();
                            rooms=admin.getRooms();
                            System.out.println("Done");
                        }else{
                            System.out.println("Error");
                        }
                        break ;
                    case 3:
                        admin.listRooms();
                        System.out.print("Enter RoomID:");
                        int Id=scan.nextInt();
                        System.out.println(Id);
                        if(rooms[Id]!=null){
                            System.out.print("Enter Reservation Name:");
                            String temp=scan.next();
                            System.out.println(temp);
                            admin.bookaRoom(Id,temp,scan);
                            guests=admin.getGuest();
                            rooms=admin.getRooms();
                        }
                        else
                            System.out.println("Room are not avaliable");
                        break ;
                    case 4:
                        admin.listRooms();
                        System.out.print("Enter RoomID:");
                        int roomID=scan.nextInt();
                        System.out.println(roomID);
                        if(rooms[roomID]!=null && rooms[roomID].isStat()!=true){
                            System.out.print("Enter Name:");
                            String t=scan.next();
                            int guestID=admin.getIDofGuest(t);
                            System.out.println(t);
                            if(admin.isInRoom(roomID,guestID)){
                                admin.cancelReserv(roomID,guestID);
                                rooms=admin.getRooms();
                                guests=admin.getGuest();
                            }
                            else
                                System.out.println("This name is not in this room");
                        }else{
                            System.out.println("This room is empty");
                        }
                        break ;
                    case 5:
                        admin.listGuests();
                        break ;

                    case 6:
                        admin.listRooms();
                        break ;
                    case 7:
                        flag=false;
                        break ;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage() + " (No True Value)");
        }

    }

    /**
     * Guest icin ana method ve donguyu icerir.
     * @param USERID
     */
    private void modGUEST(String USERID) {
        boolean flag=true;
        while(flag){
            admin.setGuests(guests);
            admin.setRooms(rooms);

            guests[Integer.parseInt(USERID)].setRooms(rooms);

            System.out.println("Guest :"+guests[Integer.parseInt(USERID)].getUserName());
            guests[Integer.parseInt(USERID)].userMenu();
            System.out.print("Enter choice:");

            int choo=0;
            choo=scan.nextInt();
            System.out.println(choo);
            switch(choo){
                case 1:
                    admin.listRooms();
                    System.out.print("Enter RoomId:");
                    int chooseID=scan.nextInt();
                    System.out.println(chooseID);
                    String tempName="";
                    for(int k=0;k<rooms.length;k++){
                        if(rooms[k]!=null && rooms[k].getRoomId()==chooseID){
                            if(rooms[k].isStat()==false){
                                tempName=guests[Integer.parseInt(USERID)].bookRoom(chooseID,scan);
                                rooms=guests[Integer.parseInt(USERID)].getRooms();
                                if(!tempName.equals("")){
                                    if(!guests[rooms[k].getOwner().intValue()].getUserName().equals(tempName)){
                                        admin.checkIn(tempName,"r",-1);//-1 yeni oldugu anlamına gelıyor .
                                        rooms[k].setFriend(findUserID(tempName));
                                        guests=admin.getGuest();
                                        guests[findUserID(tempName)].addRoomtoMe(chooseID);
                                    }else{
                                        System.out.println("This room is already yours.");
                                    }

                                }else{
                                    System.out.println("This room is not yours .");
                                }
                            }else if(rooms[k].isStat()==true){
                                guests[Integer.parseInt(USERID)].bookRoom(chooseID,scan);
                                rooms=guests[Integer.parseInt(USERID)].getRooms();
                                guests[Integer.parseInt(USERID)].addRoomtoMe(chooseID);
                            }
                        }
                    }
                    break ;
                case 2:
                    System.out.print("MYROOMS: ");
                    guests[Integer.parseInt(USERID)].listReservedRooms();
                    System.out.print("Select a RoomId:");
                    int RoomID = scan.nextInt();
                    System.out.println(RoomID);
                    Integer[] tempcheckOutHman;//Oda 4 ksilik oldgu ıcın 3 ıle ınıtıaliza edildi
                    for(int k=0;k<rooms.length;k++){
                        if(rooms[k]!=null && rooms[k].getRoomId()==RoomID){
                            tempcheckOutHman=guests[Integer.parseInt(USERID)].cancelReservation(RoomID);
                            rooms=guests[Integer.parseInt(USERID)].getRooms();
                            for(int i=0;i<tempcheckOutHman.length;i++){
                                admin.checkOut(tempcheckOutHman[i]);
                            }
                            rooms[k].deleteAllFriend();
                            guests=admin.getGuest();
                        }
                    }
                    break ;
                case 3:
                    System.out.print("MYROOMS: ");
                    guests[Integer.parseInt(USERID)].listReservedRooms();
                    break ;
                case 4:
                    flag=false;
                    break ;
            }
        }
    }

    /**
     * girilen id ye ve sifreye gore integer deger dondurur
     * @param id
     * @param password
     * @return 1(main.receptionist) or 2(main.guest) or 3(main.guest(no password)) or 0(error)
     */
    private int findUser(int id, String password) {
        User user = admin;

        try{
            if( admin!= null && user.getUserID()==id && user.getUserPassword().contentEquals(password)){
                return 1;
            }
        }
        catch(Exception e){
            System.err.println(e.toString());
        }

        for(int i=0; i<guests.length; ++i){
            user = guests[i];
            try{
                if(guests[i]!= null && user.getUserID()==id && user.getUserPassword().contentEquals(password) &&
                        !user.getUserPassword().contentEquals("r")){
                    return 2;
                }
                if(guests[i]!= null && user.getUserID()==id && (user.getUserPassword().contentEquals("r") ||
                                                                user.getUserPassword().contentEquals(""))){
                    return 3;
                }
            }
            catch(Exception e)
            {
                System.err.println(e.toString());
            }
        }
        return 0;
    }

    /**
     * Odaları ve bilgilerini outputRooms.csv dosyasına yazar.
     * @throws IOException
     */
    private void writeRoomsFile() throws IOException{
        int flag=0;
        FileWriter fileWriter = null;
        try{

            fileWriter = new FileWriter(new File("outputRooms.csv"));
            PrintWriter pw = new PrintWriter(fileWriter);
            StringBuilder sb = new StringBuilder();
            if(rooms[0]==null){
                sb.append(0);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(1);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(2);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(3);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(4);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(5);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(6);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(7);sb.append(";");sb.append("t");sb.append('\n');
                sb.append(8);sb.append(";");sb.append("t");sb.append('\n');
                flag=1;

            }

            for (int i = flag; i < rooms.length; i++) {
                if(rooms[i]!=null){
                    sb.append(rooms[i].getRoomId());sb.append(";");
                    if(rooms[i].isStat()==true){
                        sb.append("true");sb.append('\n');
                    }
                    else{
                        sb.append("false");sb.append(";");sb.append(rooms[i].getOwner());sb.append(";");
                        for(int k=0;k<rooms[i].getFriends().length;k++){
                            if(!rooms[i].getFriend(k).equals(-1)){
                                sb.append(rooms[i].getFriend(k).toString());
                                sb.append(";");
                            }
                        }
                        sb.deleteCharAt(sb.length()-1);
                        sb.append('\n');
                    }
                }
            }
            fileWriter.write(sb.toString());
            fileWriter.close();
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }
    }

    /**
     * main.User verilerini users.csv dosyasından okur
     * @return true or false
     * @throws FileNotFoundException
     * @throws IOException
     */
    private boolean readUsersFile()throws FileNotFoundException, IOException{
        String COMMA = ";";
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(USERFILENAME));
            int count=1;//0. indiste adminin bilgileri var.
            line = fileReader.readLine();

            if(line==null){return false;}
            String[] toks=line.split(COMMA);

            guests = new guest[200];
            admin=new receptionist(toks[1],toks[2]);

            while ((line = fileReader.readLine()) != null) {
                String[] tok = line.split(COMMA);
                if(tok.length==3){
                    guests[count]=new guest(Integer.parseInt(tok[0]),tok[1],tok[2]);
                }
                else{
                    guests[count]=new guest(Integer.parseInt(tok[0]),tok[1],tok[2]);
                    for (int k=3;k<tok.length;k++){
                        guests[count].addRoomtoMe(Integer.parseInt(tok[k]));
                    }
                }
                count++;
            }
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }
        return true;
    }

    /**
     * Room verilerini rooms.csv dosyasından okur.
     * @return true or false
     * @throws FileNotFoundException
     * @throws IOException
     */
    private boolean readRoomsFile()throws FileNotFoundException, IOException {
        boolean flag=false;
        String COMMA = ";";
        BufferedReader fileReader = null;
        try{
            String line = "";
            fileReader = new BufferedReader(new FileReader(ROOMSFILENAME));
            int count=0;
            rooms = new room[50];
            while ((line = fileReader.readLine()) != null) {
                String[] tok = line.split(COMMA);

                boolean flagStatus=false;
                if(tok[1].equals("true")) flagStatus=true;

                if(tok.length==2){
                    rooms[count]=new room(Integer.parseInt(tok[0]),flagStatus);
                }else{
                    int id=Integer.parseInt(tok[0]);
                    String name=tok[1];
                    int own=Integer.parseInt(tok[2]);
                    rooms[count]=new room(id,flagStatus,own);
                    for (int k=3;k<tok.length ;k++){
                        rooms[count].setFriend(Integer.parseInt(tok[k]));
                    }
                }
                flag=true;
                count++;
            }
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }

        return flag;
    }

    /**
     * Userlari dosyaya yazar.
     * main.User verilerini outputUsers.csv dosyasina yazar.
     * @throws IOException
     */
    private void writeUserFile() throws IOException{
        FileWriter fileWriter = null;
        StringBuilder sb=new StringBuilder();
        try{
            fileWriter = new FileWriter(new File("outputUsers.csv"));
            sb.append(0);sb.append(";");sb.append("admin");
            sb.append(";");sb.append("1234");sb.append('\n');

            for (int i = 1; i < guests.length ; i++) {
                if(guests[i]!=null){
                    int id=guests[i].getUserID();
                    String name=guests[i].getUserName();
                    String pass=guests[i].getUserPassword();
                    sb.append(new Integer(id).toString());sb.append(";");
                    sb.append(name);sb.append(";");
                    sb.append(pass);
                    for(int k=0;k<guests[i].reservedRooms.length;k++){
                        if(!guests[i].reservedRooms[k].equals(-1)) {
                            sb.append(";");
                            sb.append(guests[i].reservedRooms[k]);}
                    }
                    //if(guests[i].reservedRooms.length!=0) sb.deleteCharAt(sb.length()-1);
                    sb.append('\n');
                    //if(guests[i].reservedRoom!=-1)fileWriter.write(";");
                    //fileWriter.write(guests[i].reservedRoom);

                }
            }
            fileWriter.write(sb.toString());
            fileWriter.close();
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }
    }

}
