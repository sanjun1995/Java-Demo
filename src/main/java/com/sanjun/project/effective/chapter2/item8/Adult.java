package com.sanjun.project.effective.chapter2.item8;

/**
 * Created by caozhixin on 2019-08-23 17:53
 */
public class Adult {
    public static void main(String[] args) {
        try (Room myRoom = new Room(7)) {
            System.out.println("Goodbye");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
