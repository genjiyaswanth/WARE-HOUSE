package com.genji.yaswanth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genji.yaswanth.model.Uom;
import com.genji.yaswanth.service.IUomService;

@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService service;
	
	@GetMapping("/register")
	public String showRegister() {
		return "UomRegister";
	}
	
	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom, Model model) {
		Integer id = service.saveUom(uom);
		String message= "UOM saved with id:"+id;
		model.addAttribute("message",message);
		return "UomRegister";
	}
	
	@GetMapping("/all")
	public String getAllUoms(Model model) {
		List<Uom> list = service.getAllUoms();
		model.addAttribute("list",list);
		return "UomData";
	}
	
	@GetMapping("/delete")
	public String deleteUom(@RequestParam Integer id, Model model) {
		service.deleteUom(id);
		model.addAttribute("list", service.getAllUoms());
		return "UomData";
	}
	
	@GetMapping("/edit")
	public String editUom(Integer id, Model model) {
		model.addAttribute("uom", service.getOneUom(id));
		return "UomEdit";
	}
	
	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom, Model model) {
		service.updateUom(uom);
		String message="UOM "+uom.getId()+" updated";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllUoms());
		return "UomData";
	}
}
