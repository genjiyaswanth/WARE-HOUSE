package com.genji.yaswanth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genji.yaswanth.model.PurchaseOrder;
import com.genji.yaswanth.service.IPurchaseOrderService;
import com.genji.yaswanth.service.IShipmentTypeService;
import com.genji.yaswanth.service.IWhUserTypeService;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	@Autowired
	IPurchaseOrderService service;
	
	@Autowired
	private IShipmentTypeService shipmentService;
	
	@Autowired
	private IWhUserTypeService whuserService;
	
	public void addDynamicUiComponents(Model model) {
		model.addAttribute("shipmentcode", shipmentService.getShipmentTypeIdAndCodeByEnabled("YES") );
		model.addAttribute("whuser", whuserService.getWhuserTypeIdAndCodeByUserType("VENDOR") );
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		PurchaseOrder po = new PurchaseOrder();
		po.setStatus("OPEN");
		model.addAttribute("purchaseorder", po);
		addDynamicUiComponents(model);
		return "PurchaseOrderRegister";
	}
	
	@PostMapping("/save")
	public String savePurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder , Model model) {
		Integer id=service.savePurchaseOrder(purchaseOrder);
		String message = "Purchase Order " + id + " saved";
		PurchaseOrder po = new PurchaseOrder();
		po.setStatus("OPEN");
		model.addAttribute("purchaseorder", po);
		model.addAttribute("message", message);
		addDynamicUiComponents(model);
		return "PurchaseOrderRegister";
	}
	
}
