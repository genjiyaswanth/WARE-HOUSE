package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.GrnDetails;

public interface GrnDetailsRepository extends JpaRepository<GrnDetails, Integer>{

	@Query("SELECT gd FROM GrnDetails gd WHERE gd.grn.id =:id")
	public List<GrnDetails> getGrnDeatilsByGrnId(Integer id);
	
	@Modifying
	@Query("UPDATE GrnDetails gd SET gd.status =:grnDetailstatus WHERE gd.id=:grnDetailId")
	public void updateGrnDetailStatus(String grnDetailstatus, Integer grnDetailId);
}
