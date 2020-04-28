package edu.miu.waa.onlineshopping.dataLoader;

import edu.miu.waa.onlineshopping.domain.Role;
import edu.miu.waa.onlineshopping.domain.User;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("INIT DATABASE");
        System.out.println("============================Init database for table USER==============================");
        System.out.println(userService.save(User.of(1,"Lamtang","Lamtang","Lam", "Tang", true, "lamtang@yahoo.com","0978548677", Role.ADMIN)));
        System.out.println(userService.save(User.of(1,"Seller","Seller","Hoai", "Tang", true, "hoaittang@yahoo.com","0978548622", Role.SELLER)));
        System.out.println(userService.save(User.of(1,"Buyer","Buyer","Dong", "Tang", true, "dongtang@yahoo.com","0978548623", Role.BUYER)));
        System.out.println(userService.save(User.of(1,"Seller1","Seller","Hung", "Tang", false, "hungtang@yahoo.com","0978548621", Role.SELLER)));

    }
}
