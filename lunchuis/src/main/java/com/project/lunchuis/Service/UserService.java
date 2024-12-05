package com.project.lunchuis.Service;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.User;
import com.project.lunchuis.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void addPurchase(Long userId, Buy buy) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            u.getPurchases().add(buy);
            userRepository.save(u);
        });
    }

    public Optional<User> authenticate(String code, String password) {
        return userRepository.findByCodeAndPassword(code, password);
    }
}