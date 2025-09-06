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

import com.genji.yaswanth.model.ShipmentType;
import com.genji.yaswanth.service.IShipmentTypeService;
import com.genji.yaswanth.view.ShipmentTypeExcelView;
import com.genji.yaswanth.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {

	@Autowired
	private IShipmentTypeService service;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("shipment", new ShipmentType());
		return "ShipmentTypeRegister";
	}

	@PostMapping("/save")
	public String saveShipmetType(@ModelAttribute ShipmentType st, Model model) {
		Integer id = service.saveShipmentType(st);
		String message = "ShipmentType " + id + " saved";
		model.addAttribute("message", message);
		model.addAttribute("shipment", new ShipmentType());
		return "ShipmentTypeRegister";
	}

	@GetMapping("/all")
	public String showAll(Model model) {
		List<ShipmentType> list = service.getAllShipmentTypes();
		model.addAttribute("list", list);
		return "ShipmentTypeData";
	}

	@GetMapping("/delete")
	public String deleteShipmentType(@RequestParam Integer id, Model model) {
		service.deleteShipmentType(id);
		String message = "ShipmentType " + id + " deleted";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}

	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer id, Model model) {

		model.addAttribute("shipment", service.getOneShipmentType(id));
		return "ShipmentTypeEdit";
	}

	@PostMapping("/update")
	public String doUpdate(@ModelAttribute ShipmentType st, Model model) {
		service.updateShipmentType(st);
		String message = "Shipmenttype " + st.getId() + " updated";
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}
	
	@GetMapping("/excel")
	public ModelAndView shipmentExportToExcel() {
	    ModelAndView mv =new ModelAndView();
	    List<ShipmentType> shipmentList =service.getAllShipmentTypes();
	    if(shipmentList== null || shipmentList.isEmpty()) {
	    	mv.addObject("message", "NO DATA FOR EXCEL EXPORT");
		    mv.setViewName("ShipmentTypeData");
	    }
	    else {
	    	mv.addObject("shipmentList", shipmentList);
		    mv.setView(new ShipmentTypePdfView());
	    }
		return mv;
	}
	
	@GetMapping("/pdf")
	public ModelAndView shipmentExportToPdf() {
	    ModelAndView mv =new ModelAndView();
	    List<ShipmentType> shipmentList =service.getAllShipmentTypes();
	    mv.addObject("shipmentList", shipmentList);
	    mv.setView(new ShipmentTypePdfView());
		return mv;
	}
	
	
}
