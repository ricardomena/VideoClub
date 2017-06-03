package videoclub.controllers;

import videoclub.models.Pelicula;
import videoclub.models.PeliculaDao;

import videoclub.models.User;
import videoclub.models.UserDao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import java.net.URL;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

  @Autowired
  private User user;

  @Autowired
  private PeliculaDao peliculaDao;
  
  @RequestMapping("/")
  public ModelAndView index() {
    ModelAndView model = new ModelAndView("index");
    return model;
  }
  
  @RequestMapping("/pruebas2")
  public ModelAndView pruebas2() {
    ModelAndView model = new ModelAndView("pruebas2");
    //String name = user.getUser();
    System.out.println(user.getUser());
    System.out.println(user.getAdmin());
    model.addObject("admin",user.getAdmin());
    return model;
  }
  
  @RequestMapping("/register")
  public ModelAndView register() {
        ModelAndView model = new ModelAndView("register");
        return model;
    }
  /*
  @RequestMapping(value="/principal")
  @ResponseBody
  public ModelAndView principal(){
    String allPeliculas= "";
    ModelAndView model = new ModelAndView("principal");
    try {
      List<Pelicula> peliculasNew = peliculaDao.getNew();
      List<Pelicula> peliculasTop = peliculaDao.getTop();
      String poster;
      int i=1;
      for(Pelicula pelicula:peliculasNew){
         poster = pelicula.getUrlposter();
        model.addObject("new"+i,poster);
        i++;
      }
      i=1;
      for(Pelicula pelicula:peliculasTop){
        poster = pelicula.getUrlposter();
        model.addObject("top"+i,poster);
        i++;
      }
    }
    catch (Exception ex) {
      return model;
    }
    return model;
  }*/

    @Secured("ROLE_ADMIN")
    @RequestMapping("/text")
    public ModelAndView text() {
      ModelAndView model = new ModelAndView("pruebas2");
      List<Pelicula> pelicula = peliculaDao.getByName("pok");
      if(pelicula.isEmpty()){
        return model;
      }
      model.addObject("greetings", "Hello world!");
      model.addObject("url", pelicula.get(0).getUrlposter());
      model.addObject("pelicula",pelicula.get(0).getName());
      
      return model;
  }
    //@Secured("ROLE_ADMIN")
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login1");
    }/*@RequestParam(value="username",required=false) String username, 
                                    @RequestParam(value="password",required=false) String password,
                                    HttpSession sesion) {
        
        ModelAndView model = new ModelAndView("login");
        sesion.setAttribute("username", username);
        //sharedInfo = username;
        System.out.println("-- aqui --");
        System.out.println(username);
        model.addObject("username", username);
        model.addObject("password", password);
        return model;}
    /*) {
        return new ModelAndView("login1");
    }*/
    
    @RequestMapping("/logout")
        public String logout() {
        return "salí";
    }

}
