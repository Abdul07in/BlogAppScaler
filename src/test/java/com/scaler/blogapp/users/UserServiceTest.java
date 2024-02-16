package com.scaler.blogapp.users;

import com.scaler.blogapp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void can_create_user() {
        var user = userService.createUser(new CreateUserRequest("Majeed07", "pass123", "abdulkanoor@gmail.com"));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Majeed07" , user.getUsername());
        Assertions.assertEquals("john" , user.getUsername());
    }
}
