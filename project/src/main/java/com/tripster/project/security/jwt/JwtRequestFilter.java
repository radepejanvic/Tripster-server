package com.tripster.project.security.jwt;

import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		if (request.getRequestURL().toString().contains("/api/")){
			System.out.println("---- Method:" +request.getMethod()+"  URL: "+request.getRequestURL());
			System.out.println("---- Authrization: " + request.getHeader("Authorization"));

			String header = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;

			if (header != null && header.contains("Bearer")){
				jwtToken = header.substring(header.indexOf("Baerer")+7);
				System.out.println(">>JWT TOKEN: " + jwtToken);

				try{
					username = jwtTokenUtil.getUsernameFormToken(jwtToken);
					UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
					if(jwtTokenUtil.validateToken(jwtToken,userDetails)){
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
								new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						System.out.println("Username: " + userDetails.getUsername() + ", role: " + userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}catch (IllegalAccessError e){
					System.out.println("Unable to get JWT Token.");
				} catch (ExpiredJwtException e) {
					System.out.println("JWT Token has expired.");
				} catch (io.jsonwebtoken.MalformedJwtException e) {
					System.out.println("Bad JWT Token.");
				}
			} else {
			logger.warn("JWT Token does not exist.");
			}
		}
		chain.doFilter(request, response);
	}

}