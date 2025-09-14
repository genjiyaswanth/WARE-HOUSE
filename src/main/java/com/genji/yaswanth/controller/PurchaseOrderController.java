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

import com.genji.yaswanth.model.PurchaseDetails;
import com.genji.yaswanth.model.PurchaseOrder;
import com.genji.yaswanth.service.IPartService;
import com.genji.yaswanth.service.IPurchaseOrderService;
import com.genji.yaswanth.service.IShipmentTypeService;
import com.genji.yaswanth.service.IWhUserTypeService;
import com.genji.yaswanth.view.VendorInvoicePdfView;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	@Autowired
	IPurchaseOrderService service;
	
	@Autowired
	private IShipmentTypeService shipmentService;
	
	@Autowired
	private IPartService partService;
	
	@Autowired
	private IWhUserTypeService whuserService;
	
	private void addDynamicUiComponents(Model model) {
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
	
	@GetMapping("/all")
	public String showAll(Model model) {
		List<PurchaseOrder> list = service.getAllPurchaseOrders();
		model.addAttribute("list", list);
		return "PurchaseOrderData";
	}
	
	public void addDynamicUiComponentsForParts(Model model) {
		model.addAttribute("parts", partService.getPartIdAAndCode() );
		
	}
	
	@GetMapping("/parts")
	public String showPartsPage(@RequestParam Integer id, Model model) {
		PurchaseOrder po = service.getOnePurchaseOrder(id);
		model.addAttribute("pd", new PurchaseDetails());
		model.addAttribute("po", po);
		addDynamicUiComponentsForParts(model);
		List<PurchaseDetails> purchaseDetails = service.getPurchaseDetailsByPurchaseOrderId(id);
		model.addAttribute("purchasedetails", purchaseDetails);
		return "PurchaseOrderParts";
	}
	
	@PostMapping("/add")
	public String addParts(@ModelAttribute PurchaseDetails pd) {
		Integer purchaseOrderId = pd.getPurchaseOrder().getId();
		service.savePurchaseDetails(pd);
		service.updateStatus(purchaseOrderId,"PICKING");
		return "redirect:parts?id="+purchaseOrderId;
	}
	
	@GetMapping("/remove")
	public String removePurchaseDetails(@RequestParam Integer detailid ,@RequestParam Integer purchaseorderid) {
		service.removePurchaseDetails(detailid);
		if(service.getDetailsCountByOrderId(purchaseorderid)==0) {
			service.updateStatus(purchaseorderid,"OPEN");
		}
		return "redirect:parts?id="+purchaseorderid;
	}
	
	@GetMapping("/confirmorder")
	public String placeOrder(@RequestParam Integer orderid) {
		service.updateStatus(orderid,"ORDERED");
		return "redirect:parts?id="+orderid;
	}
	
	@GetMapping("/generateinvoice")
	public String generateInvoice(@RequestParam Integer id) {
		service.updateStatus(id,"INVOICED");
		return "redirect:parts?id="+id;
	}
	
	@GetMapping("/printinvoice")
	public ModelAndView printInvoice(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView();
		PurchaseOrder po = service.getOnePurchaseOrder(id);
		List<PurchaseDetails> pdlist = service.getPurchaseDetailsByPurchaseOrderId(id);
		mv.addObject("po",po);
		mv.addObject("pdlist",pdlist);
		mv.setView(new VendorInvoicePdfView());
		
		return mv;
	}
	
}
