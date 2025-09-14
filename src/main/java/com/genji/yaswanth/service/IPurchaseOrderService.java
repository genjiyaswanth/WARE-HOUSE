package com.genji.yaswanth.service;

import java.util.List;

import com.genji.yaswanth.model.PurchaseDetails;
import com.genji.yaswanth.model.PurchaseOrder;

public interface IPurchaseOrderService {

	public Integer savePurchaseOrder(PurchaseOrder po);
	
	public List<PurchaseOrder> getAllPurchaseOrders();
	
	public PurchaseOrder getOnePurchaseOrder(Integer id);
	
	public void deletePurchaseOrder(Integer id);
	
	public void updatePurchaseOrder(PurchaseOrder po);
	
	public Integer savePurchaseDetails(PurchaseDetails pd);
	
	public List<PurchaseDetails> getPurchaseDetailsByPurchaseOrderId(Integer orderId);
	
	public void removePurchaseDetails(Integer id);
	
	public PurchaseDetails getOnePart(Integer id);

	public void updateStatus(Integer purchaseOrderId, String status);

	public Integer getDetailsCountByOrderId(Integer purchaseorderid);

	
}
