package com.example.demo.service;

import com.example.demo.dto.OrderManagamentDto;

public interface OrderManagmentService {

	public void manageOrder(OrderManagamentDto dto);

	public void deleteOrder(int cId);

}
