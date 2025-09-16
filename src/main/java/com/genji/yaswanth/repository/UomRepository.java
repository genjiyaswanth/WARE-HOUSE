package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {

	@Query("SELECT COUNT(u.uomModel) FROM Uom u WHERE u.uomModel=:uomModel ")
	public Integer getUomModelCount(String uomModel);
	
	@Query("SELECT u.uomType , COUNT(u.uomType) FROM Uom u GROUP BY u.uomType")
	public List<Object[]> getUomTypeAndCount();
	
	@Query("SELECT u.id , u.uomModel FROM Uom u")
	public List<Object[]> getUomIdAndModel();
	
	
	public Page<Uom> findByUomModelContaining(String model , Pageable input);
	
}
