package com.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Password;
import com.example.mapper.PasswordMapper;
import com.example.domain.Authority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordMapper passwordMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {

		Password password = passwordMapper.confirmDuplication(mailAddress);
		System.out.println(mailAddress);
		// 本来ならここでDBなどからユーザを検索することになるが、サンプルのためリストに含まれるかで判定している
		if (password==null) {
			System.out.println("ユーザ取得失敗");
			System.out.println(password);
			throw new UsernameNotFoundException(mailAddress);
		}
		// ここで権限を付与
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(password.getAuthority()==Authority.ADMIN.getAuthorityId()) {
			authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		UserDetails user = User.withUsername(password.getMailAddress()).password(password.getPassword()).authorities(authorityList).build();
		
		// ユーザの権限
		return user;
	}

}
