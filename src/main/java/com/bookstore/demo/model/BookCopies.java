package com.bookstore.demo.model;

import javax.persistence.*;

@Entity
@Table(name="bookcount")
public class BookCopies {


//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id", nullable=false)
//    private int id;

    @Id
    @Column(name="book_id", nullable=false, unique = true)
    private int bId;

    @Column(name = "book_copies", nullable=false)
    private int copies;


    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}