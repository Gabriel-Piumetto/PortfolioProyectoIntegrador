package com.portfolio.gabriel.piumetto.Security.Dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
  private String token;
  
  private String bearer = "Bearer";
  
  private String nombreUsuario;
  
  private Collection<? extends GrantedAuthority> authorities;
  
  public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
    this.token = token;
    this.nombreUsuario = nombreUsuario;
    this.authorities = authorities;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public String getBearer() {
    return this.bearer;
  }
  
  public void setBearer(String bearer) {
    this.bearer = bearer;
  }
  
  public String getNombreUsuario() {
    return this.nombreUsuario;
  }
  
  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }
  
  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
