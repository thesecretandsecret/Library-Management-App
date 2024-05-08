package com.example.mobappproject;

public class Member {
    private int id;
    private String name;
    private int bookId;

    public Member() {
    }

    public Member(int id, String name, int bookId) {
        this.id = id;
        this.name = name;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}

