package com.mobiversa.dao;

import java.util.ArrayList;

import com.mobiversa.model.PaginationBean;
import com.mobiversa.model.PayoutModel;

public interface PayoutDao {


	public ArrayList<PayoutModel> searchPayoutData(String numberToSearch, String currentDate, String threeMonthBefore,String searchType,PaginationBean paginationBean);

//	public ArrayList<PayoutModel> listPayoutTransaction(String currentDate, String date1, String date2,
//			PaginationBean paginationBean);

	public ArrayList<PayoutModel> listPayoutTransaction(String currentDate, String date1, String date2,
			PaginationBean paginationBean);

	

	ArrayList<PayoutModel> exportPayoutTransaction(String date1, String date2);

	
}
