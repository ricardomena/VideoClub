package videoclub.models;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the Pelicula entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */

@Repository
@Transactional
public class PeliculaDao {
  
  
  /**
   * Save the pelicula in the database.
   */
  public void create(Pelicula pelicula) {
    entityManager.persist(pelicula);
    return;
  }
  
    /**
   * Return all the peliculas stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<Pelicula> getAll() {
    return entityManager.createQuery("from Pelicula").getResultList();
  }
  
  /**
   * Delete the pelicula from the database.
   */
  public void delete(Pelicula pelicula) {
    if (entityManager.contains(pelicula))
      entityManager.remove(pelicula);
    else
      entityManager.remove(entityManager.merge(pelicula));
    return;
  }
  
    /**
   * Update the passed pelicula in the database.
   */
  public void update(Pelicula pelicula) {
    entityManager.merge(pelicula);
    return;
  }

  /**
   * Return the pelicula having the passed id.
   */
  public Pelicula getById(long id) {
    return entityManager.find(Pelicula.class, id);
  }
  
  /**
   * Return the pelicula having the passed id.
   */
  public List<Pelicula> getByName(String name) {
    name = "%"+name+"%";
    return entityManager.createQuery(
        "select p from Pelicula p where p.name LIKE :name")
        .setParameter("name", name).getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Pelicula> getTop() {
   return entityManager.createQuery("select p from Pelicula p order by p.rating").setMaxResults(5).getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Pelicula> getNew() {
    return entityManager.createQuery("select p from Pelicula p order by p.year desc, p.id desc").setMaxResults(5).getResultList();
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class PeliculaDao
