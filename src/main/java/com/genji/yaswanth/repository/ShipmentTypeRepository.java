package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {

	@Query("SELECT COUNT(s.shipmentCode) FROM ShipmentType s where s.shipmentCode=:shipmentCode")
	public Integer getShipmentCodeCount(String shipmentCode);
	
	@Query("SELECT s.shipmentMode , COUNT(s.shipmentMode) FROM ShipmentType s group by s.shipmentMode")
	public List<Object[]> getShipmentModeAndCount();
	
	@Query("SELECT st.id , st.shipmentCode FROM ShipmentType st WHERE st.enableShipment=:isEnabled")
	public List<Object[]> getShipmentTypeIdAndCodeByEnabled(String isEnabled);
	
}
