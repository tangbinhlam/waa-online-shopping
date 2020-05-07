package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.Order;
import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.vo.CreateProductCommand;
import edu.miu.waa.onlineshopping.service.OrderService;
import edu.miu.waa.onlineshopping.service.ProductService;
import edu.miu.waa.onlineshopping.service.UserService;
import edu.miu.waa.onlineshopping.uploadfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seller**")
@SessionAttributes({"products", "product", "user"})
public class SellerController {

    private final ProductService productService;
    private final UserService userService;
    private final StorageService storageService;
    private final OrderService orderService;

    @ModelAttribute("user")
    public void getUser(Model model){
        User user = (User)model.getAttribute("user");
        if(user==null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("user", userService.findUserByUserName(auth.getName()));
        }
    }

    @Autowired
    public SellerController(ProductService productService, UserService userService, StorageService storageService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.storageService = storageService;
        this.orderService = orderService;
    }

    @GetMapping(value = {""})
    public String home(Model model) {
        User user = (User)model.getAttribute("user");
        List<Product> products = productService.findAllBySupplier(user.getUserId());
        model.addAttribute("products", products);
        return "seller/product-list";
    }

    @GetMapping(value = "/products")
    public String newEntryProduct(Model model) {
        model.addAttribute("product", new CreateProductCommand());
        return "seller/product-edit";
    }

    @GetMapping(value = "/products/{productId}/update")
    public String updateProduct(@PathVariable Integer productId, Model model) {
        //TODO: check exception
        Product product = productService.findProductByProductId(productId);
        Resource resource = storageService.loadAsResource(product.getImagePath());

        return "seller/product-edit";
    }

    @PostMapping(value = "/products")
    public String addNewProduct(@Valid CreateProductCommand createProductCommand, BindingResult bindingResult, Model model) {
        String pathFileName = storageService.store(createProductCommand.getImagePath());
        if (bindingResult.hasErrors()) {
            return "seller/product-edit";
        } else {
            Product newProuct = Product.of(1,
                    createProductCommand.getProductName(),
                    pathFileName, createProductCommand.getDescription(),
                    (User) model.getAttribute("user"),
                    createProductCommand.getPrice(),
                    createProductCommand.getProducer());
            productService.save(newProuct);
        }
        return "redirect:/seller/";
    }

    @PostMapping("/products/{productId}/delete")
    public String deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return "redirect:/seller/";
    }

    @GetMapping(value = "/orders")
    public String reviewOrders(Model model) {
        User user = (User)model.getAttribute("user");
        List<Order> orders = orderService.findOrderBySeller(user.getUserId());
        model.addAttribute("orders", orders);
        return "seller/order-list";
    }

    @GetMapping(value = "/orders/{orderId}")
    public String reviewOrders(@PathVariable Integer orderId, Model model) {
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "seller/order-detail-view";
    }

    @PostMapping(value = "/orders/{orderId}/approve")
    public String approveOrder(@PathVariable Integer orderId, Model model) {
        orderService.approvedOrder(orderId);
        return "redirect:/seller/orders";
    }

    @PostMapping(value = "/orders/{orderId}/reject")
    public String rejectOrder(@PathVariable Integer orderId, Model model) {
        orderService.rejectOrder(orderId);
        return "redirect:/seller/orders";
    }

    @PostMapping(value = "/orders/{orderId}/delivered")
    public String deliveredOrder(@PathVariable Integer orderId, Model model) {
        orderService.changeToDeliveredOrder(orderId);
        return "redirect:/seller/orders";
    }

}
