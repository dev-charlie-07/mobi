package com.mobiversa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobiversa.dao.PayoutDao;
import com.mobiversa.model.PaginationBean;
import com.mobiversa.model.PayoutModel;
@Service
public class PayoutserviceImpl implements PayoutService{
	
	@Autowired
	PayoutDao payoutdao;
	
	@Override
	public ArrayList<PayoutModel> listPayoutTransaction(String currentDate,String  date1,String date2,PaginationBean PaginationBean) {
		// TODO Auto-generated method stub
		return payoutdao.listPayoutTransaction(currentDate,date1,date2,PaginationBean);
		
	}
	@Override
	public String sample(String str) {
		System.out.println("Hi from service");
		return "Hi "+str;
	}
	@Override
	public ArrayList<PayoutModel> searchPayoutData(String poNumber, String currentDate, String threeMonthBefore,String searchType,PaginationBean paginationBean) {
		// TODO Auto-generated method stub
		return payoutdao.searchPayoutData(poNumber, currentDate, threeMonthBefore,searchType, paginationBean);
	}
	
	
	public ArrayList<PayoutModel> exportPayoutTransaction(String date1, String date2) {
		// TODO Auto-generated method stub
		return payoutdao.exportPayoutTransaction(date1, date2);
	}

}
