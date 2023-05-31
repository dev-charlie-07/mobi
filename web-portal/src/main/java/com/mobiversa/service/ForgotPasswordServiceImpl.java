package com.mobiversa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobiversa.dao.ForgotPasswordDao;



@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{
	@Autowired
	ForgotPasswordDao ForgotPasswordDao;
	
	@Override
	public int changeAgentPassWord(String Username, String newPwd, String OldPwd) {
		// TODO Auto-generated method stub
		return ForgotPasswordDao.changeAgentPassWord(Username, newPwd, OldPwd);
	}
	
	
}
