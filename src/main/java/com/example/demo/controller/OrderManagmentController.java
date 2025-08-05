package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderManagamentDto;
import com.example.demo.service.OrderManagmentService;

@RestController
public class OrderManagmentController {

	@Autowired
	OrderManagmentService managmentService;

	@PostMapping("/orders")
	public ResponseEntity addCustomerOrder(@RequestBody OrderManagamentDto dto) {

		managmentService.manageOrder(dto);

		return new ResponseEntity("Order placed successfully", HttpStatus.OK);

	}
	
	
	
	@DeleteMapping("order/{cid}")
	public ResponseEntity deleteOrder(@PathVariable int cid) {

		managmentService.deleteOrder(cid);

		return new ResponseEntity(HttpStatus.OK);

	}

}
