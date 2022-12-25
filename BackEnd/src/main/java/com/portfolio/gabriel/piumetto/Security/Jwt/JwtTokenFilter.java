package com.portfolio.gabriel.piumetto.Security.Jwt;

import com.portfolio.gabriel.piumetto.Security.Service.UserDetailsImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
  
  @Autowired
  JwtProvider jwtProvider;
  
  @Autowired
  UserDetailsImpl userDetailsServiceImpl;
  
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = getToken(request);
      if (token != null && this.jwtProvider.validateToken(token)) {
        String nombreUsuario = this.jwtProvider.getNombreUsuarioFromToken(token);
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication((Authentication)auth);
      } 
    } catch (Exception e) {
      logger.error("Fallel metodo doFilterInternal");
    } 
    filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
  }
  
  private String getToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer"))
      return header.replace("Bearer", ""); 
    return null;
  }
}
