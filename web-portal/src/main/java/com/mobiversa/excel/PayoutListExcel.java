package com.mobiversa.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.mobiversa.model.PayoutModel;

public class PayoutListExcel extends AbstractXlsView {

	@Override
	public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Sheet sheet = workbook.createSheet("PAYOUT_Report");

		// create header row
		Row excelHeader = sheet.createRow(0);
		excelHeader.createCell(0).setCellValue("TIME STAMP");
		excelHeader.createCell(1).setCellValue("Merchant Name");
		excelHeader.createCell(2).setCellValue("Customer Name");
		excelHeader.createCell(3).setCellValue("BRN/IC");
		excelHeader.createCell(4).setCellValue("Account No");
		excelHeader.createCell(5).setCellValue("Bank Name");
		excelHeader.createCell(6).setCellValue("Transaction_ID");
		excelHeader.createCell(7).setCellValue("Amount");
		excelHeader.createCell(8).setCellValue("Status");
		excelHeader.createCell(9).setCellValue("Date");
		excelHeader.createCell(10).setCellValue("Paid Time");

		// get data from model
		@SuppressWarnings("unchecked")
		List<PayoutModel> data = (List<PayoutModel>) model.get("data");

		// create data rows
		int rowCount = 1;
		for (PayoutModel txn : data) {
			Row excelRow = sheet.createRow(rowCount++);
			excelRow.createCell(0).setCellValue(txn.getCreateddate());
			excelRow.createCell(1).setCellValue(txn.getCreatedby());
			excelRow.createCell(2).setCellValue(txn.getPayeename());
			excelRow.createCell(3).setCellValue(txn.getPayeebrn());
			excelRow.createCell(4).setCellValue(txn.getPayeeaccnumber());
			excelRow.createCell(5).setCellValue(txn.getPayeebankname());
			excelRow.createCell(6).setCellValue(txn.getInvoiceidproof());
			excelRow.createCell(7).setCellValue(txn.getPayoutamount());
			excelRow.createCell(8).setCellValue(txn.getPayoutstatus());
			excelRow.createCell(9).setCellValue(txn.getPayoutdate());
			excelRow.createCell(10).setCellValue(txn.getPaidTime());
		}

		// set the response headers
		response.setHeader("Content-Disposition", "attachment; filename=\"mydata.xls\"");
		response.setContentType("application/vnd.ms-excel");

	}

}