package com.bookstore.demo.service;


import com.bookstore.demo.exception.userException;
import com.bookstore.demo.model.book;
import com.bookstore.demo.model.BookCopies;
import com.bookstore.demo.model.User;
import com.bookstore.demo.repository.bookcopyRepo;
import com.bookstore.demo.repository.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BookServices implements BS{

    @Autowired
    private bookRepo bookRepository;

    @Autowired
    private bookcopyRepo bookCopiesRepository;
    @Override
    public book addBook(book book) {

        return bookRepository.save(book);
    }

    @Override
    public ResponseEntity<HttpStatus> addCopies(BookCopies bc) {
        Optional<BookCopies> b=this.bookCopiesRepository.findById(bc.getbId());
        if(b.isPresent()){
            BookCopies copiesAdd=b.get();
            copiesAdd.setCopies(b.get().getCopies()+bc.getCopies());

            bookCopiesRepository.save(copiesAdd);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            bookCopiesRepository.save(bc);
            return new ResponseEntity<>(HttpStatus.OK);

        }


    }
}