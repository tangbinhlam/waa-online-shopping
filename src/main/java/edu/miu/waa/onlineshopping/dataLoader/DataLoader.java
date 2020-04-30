package edu.miu.waa.onlineshopping.dataLoader;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.service.ProductService;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("INIT DATABASE");
        System.out.println("============================Init database for table USER==============================");
        System.out.println(userService.save(User.of(1,"Lamtang","Lamtang","Lam", "Tang", true, "lamtang@yahoo.com","0978548677",
                Role.ADMIN,"I'm a admin I manager to approve new seller and review the review are made by buyer"

        )));
        User sellerUserSaved = userService.save(User.of(2,"Seller","Seller","Hoai", "Tang", true, "hoaittang@yahoo.com","0978548622",
                Role.SELLER,
                "When i was a child i wish when i can become a seller to buy some great product to help who want to have a good product can be had it and now I'm a seller. I hope you can buy my product and i'm sure that it is great prodcut with price resonable"
        ));
        System.out.println(sellerUserSaved);
        System.out.println(userService.save(User.of(3,"Buyer","Buyer","Dong", "Tang", true, "dongtang@yahoo.com","0978548623",
                Role.BUYER, "")));
        System.out.println(userService.save(User.of(4,"Seller1","Seller","Hung", "Tang", false, "hungtang@yahoo.com","0978548621",
                Role.SELLER,
                "I very like the seller so I become a seller and I hope I can sell somethings")));
        System.out.println(userService.save(User.of(5,"Seller2","Seller","Trong", "Tang", false, "trongtang@yahoo.com","0978548621",
                Role.SELLER,
                "You want to buy something? I have it for you let go to my product to get it")));

        System.out.println("============================Init database for table PRODUCT ==============================");

        System.out.println(productService.save(Product.of(6,"Galaxy S5", "https://i.ebayimg.com/images/g/ShQAAOSwna1Z1hzB/s-l640.jpg", "32GB, 2GB Ram, 1080HD, 5.1 inches, Android", sellerUserSaved, 649.99,"Samsung")));
        System.out.println(productService.save(Product.of(7,"iPhone 6", "https://www.cricketwireless.com/uiassets/DAPW4059-detail-front.jpg", "32GB, 64Bit, 1080HD, 4.7 inches, iOS 8", sellerUserSaved, 749.99,"Apple")));
        System.out.println(productService.save(Product.of(8,"Lumia 1520", "https://i.ebayimg.com/images/g/zlAAAOSwh3xepgYU/s-l640.jpg", "32GB, 4GB Ram, 1080HD, 6.3 inches, WP 8", sellerUserSaved, 749.99,"Nokia")));
        System.out.println(productService.save(Product.of(9,"Samsung Galaxy S20", "https://www.att.com/catalog/en/idse/Samsung/Samsung%20Galaxy%20S20+%205G/Cosmic%20Gray-hero-zoom.png",
                "5G Factory Unlocked New Android Cell Phone US Version | 128GB of Storage | Fingerprint ID and Facial Recognition | Long-Lasting Battery | US Warranty |Cosmic Gray",
                sellerUserSaved, 999.47,"Samsung")));
        System.out.println(productService.save(Product.of(10,"iPhone X", "https://www.att.com/catalog/en/idse/Apple/Apple%20iPhone%20X/Silver-hero-zoom.png", "Simple Mobile Prepaid - Apple iPhone XR (64GB) - Silver", sellerUserSaved, 599.99,"Apple")));
        System.out.println(productService.save(Product.of(11,"iPhone XS Max", "https://www.att.com/catalog/en/idse/Apple/Apple%20iPhone%20XS%20Max/Silver-hero-zoom.png", "iPhone XS Max features a 6.5-inch Super Retina display with custom-built OLED panels for an HDR display that provides the industry's best color accuracy, true blacks, and remarkable brightness", sellerUserSaved, 749.99,"Apple")));
        System.out.println(productService.save(Product.of(12,"iPhone SE", "https://m.media-amazon.com/images/I/810DvHOZ9nL.jpg", "New Apple iPhone SE (64GB, (Product) RED) [Carrier Locked] + Carrier Subscription [Cricket Wireless]", sellerUserSaved, 649.99,"Apple")));

        System.out.println("------------------------Display all Product of user id = 2------------------------");
        System.out.println(productService.findAllBySupplier(sellerUserSaved.getUserId()));
    }
}
