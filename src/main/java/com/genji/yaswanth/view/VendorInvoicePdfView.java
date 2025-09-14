package com.genji.yaswanth.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.genji.yaswanth.model.PurchaseDetails;
import com.genji.yaswanth.model.PurchaseOrder;
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

public class VendorInvoicePdfView extends AbstractPdfView {

    @SuppressWarnings("unchecked")
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Content-Disposition", "attachment;filename=Vendor_Invoice.pdf");

        PurchaseOrder po = (PurchaseOrder) model.get("po");
        List<PurchaseDetails> pdlist = (List<PurchaseDetails>) model.get("pdlist");

        
        Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD, Color.BLUE);
        Paragraph title = new Paragraph("VENDOR INVOICE", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);

        
        PdfPTable header = new PdfPTable(2);
        header.setWidthPercentage(100f);
        header.setSpacingAfter(20f);

        header.addCell(getHeaderCell("Vendor Code"));
        header.addCell(po.getVendor().getUserCode());
        header.addCell(getHeaderCell("Order Code"));
        header.addCell(po.getPurchaseOrderCode());
        header.addCell(getHeaderCell("Shipment Code"));
        header.addCell(po.getShipmentType().getShipmentCode());

        document.add(header);

        
        PdfPTable partsTable = new PdfPTable(7);
        partsTable.setWidthPercentage(100f);
        partsTable.setSpacingBefore(10f);
        partsTable.setSpacingAfter(20f);

        
        addTableHeader(partsTable, "CODE");
        addTableHeader(partsTable, "BASE COST");
        addTableHeader(partsTable, "QUANTITY");
        addTableHeader(partsTable, "VALUE");
        addTableHeader(partsTable, "GST %");
        addTableHeader(partsTable, "GST AMOUNT");
        addTableHeader(partsTable, "TOTAL VALUE");

        double grandTotal = 0.0;

        for (PurchaseDetails pd : pdlist) {
            double baseCost = pd.getPart().getPartCost();
            int qty = pd.getQuantity();
            double value = baseCost * qty;
            double gstPercent = pd.getPart().getGstSlob();
            double gstAmount = (value * gstPercent) / 100;
            double totalAmount = value + gstAmount;

            partsTable.addCell(pd.getPart().getPartCode());
            partsTable.addCell(String.format("%.2f", baseCost));
            partsTable.addCell(String.valueOf(qty));
            partsTable.addCell(String.format("%.2f", value));
            partsTable.addCell(String.format("%.2f", gstPercent));
            partsTable.addCell(String.format("%.2f", gstAmount));
            partsTable.addCell(String.format("%.2f", totalAmount));

            grandTotal += totalAmount;
        }

        
        PdfPCell totalCell = new PdfPCell(new Phrase("GRAND TOTAL"));
        totalCell.setColspan(6);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalCell.setBackgroundColor(Color.LIGHT_GRAY);
        partsTable.addCell(totalCell);

        PdfPCell grandTotalCell = new PdfPCell(new Phrase(String.format("%.2f", grandTotal)));
        grandTotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        grandTotalCell.setBackgroundColor(Color.YELLOW);
        partsTable.addCell(grandTotalCell);

        document.add(partsTable);

        
        Paragraph footer = new Paragraph("Generated on: " + new Date().toString(),
                                         new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY));
        footer.setAlignment(Element.ALIGN_RIGHT);
        footer.setSpacingBefore(30f);
        document.add(footer);

        Paragraph signLine = new Paragraph("_____________________________",
                new Font(Font.HELVETICA, 12, Font.BOLD));
        signLine.setAlignment(Element.ALIGN_LEFT);
        signLine.setSpacingBefore(40f);
        document.add(signLine);

        Paragraph signName = new Paragraph("Genji Yaswanth",
                new Font(Font.HELVETICA, 12, Font.NORMAL));
        signName.setAlignment(Element.ALIGN_LEFT);
        document.add(signName);
    }

    
    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell cell = new PdfPCell(new Phrase(headerTitle, new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE)));
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    
    private PdfPCell getHeaderCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 12, Font.BOLD)));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        return cell;
    }
}
