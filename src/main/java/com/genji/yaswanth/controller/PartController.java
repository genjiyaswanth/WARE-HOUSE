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

import com.genji.yaswanth.model.Part;
import com.genji.yaswanth.service.IOrderMethodService;
import com.genji.yaswanth.service.IPartService;
import com.genji.yaswanth.service.IUomService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService service;
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService orderService;
	
	public void addDynamicUiComponents(Model model) {
		model.addAttribute("uoms",uomService.getUomIdAndModel());
		model.addAttribute("ordersales",orderService.getOrderMethodIdAndCodeByMode("SALE") );
		model.addAttribute("orderpurchase",orderService.getOrderMethodIdAndCodeByMode("PURCHASE") );
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("part", new Part());
		addDynamicUiComponents(model);
		return "PartRegister";
	}
	
	@PostMapping("/save")
	public String savePart(@ModelAttribute	Part p , Model model) {
		Integer id = service.savePart(p);
		String message = "Part saved with id:"+id ;
		model.addAttribute("message",message);
		model.addAttribute("part", new Part());
		addDynamicUiComponents(model);
		return "PartRegister";
	}
	
	@GetMapping("/all")
	public String showAll(Model model) {
		List<Part> list = service.getAllParts();
		model.addAttribute("list", list);
		return "PartData";
	}
	
	@GetMapping("/delete")
	public String deletePart(@RequestParam Integer id, Model model) {
		service.deletePart(id);
		String message = "Part " + id + " deleted";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllParts());
		return "PartData";
	}
	
	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer id, Model model) {

		model.addAttribute("part", service.getOnePart(id));
		return "PartEdit";
	}

	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Part p, Model model) {
		service.updatePart(p);
		String message = "Part " + p.getId() + " updated";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllParts());
		return "PartData";
	}
	
}
