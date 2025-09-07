package com.genji.yaswanth.service;

import java.util.List;

import com.genji.yaswanth.model.Document;

public interface IDocumentService {

	public Integer save(Document doc);
	public List<Object[]> getDocumentIdAndNames();
	public Document getOneDocument(Integer id);
}
