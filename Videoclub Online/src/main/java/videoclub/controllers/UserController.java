package videoclub.controllers;

import videoclub.models.User;
import videoclub.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestParam;
 
import java.util.List;


@Controller
public class UserController {

  @Autowired
  private UserDao userDao;
  
  @Secured("ROLE_ADMIN") 
  @RequestMapping("/admin/users")
  @ResponseBody
    public ModelAndView adminUsers(){
      return new ModelAndView("admin_users");
    }
  
  @Secured("ROLE_ADMIN")  
  @RequestMapping("/admin/peliculas")
  @ResponseBody
    public ModelAndView adminPeliculas(){
        return new ModelAndView("admin_pelicula");
    }
    
  @Secured("ROLE_ADMIN")  
  @RequestMapping("/admin")
  @ResponseBody
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }
    
  @RequestMapping("/user/profile")
  @ResponseBody
    public String profile(Model model){
        model.addAttribute("saludo", "Mi primera aplicacion web Spring Boot");
        return "profile";
    }

  @RequestMapping(value="users/create")
  @ResponseBody
  public String create(String user, String password, String email, int admin, List<GrantedAuthority> roles) {
    try {
      User new_user = new User(user, password, email, admin, roles);
      userDao.create(new_user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }
  
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
  
  @RequestMapping(value="users/update")
  @ResponseBody
  public String updateName(long id, String user, String password, String email, int admin) {
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
  
  @RequestMapping(value= "/get-by-user")
  @ResponseBody
  public ModelAndView getByUser(@RequestParam(value = "search", required = false)String search) {
    //search = "p";
      List<User> usuarios = userDao.getByUserList(search);
      if(usuarios.isEmpty()){
        return new ModelAndView("pruebas");
      }
      return new ModelAndView("pruebas3").addObject("usuarios",usuarios).addObject("usuario1",usuarios.get(0));
  }
}
