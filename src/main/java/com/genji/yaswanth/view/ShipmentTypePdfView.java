package com.genji.yaswanth.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.genji.yaswanth.model.ShipmentType;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShipmentTypePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-Disposition", "attachment;filename=shipment.pdf");
		List<ShipmentType> shipmentList = (List<ShipmentType>) model.get("shipmentList");
		
		Font titlefont =new Font(Font.TIMES_ROMAN, 22, Font.BOLD, new Color(84,192,247));
		Paragraph date = new Paragraph(new Date().toString());
		Paragraph title = new Paragraph("SHIPMENT DETAILS",titlefont);
		title.setSpacingAfter(10.0f);
		title.setAlignment(Element.ALIGN_CENTER);
		
		PdfPTable table =new PdfPTable(6);
		table.addCell("ID");
		table.addCell("SHIPMENT MODE");
		table.addCell("SHIPMENT CODE");
		table.addCell("ENABLE SHIPMENT");
		table.addCell("SHIPMENT GRADE");
		table.addCell("DESCRIPTION");
		
		for(ShipmentType st : shipmentList) {
			table.addCell(String.valueOf(st.getId()));
			table.addCell(st.getShipmentMode());
			table.addCell(st.getShipmentCode());
			table.addCell(st.getEnableShipment());
			table.addCell(st.getShipmentGrade());
			table.addCell(st.getDescription());
		}
		
		document.add(title);
		document.add(table);
		document.add(date);
		
	}

}
