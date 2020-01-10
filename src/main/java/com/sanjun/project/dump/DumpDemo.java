package com.sanjun.project.dump;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caozhixin on 2019-12-07 16:46
 */
public class DumpDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        while (true) {
            students.add(new Student("sanjun", 25));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
