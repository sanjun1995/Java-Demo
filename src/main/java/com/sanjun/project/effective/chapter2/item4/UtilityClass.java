package com.sanjun.project.effective.chapter2.item4;

/**
 * Created by caozhixin on 2019-08-23 16:10
 */

// Noninstantiable utility
public class UtilityClass {
    // Suppress default constructor for noninstantiability
    private UtilityClass() {
        throw new AssertionError();
    }
    // Remainder omitted
}
