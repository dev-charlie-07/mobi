//package com.mobiversa.excel;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.servlet.view.document.AbstractXlsxView;
//
////import com.mobiversa.util.SettlementModel;
//
//public class EzySettleListExcel extends AbstractXlsxView  {
//
////	@Override
////	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request,
////			HttpServletResponse response) throws Exception {
////
////		HSSFSheet excelSheet = workbook.createSheet("EZYSETTLE Transaction List");
////		setExcelHeader(excelSheet);
////
////		List txnList = (List) model.get("txnList");
////		//setExcelRows(excelSheet, txnList);
////
////	}
//
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		
//		
//	}
//
//	public void setExcelHeader(HSSFSheet excelSheet) {
//		HSSFRow excelHeader = excelSheet.createRow(0);
//		excelHeader.createCell(0).setCellValue("Date");
//		excelHeader.createCell(1).setCellValue("MID");
//		excelHeader.createCell(2).setCellValue("TID");
//		excelHeader.createCell(3).setCellValue("Amount(RM)");
//		excelHeader.createCell(4).setCellValue("Card Number");
//		excelHeader.createCell(5).setCellValue("RRN");
//		excelHeader.createCell(6).setCellValue("Invoice ID");
//		excelHeader.createCell(7).setCellValue("Status");
//		excelHeader.createCell(8).setCellValue("Payment Method");
//		excelHeader.createCell(9).setCellValue("MDR Amount");
//		excelHeader.createCell(10).setCellValue("Net Amount");
//		excelHeader.createCell(11).setCellValue("Payment Date");
//
//	}
//
//
//	/*
//	 * public void setExcelRows(HSSFSheet excelSheet, List<SettlementModel> txnList)
//	 * { int record = 1; for (SettlementModel txn : txnList) { HSSFRow excelRow =
//	 * excelSheet.createRow(record++);
//	 * excelRow.createCell(0).setCellValue(txn.getDate());
//	 * excelRow.createCell(1).setCellValue(txn.getMid());
//	 * excelRow.createCell(2).setCellValue(txn.getTid());
//	 * excelRow.createCell(3).setCellValue(txn.getTxnAmount());
//	 * excelRow.createCell(4).setCellValue(txn.getMaskedPan());
//	 * excelRow.createCell(5).setCellValue(txn.getRrn());
//	 * excelRow.createCell(6).setCellValue(txn.getInvoiceId());
//	 * excelRow.createCell(7).setCellValue(txn.getStatus());
//	 * excelRow.createCell(8).setCellValue(txn.getCardBrand() + " " +
//	 * txn.getCardType()); excelRow.createCell(9).setCellValue(txn.getMdrAmount());
//	 * excelRow.createCell(10).setCellValue(txn.getNetAmount());
//	 * excelRow.createCell(11).setCellValue(txn.getPaymentDate());
//	 * 
//	 * } }
//	 */
//
//}
