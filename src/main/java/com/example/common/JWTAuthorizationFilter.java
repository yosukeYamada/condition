package com.example.common;

import static com.example.common.SecurityConstants.HEADER_STRING;
import static com.example.common.SecurityConstants.SECRET;
import static com.example.common.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.domain.Authority;
import com.example.mapper.DepMapper;
import com.example.mapper.UserMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		Enumeration<String> headers = req.getHeaderNames();

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		// AuthorizationヘッダのBearer Prefixである場合
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DepMapper depMapper;

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "").replace(TOKEN_PREFIX, "").trim()).getBody()
					.getSubject();
			Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "").replace(TOKEN_PREFIX, "").trim()).getBody();
			List grants = (List) claims.get("role");
			String[] arrayRole = new String[grants.size()];
			for (int i = 0; i < grants.size(); i++) {
				LinkedHashMap grant = (LinkedHashMap) grants.get(i);
				String rolestr = (String) grant.get("authority");
				arrayRole[i] = rolestr;
			}
			if (user != null) {
				List<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(arrayRole);
				return new UsernamePasswordAuthenticationToken(user, null, roles);
			}
			return null;
		}
		return null;
	}

}