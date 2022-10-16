package com.ohmycode.todolist.services;

import com.ohmycode.todolist.models.entities.User;
import com.ohmycode.todolist.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public User insert(User user) {
        return userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
