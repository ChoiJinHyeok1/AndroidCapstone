package com.example.capstone.Info;

import com.google.firebase.Timestamp;

public class CommentInfo {

    private String cpublisher;
    private String ccontents;
    private Timestamp ccreatedAt;
    private Integer clikecnt;


    public CommentInfo(String ccontents, Timestamp ccreatedAt) {
        this.ccontents = ccontents;
        this.ccreatedAt = ccreatedAt;
    }

    public CommentInfo(String cpublisher, String ccontents, Timestamp ccreatedAt, Integer clikecnt) {
        this.cpublisher = cpublisher;
        this.ccontents = ccontents;
        this.ccreatedAt = ccreatedAt;
        this.clikecnt = clikecnt;
    }

    public String getCpublisher() {
        return cpublisher;
    }

    public void setCpublisher(String cpublisher) {
        this.cpublisher = cpublisher;
    }

    public String getCcontents() {
        return ccontents;
    }

    public void setCcontents(String ccontents) {
        this.ccontents = ccontents;
    }

    public Timestamp getCcreatedAt() {
        return ccreatedAt;
    }

    public void setCcreatedAt(Timestamp ccreatedAt) {
        this.ccreatedAt = ccreatedAt;
    }

    public Integer getClikecnt() {
        return clikecnt;
    }

    public void setClikecnt(Integer clikecnt) {
        this.clikecnt = clikecnt;
    }
}
