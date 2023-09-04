package com.hai818i.tp4.controllers;

import com.hai818i.tp4.models.User;
import com.hai818i.tp4.repositories.LocationRepository;
import com.hai818i.tp4.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class UsersControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private UsersController userController;

    @Test
    public void testSetAndGetFirstName() {
        User user = new User();
        assertNull(user.getFirst_name());
        user.setFirst_name("John");
        assertEquals("John", user.getFirst_name());
    }

    @Test
    public void testSetAndGetEmail() {
        User user = new User();
        assertNull(user.getEmail());
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testSetAndGetPhoneNumber() {
        User user = new User();
        assertNull(user.getPhone_number());
        user.setPhone_number("+1234567890");
        assertEquals("+1234567890", user.getPhone_number());
    }

    @Test
    public void testSetAndGetPassword() {
        User user = new User();
        assertNull(user.getPassword());
        user.setPassword("secret");
        assertEquals("secret", user.getPassword());
    }

}