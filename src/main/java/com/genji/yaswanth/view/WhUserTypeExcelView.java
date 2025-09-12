package com.genji.yaswanth.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.genji.yaswanth.model.WhUserType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WhUserTypeExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=WhUserType.xlsx");
		List<WhUserType> whuserList = (List<WhUserType>) model.get("whuserList");
		Sheet sheet=workbook.createSheet("WHUSERTYPE");
		setHeader(sheet);
		setData(sheet,whuserList);
		
	}

	private void setData(Sheet sheet, List<WhUserType> whuserList) {
		int rowNum=1;
		for(WhUserType wh: whuserList) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(wh.getId());
			row.createCell(1).setCellValue(wh.getUserType());
			row.createCell(2).setCellValue(wh.getUserCode());
			row.createCell(3).setCellValue(wh.getUserFor());
			row.createCell(4).setCellValue(wh.getUserEmail());
			row.createCell(5).setCellValue(wh.getUserContact());
			row.createCell(6).setCellValue(wh.getUserIdType());
			row.createCell(7).setCellValue(wh.getIfOther());
			row.createCell(8).setCellValue(wh.getIdNumber());
		}
		
	}

	private void setHeader(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("USER FOR");
		row.createCell(4).setCellValue("EMAIL");
		row.createCell(5).setCellValue("CONTACT");
		row.createCell(6).setCellValue("ID TYPE");
		row.createCell(7).setCellValue("IF OTHER");
		row.createCell(8).setCellValue("ID NUMBER");
	}

}
