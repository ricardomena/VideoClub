package videoclub;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.PostConstruct;

import videoclub.models.User;
import videoclub.models.UserDao;

import java.util.Arrays;


@Component public class DatabaseLoader {
   @Autowired   
   private UserDao userDao;
   @PostConstruct   private void initDatabase() {      
      // User #1: "user", with password "p1" and role "USER"   
      if(userDao.getByUser("user5") == null){
         GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };  
         userDao.create(new User("user5", "p1", "user5@videoclub.com", 0, Arrays.asList(userRoles)));
      }
      //user.save(new User("user", "p1", Arrays.asList(userRoles)));
      // User #2: "root", with password "p2" and roles "USER" and "ADMIN"  
      if(userDao.getByUser("root2") == null){
         GrantedAuthority[] adminRoles = { new SimpleGrantedAuthority("ROLE_USER"),         
                                           new SimpleGrantedAuthority("ROLE_ADMIN") }; 
         userDao.create(new User("root2", "p2", "e@e.com", 1, Arrays.asList(adminRoles)));
      }
      //user.save(new User("root", "p2", Arrays.asList(adminRoles)));  
   } 
   
}
