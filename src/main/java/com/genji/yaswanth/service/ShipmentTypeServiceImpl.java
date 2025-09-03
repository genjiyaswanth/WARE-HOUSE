package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.ShipmentTypeNotFoundException;
import com.genji.yaswanth.model.ShipmentType;
import com.genji.yaswanth.repository.ShipmentTypeRepository;
@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository repo;
	@Override
	public Integer saveShipmentType(ShipmentType st) {
		
		return repo.save(st).getId();
	}

	@Override
	public void updateShipmentType(ShipmentType st) {
		
		repo.save(st);
	}

	@Override
	public void deleteShipmentType(Integer id) {
		ShipmentType st= getOneShipmentType(id);
		repo.delete(st);

	}

	@Override
	public ShipmentType getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt=repo.findById(id);
		ShipmentType st=null;
		if(opt.isPresent()) {
			st=opt.get();
		}
		else {
			throw new ShipmentTypeNotFoundException("ShipmentType "+id+" not exist");
		}
		return st;
		
	}

	@Override
	public List<ShipmentType> getAllShipmentTypes() {
		
		return repo.findAll();
	}

}
