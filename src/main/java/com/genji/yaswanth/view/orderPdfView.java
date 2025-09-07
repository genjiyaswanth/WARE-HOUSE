package com.genji.yaswanth.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.genji.yaswanth.model.OrderMethod;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class orderPdfView extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=order.pdf");
		List<OrderMethod> orderList = (List<OrderMethod>) model.get("orderList");
		
		Font titlefont =new Font(Font.TIMES_ROMAN, 22, Font.BOLD, new Color(84,192,247));
		Paragraph date = new Paragraph(new Date().toString());
		Paragraph title = new Paragraph("ORDER DETAILS",titlefont);
		title.setSpacingAfter(10.0f);
		title.setAlignment(Element.ALIGN_CENTER);
		
		PdfPTable table =new PdfPTable(6);
		table.addCell("ID");
		table.addCell("ORDER MODE");
		table.addCell("ORDER CODE");
		table.addCell("ORDER TYPE");
		table.addCell("ORDER ACCEPTED");
		table.addCell("DESCRIPTION");
		
		for(OrderMethod om : orderList) {
			table.addCell(String.valueOf(om.getId()));
			table.addCell(om.getOrderMode());
			table.addCell(om.getOrderCode());
			table.addCell(om.getOrderType());
			table.addCell(om.getOrderAccepted().toString());
			table.addCell(om.getDescription());
		}
		
		document.add(title);
		document.add(table);
		document.add(date);
		
	}

	

}
