package videoclub.controllers;

import videoclub.models.User;
import videoclub.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Class UserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  @RequestMapping("/admin/users")
  @ResponseBody
    public String adminUsers(Model model){
        model.addAttribute("saludo", "Mi primera aplicacion web Spring Boot");
        return "admin_users";
    }
    
  @RequestMapping("/admin/peliculas")
  @ResponseBody
    public String adminPeliculas(Model model){
        model.addAttribute("saludo", "Mi primera aplicacion web Spring Boot");
        return "admin_pelicula";
    }
    
  @RequestMapping("/user/profile")
  @ResponseBody
    public String profile(Model model){
        model.addAttribute("saludo", "Mi primera aplicacion web Spring Boot");
        return "profile";
    }
  
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="users/create")
  @ResponseBody
  public String create(String user, String password, String email, Boolean admin) {
    try {
      User new_user = new User(user, password, email, admin);
      userDao.create(new_user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }
  
  /**
   * Retrieve all the users
   */
  @RequestMapping(value="users/all-users")
  @ResponseBody
  public String getUsers() {
    String allUsers= "";
    try {
      List<User> users = userDao.getAll();
      for(User user:users){
        allUsers += String.valueOf(user.getId()) + " ";
        allUsers += String.valueOf(user.getUser()) + " ";
        allUsers += String.valueOf(user.getEmail()) + "/";
      }
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user is: " + allUsers;
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="users/update")
  @ResponseBody
  public String updateName(long id, String user, String password, String email, Boolean admin) {
    try {
      User new_user = userDao.getById(id);
      new_user.setPassword(password);
      new_user.setUser(user);
      new_user.setAdmin(admin);
      new_user.setEmail(email);
      userDao.update(new_user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  } 
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="users/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="users/get-by-admin")
  @ResponseBody
  public String getByAdmin(Boolean admin) {
    String userId;
    try {
      User user = userDao.getByAdmin(admin);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }
  
  @RequestMapping(value="users/get-by-id")
  @ResponseBody
  public String getById(long id) {
    String userName = "";
    try {
      User user = userDao.getById(id);
      userName = String.valueOf(user.getUser());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user is: " + userName;
  }
  
  @RequestMapping(value="users/get-by-user")
  @ResponseBody
  public String getById(String userName) {
    String userId = "";
    try {
      User user = userDao.getByUser(userName);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user is: " + userId;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;
  
} // class UserController
