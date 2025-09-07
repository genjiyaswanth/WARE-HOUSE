package com.genji.yaswanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genji.yaswanth.exceptions.DocumentNotFountException;
import com.genji.yaswanth.model.Document;
import com.genji.yaswanth.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository repo;
	@Override
	public Integer save(Document doc) {
		Integer id= repo.save(doc).getId();
		return id;
	}
	@Override
	public List<Object[]> getDocumentIdAndNames() {
		
		return repo.getDocumentIdAndNames();
	}
	@Override
	public Document getOneDocument(Integer id) {
		Optional<Document> opt =repo.findById(id);
		Document doc= null;
		if(opt.isPresent()) {
			doc= opt.get();
		}
		else {
			throw new DocumentNotFountException("Document with id:"+id+" not exist");
		}
		return doc;
		
	}
	

}
