package com.example.capstone;

public class MessageItem {
    String msg;
    String uid;

    public MessageItem(String msg, String uid) {
        this.msg = msg;
        this.uid = uid;
    }

    public MessageItem() {}

    public String getMsg() {
        return msg;
    }

    public void SetMsg(String msg) {
        this.msg = msg;
    }

    public String getUid() { return uid; }

    public void SetUid(String uid) { this.uid = uid; }
}
