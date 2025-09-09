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
import org.springframework.web.servlet.ModelAndView;

import com.genji.yaswanth.model.OrderMethod;
import com.genji.yaswanth.service.IOrderMethodService;
import com.genji.yaswanth.view.OrderExcelView;
import com.genji.yaswanth.view.orderPdfView;

@Controller
@RequestMapping("/order")
public class OrderMethodController {

	@Autowired
	private IOrderMethodService service;
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("order", new OrderMethod());
		return "OrderRegister";
	}

	@PostMapping("/save")
	public String saveOrder(@ModelAttribute OrderMethod ord , Model model) {
		Integer id=service.save(ord);
		String message= "Order with id "+id+" saved";
		model.addAttribute("message", message);
		model.addAttribute("order", new OrderMethod());
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
		model.addAttribute("order", service.getOneOrderMethod(id) );
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
	
	@GetMapping("/excel")
	public ModelAndView orderExportToExcel() {
		ModelAndView mv =new ModelAndView();
		List<OrderMethod> orderList = service.getAllOrderMethods();
		if(orderList == null || orderList.isEmpty()) {
			mv.addObject("message", "NO DATA FOR EXCEL EXPORT");
		    mv.setViewName("OrderData");
		}
		else {
			mv.addObject("orderList", orderList);
			mv.setView(new OrderExcelView());
		}
		return mv;
	}
	
	@GetMapping("/pdf")
	public ModelAndView orderExportToPdf() {
	    ModelAndView mv =new ModelAndView();
	    List<OrderMethod> orderList =service.getAllOrderMethods();
	    mv.addObject("orderList", orderList);
	    mv.setView(new orderPdfView());
		return mv;
	}

}
