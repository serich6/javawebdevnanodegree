package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest extends TestCase {
    private CartController cartController;
    private UserController userController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp() {
        cartController = new CartController();
        TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
        TestUtils.injectObjects(cartController, "userRepository", userRepository);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepository);

        // Create a user for each test
        User u = new User();
        u.setId(1);
        u.setUsername("test");
        u.setPassword("pwd1234");
        u.setCart(new Cart());
        when(userRepository.findByUsername("test")).thenReturn(u);

        // stage an item
        Item i = new Item();
        i.setId(0L);
        i.setName("wingding");
        i.setPrice(BigDecimal.valueOf(2.99));
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(i));
    }

    @Test
    public void testAddTocart() {
        ResponseEntity<Cart> response = addItemToCart();
        Cart cart = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(BigDecimal.valueOf(8.97), cart.getTotal());
    }

    @Test
    public void testRemoveFromcart() {
        ResponseEntity<Cart> response = addItemToCart();

        // remove item
        ModifyCartRequest cartRequest1 = new ModifyCartRequest();
        cartRequest1.setItemId(0L);
        cartRequest1.setUsername("test");
        cartRequest1.setQuantity(2);
        response = cartController.removeFromcart(cartRequest1);
        assertEquals(200, response.getStatusCodeValue());
        Cart c = response.getBody();
        assertEquals(BigDecimal.valueOf(2.99), c.getTotal());
    }

    public ResponseEntity<Cart> addItemToCart() {
        // add the item to the cart first
        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(0L);
        cartRequest.setUsername("test");
        cartRequest.setQuantity(3);
        return cartController.addTocart(cartRequest);
    }
}