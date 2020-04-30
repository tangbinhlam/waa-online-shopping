package edu.miu.waa.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandleController {
    @GetMapping("/not-approved-yet")
    String goNotAproveYet(){
        return "seller/alert-not-approve-yet";
    }
}
