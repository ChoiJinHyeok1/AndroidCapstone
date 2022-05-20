package com.example.capstone;

import com.google.firebase.Timestamp;

//게시글
public class PostInfo {
    private String title;
    private String contents;
    private String publisher;
    private Timestamp createdAt;
    private Integer likecnt;
    private String postId;


    public PostInfo(){}


    public PostInfo(String title, String contents, String publisher, Timestamp createdAt, Integer likecnt, String postId) {
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.likecnt = likecnt;
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Integer getLikecnt() {
        return likecnt;
    }
    public void setLikecnt(Integer likecnt) {
        this.likecnt = likecnt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
