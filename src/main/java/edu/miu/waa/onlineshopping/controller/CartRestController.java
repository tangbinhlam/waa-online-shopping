package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

@RestController
@RequestMapping(path = "/rest/api/v1")
public class CartRestController {

    private final ServletContext servletContext;

    @Autowired
    public CartRestController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @PostMapping("/carts/{productId}/{quantity}")
    public ResponseEntity<Integer> create(@PathVariable Integer productId, @PathVariable Integer quantity) {
        Cart cart = getCurrentCart();
        cart.addItemToCart(productId, quantity);
        servletContext.setAttribute("cart", cart);
        return ResponseEntity.status(HttpStatus.OK).body(productId);
    }

    private Cart getCurrentCart() {
        Cart cart = (Cart) servletContext.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }
}
