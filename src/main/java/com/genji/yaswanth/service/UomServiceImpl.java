package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.UomNotFoundException;
import com.genji.yaswanth.model.Uom;
import com.genji.yaswanth.repository.UomRepository;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository repo;

	@Override
	public Integer saveUom(Uom uom) {

		return repo.save(uom).getId();
	}

	@Override
	public List<Uom> getAllUoms() {

		return repo.findAll();
	}

	@Override
	public void updateUom(Uom uom) {
		repo.save(uom);
	}

	@Override
	public void deleteUom(Integer id) {

		Uom uom = getOneUom(id);
		repo.delete(uom);
	}

	@Override
	public Uom getOneUom(Integer id) {
		Optional<Uom> opt = repo.findById(id);
		Uom uom = null;
		if (opt.isPresent()) {
			uom = opt.get();
		} else {
			throw new UomNotFoundException("Uom " + id + " not exist");
		}
		return uom;
	}

}
