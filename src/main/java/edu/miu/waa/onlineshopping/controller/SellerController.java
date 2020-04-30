package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.vo.CreateProductCommand;
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
import java.util.List;

@Controller
@RequestMapping("/seller**")
@SessionAttributes({"products", "product", "user"})
public class SellerController {

    private ProductService productService;
    private UserService userService;
    private StorageService storageService;

    @Autowired
    public SellerController(ProductService productService, UserService userService, StorageService storageService) {
        this.productService = productService;
        this.userService = userService;
        this.storageService = storageService;
    }

    @GetMapping(value = {""})
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user", user);
        List<Product> products = productService.findAllBySupplier(user.getUserId());
        model.addAttribute("products", products);
        return "seller/product-list";
    }

    @GetMapping(value = "/products/new")
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

    @PostMapping(value = "/products/new")
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

}
