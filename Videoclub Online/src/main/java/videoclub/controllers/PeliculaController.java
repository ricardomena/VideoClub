package videoclub.controllers;

import videoclub.models.Pelicula;
import videoclub.models.PeliculaDao;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.List;





@Controller
@RequestMapping(value = "/peliculas")
public class PeliculaController {

  @Autowired
  private PeliculaDao peliculaDao;

  @RequestMapping( value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
    public ModelAndView peliculaInfo(@PathVariable("id") Long id){
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
  public ModelAndView getByName(@RequestParam(value = "search", required = false)String search) {
      search = "p";
      List<Pelicula> peliculas = peliculaDao.getByName(search);
      if(peliculas.isEmpty()){
        return new ModelAndView("pruebas");
      }
      return new ModelAndView("pruebas").addObject("peliculas",peliculas).addObject("pelicula1",peliculas.get(0));
  }
  
  @RequestMapping(value= "/get")
  @ResponseBody
  public ModelAndView get(@RequestParam(value = "search", required = false)String search) throws IOException, ParseException
  {
        StringBuilder respuesta = new StringBuilder();
        String link = "https://tv-v2.api-fetch.website/movies/1?sort=last%20added&keywords=Rogue%20One:%20A%20Star%20Wars%20Story";

        URL url =  new URL(link);
        HttpURLConnection conexion = null;
                
        conexion = (HttpURLConnection) url.openConnection();
        conexion.addRequestProperty("User-Agent","Mozilla/5.0");


        conexion.getInputStream();
        BufferedInputStream in = new BufferedInputStream(conexion.getInputStream());
        int ch;
        while ((ch = in.read()) != -1) {
          respuesta.append((char) ch);
        }
        System.out.println(respuesta);
        return new ModelAndView("pruebas4").addObject("out",respuesta);
  }
  
  //https://tv-v2.api-fetch.website/movies/1?sort=last%20added&keywords=
}
