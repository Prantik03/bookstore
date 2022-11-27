package com.bookstore.demo.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false)
    private int uId;

    @Column(name="user_name", unique = true)
    //not blank annotation
    private String username;

    @Column(name="email", unique = true)
    private String mail;

    @Column(name="phone", nullable = false, unique = true)
    private long phone;

    @Column(name="wallet")
    private int wallet;

    @Column(name="user_status", nullable = false)
    private boolean status;

    @Column(name="books_rented")
    //@JsonIgnore
    private int booksRented;


    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBooksRented() {
        return booksRented;
    }

    public void setBooksRented(int booksRented) {
        this.booksRented = booksRented;
    }
}