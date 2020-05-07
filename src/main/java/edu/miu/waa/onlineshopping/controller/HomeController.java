package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.CardItem;
import edu.miu.waa.onlineshopping.domain.model.Cart;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final ServletContext servletContext;

    @Autowired
    public HomeController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping(value = {""})
    public ModelAndView home(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if (user==null) {
            modelAndView.setViewName("redirect:/login");
        }else if (user.getRole().equals(Role.ADMIN)){
            modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("admin/home");
        } else if (user.getRole().equals(Role.SELLER)) {
            modelAndView.setViewName("redirect:/seller/");
        } else if(user.getRole().equals(Role.BUYER)) {
            servletContext.setAttribute("cart", new Cart(auth.getName(), new ArrayList<CardItem>()));
            modelAndView.setViewName("redirect:/buyer/");
        }
        return modelAndView;
    }
}
