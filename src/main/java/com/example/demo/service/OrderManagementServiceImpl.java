package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderManagamentDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.OrderRepo;

@Service
public class OrderManagementServiceImpl implements OrderManagmentService {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	OrderRepo orderRepo;

	@Override
	public void manageOrder(OrderManagamentDto dto) {

		// Step 1: Create a new Customer object and set name, address, and orders from
		// DTO
		Customer c = new Customer();
		c.setName(dto.getName());
		c.setAddress(dto.getAddress());
		c.setList(dto.getList()); // list contains order data

		// Step 2: Save the customer in the database
		Customer savedCustomer = customerRepo.save(c);

		// Step 3: Get the list of orders from the saved customer
		List<Order> l = savedCustomer.getList();

		// Step 4: Set the customer for each order and save the order
		for (Order o : l) {
			o.setCustomer(savedCustomer); // Set the relationship (foreign key)
			orderRepo.save(o); // Save each order in the order table
		}

		// At the end, customer and its orders are stored properly with relationship.
	}

	@Override
	public void deleteOrder(int cId) {
	    Customer c = customerRepo.findById(cId).get();

	    List<Order> copyList = new ArrayList<>(c.getList());

	    for (Order o : copyList) {
	        if ("failed".equals(o.getStatus())) {
	            c.getList().remove(o);
	        }
	    }

	    customerRepo.save(c);
	}


}
