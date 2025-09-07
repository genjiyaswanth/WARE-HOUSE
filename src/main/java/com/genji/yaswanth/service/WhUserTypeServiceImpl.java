package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.WhUserNotFoundException;
import com.genji.yaswanth.model.WhUserType;
import com.genji.yaswanth.repository.WhUserTypeRepository;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepository repo;
	@Override
	public Integer save(WhUserType wh) {
		Integer id = repo.save(wh).getId();
		return id;
	}

	@Override
	public List<WhUserType> getAllWhUsers() {
		List<WhUserType> list = repo.findAll();
		return list;
	}

	@Override
	public WhUserType getOneWhuser(Integer id) {
		Optional<WhUserType> opt = repo.findById(id);
		WhUserType wh = null;
		if(opt.isPresent()) {
			wh = opt.get();
		}
		else {
			throw new WhUserNotFoundException("WhUser " + id + " not exist");
		}
		return wh;
	}

	@Override
	public void deleteWhUser(Integer id) {
		
		WhUserType wh = getOneWhuser(id);
		repo.delete(wh);

	}

	@Override
	public void updateWhUser(WhUserType wh) {
		
		repo.save(wh);

	}

}
