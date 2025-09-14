package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

	@Query("SELECT p.id , p.partCode FROM Part p")
	public List<Object[]> getPartIdAndCode();
	
}
