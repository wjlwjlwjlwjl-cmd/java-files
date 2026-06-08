package com.example.demo.classes;

import lombok.Data;

/*
 * from
 * to
 * msg
 */

@Data
public class MsgInfo {
    private String from;
    private String to;
    private String msg;

    public MsgInfo(String from, String to, String msg) {
        this.from = from;
        this.to = to;
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMsg() {
        return msg;
    }
}
