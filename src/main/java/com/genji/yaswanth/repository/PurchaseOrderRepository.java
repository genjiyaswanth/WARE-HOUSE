package com.genji.yaswanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.genji.yaswanth.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

	
	
}
