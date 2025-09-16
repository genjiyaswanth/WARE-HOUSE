package com.genji.yaswanth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.genji.yaswanth.model.Uom;
import com.genji.yaswanth.service.IUomService;
import com.genji.yaswanth.util.UomUtil;
import com.genji.yaswanth.view.UomExcelView;
import com.genji.yaswanth.view.UomPdfView;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService service;
	
	@Autowired
	private UomUtil util;
	
	@Autowired
	private ServletContext sc;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}

	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom, Model model) {
		Integer id = service.saveUom(uom);
		String message = "UOM saved with id:" + id;
		model.addAttribute("message", message);
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}

	@GetMapping("/all")
	public String getAllUoms(@PageableDefault(page=0 , size =2) Pageable input,
							@RequestParam(name ="uomModel", required = false, defaultValue = "") String uomModel,
							Model model) {
		
		if(uomModel.equals("")) {
			Page<Uom> page = service.getAllUoms(input);
			List<Uom> list = page.getContent();
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			Uom uom = new Uom();
			uom.setUomModel(uomModel);
			model.addAttribute("uom", uom);
		}
		else {
			Page<Uom> page = service.findByUomModelContaining(uomModel, input);
			List<Uom> list = page.getContent();
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			Uom uom = new Uom();
			uom.setUomModel(uomModel);
			model.addAttribute("uom", uom);
		}
	
		return "UomData";
	}

	@GetMapping("/delete")
	public String deleteUom(@RequestParam Integer id,@PageableDefault(page=0,size=2) Pageable input, Model model) {
		service.deleteUom(id);
		String message = "UOM id "+id+" deleted";
		model.addAttribute("message", message);
		Page<Uom> page = service.getAllUoms(input);
		List<Uom> list = page.getContent();
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "UomData";
	}

	@GetMapping("/edit")
	public String editUom(Integer id, Model model) {
		model.addAttribute("uom", service.getOneUom(id));
		return "UomEdit";
	}

	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom,@PageableDefault(page=0,size=2) Pageable input, Model model) {
		service.updateUom(uom);
		Page<Uom> page = service.getAllUoms(input);
		List<Uom> list = page.getContent();
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		String message = "UOM " + uom.getId() + " updated";
		model.addAttribute("message", message);

		return "UomData";
	}
	
	@GetMapping("/excel")
	public ModelAndView uomExportToExcel() {
		ModelAndView mv= new ModelAndView();
		List<Uom> uomList = service.getAllUoms();
		
		if(uomList==null || uomList.isEmpty()) {
			mv.addObject("message", "NO DATA FOR EXCEL EXPORT");
			mv.setViewName("UomData");
		}
		else {
			mv.addObject("uomList", uomList);
			mv.setView(new UomExcelView());
		}
		return mv;
	}
	
	@GetMapping("/pdf")
	public ModelAndView uomExportToPdf() {
		ModelAndView mv = new ModelAndView();
		List<Uom> uomList = service.getAllUoms();
		mv.addObject("uomList", uomList);
		mv.setView(new UomPdfView());
		return mv;
	}
	
	@GetMapping("/validate-uommodel")
	public @ResponseBody String validateUomModle(String uomModel) {
		String message ="";
		if(service.isUomModelExist(uomModel)) {
			message="Uom model"+uomModel+" already exists";
		}

		return message;
	}
	
	@GetMapping("/charts")
	public String showCharts() {
		List<Object[]> data =service.getUomTypeAndCount();
		String path=sc.getRealPath("/");
		util.geteratePieChart(path, data);
		util.generateBarChart(path, data);
		return "UomCharts";
	}
}
