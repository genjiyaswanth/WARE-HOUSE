package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.PurchaseOrderNotFoundException;
import com.genji.yaswanth.model.PurchaseOrder;
import com.genji.yaswanth.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;
	
	@Override
	public Integer savePurchaseOrder(PurchaseOrder po) {
		po=repo.save(po);
		return po.getId();
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		
		return repo.findAll();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		Optional<PurchaseOrder> opt = repo.findById(id);
		PurchaseOrder po = null;
		if(opt.isPresent()) {
			po=opt.get();
		}
		else {
			throw new PurchaseOrderNotFoundException("Purchase Order '"+id+"' not exist");
		}
		return null;
	}

	@Override
	public void deletePurchaseOrder(Integer id) {
		PurchaseOrder po = getOnePurchaseOrder(id);
		repo.delete(po);

	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder po) {
		repo.save(po);

	}

}
