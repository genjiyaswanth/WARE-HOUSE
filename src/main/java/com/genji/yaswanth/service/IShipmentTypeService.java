package com.genji.yaswanth.service;

import java.util.List;
import java.util.Map;

import com.genji.yaswanth.model.ShipmentType;

public interface IShipmentTypeService {

	public Integer saveShipmentType(ShipmentType st);

	public void updateShipmentType(ShipmentType id);

	public void deleteShipmentType(Integer id);

	public ShipmentType getOneShipmentType(Integer id);

	public List<ShipmentType> getAllShipmentTypes();
	
	public boolean isShipmentCodeExist(String shipmentCode);
	
	public List<Object[]> getShipmentModeAndCount();
	
	public Map<Integer,String> getShipmentTypeIdAndCodeByEnabled(String isEnabled);
}
