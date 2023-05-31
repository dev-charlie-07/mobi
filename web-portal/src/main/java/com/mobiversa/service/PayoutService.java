package com.mobiversa.service;

import java.util.ArrayList;

import com.mobiversa.model.PaginationBean;
import com.mobiversa.model.PayoutModel;

public interface PayoutService {
	public ArrayList<PayoutModel> listPayoutTransaction(String  currentDate,String  date1,String date2,PaginationBean PaginationBean);

	
	String sample(String str);

//	void ListofBnplSummary(PayoutModel payout, String data, String date1, String txntype);

	ArrayList<PayoutModel> searchPayoutData(String poNumber, String currentDate, String threeMonthBefore,
			String searchType, PaginationBean paginationBean);

	public ArrayList<PayoutModel> exportPayoutTransaction(String date1, String date2);
}
