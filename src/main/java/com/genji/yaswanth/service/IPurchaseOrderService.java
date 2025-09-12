package com.genji.yaswanth.service;

import java.util.List;

import com.genji.yaswanth.model.PurchaseOrder;

public interface IPurchaseOrderService {

	public Integer savePurchaseOrder(PurchaseOrder po);
	
	public List<PurchaseOrder> getAllPurchaseOrders();
	
	public PurchaseOrder getOnePurchaseOrder(Integer id);
	
	public void deletePurchaseOrder(Integer id);
	
	public void updatePurchaseOrder(PurchaseOrder po);
	
}
