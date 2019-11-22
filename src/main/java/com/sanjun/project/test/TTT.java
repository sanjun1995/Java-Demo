package com.sanjun.project.test;

import lombok.Data;

/**
 * Created by caozhixin on 2019-11-22 16:13
 */
@Data
public class TTT {
    private int code;
    private String msg;

    public TTT(int code) {
        this.code = code;
    }
}
