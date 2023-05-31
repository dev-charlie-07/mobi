package com.mobiversa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobiversa.common.bo.Merchant;

@Service
public class ForgotPasswordDaoImpl implements ForgotPasswordDao {

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder encoder;

	@Transactional
	@Override
	public int changeAgentPassWord(String Username, String newPwd, String OldPwd) {

		String sql = "SELECT PASSWORD FROM NEWAGENT WHERE USERNAME='" + Username + "'";

		Query sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<String> resultSet = sqlQuery.list();

		if (!resultSet.isEmpty()) {
			boolean matches = encoder.matches(OldPwd, resultSet.get(0));

			System.out.println(matches);
			if (matches == true) {
				// String query = "UPDATE NEWAGENT SET PASSWORD ='"+ encoder.encode(newPwd)+"'
				// where USERNAME =:userName";
				String query = "UPDATE NEWAGENT SET PASSWORD=:password WHERE USERNAME = :username";
				Query sqlQuery1 = sessionFactory.getCurrentSession().createSQLQuery(query);
				sqlQuery1.setParameter("password", encoder.encode(newPwd));
				sqlQuery1.setParameter("username", Username);
				int rs = sqlQuery1.executeUpdate();
				return rs;
			}
		}
		String query2 = "SELECT PASSWORD FROM MERCHANT WHERE USERNAME='" + Username + "'";
		Query sqlQuery1 = sessionFactory.getCurrentSession().createSQLQuery(query2);

		List<String> resultSet1 = sqlQuery1.list();
		System.out.println(resultSet1.get(0));
		if (!resultSet1.isEmpty()) {
			boolean matches = encoder.matches(OldPwd, resultSet1.get(0));
			System.out.println(matches);
			System.out.println(matches);
			if (matches == true) {
				String query = "UPDATE MERCHANT SET PASSWORD=:password WHERE USERNAME = :username";
				Query sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery(query);
				sqlQuery2.setParameter("password", encoder.encode(newPwd));
				sqlQuery2.setParameter("username", Username);
				int rs = sqlQuery2.executeUpdate();
				return rs;
			}
		}

		return 0;
	}

}
