package com.mobiversa.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private DataSource dataSource;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// Try to authenticate against TABLE1
		String query1 = "SELECT USERNAME, PASSWORD, ROLE FROM NEWAGENT WHERE USERNAME=?";
		List<Map<String, Object>> results1 = jdbcTemplate.queryForList(query1, username);
		if (!results1.isEmpty()) {
			String password = (String) results1.get(0).get("PASSWORD");
			List<GrantedAuthority> authorities = new ArrayList<>();
			String role = (String) results1.get(0).get("ROLE");
			authorities.add(new SimpleGrantedAuthority(role));
			// Add any necessary authorities for TABLE1 users
			return new User(username, password, authorities);
		}

		// Try to authenticate against TABLE2
		String query2 = "SELECT USERNAME, PASSWORD, ROLE FROM MERCHANT WHERE USERNAME=?";
		List<Map<String, Object>> results2 = jdbcTemplate.queryForList(query2, username);
		if (!results2.isEmpty()) {
			String password = (String) results2.get(0).get("PASSWORD");
			List<GrantedAuthority> authorities = new ArrayList<>();
			String role = (String) results2.get(0).get("ROLE");
			authorities.add(new SimpleGrantedAuthority(role));
			// Add any necessary authorities for TABLE2 users
			return new User(username, password, authorities);
		}

		throw new UsernameNotFoundException("User not found");
	}

}