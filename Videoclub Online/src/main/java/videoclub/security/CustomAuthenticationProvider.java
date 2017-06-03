package videoclub.security;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpSession;

import videoclub.models.UserDao;
import videoclub.models.User;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private User user;
    
    private HttpSession sesion;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println(username);
        System.out.println(password);
        
        //sesion.setAttribute("username",username);
        User userTemp = userDao.getByUser(username);
        user.setUser(username);
        user.setAdmin(userTemp.getAdmin());
        if (userTemp == null) {
            System.out.println("------ 1 -------");
            throw new BadCredentialsException("User not found");
        }
        if (!new BCryptPasswordEncoder().matches(password, userTemp.getPassword())) {
            System.out.println("------ 2 -------");
            System.out.println(username);
            System.out.println(password);
            throw new BadCredentialsException("Wrong password");
        }
        List<GrantedAuthority> roles = userTemp.getRoles();
        return new UsernamePasswordAuthenticationToken(username,password,roles);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}