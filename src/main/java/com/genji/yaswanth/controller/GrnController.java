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

import com.genji.yaswanth.model.Grn;
import com.genji.yaswanth.model.GrnDetails;
import com.genji.yaswanth.model.PurchaseDetails;
import com.genji.yaswanth.service.IGrnService;
import com.genji.yaswanth.service.IPurchaseOrderService;

@Controller
@RequestMapping("grn")
public class GrnController {

	@Autowired
	private IGrnService service;
	
	@Autowired
	private IPurchaseOrderService purchaseOrderService;
	
	private void addDynamicUiComponents(Model model) {
		model.addAttribute("po", purchaseOrderService.getPurchaseOrderIdAndCodeWithInvoicedForGrn("INVOICED"));
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("grn", new Grn());
		addDynamicUiComponents(model);
		return "GrnRegister";
	}
	
	@PostMapping("/save")
	public String saveGrn(@ModelAttribute Grn grn , Model model) {
		Integer id = service.saveGrn(grn);
		String message = "GRN saved with id:" + id;
		model.addAttribute("message", message);
		model.addAttribute("grn", new Grn());
		purchaseOrderService.updateStatus(grn.getPurchaseOrder().getId(), "RECEIVED");
		addDynamicUiComponents(model);
		createGrnDetails(grn);
		return "GrnRegister";
	}
	
	
	
	private void createGrnDetails(Grn grn) {
		Integer purchaseOrderId= grn.getPurchaseOrder().getId();
		List<PurchaseDetails> pdList = purchaseOrderService.getPurchaseDetailsByPurchaseOrderId(purchaseOrderId);
		for(PurchaseDetails pd : pdList) {
			GrnDetails grnDetails = new GrnDetails();
			grnDetails.setItemCode(pd.getPart().getPartCode());
			grnDetails.setCost(pd.getPart().getPartCost());
			grnDetails.setQuantity(pd.getQuantity());
			grnDetails.setItemValue(pd.getPart().getPartCost() * pd.getQuantity() );
			grnDetails.setGrn(grn);
			
			service.saveGrnDetails(grnDetails);
		}
		
	}
	
	@GetMapping("/all")
	public String getAllGrns(Model model) {
		List<Grn> grnList = service.getAllGrns();
		model.addAttribute("grnlist", grnList);
		return "GrnData";
	}
	
	@GetMapping("/viewparts")
	public String getGrnDetails(@RequestParam Integer id, Model model) {
		List<GrnDetails> grndetails = service.getGrnDetailsByGrnId(id);
		Grn grn = service.getOneGrn(id);
		model.addAttribute("grndetails", grndetails);
		model.addAttribute("grn", grn);
		return "GrnDetails";
	}
	
	@GetMapping("/updatestatus")
	public String updateGrnDetailsStatus(@RequestParam Integer grndetailid,
										 @RequestParam Integer grnid ,
										 @RequestParam String status )
	{
		service.updateGrnDetailStatus(status, grndetailid);
		return "redirect:viewparts?id="+grnid;
	}
	
}
