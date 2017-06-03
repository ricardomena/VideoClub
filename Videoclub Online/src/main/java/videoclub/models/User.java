package videoclub.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.persistence.ElementCollection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
@Table(name = "usuarios")
public class User {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  private String user;
  
  @NotNull
  private String password;
  
  @NotNull
  private String email;
  
  @NotNull
  private int admin;
  
  @ElementCollection(fetch = FetchType.EAGER)   
  private List<GrantedAuthority> roles;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  public User() {}   
  
  public User(long id) { 
    this.id = id;
  }
  
  public User(String user, String password, String email, int admin, List<GrantedAuthority> roles) {
    this.user = user;
    this.password = new BCryptPasswordEncoder().encode(password);        
    this.admin = admin;
    this.email = email;
    this.roles = roles;
  }
  /*
  public User( String user,  String password, List<GrantedAuthority> roles) {
    this.user = user;
    this.password = new BCryptPasswordEncoder().encode(password);   
    this.roles = roles;
  }*/

  public long getId() {
    return id;
  }

  public String getUser() {
    return user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public int getAdmin() {
    return admin;
  }

  public void setAdmin(int admin) {
    this.admin = admin;
  }
  
  public List<GrantedAuthority> getRoles(){
    return roles;
  } 
  
  public void setRoles(List<GrantedAuthority> roles){
    this.roles = roles;
  }
  
} // class User
