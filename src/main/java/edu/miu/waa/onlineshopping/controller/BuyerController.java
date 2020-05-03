package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.CardItem;
import edu.miu.waa.onlineshopping.domain.model.Cart;
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

import javax.servlet.ServletContext;
import java.util.List;

@Controller
@RequestMapping("/buyer**")
@SessionAttributes({"products", "user"})
public class BuyerController {

    private final ProductService productService;
    private final UserService userService;
    private final ServletContext servletContext;

    @Autowired
    public BuyerController(ProductService productService, UserService userService, ServletContext servletContext) {
        this.productService = productService;
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping(value = {""})
    public String home(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "buyer/buyer-home";
    }

    @PostMapping(value = "/carts/{productId}/add")
    public String home(@PathVariable  Integer productId, Product product, Model model) {
        Cart cart = (Cart)servletContext.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }
        cart.addItemToCart(productId);
        servletContext.setAttribute("cart", cart);
        System.out.println(cart);
        return "redirect:/buyer/";
    }
}
