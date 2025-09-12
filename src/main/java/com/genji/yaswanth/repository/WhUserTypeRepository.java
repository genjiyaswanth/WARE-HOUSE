package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {

	@Query("SELECT COUNT(w.userEmail) FROM WhUserType w WHERE w.userEmail=:email")
	public Integer getEmailCount(String email);
	
	@Query("SELECT w.id , w.userCode FROM WhUserType w where w.userType=:userType")
	public List<Object[]> getWhuserTypeIdAndCodeByUserType(String userType);
	
}
