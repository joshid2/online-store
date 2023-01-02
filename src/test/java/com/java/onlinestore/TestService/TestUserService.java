package com.java.onlinestore.TestService;

import com.java.onlinestore.model.User;
import com.java.onlinestore.model.UserRoles;
import com.java.onlinestore.repository.UserRepository;
import com.java.onlinestore.services.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.java.onlinestore.model.UserRoles.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {TestUserService.class})
public class TestUserService {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @Order(1)
    public void testAddCustomer() {


        User userTest = new User(1, "DhruvJoshi", "dhruv@gmail.com", null);

        when(userRepository.save(userTest)).thenReturn(userTest);
        assertEquals("DhruvJoshi", userService.addCustomer(userTest).getUsername());
        assertEquals(CUSTOMER, userService.addCustomer(userTest).getUserRoles());

    }

    @Test
    @Order(2)
    public void testAddOwner() {
        User user = new User(2, "Tom", "tom@123", null);

        when(userRepository.save(user)).thenReturn(user);
        assertEquals(OWNER, userService.addOwner(user).getUserRoles());

    }

    @Test
    @Order(3)
    public void testAddSeller() {
        User user = new User(3, "Rob", "Rob@123", null);

        when(userRepository.save(user)).thenReturn(user);
        assertEquals("Rob@123", userService.addSeller(user).getPassword());
        assertEquals(SELLER, userService.addSeller(user).getUserRoles());
    }

    @Test
    @Order(4)
    public void testGetAllUser() {
        List<User> user = new ArrayList<User>();
        user.add(new User(1, "Peter", "Peter@123", ADMIN));
        user.add(new User(2, "Peter1", "Peter1@123", CUSTOMER));
        user.add(new User(3, "Peter2", "Peter2@123", OWNER));
        user.add(new User(4, "Peter3", "Peter3@123", SELLER));
        when(userRepository.findAll()).thenReturn(user);
        assertEquals(4, userService.getAllUser().size());


    }
}
