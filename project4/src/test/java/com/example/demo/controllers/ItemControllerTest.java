package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest extends TestCase {
    private ItemController itemController;
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp() {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);

        Item i = new Item();
        i.setId(0L);
        i.setName("wingding");
        i.setDescription("thingamabob");
        i.setPrice(BigDecimal.valueOf(2.00));

        // mocking whens
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(i));
        // Create a list for this return, since it's not a single obj
        List<Item> list = new ArrayList<>();
        Item i2 = new Item();
        i2.setId(0L);
        i2.setName("wingding");
        i2.setDescription("thingamabob");
        i2.setPrice(BigDecimal.valueOf(2.00));
        list.add(i2);
        when(itemRepository.findByName("testGetByName")).thenReturn(list);
    }

    @Test
    public void testGetItems() {
        ResponseEntity<List<Item>> i = itemController.getItems();
        assertNotNull(i);
        assertEquals(200, i.getStatusCodeValue());
    }

    @Test
    public void testGetItemById() {
        ResponseEntity<Item> i = itemController.getItemById(0L);
        assertEquals(200, i.getStatusCodeValue());
    }

    @Test
    public void testGetItemsByName() {
        ResponseEntity<List<Item>> i = itemController.getItemsByName("testGetByName");
        assertNotNull(i);
        assertEquals(200, i.getStatusCodeValue());
    }
}