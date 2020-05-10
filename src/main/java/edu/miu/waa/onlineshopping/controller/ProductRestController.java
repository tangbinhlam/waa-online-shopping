package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/api/v1")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Integer> create(@PathVariable Integer productId) {
        productService.delete(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productId);
    }
}
