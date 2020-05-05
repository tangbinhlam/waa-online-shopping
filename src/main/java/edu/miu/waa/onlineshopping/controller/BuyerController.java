package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.CardItem;
import edu.miu.waa.onlineshopping.domain.model.Cart;
import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.service.ProductService;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping("/buyer**")
@SessionAttributes({"products", "user", "orders"})
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
    public String home(@PathVariable Integer productId, Product product, Model model) {
        Cart cart = getCurrentCart();
        cart.addItemToCart(productId);
        servletContext.setAttribute("cart", cart);
        System.out.println(cart);
        return "redirect:/buyer/";
    }


    @PostMapping(value = "/carts/{productId}/remove")
    public String home(@PathVariable Integer productId, Model model) {
        Cart cart = getCurrentCart();
        cart.removeItemFormCart(productId);
        servletContext.setAttribute("cart", cart);
        System.out.println(cart);
        return "redirect:/buyer/carts/details/";
    }

    @GetMapping(value = "/carts/details")
    public String cartDetail(Model model) {
        Cart cart = getCurrentCart();
        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));
        double sum = cart.getCardItems().stream().map(cardItem -> cardItem.getQuantity() * cardItem.getProduct().getPrice()).reduce(0.0, Double::sum);
        servletContext.setAttribute("cart", cart);
        model.addAttribute("cardItems", cart.getCardItems());
        model.addAttribute("total", sum);
        System.out.println(cart);
        return "buyer/cart-detail";
    }

    @GetMapping(value = "/orders/view")
    public String orderView(Model model) {
        Cart cart = getCurrentCart();
        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));

        List<CardItem> cardItems = cart.getCardItems();
        Map<String, List<CardItem>> maps = cardItems.stream().collect(groupingBy(cardItem -> cardItem.getProduct().getSupplier().getUserName()));
        double sum = cart.getCardItems().stream().map(cardItem -> cardItem.getQuantity() * cardItem.getProduct().getPrice()).reduce(0.0, Double::sum);
        servletContext.setAttribute("cart", cart);
        model.addAttribute("orders", maps.values());
        model.addAttribute("total", sum);
        System.out.println(cart);
        return "buyer/order-review";
    }

    private Cart getCurrentCart() {
        Cart cart = (Cart) servletContext.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }
}
