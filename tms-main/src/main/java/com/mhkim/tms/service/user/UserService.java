package com.mhkim.tms.service.user;

import com.mhkim.tms.entity.user.User;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userIdx) {
        return userRepository.findById(userIdx)
                .orElseThrow(() -> new NotFoundException(User.class, userIdx));
    }

    @Transactional
    public User addUser(User userRequest) {
        userRepository.findByEmail(userRequest.getEmail())
                .ifPresent(user -> {
                    throw new AlreadyExistsException(User.class, userRequest.getEmail());
                });

        return save(userRequest);
    }

    @Transactional
    public User updateUser(Long userIdx, String name) {
        return userRepository.findById(userIdx)
                .map(user -> {
                    user.updateUser(name);
                    return save(user);
                })
                .orElseThrow(() -> new NotFoundException(User.class, userIdx));
    }

    @Transactional
    public User deleteUser(Long userIdx) {
        return userRepository.findById(userIdx)
                .map(user -> {
                    userRepository.deleteById(user.getUserIdx());
                    return user;
                })
                .orElseThrow(() -> new NotFoundException(User.class, userIdx));
    }

    private User save(User user) {
        return userRepository.save(user);
    }

}
