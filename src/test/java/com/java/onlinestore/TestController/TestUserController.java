package com.java.onlinestore.TestController;


import com.java.onlinestore.controllers.UserController;
import com.java.onlinestore.model.User;
import com.java.onlinestore.model.UserRoles;
import com.java.onlinestore.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static com.java.onlinestore.model.UserRoles.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@SpringBootTest(classes = {TestUserController.class})
public class TestUserController {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    List<User> users;
    User user;

    @Test
    void testGetAllUser() throws Exception {

        users=new ArrayList<User>();
        users.add(new User(1,"Dhruv1","dhruv@123",ADMIN));
        users.add(new User(2,"Peter1","peter@123",OWNER));
        users.add(new User(3,"Sam1","sam@123",SELLER));
        users.add(new User(4,"Han1","han@123",CUSTOMER));
        when(userService.getAllUser()).thenReturn(users);

        assertEquals(4,userController.getAllUser().size());
        assertEquals(ADMIN,userController.getAllUser().get(0).getUserRoles());

    }

    @Test
    void testAddCustomer() throws Exception{
        user=new User(4,"Han1","han@123",CUSTOMER);

        when(userService.addCustomer(user)).thenReturn(user);
        assertEquals("han@123",userController.addCustomer(user).getPassword());
    }

    @Test
    void testAddSeller() throws Exception{
        user=new User(3,"Sam1","sam@123",SELLER);

        when(userService.addSeller(user)).thenReturn(user);
        assertEquals(SELLER,userController.addSeller(user).getUserRoles());
    }
    @Test
    void testAddOwner() throws Exception{
        user=new User(2,"Peter1","peter@123",OWNER);

        when(userService.addOwner(user)).thenReturn(user);
        assertEquals(OWNER,userController.addOwner(user).getUserRoles());
    }
}
