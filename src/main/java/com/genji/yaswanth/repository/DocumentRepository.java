package com.genji.yaswanth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genji.yaswanth.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

	@Query("SELECT d.id , d.docName FROM Document d")
	public List<Object[]> getDocumentIdAndNames();
	
}
