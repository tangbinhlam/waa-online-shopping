package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.User;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"users"})
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping("reviewSeller")
    public String reviewSeller(Model model) {
        List<User> users = userService.findByActiveFalse();
        model.addAttribute("users", users);
        return "admin/approve-seller";
    }

    @PostMapping("/users/{userId}/approve")
    public String approveSeller(@PathVariable Integer userId, Model model) {
        userService.approveSeller(userId);
        return "redirect:/admin/reviewSeller";
    }
}
