package com.genji.yaswanth.service;

import java.util.List;

import com.genji.yaswanth.model.OrderMethod;

public interface IOrderMethodService {

	public Integer save(OrderMethod om);
	public List<OrderMethod> getAllOrderMethods();
	public void deleteOrderMethod(Integer id);
	public void updateOrderMethod(OrderMethod om);
	public OrderMethod getOneOrderMethod(Integer id);
	
}