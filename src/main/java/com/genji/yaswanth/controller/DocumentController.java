package com.genji.yaswanth.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.genji.yaswanth.model.Document;
import com.genji.yaswanth.service.IDocumentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private IDocumentService service;
	
	@GetMapping("/all")
	public String showDocuments(Model model) {
		List<Object[]> list=service.getDocumentIdAndNames();
		model.addAttribute("list",list);
		return "Document";
	}
	
	@PostMapping("/save")
	public String saveDocument(@RequestParam Integer fileId ,@RequestParam MultipartFile fileObj) throws IOException {
		Document doc= new Document();
		if(fileObj!=null) {
			doc.setId(fileId);
			doc.setDocName(fileObj.getOriginalFilename());
			doc.setDocData(fileObj.getBytes());
			service.save(doc);
		}
		return "redirect:all";
	}
	
	@GetMapping("/download")
	public void downloadDocument(@RequestParam Integer id , HttpServletResponse response) throws IOException {
		Document doc = service.getOneDocument(id);
		response.setHeader("Content-Disposition", "attachment;filename="+doc.getDocName());
		FileCopyUtils.copy(doc.getDocData(), response.getOutputStream());
		
	}
	
	
	
}
