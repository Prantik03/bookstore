package com.bookstore.demo.service;

import com.bookstore.demo.model.*;
import com.bookstore.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
@Service
@Transactional
public class genServices implements GS{

    @Autowired
    private genRepo generalRepository;

    @Autowired
    private reviewRepo reviewRepository;

    @Autowired
    private userRepo userRepository;

    @Autowired
    private bookRepo bookRepository;

    @Autowired
    private bookcopyRepo bookCopiesRepository;

    @Autowired
    private rentRepo rentedRepository;

    @Override
    public ResponseEntity<HttpStatus> returnBook(int rentedId){

        Optional<rent> rented = rentedRepository.findById(rentedId);
        rent r=rented.get();

        if(rented.isPresent())
        {
            Optional<book> b = bookRepository.findById(Long.valueOf(r.getbId()));
            book book=b.get();
            double rent_amount=0.1*(double)book.getPrice();
            double security_deposit=0.2*(double)book.getPrice();

            Optional<BookCopies> bc=bookCopiesRepository.findById(r.getbId());
            BookCopies bookCopies=bc.get();

            Optional<User> u=userRepository.findById(Long.valueOf(r.getuId()));
            User user=u.get();

            user.setWallet((int) (user.getWallet()+security_deposit-rent_amount));
            bookCopies.setCopies(bookCopies.getCopies()+1);
            user.setBooksRented(user.getBooksRented()-1);

            r.setReturnDateTime(LocalDateTime.now());

            generalRepository.save(r);
            bookCopiesRepository.save(bookCopies);
            userRepository.save(user);


        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<HttpStatus> borrowBook(int uId, int bookId){

        //System.out.println(uId + " " + bookId);
        Optional<User> u = userRepository.findById(Long.valueOf(uId));
        Optional<book> b = bookRepository.findById(Long.valueOf(bookId));
        Optional<BookCopies> bc=bookCopiesRepository.findById(bookId);

        User user=u.get();

        book book=b.get();
        BookCopies bookCopies=bc.get();

        rent r=new rent();
        if(u.isPresent() && b.isPresent())
        {
            double minimum_balance=0.3*(double)(book.getPrice());

            if(bookCopies.getCopies()>0 && minimum_balance<user.getWallet() && user.getBooksRented()<3 && user.isStatus())
            {
                bookCopies.setCopies(bookCopies.getCopies()-1);
                user.setWallet(user.getWallet()-(int)(0.2*(double)(book.getPrice())));
                user.setBooksRented(user.getBooksRented()+1);

                r.setuId(user.getuId());
                r.setbId(book.getbId());


                generalRepository.save(r);
                bookCopiesRepository.save(bookCopies);
                userRepository.save(user);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<HttpStatus> reviewBook(int uId, int bookId, String review) {

        Optional<User> u = userRepository.findById(Long.valueOf(uId));
        Optional<book> b = bookRepository.findById(Long.valueOf(bookId));
        User user=u.get();
        book book=b.get();
        Review r=new Review();
        if(u.isPresent() && b.isPresent())
        {
            if(ifRented(uId,bookId)) {
                r.setuId(user.getuId());
                r.setbId(book.getbId());
                r.setReview(review);
                reviewRepository.save(r);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<HttpStatus> likeBook(int uId, int bookId) {
        Optional<User> u = userRepository.findById(Long.valueOf(uId));
        Optional<book> b = bookRepository.findById(Long.valueOf(bookId));
        //User user=u.get();
        book book=b.get();
        if(u.isPresent() && b.isPresent())
        {
            if(ifRented(uId,bookId)) {
                book.setLikes(book.getLikes() + 1);
                bookRepository.save(book);
                return new ResponseEntity<>(HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean ifRented(int uId, int bookId) {
        Optional<User> u = userRepository.findById(Long.valueOf(uId));
        Optional<book> b = bookRepository.findById(Long.valueOf(bookId));
        if(u.isPresent() && b.isPresent()){
            List<rent> list= new ArrayList<>();
            list=this.rentedRepository.findAll();
            for (rent r:list) {
                if(r.getuId()==uId && r.getbId()==bookId){
                    return true;
                }
            }
            return false;

        }


        return false;
    }

//    @Override
//    public ResponseEntity<List<Book>> availableBooks() {
//        List<Book> list= new ArrayList<>();
//        list=this.bookRepository.findAll();
//        return (ResponseEntity<List<Book>>) list;
//    }
//    @Query(value = "select r from rented r where user_id=?1")
//     ResponseEntity<List<Rented>> showTransactions(int uId){
//
//    };



};