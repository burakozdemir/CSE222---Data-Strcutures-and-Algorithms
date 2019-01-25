package main;

import java.util.Scanner;

/**
 * Müsterinin otomasyonda yapabileceklerini gosteren arayuz.
 * @author Burak Ozdemir 141044027
 */

public interface guestSkills {

    /**
     * Musteri odayı rezerv eder.
     * @param Roomid
     * @return true or false.
     */
    public String bookRoom(int Roomid, Scanner scan);

    /**
     * MUsteri rezervasyonu iptal eder.
     * @param RoomID
     * @return true or false.
     */
    public Integer[] cancelReservation(int RoomID);

}
