package com.genji.yaswanth.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.PartNotFountException;
import com.genji.yaswanth.model.Part;
import com.genji.yaswanth.repository.PartRepository;
import com.genji.yaswanth.util.CollectionUtil;
@Service
public class PartServiceImpl implements IPartService {

	@Autowired
	private PartRepository repo;
	
	@Override
	public Integer savePart(Part p) {
		p= repo.save(p);
		return p.getId();
	}

	@Override
	public List<Part> getAllParts() {
		
		return repo.findAll();
	}

	@Override
	public Part getOnePart(Integer id) {
		Optional<Part> opt=repo.findById(id);
		Part p = null;
		if(opt.isPresent()) {
			p=opt.get();
		}
		else {
			throw new PartNotFountException("Part With id:"+id+" not exist");
		}
		return p;
	}

	@Override
	public void deletePart(Integer id) {
		
		Part p =getOnePart(id);
		repo.delete(p);

	}

	@Override
	public void updatePart(Part p) {
		repo.save(null);
	}

	@Override
	public Map<Integer, String> getPartIdAAndCode() {
		List<Object[]> list = repo.getPartIdAndCode();
		Map<Integer,String> map =CollectionUtil.convertListToMap(list);
		return map;
	}

}
