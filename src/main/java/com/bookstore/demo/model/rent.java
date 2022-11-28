package com.bookstore.demo.model;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="rented")
public class rent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rented_id", nullable=false)
    private int rentedId;
    @Column(name="book_id", nullable=false)
    private int bId;

    @Column(name="user_id", nullable=false)
    private int uId;

    @CreationTimestamp
    @Column(name="issueDateTime", columnDefinition = "TIMESTAMP", nullable=false)
    private Date issueDateTime;

    @Column(name="returnDateTime", columnDefinition = "TIMESTAMP")
    private Date returnDateTime;

//    @Column(name="amount", nullable=false)
//    private int amount;

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Date getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(Date issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public Date getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
}