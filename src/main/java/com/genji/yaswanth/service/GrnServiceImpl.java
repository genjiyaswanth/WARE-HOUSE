package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.GrnNotFoundException;
import com.genji.yaswanth.model.Grn;
import com.genji.yaswanth.model.GrnDetails;
import com.genji.yaswanth.repository.GrnDetailsRepository;
import com.genji.yaswanth.repository.GrnRepository;
@Service
public class GrnServiceImpl implements IGrnService {

	@Autowired
	private GrnRepository repo;
	
	@Autowired
	private GrnDetailsRepository grnDetailsRepo;
	
	@Override
	public Integer saveGrn(Grn grn){
		grn = repo.save(grn);
		return grn.getId();
	}

	@Override
	public Grn getOneGrn(Integer id) {
		Optional<Grn> opt = repo.findById(id);
		Grn grn = null;
		if(opt.isPresent()) {
			grn = opt.get();
		}
		else {
			throw new GrnNotFoundException("Grn with Id:"+id+" not found");
		}
		return grn;
	}

	@Override
	public List<Grn> getAllGrns() {
		
		return repo.findAll();
	}

	@Override
	public Integer saveGrnDetails(GrnDetails grndetails) {
		grndetails = grnDetailsRepo.save(grndetails);
		return grndetails.getId();
	}

}
