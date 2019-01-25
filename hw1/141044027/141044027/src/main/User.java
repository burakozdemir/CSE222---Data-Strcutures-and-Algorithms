package main;

/**
 * ---Simple Hotel main.Automation----
 * main.User Abstract Class
 *
 * @author Burak Ozdemir 141044027
 */
public  abstract class User {

    private String userName;
    private String userPassword;
    private int userID;


    /**
     * Constructor.
     * @param ID
     * @param name
     * @param password
     */
    public User(int ID,String name ,String password){
        this.userID=ID;
        this.userName=name;
        this.userPassword=password;
    }


    /**
     * Constructor
     */
    public User(){
        this(0,null,null);
    }

    /**
     * UserName getter
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * UserName setter
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * UserPassword getter
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * UserPassword setter
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * UserID getter
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * UserID setter
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * klaslar kendisine gore implement edicekler.
     */
    public void userMenu(){}

}
