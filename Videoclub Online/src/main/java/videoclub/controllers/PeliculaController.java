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

/**
 * Class PeliculaController
 */
@Controller
@RequestMapping(value = "/peliculas")
public class PeliculaController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  @RequestMapping( value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
    public String peliculaInfo(@PathVariable("id") Long id, Model model){
        Pelicula pelicula = peliculaDao.getById(id);
        model.addAttribute("pelicula", pelicula);
        return "pelicula_info";
    }

  /**
   * Create a new pelicula with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String name, String url) {
    try {
      Pelicula new_pelicula = new Pelicula(name, url);
      peliculaDao.create(new_pelicula);
    }
    catch (Exception ex) {
      return "Error creating the pelicula: " + ex.toString();
    }
    return "Pelicula succesfully created!";
  }
  
  /**
   * Retrieve all the peliculas
   */
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
  
  /**
   * Update the email and the name for the pelicula indentified by the passed id.
   */
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
  
  /**
   * Delete the pelicula with the passed id.
   */
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
  

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the PeliculaDao used inside this controller.
  @Autowired
  private PeliculaDao peliculaDao;
  
} // class PeliculaController
