package com.genji.yaswanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {

	@Query("SELECT COUNT(w.userEmail) FROM WhUserType w WHERE w.userEmail=:email")
	public Integer getEmailCount(String email);
	
}
