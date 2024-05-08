package com.example.mobappproject;

public class Lending {
    private int id;
    private int memberId;
    private int bookId;
    private String lendingDate;
    private String returnDate;

    public Lending(int id, int memberId, int bookId, String lendingDate, String returnDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.lendingDate = lendingDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(String lendingDate) {
        this.lendingDate = lendingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Lending{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", lendingDate='" + lendingDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}

