package videoclub.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the User entity.
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
public class UserDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(User user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return entityManager.createQuery("from User").getResultList();
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
    /**
   * Update the passed user in the database.
   */
  public void update(User user) {
    entityManager.merge(user);
    return;
  }
  
  /**
   * Return the user having the passed email.
   */
  public User getByAdmin(Boolean admin) {
    return (User) entityManager.createQuery(
        "from User where admin = :admin")
        .setParameter("admin", admin)
        .getSingleResult();
  }

  /**
   * Return the user having the passed id.
   */
  public User getById(long id) {
    return entityManager.find(User.class, id);
  }
  
  /**
   * Return the user having the passed id.
   */
  public User getByUser(String user) {
    User usuario = null;
    List query = entityManager.createQuery(
        "select u from User u where u.user = :user")
        .setParameter("user", user).getResultList();
    if(query.isEmpty())
      return usuario;
    return (User) query.get(0);

  }



  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class UserDao
