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
        int id = 13;
        System.out.println(userService.save(User.of(id++,"Lamtang","Lamtang","Lam", "Tang", true, "lamtang@yahoo.com","0978548677",
                Role.ADMIN,"I'm a admin I manager to approve new seller and review the review are made by buyer"

        )));
        System.out.println("SELLER:");
        User seller1 = userService.save(User.of(id++,"Seller","Seller","Hoai", "Tang", true, "hoaittang@yahoo.com","0978548622",
                Role.SELLER,
                "When i was a child i wish when i can become a seller to buy some great product to help who want to have a good product can be had it and now I'm a seller. I hope you can buy my product and i'm sure that it is great prodcut with price resonable"
        ));
        System.out.println(seller1);

        System.out.println("SELLER 1:");
        User seller2 = userService.save(User.of(id++,"Seller1","Seller","Hung", "Tang", true, "hungtang@yahoo.com","0978548621",
                Role.SELLER,
                "I very like the seller so I become a seller and I hope I can sell somethings"));
        System.out.println(seller2);

        System.out.println(userService.save(User.of(id++,"Seller2","Seller","Trong", "Tang", false, "trongtang@yahoo.com","0978548621",
                Role.SELLER,
                "You want to buy something? I have it for you let go to my product to get it")));

        System.out.println("BUYER:");
        System.out.println(userService.save(User.of(id++,"Buyer","Buyer","Dong", "Tang", true, "dongtang@yahoo.com","0978548623",
                Role.BUYER, "")));

        System.out.println("============================Init database for table PRODUCT ==============================");
        System.out.println("============================Add product for seller 1 ==============================");

        System.out.println(productService.save(Product.of(id++,"Canon EOS M50", "CanonE0SM50.jpg",
                "Canon EOS M50 Mirrorless Digital Camera with 15-45mm Lens and Accessory Kit (Black)",
                seller2, 499.99,"Canon")));
        System.out.println(productService.save(Product.of(id++,"Canon EOS 90D", "CanonEOS90D.jpg",
                "Canon EOS 90D DSLR Camera Body with Software Kit",
                seller2, 1499.99,"Canon")));
        System.out.println(productService.save(Product.of(id++,"Canon EOS R", "CanonEOSR.jpg",
                "Canon EOS R Mirrorless Digital Camera Body with Accessories Kit",
                seller2, 1799.00,"Canon")));
        System.out.println(productService.save(Product.of(id++,"SanDisk 64GB Extreme PRO", "SanDisk64GB.jpg",
                "SanDisk 64GB Extreme PRO UHS-II SDXC Memory Card",
                seller2, 97.99,"SanDisk")));
        System.out.println(productService.save(Product.of(id++,"Canon Speedlite 600EX II-RT", "CanonSpeedlite.jpg",
                "Complete with built-in radio transmission wireless functionality, the Speedlite 600EX II-RT sits at the top of Canon's on-camera E-TTL / E-TTL II compatible flash lineup with a powerful guide number of 197' at ISO 100 and 200mm. This revision improves continuous flash performance by 1.1-1.5x, or up to 2.0x with optional battery pack",
                seller2, 399.00,"Canon")));

        System.out.println(productService.save(Product.of(id++,"Nikon D850 DSLR Camera (Body Only)", "NikonD850.jpg",
                "Proving that speed and resolution can indeed coexist, the Nikon D850 is a multimedia DSLR that brings together robust stills capabilities along with apt movie and time-lapse recording. Revolving around a newly designed 45.7MP BSI CMOS sensor and proven EXPEED 5 image processor, the D850 is clearly distinguished by its high resolution",
                seller2, 2996.99,"Nikon")));
        System.out.println(productService.save(Product.of(id++,"Nikon Z 50 Mirrorless Digital Camera", "NikonZ50.jpg",
                "Nikon Z 50 Mirrorless Digital Camera with 16-50mm and 50-250mm Lenses and Accessories Kit",
                seller2, 1196.95,"Nikon")));
        System.out.println(productService.save(Product.of(id++,"Nikon COOLPIX P900 Digital Camera", "NikonCOOLPIX.jpg",
                "Nikon COOLPIX P900 Digital Camera (Refurbished by Nikon USA)",
                seller2, 399.00,"Nikon")));
        System.out.println(productService.save(Product.of(id++,"Nikon Z 7 Mirrorless Digital Camera", "NikonZ7.jpg",
                "Nikon Z 7 Mirrorless Digital Camera with FTZ Mount Adapter and Bag Kit",
                seller2, 2843.90,"Nikon")));

        System.out.println("============================Add product for seller ==============================");
        System.out.println(productService.save(Product.of(id++,"Galaxy S5", "galaxy_s5.jpg", "32GB, 2GB Ram, 1080HD, 5.1 inches, Android", seller1, 649.99,"Samsung")));
        System.out.println(productService.save(Product.of(id++,"iPhone 6", "iPhone6.jpg", "32GB, 64Bit, 1080HD, 4.7 inches, iOS 8", seller1, 749.99,"Apple")));
        System.out.println(productService.save(Product.of(id++,"Lumia 1520", "Lumia1520.jpg", "32GB, 4GB Ram, 1080HD, 6.3 inches, WP 8", seller1, 749.99,"Nokia")));
        System.out.println(productService.save(Product.of(id++,"Samsung Galaxy S20", "SamsungGalaxyS20.jpg",
                "5G Factory Unlocked New Android Cell Phone US Version | 128GB of Storage | Fingerprint ID and Facial Recognition | Long-Lasting Battery | US Warranty |Cosmic Gray",
                seller1, 999.47,"Samsung")));
        System.out.println(productService.save(Product.of(id++,"iPhone X", "IphoneX.jpg", "Simple Mobile Prepaid - Apple iPhone XR (64GB) - Silver", seller1, 599.99,"Apple")));
        System.out.println(productService.save(Product.of(id++,"iPhone XS Max", "IphoneXSMax.jpg", "iPhone XS Max features a 6.5-inch Super Retina display with custom-built OLED panels for an HDR display that provides the industry's best color accuracy, true blacks, and remarkable brightness", seller1, 749.99,"Apple")));
        System.out.println(productService.save(Product.of(id++,"iPhone SE", "IPhoneSE.jpg", "New Apple iPhone SE (64GB, (Product) RED) [Carrier Locked] + Carrier Subscription [Cricket Wireless]", seller1, 649.99,"Apple")));

    }

}
