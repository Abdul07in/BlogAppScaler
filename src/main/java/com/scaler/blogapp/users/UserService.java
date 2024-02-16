package com.scaler.blogapp.users;

import com.scaler.blogapp.users.dtos.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserRequest cur) {
        var newUser = UserEntity.builder()
                .username(cur.getUsername())
//                .password(password) // TODO : Encrypt password
                .email(cur.getEmail())
                .build();
        return userRepository.save(newUser);
    }


    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        // TODO : Check password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("Username : " + username + " ,not found...");
        }

        public UserNotFoundException(Long authorId) {
            super("User Id : " + authorId + " ,not found...");
        }
    }


}
