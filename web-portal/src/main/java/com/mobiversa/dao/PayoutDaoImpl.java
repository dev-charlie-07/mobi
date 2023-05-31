package com.mobiversa.dao;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobiversa.model.PaginationBean;
import com.mobiversa.model.PayoutModel;

@Service
public class PayoutDaoImpl implements PayoutDao {

	@Autowired
	protected SessionFactory sessionFactory;

	private static final Logger logger = LogManager.getLogger(PayoutDaoImpl.class);

	@Transactional
	@Override
	public ArrayList<PayoutModel> listPayoutTransaction(String currentDate, String date1, String date2,
			PaginationBean paginationBean) {

		System.out.println("To date in dao :" + date2);
		System.out.println("fromdate in dao ::" + date1);

		@SuppressWarnings("deprecation")
		Query sqlQuery = null;
		ArrayList<PayoutModel> fss = new ArrayList<PayoutModel>();

		if (date1 != null && date2 != null) {
			String sql = "SELECT p.CREATED_BY,p.CREATED_DATE,p.PAYEE_ACC_NUMBER,p.PAYEE_BRN,p.PAYEE_BANK_NAME,p.PAYEE_EMAIL,p.PAYEE_IC,p.PAYEE_MOBILE,p.PAYEE_NAME,"
					+ "p.PAYOUT_AMOUNT,p.MODIFIED_BY,p.PAYOUT_STATUS,p.SETTLE_DATE,p.SETTLE_NET_AMOUNT,p.INVOICE_ID_PROOF,p.PAYMENT_REASON,p.SOURCE_OF_FUND,p.SWIFT_IFSC_CODE,p.MERCHANT_FK,p.PAID_TIME,p.PAID_DATE,p.SUB_MERCHANT_MID,ml.BUSINESS_NAME FROM PAYOUT_DETAIL p INNER JOIN MID m ON p.SUB_MERCHANT_MID=m.SUB_MERCHANT_MID INNER JOIN MERCHANT ml ON m.MERCHANT_FK=ml.ID WHERE p.MODIFIED_BY BETWEEN '"
					+ date1 + "' and '" + date2 + "' ORDER BY p.CREATED_DATE desc ";

			sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
			/*
			 * paginationBean.setDateFromBackend(date1);
			 * paginationBean.setDate1FromBackend(date2);
			 */
		} else {

			String sql = "SELECT p.CREATED_BY,p.CREATED_DATE,p.PAYEE_ACC_NUMBER,p.PAYEE_BRN,p.PAYEE_BANK_NAME,p.PAYEE_EMAIL,p.PAYEE_IC,p.PAYEE_MOBILE,p.PAYEE_NAME,"
					+ "p.PAYOUT_AMOUNT,p.MODIFIED_BY,p.PAYOUT_STATUS,p.SETTLE_DATE,p.SETTLE_NET_AMOUNT,p.INVOICE_ID_PROOF,p.PAYMENT_REASON,p.SOURCE_OF_FUND,p.SWIFT_IFSC_CODE,p.MERCHANT_FK,p.PAID_TIME,p.PAID_DATE,p.SUB_MERCHANT_MID,ml.BUSINESS_NAME FROM PAYOUT_DETAIL p INNER JOIN MID m ON p.SUB_MERCHANT_MID=m.SUB_MERCHANT_MID INNER JOIN MERCHANT ml ON m.MERCHANT_FK=ml.ID WHERE p.MODIFIED_BY ='"
					+ currentDate + "' ORDER BY p.CREATED_DATE desc ";

			sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);

			/*
			 * paginationBean.setDateFromBackend(currentDate);
			 * paginationBean.setDate1FromBackend(currentDate);
			 */

		}
		int pageSize = 10;
		int pageNumFromJsp = paginationBean.getCurrPage();
		logger.info("Page Number:" + pageNumFromJsp);
		sqlQuery.setFirstResult((pageNumFromJsp * pageSize) - pageSize);
		sqlQuery.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = sqlQuery.list();
		logger.info(resultSet);
		for (Object[] rec : resultSet) {

			PayoutModel fs = new PayoutModel();

			if (rec[0] == null || rec[0].toString().isEmpty()) {

				fs.setCreatedby("");

			} else if (rec[0] != null) {
				fs.setCreatedby(rec[0].toString());
			}

			if (rec[1] == null || rec[1].toString().isEmpty()) {

				fs.setCreateddate("");
			}

			else if (rec[1] != null) {

				// Sheik Changes

				String time = null;
				String date = null;
				String stamp = null;
				try {

					time = new SimpleDateFormat("HH:mm:ss")
							.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec[1].toString()));
					date = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec[1].toString()));
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("Time throws error and Can't extract time from date time payout detail table");
				}
				stamp = date + " " + time;
				fs.setCreateddate(stamp);

			}

			if (rec[2] == null || rec[2].toString().isEmpty()) {

				fs.setPayeeaccnumber("");

			} else if (rec[2] != null) {

				fs.setPayeeaccnumber(rec[2].toString());

			}

			if (rec[3] == null || rec[3].toString().isEmpty()) {

				fs.setPayeebrn("");

			} else

			if (rec[3] != null) {

				fs.setPayeebrn(rec[3].toString());

			}

			if (rec[4] == null || rec[4].toString().isEmpty()) {

				fs.setPayeebankname("");

			} else

			if (rec[4] != null) {

				fs.setPayeebankname(rec[4].toString());
			}

			if (rec[5] == null || rec[5].toString().isEmpty()) {

				fs.setPayeeemail("");

			} else

			if (rec[5] != null) {

				fs.setPayeeemail(rec[5].toString());

			}

			if (rec[6] == null || rec[6].toString().isEmpty()) {

				fs.setPayeeic("");

			} else if (rec[6] != null) {

				fs.setPayeeic(rec[6].toString());
			}

			if (rec[7] == null || rec[7].toString().isEmpty()) {

				fs.setPayeemobile("");

			} else if (rec[7] != null) {
				fs.setPayeemobile(rec[7].toString());
			}

			if (rec[8] == null || rec[8].toString().isEmpty()) {

				fs.setPayeename("");

			} else if (rec[8] != null) {
				fs.setPayeename(rec[8].toString());
			}

			if (rec[9] == null || rec[9].toString().isEmpty()) {

				fs.setPayoutamount("");

			} else if (rec[9] != null) {
				Double d = new Double(rec[9].toString());
				String pattern = "#,##0.00";
				DecimalFormat myFormatter = new DecimalFormat(pattern);
				String output = myFormatter.format(d);
				fs.setPayoutamount(output);

			}

			if (rec[10] == null || rec[10].toString().isEmpty()) {

				fs.setPayoutdate("");

			} else if (rec[10] != null) {

				String pdate = null;
				try {
					pdate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd").parse(rec[10].toString()));

				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				fs.setPayoutdate(pdate);

			}

			if (rec[11] == null) {

				fs.setPayoutstatus("Requested");

			} else if (rec[11] != null) {

				if (rec[11].toString().equalsIgnoreCase("A")) {
					fs.setPayoutstatus("To Process");

				} else if (rec[11].toString().equalsIgnoreCase("F")) {
					fs.setPayoutstatus("Failed");
				} else if (rec[11].toString().equalsIgnoreCase("S")) {
					fs.setPayoutstatus("Processing");
				} else if (rec[11].toString().equalsIgnoreCase("pp")) {
					fs.setPayoutstatus("Paid");
				} else if (rec[11].toString().equalsIgnoreCase("pd")) {
					fs.setPayoutstatus("Declined");
				}

			}

			if (rec[12] == null || rec[12].toString().isEmpty()) {

				fs.setSettledate("");

			} else if (rec[12] != null) {

				String sdate = null;
				try {
					sdate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd").parse(rec[10].toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				fs.setSettledate(sdate);
			}

			if (rec[13] == null || rec[13].toString().isEmpty()) {

				fs.setSettlenetamount("");

			} else if (rec[13] != null) {

				Double d = new Double(rec[13].toString());
				String pattern = "#,##0.00";
				DecimalFormat myFormatter = new DecimalFormat(pattern);
				String output = myFormatter.format(d);
				fs.setSettlenetamount(output);
			}

			if (rec[14] == null || rec[14].toString().isEmpty()) {

				fs.setInvoiceidproof("");

			} else if (rec[14] != null) {
				fs.setInvoiceidproof(rec[14].toString());
			}

			if (rec[15] == null || rec[15].toString().isEmpty()) {

				fs.setPaymentreason("");

			} else if (rec[15] != null) {
				fs.setPaymentreason(rec[15].toString());
			}

			if (rec[16] == null || rec[16].toString().isEmpty()) {

				fs.setSourceoffund("");

			} else if (rec[16] != null) {
				fs.setSourceoffund(rec[16].toString());
			}

			if (rec[17] == null || rec[17].toString().isEmpty()) {

				fs.setSwiftifsccode("");

			} else if (rec[17] != null) {
				fs.setSwiftifsccode(rec[17].toString());
			}

			if (rec[18] == null || rec[18].toString().isEmpty()) {
				fs.setMerchantId("");
			} else {
				fs.setMerchantId(rec[18].toString());
			}

			if (rec[19] == null || rec[19].toString().isEmpty()) {
				fs.setPaidTime("");
			} else {
				fs.setPaidTime(rec[19].toString());
			}

			if (rec[20] == null || rec[20].toString().isEmpty()) {
				fs.setPaidDate("");
			} else {
				fs.setPaidDate(rec[20].toString());
			}
			if (rec[21] == null || rec[21].toString().isEmpty()) {
				fs.setSubMerchantMID("");
			} else {
				fs.setSubMerchantMID(rec[21].toString());
			}
			if (rec[22] == null || rec[22].toString().isEmpty()) {
				fs.setMmId("");
			} else {
				fs.setMmId(rec[22].toString());
			}

			fss.add(fs);

			System.out.println(fs.getPayeename());
		}

		return fss;
	}

	@Override
	@Transactional
	public ArrayList<PayoutModel> searchPayoutData(String numberToSearch, String currentDate, String threeMonthBefore,
			String searchType, PaginationBean paginationBean) {
		Query sqlQuery = null;
		ArrayList<PayoutModel> fss = new ArrayList<PayoutModel>();
		if (!searchType.equalsIgnoreCase("payoutNo")) {
			String sql = "SELECT p.CREATED_BY,p.CREATED_DATE,p.PAYEE_ACC_NUMBER,p.PAYEE_BRN,p.PAYEE_BANK_NAME,p.PAYEE_EMAIL,p.PAYEE_IC,p.PAYEE_MOBILE,p.PAYEE_NAME,"
					+ "p.PAYOUT_AMOUNT,p.MODIFIED_BY,p.PAYOUT_STATUS,p.SETTLE_DATE,p.SETTLE_NET_AMOUNT,p.INVOICE_ID_PROOF,p.PAYMENT_REASON,p.SOURCE_OF_FUND,p.SWIFT_IFSC_CODE,p.MERCHANT_FK,p.PAID_TIME,p.PAID_DATE,p.SUB_MERCHANT_MID,ml.BUSINESS_NAME FROM PAYOUT_DETAIL p INNER JOIN MID m ON p.SUB_MERCHANT_MID=m.SUB_MERCHANT_MID INNER JOIN MERCHANT ml ON m.MERCHANT_FK=ml.ID "
					+ "WHERE p.PAYEE_ACC_NUMBER='" + numberToSearch + "'AND p.CREATED_DATE BETWEEN '" + threeMonthBefore
					+ "' and '" + currentDate + "' ORDER BY p.CREATED_DATE desc ";

			sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		} else {
			String sql = "SELECT p.CREATED_BY,p.CREATED_DATE,p.PAYEE_ACC_NUMBER,p.PAYEE_BRN,p.PAYEE_BANK_NAME,p.PAYEE_EMAIL,p.PAYEE_IC,p.PAYEE_MOBILE,p.PAYEE_NAME,"
					+ "p.PAYOUT_AMOUNT,p.MODIFIED_BY,p.PAYOUT_STATUS,p.SETTLE_DATE,p.SETTLE_NET_AMOUNT,p.INVOICE_ID_PROOF,p.PAYMENT_REASON,p.SOURCE_OF_FUND,p.SWIFT_IFSC_CODE,p.MERCHANT_FK,p.PAID_TIME,p.PAID_DATE,p.SUB_MERCHANT_MID,ml.BUSINESS_NAME FROM PAYOUT_DETAIL p INNER JOIN MID m ON p.SUB_MERCHANT_MID=m.SUB_MERCHANT_MID INNER JOIN MERCHANT ml ON m.MERCHANT_FK=ml.ID "
					+ "WHERE p.INVOICE_ID_PROOF='" + numberToSearch + "'AND p.CREATED_DATE BETWEEN '" + threeMonthBefore
					+ "' and '" + currentDate + "' ORDER BY p.CREATED_DATE desc ";

			sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		}

		List<Object[]> resultSet = sqlQuery.list();
		logger.info(resultSet);
		for (Object[] rec : resultSet) {

			PayoutModel fs = new PayoutModel();

			if (rec[0] == null || rec[0].toString().isEmpty()) {

				fs.setCreatedby("");

			} else if (rec[0] != null) {
				fs.setCreatedby(rec[0].toString());
			}

			if (rec[1] == null || rec[1].toString().isEmpty()) {

				fs.setCreateddate("");
			}

			else if (rec[1] != null) {

				String time = null;
				String date = null;
				String stamp = null;
				try {

					time = new SimpleDateFormat("HH:mm:ss")
							.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec[1].toString()));
					date = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec[1].toString()));
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("Time throws error and Can't extract time from date time payout detail table");
				}
				stamp = date + " " + time;
				fs.setCreateddate(stamp);

			}
			if (rec[2] == null || rec[2].toString().isEmpty()) {

				fs.setPayeeaccnumber("");

			} else if (rec[2] != null) {

				fs.setPayeeaccnumber(rec[2].toString());

			}

			if (rec[3] == null || rec[3].toString().isEmpty()) {

				fs.setPayeebrn("");

			} else

			if (rec[3] != null) {

				fs.setPayeebrn(rec[3].toString());

			}

			if (rec[4] == null || rec[4].toString().isEmpty()) {

				fs.setPayeebankname("");

			} else

			if (rec[4] != null) {

				fs.setPayeebankname(rec[4].toString());
			}

			if (rec[5] == null || rec[5].toString().isEmpty()) {

				fs.setPayeeemail("");

			} else

			if (rec[5] != null) {

				fs.setPayeeemail(rec[5].toString());

			}

			if (rec[6] == null || rec[6].toString().isEmpty()) {

				fs.setPayeeic("");

			} else if (rec[6] != null) {

				fs.setPayeeic(rec[6].toString());
			}

			if (rec[7] == null || rec[7].toString().isEmpty()) {

				fs.setPayeemobile("");

			} else if (rec[7] != null) {
				fs.setPayeemobile(rec[7].toString());
			}

			if (rec[8] == null || rec[8].toString().isEmpty()) {

				fs.setPayeename("");

			} else if (rec[8] != null) {
				fs.setPayeename(rec[8].toString());
			}

			if (rec[9] == null || rec[9].toString().isEmpty()) {

				fs.setPayoutamount("");

			} else if (rec[9] != null) {
				Double d = new Double(rec[9].toString());
				String pattern = "#,##0.00";
				DecimalFormat myFormatter = new DecimalFormat(pattern);
				String output = myFormatter.format(d);
				fs.setPayoutamount(output);

			}

			if (rec[10] == null || rec[10].toString().isEmpty()) {

				fs.setPayoutdate("");

			} else if (rec[10] != null) {

				String pdate = null;
				try {
					pdate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd").parse(rec[10].toString()));

				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				fs.setPayoutdate(pdate);

			}

			if (rec[11] == null) {

				fs.setPayoutstatus("Requested");

			} else if (rec[11] != null) {

				if (rec[11].toString().equalsIgnoreCase("A")) {
					fs.setPayoutstatus("To Process");

				} else if (rec[11].toString().equalsIgnoreCase("F")) {
					fs.setPayoutstatus("Failed");
				} else if (rec[11].toString().equalsIgnoreCase("S")) {
					fs.setPayoutstatus("Processing");
				} else if (rec[11].toString().equalsIgnoreCase("pp")) {
					fs.setPayoutstatus("Paid");
				} else if (rec[11].toString().equalsIgnoreCase("pd")) {
					fs.setPayoutstatus("Declined");
				}

			}

			if (rec[12] == null || rec[12].toString().isEmpty()) {

				fs.setSettledate("");

			} else if (rec[12] != null) {

				String sdate = null;
				try {
					sdate = new SimpleDateFormat("dd/MM/yyyy")
							.format(new SimpleDateFormat("yyyy-MM-dd").parse(rec[10].toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				fs.setSettledate(sdate);
			}

			if (rec[13] == null || rec[13].toString().isEmpty()) {

				fs.setSettlenetamount("");

			} else if (rec[13] != null) {

				Double d = new Double(rec[13].toString());
				String pattern = "#,##0.00";
				DecimalFormat myFormatter = new DecimalFormat(pattern);
				String output = myFormatter.format(d);
				fs.setSettlenetamount(output);
			}

			if (rec[14] == null || rec[14].toString().isEmpty()) {

				fs.setInvoiceidproof("");

			} else if (rec[14] != null) {
				fs.setInvoiceidproof(rec[14].toString());
			}

			if (rec[15] == null || rec[15].toString().isEmpty()) {

				fs.setPaymentreason("");

			} else if (rec[15] != null) {
				fs.setPaymentreason(rec[15].toString());
			}

			if (rec[16] == null || rec[16].toString().isEmpty()) {

				fs.setSourceoffund("");

			} else if (rec[16] != null) {
				fs.setSourceoffund(rec[16].toString());
			}

			if (rec[17] == null || rec[17].toString().isEmpty()) {

				fs.setSwiftifsccode("");

			} else if (rec[17] != null) {
				fs.setSwiftifsccode(rec[17].toString());
			}

			if (rec[18] == null || rec[18].toString().isEmpty()) {
				fs.setMerchantId("");
			} else {
				fs.setMerchantId(rec[18].toString());
			}

			if (rec[19] == null || rec[19].toString().isEmpty()) {
				fs.setPaidTime("");
			} else {
				fs.setPaidTime(rec[19].toString());
			}

			if (rec[20] == null || rec[20].toString().isEmpty()) {
				fs.setPaidDate("");
			} else {
				fs.setPaidDate(rec[20].toString());
			}
			if (rec[21] == null || rec[21].toString().isEmpty()) {
				fs.setSubMerchantMID("");
			} else {
				fs.setSubMerchantMID(rec[21].toString());
			}
			if (rec[22] == null || rec[22].toString().isEmpty()) {
				fs.setMmId("");
			} else {
				System.out.println("SUB_MERCHANT_NAME" + rec[22].toString());
				fs.setMmId(rec[22].toString());
			}

			fss.add(fs);
		}

		return fss;

	}

	@Transactional
	@Override
	public ArrayList<PayoutModel> exportPayoutTransaction(String date1, String date2) {

		Query sqlQuery = null;
		ArrayList<PayoutModel> fss = new ArrayList<PayoutModel>();
		try {
			String sql = "SELECT p.CREATED_BY,p.CREATED_DATE,p.PAYEE_ACC_NUMBER,p.PAYEE_BRN,p.PAYEE_BANK_NAME,p.PAYEE_EMAIL,p.PAYEE_IC,p.PAYEE_MOBILE,p.PAYEE_NAME,"
					+ "p.PAYOUT_AMOUNT,p.MODIFIED_BY,p.PAYOUT_STATUS,p.SETTLE_DATE,p.SETTLE_NET_AMOUNT,p.INVOICE_ID_PROOF,p.PAYMENT_REASON,p.SOURCE_OF_FUND,p.SWIFT_IFSC_CODE,p.MERCHANT_FK,p.PAID_TIME,p.PAID_DATE,p.SUB_MERCHANT_MID,ml.BUSINESS_NAME FROM PAYOUT_DETAIL p INNER JOIN MID m ON p.SUB_MERCHANT_MID=m.SUB_MERCHANT_MID INNER JOIN MERCHANT ml ON m.MERCHANT_FK=ml.ID WHERE p.MODIFIED_BY BETWEEN '"
					+ date1 + "' and '" + date2 + "' ORDER BY p.CREATED_DATE desc ";

			sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);

			List<Object[]> resultSet = sqlQuery.list();
			logger.info(resultSet);
			for (Object[] rec : resultSet) {

				PayoutModel fs = new PayoutModel();

				if (rec[0] == null || rec[0].toString().isEmpty()) {

					fs.setCreatedby("");

				} else if (rec[0] != null) {
					fs.setCreatedby(rec[0].toString());
				}

				if (rec[1] == null || rec[1].toString().isEmpty()) {

					fs.setCreateddate("");
				}

				else if (rec[1] != null) {

					// Sheik Changes

					String time = null;
					String date = null;
					String stamp = null;
					try {

						time = new SimpleDateFormat("HH:mm:ss")
								.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec[1].toString()));
						date = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec[1].toString()));
					} catch (Exception e) {
						// TODO: handle exception
						logger.info("Time throws error and Can't extract time from date time payout detail table");
					}
					stamp = date + " " + time;
					fs.setCreateddate(stamp);

				}

				if (rec[2] == null || rec[2].toString().isEmpty()) {

					fs.setPayeeaccnumber("");

				} else if (rec[2] != null) {

					fs.setPayeeaccnumber(rec[2].toString());

				}

				if (rec[3] == null || rec[3].toString().isEmpty()) {

					fs.setPayeebrn("");

				} else

				if (rec[3] != null) {

					fs.setPayeebrn(rec[3].toString());

				}

				if (rec[4] == null || rec[4].toString().isEmpty()) {

					fs.setPayeebankname("");

				} else

				if (rec[4] != null) {

					fs.setPayeebankname(rec[4].toString());
				}

				if (rec[5] == null || rec[5].toString().isEmpty()) {

					fs.setPayeeemail("");

				} else

				if (rec[5] != null) {

					fs.setPayeeemail(rec[5].toString());

				}

				if (rec[6] == null || rec[6].toString().isEmpty()) {

					fs.setPayeeic("");

				} else if (rec[6] != null) {

					fs.setPayeeic(rec[6].toString());
				}

				if (rec[7] == null || rec[7].toString().isEmpty()) {

					fs.setPayeemobile("");

				} else if (rec[7] != null) {
					fs.setPayeemobile(rec[7].toString());
				}

				if (rec[8] == null || rec[8].toString().isEmpty()) {

					fs.setPayeename("");

				} else if (rec[8] != null) {
					fs.setPayeename(rec[8].toString());
				}

				if (rec[9] == null || rec[9].toString().isEmpty()) {

					fs.setPayoutamount("");

				} else if (rec[9] != null) {
					Double d = new Double(rec[9].toString());
					String pattern = "#,##0.00";
					DecimalFormat myFormatter = new DecimalFormat(pattern);
					String output = myFormatter.format(d);
					fs.setPayoutamount(output);

				}

				if (rec[10] == null || rec[10].toString().isEmpty()) {

					fs.setPayoutdate("");

				} else if (rec[10] != null) {

					String pdate = null;
					try {
						pdate = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd").parse(rec[10].toString()));

					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					fs.setPayoutdate(pdate);

				}

				if (rec[11] == null) {

					fs.setPayoutstatus("Requested");

				} else if (rec[11] != null) {

					if (rec[11].toString().equalsIgnoreCase("A")) {
						fs.setPayoutstatus("To Process");

					} else if (rec[11].toString().equalsIgnoreCase("F")) {
						fs.setPayoutstatus("Failed");
					} else if (rec[11].toString().equalsIgnoreCase("S")) {
						fs.setPayoutstatus("Processing");
					} else if (rec[11].toString().equalsIgnoreCase("pp")) {
						fs.setPayoutstatus("Paid");
					} else if (rec[11].toString().equalsIgnoreCase("pd")) {
						fs.setPayoutstatus("Declined");
					}

				}

				if (rec[12] == null || rec[12].toString().isEmpty()) {

					fs.setSettledate("");

				} else if (rec[12] != null) {

					String sdate = null;
					try {
						sdate = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd").parse(rec[10].toString()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					fs.setSettledate(sdate);
				}

				if (rec[13] == null || rec[13].toString().isEmpty()) {

					fs.setSettlenetamount("");

				} else if (rec[13] != null) {

					Double d = new Double(rec[13].toString());
					String pattern = "#,##0.00";
					DecimalFormat myFormatter = new DecimalFormat(pattern);
					String output = myFormatter.format(d);
					fs.setSettlenetamount(output);
				}

				if (rec[14] == null || rec[14].toString().isEmpty()) {

					fs.setInvoiceidproof("");

				} else if (rec[14] != null) {
					fs.setInvoiceidproof(rec[14].toString());
				}

				if (rec[15] == null || rec[15].toString().isEmpty()) {

					fs.setPaymentreason("");

				} else if (rec[15] != null) {
					fs.setPaymentreason(rec[15].toString());
				}

				if (rec[16] == null || rec[16].toString().isEmpty()) {

					fs.setSourceoffund("");

				} else if (rec[16] != null) {
					fs.setSourceoffund(rec[16].toString());
				}

				if (rec[17] == null || rec[17].toString().isEmpty()) {

					fs.setSwiftifsccode("");

				} else if (rec[17] != null) {
					fs.setSwiftifsccode(rec[17].toString());
				}

				if (rec[18] == null || rec[18].toString().isEmpty()) {
					fs.setMerchantId("");
				} else {
					fs.setMerchantId(rec[18].toString());
				}

				if (rec[19] == null || rec[19].toString().isEmpty()) {
					fs.setPaidTime("");
				} else {
					fs.setPaidTime(rec[19].toString());
				}

				if (rec[20] == null || rec[20].toString().isEmpty()) {
					fs.setPaidDate("");
				} else {
					fs.setPaidDate(rec[20].toString());
				}
				if (rec[21] == null || rec[21].toString().isEmpty()) {
					fs.setSubMerchantMID("");
				} else {
					fs.setSubMerchantMID(rec[21].toString());
				}
				if (rec[22] == null || rec[22].toString().isEmpty()) {
					fs.setMmId("");
				} else {
					fs.setMmId(rec[22].toString());
				}

				fss.add(fs);

				System.out.println(fs.getPayeename());
			}

			return fss;
		}catch (Exception e) {
			
		}
		return fss;
		

	}
}
