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

import com.genji.yaswanth.model.OrderMethod;
import com.genji.yaswanth.service.IOrderMethodService;

@Controller
@RequestMapping("/ord")
public class OrderMethodController {

	@Autowired
	private IOrderMethodService service;
	
	@GetMapping("/register")
	public String getRegister() {
		return "OrderRegister";
	}

	@PostMapping("/save")
	public String saveOrder(@ModelAttribute OrderMethod ord , Model model) {
		Integer id=service.save(ord);
		String message= "Order with id "+id+" saved";
		model.addAttribute("message", message);
		return "OrderRegister";
	}
	
	@GetMapping("/all")
	public String getAllOrders(Model model) {
		List<OrderMethod> list = service.getAllOrderMethods();
		model.addAttribute("list", list);
		return "OrderData";
	}
	
	@GetMapping("/delete")
	public String deleteOrder(@RequestParam Integer id, Model model) {
		service.deleteOrderMethod(id);
		String message = "Order id "+id+" deleted";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllOrderMethods());
		return "OrderData";
	}
	
	@GetMapping("/edit")
	public String editOrder(@RequestParam Integer id, Model model) {
		model.addAttribute("order", service.getOneOrderMethod(id));
		return "OrderEdit";
	}
	
	@PostMapping("/update")
	public String updateOrder(OrderMethod ord , Model model) {
		service.updateOrderMethod(ord);
		String message = "Order id "+ord.getId()+" updated";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllOrderMethods());
		return "OrderData";
	}
	

}
