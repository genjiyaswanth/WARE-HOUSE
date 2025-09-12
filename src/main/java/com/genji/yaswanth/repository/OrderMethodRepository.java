package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

	@Query("SELECT COUNT(o.orderCode) FROM OrderMethod o WHERE o.orderCode=:orderCode")
	public Integer getOrderCodeCount(String orderCode);
	
	@Query("SELECT o.id , o.orderCode FROM OrderMethod o where o.orderMode=:mode ")
	public List<Object[]> getOrderMethodIdAndCodeByMode(String mode);
	
}
