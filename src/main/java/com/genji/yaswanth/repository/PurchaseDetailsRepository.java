package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.PurchaseDetails;

public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Integer> {

	@Query("SELECT dt FROM PurchaseDetails dt JOIN dt.purchaseOrder WHERE dt.purchaseOrder.id=:orderId")
	public List<PurchaseDetails> getPurchaseDeatailsByPurchaseOrderId(Integer orderId);
	
	@Query("SELECT COUNT(pd.id) FROM PurchaseDetails pd WHERE pd.purchaseOrder.id=:purchaseorderid")
	public Integer getDetailsCountByOrderId(Integer purchaseorderid);
}
