package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/login"})
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    // TODO Need to redirect app PRG (POST/REDIRECT/GET)
    @PostMapping(value = "/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            user.setActive(user.getRole() != Role.SELLER);
            userService.save(user);
            return "redirect:/";

        }
    }

}
