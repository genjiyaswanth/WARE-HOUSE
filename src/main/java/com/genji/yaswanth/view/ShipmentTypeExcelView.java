package com.genji.yaswanth.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.genji.yaswanth.model.ShipmentType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShipmentTypeExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 response.addHeader("Content-Disposition", "attachment;filename=shipment.xlsx");
		 List<ShipmentType> shipmentList = (List<ShipmentType>) model.get("shipmentList");
		 
		 Sheet sheet = workbook.createSheet("SHIPMENT_TYPE");
		 
		 setHeader(sheet);
		 setData(sheet,shipmentList);
		
	}

	private void setData(Sheet sheet, List<ShipmentType> shipmentList) {
		
		int rowNum =1;
		
		for(ShipmentType st : shipmentList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(st.getId());
			row.createCell(1).setCellValue(st.getShipmentMode());
			row.createCell(2).setCellValue(st.getShipmentCode());
			row.createCell(3).setCellValue(st.getEnableShipment());
			row.createCell(4).setCellValue(st.getShipmentGrade());
			row.createCell(5).setCellValue(st.getDescription());
		}
		
	}

	private void setHeader(Sheet sheet) {
		
		Row row =sheet.createRow(0);
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("SHIPMENT MODE");
		row.createCell(2).setCellValue("SHIPMENT CODE");
		row.createCell(3).setCellValue("ENABLE SHIPMENT");
		row.createCell(4).setCellValue("SHIPMENT GRADE");
		row.createCell(5).setCellValue("DESCRIPTION");
		
		
	}

}
