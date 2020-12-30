package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
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

public class OrderControllerTest extends TestCase {
    private OrderController orderController;
    private OrderRepository orderRepository = mock(OrderRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);

    @Before
    public void setUp() {
        orderController = new OrderController();
        TestUtils.injectObjects(orderController, "userRepository", userRepository);
        TestUtils.injectObjects(orderController, "orderRepository", orderRepository);
        // Create a user for each test
        User u = new User();
        u.setId(1);
        u.setUsername("test");
        u.setPassword("pwd1234");
        u.setCart(new Cart());
        when(userRepository.findByUsername("test")).thenReturn(u);

        // create an item
        Item i = new Item();
        i.setId(0L);
        i.setName("wingding");
        i.setPrice(BigDecimal.valueOf(2.99));

        // Add an item to the cart
        Cart c = new Cart();
        c.setId(0L);
        c.setUser(u);
        List<Item> list = new ArrayList<>();
        list.add(i);
        c.setItems(list);
        c.setTotal(BigDecimal.valueOf(2.99));

        // forgot to add the cart back to the user o_0
        u.setCart(c);
    }

    @Test
    public void testSubmit() {
        ResponseEntity<UserOrder> order = orderController.submit("test");
        assertEquals(200, order.getStatusCodeValue());
    }

    @Test
    public void testGetOrdersForUser() {
        ResponseEntity<List<UserOrder>> order = orderController.getOrdersForUser("test");
        assertEquals(200, order.getStatusCodeValue());
    }
}