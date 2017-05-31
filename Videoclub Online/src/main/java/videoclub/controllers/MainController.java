package videoclub.controllers;

import videoclub.models.Pelicula;
import videoclub.models.PeliculaDao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;


@Controller
public class MainController {

  @Autowired
  private PeliculaDao peliculaDao;

  @RequestMapping("/")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
  
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
  }


    @RequestMapping("/text")
    public ModelAndView text() {
      ModelAndView model = new ModelAndView("pruebas");
      List<Pelicula> pelicula = peliculaDao.getByName("pok");
      if(pelicula.isEmpty()){
        return model;
      }
      model.addObject("greetings", "Hello world!");
      model.addObject("url", pelicula.get(0).getUrlposter());
      model.addObject("pelicula",pelicula.get(0).getName());
      return model;
  }

}
