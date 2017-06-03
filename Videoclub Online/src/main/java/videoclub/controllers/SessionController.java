package videoclub.controllers;

import videoclub.models.User;
import videoclub.models.UserDao;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;


import java.util.List;
import java.util.ArrayList;

@Controller
public class SessionController {

    //@Autowired
    //private UserDao userDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private User user;

    
    /*
    public ModelAndView processForm(@RequestParam(value="username",required=false) String username, 
                                    @RequestParam(value="password",required=false) String password,
                                    HttpSession sesion) {
        
        ModelAndView model = new ModelAndView("pruebas2");
        sesion.setAttribute("username", username);
        sharedInfo = username;
        System.out.println("-- aqui --");
        System.out.println(username);
        model.addObject("username", username);
        model.addObject("password", password);
        return model;

    }*/
    
    @RequestMapping("/showData")
    public ModelAndView show(){
        return null;
    }



    
}