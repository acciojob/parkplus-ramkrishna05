//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services.impl;

import com.driver.model.User;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository4;

    public UserServiceImpl() {
    }

    public void deleteUser(Integer userId) {
        this.userRepository4.deleteById(userId);
    }

    public User updatePassword(Integer userId, String password) {
        User user = (User)this.userRepository4.findById(userId).get();
        user.setPassword(password);
        this.userRepository4.save(user);
        return user;
    }

    public void register(String name, String phoneNumber, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setReservationList(new ArrayList());
        this.userRepository4.save(user);
    }
}
