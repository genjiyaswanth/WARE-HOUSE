package com.genji.yaswanth.service;

import java.util.List;
import java.util.Map;

import com.genji.yaswanth.model.Part;

public interface IPartService {

	public Integer savePart(Part p);
	public List<Part> getAllParts();
	public Part getOnePart(Integer id);
	public void deletePart(Integer id);
	public void updatePart(Part p);
	public Map<Integer, String> getPartIdAAndCode();
	
}
