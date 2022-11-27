package com.bookstore.demo.service;


import com.bookstore.demo.exception.userException;
import com.bookstore.demo.model.User;
import com.bookstore.demo.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class userServices implements US {
    @Autowired
    private userRepo userRepository;


    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User suspendUser(User user){
        Optional<User> u=this.userRepository.findById(Long.valueOf(user.getuId()));
        if(u.isPresent()){
            User userUpdate=u.get();
            userUpdate.setStatus(false);
            return this.userRepository.save(userUpdate);
        }else{
            throw new userException("User not found with id: "+user.getuId());
        }
    };

    @Override
    public User updateUser(User user){
        Optional<User> u=this.userRepository.findById(Long.valueOf(user.getuId()));
        if(u.isPresent()){
            User userUpdate=u.get();
            userUpdate.setuId(user.getuId());
            userUpdate.setUsername(user.getUsername());
            userUpdate.setMail(user.getMail());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setWallet(user.getWallet());
            userUpdate.setStatus(user.isStatus());
            return this.userRepository.save(userUpdate);
        }else{
            throw new userException("User not found with id: "+user.getuId());
        }
    };

    @Override
    public User addMoney(User user){
        Optional<User> u=this.userRepository.findById(Long.valueOf(user.getuId()));
        if(u.isPresent()){
            User userUpdate=u.get();
            if(user.getWallet() > 0 && user.getWallet() % 500 == 0)
                userUpdate.setWallet(user.getWallet() + u.get().getWallet());
            return this.userRepository.save(userUpdate);
        }else{
            throw new userException("User not found with id: "+user.getuId());
        }

    }


}