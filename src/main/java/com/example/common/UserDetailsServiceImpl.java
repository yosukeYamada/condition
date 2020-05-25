package com.example.common;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Password;
import com.example.mapper.PasswordMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordMapper passwordMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {

		Password password = passwordMapper.load(mailAddress);
		// 本来ならここでDBなどからユーザを検索することになるが、サンプルのためリストに含まれるかで判定している
		if (password==null) {
			throw new UsernameNotFoundException(mailAddress);
		}
		
		UserDetails user = User.withUsername(password.getMailAddress()).password(password.getPassword()).authorities("ROLE_USER").build();
		
		// ユーザの権限
		return user;
	}

}
