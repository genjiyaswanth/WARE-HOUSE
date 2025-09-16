package com.genji.yaswanth.service;

import java.util.List;

import com.genji.yaswanth.model.Grn;
import com.genji.yaswanth.model.GrnDetails;

public interface IGrnService {

	public Integer saveGrn(Grn g);

	public Grn getOneGrn(Integer id);

	public List<Grn> getAllGrns();
	
	public Integer saveGrnDetails(GrnDetails grndetails);
	
	public List<GrnDetails> getGrnDetailsByGrnId(Integer id);
	
	public void updateGrnDetailStatus(String grnDetailstatus, Integer grnDetailId);

}
