package videoclub.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
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
  private Boolean admin;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public User() { }

  public User(long id) { 
    this.id = id;
  }

  public User(String user, String password, String email, Boolean admin) {
    this.user = user;
    this.password = password;
    this.admin = admin;
    this.email = email;
  }

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
  
  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }
  
} // class User
