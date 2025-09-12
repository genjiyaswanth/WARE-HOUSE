package com.genji.yaswanth.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.genji.yaswanth.model.WhUserType;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WhUserTypePdfView extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.addHeader("Content-Disposition", "attachment;filename=whusertype.pdf");

		List<WhUserType> whuserList = (List<WhUserType>) model.get("whuserList");

		
		Font titleFont = new Font(Font.TIMES_ROMAN, 22, Font.BOLD, new Color(0, 102, 204));
		Paragraph title = new Paragraph("WAREHOUSE USER TYPE DETAILS", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(15f);

		
		Font dateFont = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC, Color.GRAY);
		Paragraph date = new Paragraph("Generated on: " + new Date().toString(), dateFont);
		date.setAlignment(Element.ALIGN_RIGHT);
		date.setSpacingBefore(10f);

		
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);
		String[] headers = { "ID", "TYPE", "CODE", "USER FOR", "EMAIL", "CONTACT", "ID TYPE", "IF OTHER", "ID NUMBER" };
		for (String h : headers) {
			PdfPCell headerCell = new PdfPCell(new Phrase(h, headFont));
			headerCell.setBackgroundColor(new Color(0, 102, 204));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setPadding(6f);
			table.addCell(headerCell);
		}

		
		Font bodyFont = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.BLACK);
		int rowCount = 0;
		for (WhUserType wh : whuserList) {
			Color bgColor = (rowCount % 2 == 0) ? new Color(224, 235, 255) : Color.WHITE;

			table.addCell(makeCell(String.valueOf(wh.getId()), bodyFont, bgColor));
			table.addCell(makeCell(wh.getUserType(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getUserCode(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getUserFor(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getUserEmail(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getUserContact(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getUserIdType(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getIfOther(), bodyFont, bgColor));
			table.addCell(makeCell(wh.getIdNumber(), bodyFont, bgColor));

			rowCount++;
		}

		
		document.add(title);
		document.add(table);
		document.add(date);
	}

	
	private PdfPCell makeCell(String content, Font font, Color bgColor) {
		PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		cell.setBackgroundColor(bgColor);
		return cell;
	}
}
