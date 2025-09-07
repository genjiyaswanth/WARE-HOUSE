package com.genji.yaswanth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genji.yaswanth.model.WhUserType;
import com.genji.yaswanth.service.IWhUserTypeService;

@Controller
@RequestMapping("/whuser")
public class WhUserController {

	@Autowired
	private IWhUserTypeService service;
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("whuser", new WhUserType());
		return "WhUserRegister";
	}
	
	@PostMapping("/save")
	public String saveWhUser(@ModelAttribute WhUserType wh , Model model) {
		Integer id = service.save(wh);
		String message = "WhUser saved with id:" + id;
		model.addAttribute("message", message );
		model.addAttribute("whuser" , new WhUserType());
		return "WhUserRegister";
	}
	
	@GetMapping("/all")
	public String getAllWhUsers(Model model) {
		List<WhUserType> list = service.getAllWhUsers();
		model.addAttribute("whuser", list);
		return "WhUserData";
	}
	
	@GetMapping("/delete")
	public String deleteWhUser(Integer id , Model model) {
		service.deleteWhUser(id);
		String message = "Whuser deleted with id:" + id;
		model.addAttribute("message", message);
		model.addAttribute("whuser", service.getAllWhUsers());
		return "WhUserData";
	}
	@GetMapping("/edit")
	public String editWhUser(Integer id , Model model) {
		model.addAttribute("whuser", service.getOneWhuser(id));
		return "WhUserEdit";
	}
	
	@PostMapping("/update")
	public String updateWhUser(WhUserType wh , Model model) {
		service.updateWhUser(wh);
		String message = "WhUser saved with id:" + wh.getId();
		model.addAttribute("message", message);
		model.addAttribute("whuser", service.getAllWhUsers());
		return "WhUserData";
	}
	
	
	
}
