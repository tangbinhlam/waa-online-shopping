package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.service.ProductService;
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
@RequestMapping("/seller**")
@SessionAttributes({"products"})
public class SellerController {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public SellerController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping(value = {""})
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Product> products = productService.findAllBySupplier(user.getUserId());
        model.addAttribute("products", products);
        return "seller/product-list";
    }

    @PostMapping("/products/{productId}/delete")
    public String deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return "redirect:/seller/";
    }

}
