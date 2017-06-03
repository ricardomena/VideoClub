package videoclub.models;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PeliculaDao {
  
  @PersistenceContext
  private EntityManager entityManager;

  public void create(Pelicula pelicula) {
    entityManager.persist(pelicula);
    return;
  }
  

  @SuppressWarnings("unchecked")
  public List<Pelicula> getAll() {
    return entityManager.createQuery("from Pelicula").getResultList();
  }

  public void delete(Pelicula pelicula) {
    if (entityManager.contains(pelicula))
      entityManager.remove(pelicula);
    else
      entityManager.remove(entityManager.merge(pelicula));
    return;
  }
  
  public void update(Pelicula pelicula) {
    entityManager.merge(pelicula);
    return;
  }

  public Pelicula getById(long id) {
    return entityManager.find(Pelicula.class, id);
  }

  public List<Pelicula> getByName(String name) {
    name = "%"+name+"%";
    return entityManager.createQuery(
        "select p from Pelicula p where p.name LIKE :name")
        .setParameter("name", name).setMaxResults(10).getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Pelicula> getTop() {
   return entityManager.createQuery("select p from Pelicula p order by p.rating").setMaxResults(5).getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Pelicula> getNew() {
    return entityManager.createQuery("select p from Pelicula p order by p.year desc, p.id desc").setMaxResults(5).getResultList();
  }
  
}
