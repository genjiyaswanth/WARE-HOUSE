package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.OrderMethodNotFoundException;
import com.genji.yaswanth.model.OrderMethod;
import com.genji.yaswanth.repository.OrderMethodRepository;

@Service
public class OrderMethodImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo;
	
	@Override
	public Integer save(OrderMethod ord) {
		Integer id = repo.save(ord).getId();
		return id;
	}

	@Override
	public List<OrderMethod> getAllOrderMethods() {
		List<OrderMethod> list=repo.findAll();
		return list;
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		OrderMethod ord= getOneOrderMethod(id);
		repo.delete(ord);
	}

	@Override
	public void updateOrderMethod(OrderMethod ord) {
		
		repo.save(ord);
	}

	@Override
	public OrderMethod getOneOrderMethod(Integer id) {
		Optional<OrderMethod> opt = repo.findById(id);
		OrderMethod ord=null;
		if(opt.isPresent()) {
			ord= opt.get();
		}
		else {
			throw new OrderMethodNotFoundException("Order with id "+id+" not exists");
		}
		return ord;
	}

}
