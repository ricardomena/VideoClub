package videoclub.controllers;

import videoclub.models.Pelicula;
import videoclub.models.PeliculaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import java.util.List;


@Controller
@RequestMapping(value = "/peliculas")
public class PeliculaController {

  @Autowired
  private PeliculaDao peliculaDao;

  @RequestMapping( value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
    public ModelAndView peliculaInfo(@PathVariable("id") Long id, Model model){
        Pelicula pelicula = peliculaDao.getById(id);
        return new ModelAndView("pelicula_info").addObject("pelicula", pelicula);
    }

  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String name, String url) {
    try {
      Pelicula new_pelicula = new Pelicula();
      peliculaDao.create(new_pelicula);
    }
    catch (Exception ex) {
      return "Error creating the pelicula: " + ex.toString();
    }
    return "Pelicula succesfully created!";
  }
  
  @RequestMapping(value="/all-peliculas")
  @ResponseBody
  public String getPeliculas() {
    String allPeliculas= "";
    try {
      List<Pelicula> peliculas = peliculaDao.getAll();
      for(Pelicula pelicula:peliculas){
        allPeliculas += String.valueOf(pelicula.getId()) + " ";
        allPeliculas += String.valueOf(pelicula.getName()) + " ";
      }
    }
    catch (Exception ex) {
      return "Pelicula not found: " + ex.toString();
    }
    return "The pelicula is: " + allPeliculas;
  }
  
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String name, String url) {
    try {
      Pelicula new_pelicula = peliculaDao.getById(id);
      new_pelicula.setName(name);
      new_pelicula.setUrl(url);
      peliculaDao.update(new_pelicula);
    }
    catch (Exception ex) {
      return "Error updating the pelicula: " + ex.toString();
    }
    return "Pelicula succesfully updated!";
  } 
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      Pelicula pelicula = new Pelicula(id);
      peliculaDao.delete(pelicula);
    }
    catch (Exception ex) {
      return "Error deleting the pelicula: " + ex.toString();
    }
    return "Pelicula succesfully deleted!";
  }
  
  @RequestMapping(value="/get-by-id")
  @ResponseBody
  public String getById(long id) {
    String peliculaName = "";
    try {
      Pelicula pelicula = peliculaDao.getById(id);
      peliculaName = String.valueOf(pelicula.getName());
    }
    catch (Exception ex) {
      return "Pelicula not found: " + ex.toString();
    }
    return "The movie is: " + peliculaName;
  }
  
  @RequestMapping(value= "/get-by-name")
  @ResponseBody
  public ModelAndView getByName( String name) {
      //getByID(@RequestParam()
      name = "p";
      List<Pelicula> peliculas = peliculaDao.getByName(name);
      if(peliculas.isEmpty()){
        return new ModelAndView("pruebas");
      }
      ModelAndView model = new ModelAndView("pruebas");
      String salida = "";
      for(Pelicula pelicula:peliculas){
        salida = salida + "Nombre: " + pelicula.getName() + " Id: " + pelicula.getId() + "\n";
      }
      return model.addObject("peliculas",peliculas).addObject("pelicula1",peliculas.get(0));
  }
  
}
