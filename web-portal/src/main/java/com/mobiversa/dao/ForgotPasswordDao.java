package com.mobiversa.dao;

public interface ForgotPasswordDao {

	int changeAgentPassWord(String Username, String newPwd, String OldPwd);

}
