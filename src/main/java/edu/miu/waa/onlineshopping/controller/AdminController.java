package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.ProductComment;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.service.ProductCommentService;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"users"})
public class AdminController {


    private final UserService userService;
    private final ProductCommentService productCommentService;

    @Autowired
    public AdminController(UserService userService, ProductCommentService productCommentService) {
        this.userService = userService;
        this.productCommentService = productCommentService;
    }

    @GetMapping(value = {""})
    public String home(Model model) {
        return "redirect:/admin/review-seller";
    }

    @GetMapping("review-seller")
    public String reviewSeller(Model model) {
        List<User> users = userService.findByActiveFalse();
        model.addAttribute("users", users);
        return "admin/approve-seller";
    }

    @PostMapping("/users/{userId}/approve")
    public String approveSeller(@PathVariable Integer userId, Model model) {
        userService.approveSeller(userId);
        return "redirect:/admin/review-seller";
    }

    @PostMapping("productComments/{productCommentId}/approve")
    public String approveComment(@PathVariable Integer productCommentId, Model model) {
        productCommentService.approve(productCommentId);
        return "redirect:/admin/review-comment";
    }

    @PostMapping("productComments/{productCommentId}/reject")
    public String rejectComment(@PathVariable Integer productCommentId, Model model) {
        productCommentService.reject(productCommentId);
        return "redirect:/admin/review-comment";
    }

    @GetMapping("review-comment")
    public String reviewComment(Model model) {
        List<ProductComment> productComments = productCommentService.getNewProductComments();
        model.addAttribute("productComments", productComments);
        return "admin/approve-review";
    }
}
