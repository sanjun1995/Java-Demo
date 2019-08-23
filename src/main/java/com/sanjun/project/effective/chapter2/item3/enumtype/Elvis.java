package com.sanjun.project.effective.chapter2.item3.enumtype;

/**
 * Created by caozhixin on 2019-08-23 15:48
 */
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
