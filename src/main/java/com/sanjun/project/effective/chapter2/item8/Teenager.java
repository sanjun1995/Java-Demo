package com.sanjun.project.effective.chapter2.item8;

/**
 * Created by caozhixin on 2019-08-23 18:20
 */
// Ill-behaved client of resource with cleaner safety-net (Page 33)
public class Teenager {
    public static void main(String[] args) {
        new Room(99);
        System.out.println("Peace out");

        // Uncomment next line and retest behavior, but note that you MUST NOT depend on this behavior!
//      System.gc();
    }
}
