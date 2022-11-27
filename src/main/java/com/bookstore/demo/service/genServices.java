package com.bookstore.demo.service;


import com.bookstore.demo.exception.userException;
import com.bookstore.demo.model.book;
import com.bookstore.demo.model.BookCopies;
import com.bookstore.demo.model.rent;
import com.bookstore.demo.model.User;
import com.bookstore.demo.repository.bookcopyRepo;
import com.bookstore.demo.repository.bookRepo;
import com.bookstore.demo.repository.userRepo;
import com.bookstore.demo.repository.genRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class genServices implements GS{

    @Autowired
    private genRepo generalRepository;

    @Autowired
    private userRepo userRepository;

    @Autowired
    private bookRepo bookRepository;

    @Autowired
    private bookcopyRepo bookCopiesRepository;

    @Override
    public ResponseEntity<HttpStatus> borrowBook(int uId, int bookId){

        System.out.println(uId + " " + bookId);
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

                //bookCopies.setCopies(bookCopies.getCopies()-1);

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
        return null;
    }



}
