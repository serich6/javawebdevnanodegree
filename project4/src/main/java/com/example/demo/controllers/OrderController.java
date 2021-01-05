package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@PostMapping("/submit/{username}")
	public ResponseEntity<UserOrder> submit(@PathVariable String username) {
		logger.info("SUBMIT_ORDER_REQUEST_INITIATED: Attempting to submit order for user: " + username);
		User user = userRepository.findByUsername(username);
		if(user == null) {
			logger.error("ERROR: User not found during order submission");
			return ResponseEntity.notFound().build();
		}
		UserOrder order = UserOrder.createFromCart(user.getCart());
		orderRepository.save(order);
		logger.info("SUBMIT_ORDER_REQUEST_SUCCESS: Order created with username: " + username);
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/history/{username}")
	public ResponseEntity<List<UserOrder>> getOrdersForUser(@PathVariable String username) {
		logger.info("GET_ORDER_REQUEST_INITIATED: Attempting to find orders for user: " + username);
		User user = userRepository.findByUsername(username);
		if(user == null) {
			logger.error("ERROR: User not found during order history pull");
			return ResponseEntity.notFound().build();
		}
		logger.info("GET_ORDER_HISTORY_REQUEST_SUCCESS: Order history fetched with username: " + username);
		return ResponseEntity.ok(orderRepository.findByUser(user));
	}
}
