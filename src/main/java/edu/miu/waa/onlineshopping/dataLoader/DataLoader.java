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
        System.out.println(userService.save(User.of(1,"Lamtang","Lamtang","Lam", "Tang", true, "lamtang@yahoo.com","0978548677",
                Role.ADMIN,"I'm a admin I manager to approve new seller and review the review are made by buyer"

        )));
        System.out.println(userService.save(User.of(2,"Seller","Seller","Hoai", "Tang", true, "hoaittang@yahoo.com","0978548622",
                Role.SELLER,
                "When i was a child i wish when i can become a seller to buy some great product to help who want to have a good product can be had it and now I'm a seller. I hope you can buy my product and i'm sure that it is great prodcut with price resonable"
        )));
        System.out.println(userService.save(User.of(3,"Buyer","Buyer","Dong", "Tang", true, "dongtang@yahoo.com","0978548623",
                Role.BUYER, "")));
        System.out.println(userService.save(User.of(4,"Seller1","Seller","Hung", "Tang", false, "hungtang@yahoo.com","0978548621",
                Role.SELLER,
                "I very like the seller so I become a seller and I hope I can sell somethings")));
        System.out.println(userService.save(User.of(5,"Seller2","Seller","Trong", "Tang", false, "trongtang@yahoo.com","0978548621",
                Role.SELLER,
                "You want to buy something? I have it for you let go to my product to get it")));

    }
}
