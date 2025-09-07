package com.genji.yaswanth.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.genji.yaswanth.model.OrderMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=order.xlsx");
		List<OrderMethod> orderList = (List<OrderMethod>) model.get("orderList");
		Sheet sheet =workbook.createSheet();
		setHeader(sheet);
		setData(sheet,orderList);
	}

	private void setData(Sheet sheet, List<OrderMethod> orderList) {
		
		int rowNum =1 ;
		for(OrderMethod om : orderList) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(om.getId());
			row.createCell(1).setCellValue(om.getOrderMode());
			row.createCell(2).setCellValue(om.getOrderCode());
			row.createCell(3).setCellValue(om.getOrderType());
			row.createCell(4).setCellValue(om.getOrderAccepted().toString());
			row.createCell(5).setCellValue(om.getDescription());
		}
		
	}

	private void setHeader(Sheet sheet) {
		
		Row row =sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Order MODE");
		row.createCell(2).setCellValue("Order Code");
		row.createCell(3).setCellValue("Order Type");
		row.createCell(4).setCellValue("Order Accepted");
		row.createCell(5).setCellValue("Description");
		
		
	}

	
}
