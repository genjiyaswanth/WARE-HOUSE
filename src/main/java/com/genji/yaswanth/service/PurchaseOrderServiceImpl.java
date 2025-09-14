package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genji.yaswanth.exceptions.PurchaseDetailsNotFoundException;
import com.genji.yaswanth.exceptions.PurchaseOrderNotFoundException;
import com.genji.yaswanth.model.PurchaseDetails;
import com.genji.yaswanth.model.PurchaseOrder;
import com.genji.yaswanth.repository.PurchaseDetailsRepository;
import com.genji.yaswanth.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;
	
	@Autowired
	private PurchaseDetailsRepository detailsRepo;
	
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
		return po;
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

	@Override
	public Integer savePurchaseDetails(PurchaseDetails pd) {
		detailsRepo.save(pd);
		return pd.getId();
	}

	@Override
	public List<PurchaseDetails> getPurchaseDetailsByPurchaseOrderId(Integer orderId) {
		
		return detailsRepo.getPurchaseDeatailsByPurchaseOrderId(orderId);
	}

	@Override
	public void removePurchaseDetails(Integer id) {
		
		PurchaseDetails pd = getOnePart(id);
		detailsRepo.delete(pd);
		
	}

	@Override
	public PurchaseDetails getOnePart(Integer id) {
		Optional<PurchaseDetails> opt = detailsRepo.findById(id);
		PurchaseDetails pd = null;
		if(opt.isPresent()) {
			pd=opt.get();
		}
		else {
			throw new PurchaseDetailsNotFoundException("Puchase Details with id:"+id+" not exist");
		}
		return pd;
	}

	@Transactional
	@Override
	public void updateStatus(Integer purchaseOrderId, String status) {
		
		repo.updateStatus(purchaseOrderId,status);
		
	}

	@Override
	public Integer getDetailsCountByOrderId(Integer purchaseorderid) {
		
		return detailsRepo.getDetailsCountByOrderId(purchaseorderid);
	}

}
