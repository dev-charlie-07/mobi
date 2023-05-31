package com.mobiversa.controller;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.mobiversa.excel.PayoutListExcel;
import com.mobiversa.excel.PayoutSummaryPdf;
import com.mobiversa.model.PaginationBean;
import com.mobiversa.model.PayoutModel;
import com.mobiversa.service.PayoutService;

@Controller
@RequestMapping("/payoutSummary")
public class PayoutController {

	private static final Logger logger = LogManager.getLogger(PayoutController.class);

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	PayoutService payoutService;

	@Transactional
	@RequestMapping("/list")
	public String showMyLoginPage(final Model model, HttpServletRequest request) {

		String currentDate = LocalDate.now().toString();
		logger.info(currentDate);
		int currPage = 1;
		PaginationBean paginationBean = new PaginationBean();
		paginationBean.setCurrPage(currPage);

		ArrayList<PayoutModel> payoutList = payoutService.listPayoutTransaction(currentDate, null, null,
				paginationBean);
		request.setAttribute("payoutList", payoutList);
		request.setAttribute("paginationBean", paginationBean);
		request.setAttribute("payoutListSize", payoutList.size());
		request.setAttribute("fromDate", "11-04-2023");
		System.out.println(currentDate);
		System.out.println(payoutList);

		return "payoutportal";

	}

	@RequestMapping(value = { "/searchPayout" }, method = RequestMethod.GET)
	public String searchPayoutList(HttpServletRequest request, final Model model, @RequestParam final String fromdate,
			@RequestParam final String todate, @RequestParam(required = false, defaultValue = "1") final int pagenum) {
		String fromDate = HtmlUtils.htmlEscape(fromdate);
		String toDate = HtmlUtils.htmlEscape(todate);
		PaginationBean paginationBean = new PaginationBean();

		System.out.println("FROM DATE ::" + fromDate);
		System.out.println("TO DATE ::" + toDate);
		System.out.println("Page Number ::" + pagenum);
		paginationBean.setCurrPage(pagenum);
		paginationBean.setDateFromBackend(fromDate);
		paginationBean.setDate1FromBackend(todate);
		ArrayList<PayoutModel> payoutList = payoutService.listPayoutTransaction(null, fromDate, toDate, paginationBean);
		request.setAttribute("payoutList", payoutList);
		request.setAttribute("payoutListSize", payoutList.size());
		request.setAttribute("paginationBean", paginationBean);

		return "payoutportal";
	}

	@RequestMapping(value = { "/exportPayout" }, method = RequestMethod.GET)
	public void exportPayoutList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam final String fromdate, @RequestParam final String todate, @RequestParam final String type)
			throws Exception {
		System.out.println("To date :" + todate);
		System.out.println("fromdate :" + fromdate);

		PaginationBean pg = new PaginationBean();
		ArrayList<PayoutModel> payoutList = payoutService.exportPayoutTransaction(fromdate, todate);
		System.out.println("SIZE OF LIST   : " + payoutList.size());

		if (type.equalsIgnoreCase("excel")) {
			Map<String, Object> model = new HashMap<>();
			model.put("data", payoutList);

			// create a new instance of the Workbook
			Workbook workbook = new HSSFWorkbook(); // for Excel 97-2003 format (.xls)

			// create a new instance of the Excel view and render it
			PayoutListExcel excelView = new PayoutListExcel();
			excelView.buildExcelDocument(model, workbook, request, response);

			// set response headers
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"mydata.xls\"");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			workbook.write(response.getOutputStream());
		} else {
			Map<String, Object> model = new HashMap<>();
			model.put("txnList", payoutList);
			PayoutSummaryPdf pdfView = new PayoutSummaryPdf();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			pdfView.buildPdfDocument(model, document, writer, request, response);

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"mypdf.pdf\"");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentLength(baos.size());

			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			baos.close();
		}

	}

	@RequestMapping(value = { "/searchByNumber" }, method = RequestMethod.GET)
	public String searchByNumber(HttpServletRequest request, final Model model, @RequestParam final String poNumber,
			@RequestParam final String searchType,
			@RequestParam(required = false, defaultValue = "1") final int currPage) {

		System.out.println("PO_NUMBER ::" + poNumber);
		System.out.println("SEARCH_TYPE ::" + searchType);
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Subtract 3 months from the current date and time
		LocalDateTime threeMonthsBefore = currentDateTime.minusMonths(3);

		// Create a formatter for the output date and time format
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Format the LocalDateTime object to the desired output format
		String outputDateTime = threeMonthsBefore.format(outputFormatter);
		String currentDateTime1 = currentDateTime.format(outputFormatter);
		System.out.println("Current Date and Time: " + currentDateTime);
		System.out.println("Date and Time of 3 Months Before: " + outputDateTime);

		PaginationBean paginationBean = new PaginationBean();
		paginationBean.setCurrPage(currPage);

		ArrayList<PayoutModel> payoutList = payoutService.searchPayoutData(poNumber, currentDateTime1, outputDateTime,
				searchType, paginationBean);
		request.setAttribute("payoutList", payoutList);
		request.setAttribute("payoutListSize", payoutList.size());
		request.setAttribute("paginationBean", paginationBean);
		return "payoutportal";
	}

}
