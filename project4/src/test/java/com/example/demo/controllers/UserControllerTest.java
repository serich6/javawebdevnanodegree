package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest extends TestCase {
    private UserController userController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp() {
        userController = new UserController();
        TestUtils.injectObjects(userController, "userRepository", userRepository);
        TestUtils.injectObjects(userController, "cartRepository", cartRepository);
        TestUtils.injectObjects(userController, "bCryptPasswordEncoder", encoder);

        User user = new User();
        user.setId(1);
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(user));
    }

    // From course example
    @Test
    public void testCreateUserPositive() {
        when(encoder.encode("pwd1234")).thenReturn("thisIsHashed");
        CreateUserRequest req = new CreateUserRequest();
        req.setUsername("test");
        req.setPassword("pwd1234");
        req.setConfirmedPassword("pwd1234");
        final ResponseEntity<User> r = userController.createUser(req);

        assertNotNull(r);
        assertEquals(200, r.getStatusCodeValue());

        User u = r.getBody();
        assertNotNull(u);
        assertEquals(0, u.getId());
        assertEquals("test", u.getUsername());
        assertEquals("thisIsHashed", u.getPassword());
    }

    @Test
    public void testFindByIdPositive() {
        ResponseEntity<User> response = userController.findById(2L);
        assertEquals(200, response.getStatusCodeValue());
        User u = response.getBody();
        assertEquals(1, u.getId());
    }

//    @Test
//    public void testFindByUserNamePositive() {
//        when(encoder.encode("pwd1234")).thenReturn("thisIsHashed");
//        CreateUserRequest req = new CreateUserRequest();
//        req.setUsername("test2");
//        req.setPassword("pwd1234");
//        req.setConfirmedPassword("pwd1234");
//        final ResponseEntity<User> r = userController.createUser(req);
//
//        ResponseEntity<User> response = userController.findByUserName("test2");
//        assertEquals(200, response.getStatusCodeValue());
//        User u = response.getBody();
//        assertEquals(0, u.getId());
//    }

    @Test
    public void testShortPassword() {
        CreateUserRequest req = new CreateUserRequest();
        req.setUsername("test");
        req.setPassword("pwd");
        req.setConfirmedPassword("pwd");
        final ResponseEntity<User> r = userController.createUser(req);
        assertEquals(400, r.getStatusCodeValue());
    }

    @Test
    public void testPasswordsMismatch() {
        CreateUserRequest req = new CreateUserRequest();
        req.setUsername("test");
        req.setPassword("pwd");
        req.setConfirmedPassword("pwd1");
        final ResponseEntity<User> r = userController.createUser(req);
        assertEquals(400, r.getStatusCodeValue());
    }
}