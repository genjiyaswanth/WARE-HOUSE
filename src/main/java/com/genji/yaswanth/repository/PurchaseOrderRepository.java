package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

	@Modifying
	@Query("UPDATE PurchaseOrder po SET po.status=:status WHERE po.id=:purchaseOrderId")
	void updateStatus(Integer purchaseOrderId, String status);

	@Query("SELECT po.id, po.purchaseOrderCode FROM PurchaseOrder po WHERE po.status=:status")
	List<Object[]> getPurchaseOrderIdAndCodeWithInvoicedForGrn(String status);

}
