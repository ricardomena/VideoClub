package videoclub.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an Name for this web application.
 */
@Entity
@Table(name = "peliculas")
public class Pelicula {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String url;
  
  private String description;
  
  private Integer year;
  
  private String director;
  
  private String casting;
  
  private String url_poster;
  
  private int rating;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public Pelicula() { }

  public Pelicula(long id) { 
    this.id = id;
  }

  public Pelicula(String name, String url) {
    this.name = name;
    this.url = url;
  }
  
  public Pelicula(String name, String url, String description, Integer year, String director, String casting, String url_poster, int rating) {
    this.name = name;
    this.url = url;
    this.description = description;
    this.year = year;
    this.director = director;
    this.casting = casting;
    this.url_poster = url_poster;
    this.rating = rating;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }
  
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }
  
  public String getCasting() {
    return casting;
  }

  public void setCasting(String casting) {
    this.casting = casting;
  }
  
  public String getUrlposter() {
    return url_poster;
  }

  public void setUrlposter(String url_poster) {
    this.url_poster = url_poster;
  }
  
  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }
  
  
} // class Name
