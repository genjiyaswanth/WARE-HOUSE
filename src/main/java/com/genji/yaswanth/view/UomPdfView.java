package com.genji.yaswanth.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.genji.yaswanth.model.Uom;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UomPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-Disposition", "attachment;filename=uom.pdf");
		List<Uom> uomList = (List<Uom>) model.get("uomList");
		Font titlefont =new Font(Font.TIMES_ROMAN, 22, Font.BOLD, new Color(84,192,247));
		Paragraph title = new Paragraph("UOM DETAILS",titlefont);
		Paragraph date = new Paragraph(new Date().toString());
		title.setSpacingAfter(10.0f);
		title.setAlignment(Element.ALIGN_CENTER);
		
		PdfPTable table =new PdfPTable(4);
		table.addCell("ID");
		table.addCell("UOM TYPE");
		table.addCell("UOM MODEL");
		table.addCell("DESCRIPTION");
		
		for(Uom uom : uomList) {
			table.addCell(String.valueOf(uom.getId()));
			table.addCell(uom.getUomType());
			table.addCell(uom.getUomModel());
			table.addCell(uom.getDescription());
		}
		
		document.add(title);
		document.add(table);
		document.add(date);
	}

}
