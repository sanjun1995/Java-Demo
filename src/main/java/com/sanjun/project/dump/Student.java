package com.sanjun.project.dump;

import lombok.Data;

/**
 * Created by caozhixin on 2019-12-07 16:50
 */
@Data
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
