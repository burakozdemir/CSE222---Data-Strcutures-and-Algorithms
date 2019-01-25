package main;

import java.util.Scanner;

/**
 * Görevli personelin otomasyonda yapabileceklerini gosteren arayuz.
 * @author Burak Ozdemir 141044027
 */
public interface receptionistSkills {
    /**
     * Musterinin odadan cikisini yapar.
     * @param name
     * @param Roomid
     * @param scan
     * @return true or false
     */
    public boolean bookaRoom(int Roomid,String name,Scanner scan);

    /**
     * Otele musteri kabul eder.
     * @param name
     * @param password
     * @return true or false
     */
    public boolean checkIn(String name,String password,int UserID);

    /**
     * Kullanici siler.
     * @param guestID
     * @return true or false
     */
    public boolean checkOut(int guestID);

}
