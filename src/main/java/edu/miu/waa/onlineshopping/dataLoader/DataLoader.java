package edu.miu.waa.onlineshopping.dataLoader;

import edu.miu.waa.onlineshopping.domain.model.*;
import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.service.OrderService;
import edu.miu.waa.onlineshopping.service.ProductCommentService;
import edu.miu.waa.onlineshopping.service.ProductService;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    private final ProductCommentService productCommentService;

    @Autowired
    public DataLoader(UserService userService, ProductService productService, OrderService orderService, ProductCommentService productCommentService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.productCommentService = productCommentService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("INIT DATABASE");
        System.out.println("============================Init database for table USER==============================");
        System.out.println(userService.save(User.of(null, "Lamtang", "Lamtang", "Lam", "Tang", true, "lamtang@yahoo.com", "0978548677",
                Role.ADMIN, "I'm a admin I manager to approve new seller and review the review are made by buyer",null
        )));
        System.out.println("SELLER: Hoai");
        Account account = Account.of(null, 500000.00,
                Address.of(null, "300 N Court St", "3E", "IA", "Fairfield", "52556", "USA"),
                LocalDate.of(2020,5,1), LocalDate.of(2027,5,1));
        User newSeller1 = User.of(null, "Seller", "Seller", "Hoai", "Tang", true, "hoaittang@yahoo.com", "0978548622",
                Role.SELLER,
                "When i was a child i wish when i can become a seller to buy some great product to help who want to have a good product can be had it and now I'm a seller. I hope you can buy my product and i'm sure that it is great prodcut with price resonable"
                ,account);
        User seller1 = userService.save(newSeller1);
        System.out.println(seller1);

        System.out.println("SELLER: Hung");
        Account account2 = Account.of(null, 500000.00,
                Address.of(null, "2701 W Burlington Ave", "2K", "IA", "Fairfield", "52556", "USA"),
                LocalDate.of(2020,5,1), LocalDate.of(2027,5,1));
        User newSeller2 = User.of(null, "Seller1", "Seller", "Hung", "Tang", true, "hungtang@yahoo.com", "0978548621",
                Role.SELLER,
                "I very like the seller so I become a seller and I hope I can sell somethings"
                ,account2);
        User seller2 = userService.save(newSeller2);
        System.out.println(seller2);

        System.out.println("SELLER: Trong Inactive");
        Account account3 = Account.of(null, 500000.00,
                Address.of(null, "707 W Burlington Ave", "2K", "IA", "Fairfield", "52556", "USA"),
                LocalDate.of(2020,5,1), LocalDate.of(2027,5,1));
        User newSeller3 = User.of(null,"Seller2", "Seller", "Trong", "Tang", false, "trongtang@yahoo.com", "0978548621",
                Role.SELLER,
                "You want to buy something? I have it for you let go to my product to get it"
                ,account3);
        User seller3 = userService.save(newSeller3);
        System.out.println(seller3);

        System.out.println("BUYER:");
        Account account4 = Account.of(null, 500000.00,
                Address.of(null, "2000 S Main St", "2K", "IA", "Fairfield", "52556", "USA"),
                LocalDate.of(2020,5,1), LocalDate.of(2027,5,1));
        User buyer = userService.save(User.of(null, "Buyer", "Buyer", "Dong", "Tang", true, "dongtang@yahoo.com", "0978548623",
                Role.BUYER, "", account4));
        System.out.println(buyer);

        System.out.println("============================Init database for table PRODUCT ==============================");
        System.out.println("============================Add product for seller 1 ==============================");

        System.out.println(productService.save(Product.of(null, "Canon EOS M50", "CanonE0SM50.jpg",
                "Canon EOS M50 Mirrorless Digital Camera with 15-45mm Lens and Accessory Kit (Black)",
                seller2, 499.99, "Canon")));
        System.out.println(productService.save(Product.of(null, "Canon EOS 90D", "CanonEOS90D.jpg",
                "Canon EOS 90D DSLR Camera Body with Software Kit",
                seller2, 1499.99, "Canon")));
        System.out.println(productService.save(Product.of(null, "Canon EOS R", "CanonEOSR.jpg",
                "Canon EOS R Mirrorless Digital Camera Body with Accessories Kit",
                seller2, 1799.00, "Canon")));
        System.out.println(productService.save(Product.of(null, "SanDisk 64GB Extreme PRO", "SanDisk64GB.jpg",
                "SanDisk 64GB Extreme PRO UHS-II SDXC Memory Card",
                seller2, 97.99, "SanDisk")));
        System.out.println(productService.save(Product.of(null, "Canon Speedlite 600EX II-RT", "CanonSpeedlite.jpg",
                "Complete with built-in radio transmission wireless functionality, the Speedlite 600EX II-RT sits at the top of Canon's on-camera E-TTL / E-TTL II compatible flash lineup with a powerful guide number of 197' at ISO 100 and 200mm. This revision improves continuous flash performance by 1.1-1.5x, or up to 2.0x with optional battery pack",
                seller2, 399.00, "Canon")));

        System.out.println(productService.save(Product.of(null, "Nikon D850 DSLR Camera (Body Only)", "NikonD850.jpg",
                "Proving that speed and resolution can indeed coexist, the Nikon D850 is a multimedia DSLR that brings together robust stills capabilities along with apt movie and time-lapse recording. Revolving around a newly designed 45.7MP BSI CMOS sensor and proven EXPEED 5 image processor, the D850 is clearly distinguished by its high resolution",
                seller2, 2996.99, "Nikon")));
        System.out.println(productService.save(Product.of(null, "Nikon Z 50 Mirrorless Digital Camera", "NikonZ50.jpg",
                "Nikon Z 50 Mirrorless Digital Camera with 16-50mm and 50-250mm Lenses and Accessories Kit",
                seller2, 1196.95, "Nikon")));
        System.out.println(productService.save(Product.of(null, "Nikon COOLPIX P900 Digital Camera", "NikonCOOLPIX.jpg",
                "Nikon COOLPIX P900 Digital Camera (Refurbished by Nikon USA)",
                seller2, 399.00, "Nikon")));
        System.out.println(productService.save(Product.of(null, "Nikon Z 7 Mirrorless Digital Camera", "NikonZ7.jpg",
                "Nikon Z 7 Mirrorless Digital Camera with FTZ Mount Adapter and Bag Kit",
                seller2, 2843.90, "Nikon")));

        System.out.println("============================Add product for seller ==============================");
        System.out.println(productService.save(Product.of(null, "Galaxy S5", "galaxy_s5.jpg", "32GB, 2GB Ram, 1080HD, 5.1 inches, Android",
                seller1, 649.99, "Samsung")));
        System.out.println(productService.save(Product.of(null, "iPhone 6", "iPhone6.jpg", "32GB, 64Bit, 1080HD, 4.7 inches, iOS 8",
                seller1, 749.99, "Apple")));
        System.out.println(productService.save(Product.of(null, "Lumia 1520", "Lumia1520.jpg", "32GB, 4GB Ram, 1080HD, 6.3 inches, WP 8",
                seller1, 749.99, "Nokia")));
        System.out.println(productService.save(Product.of(null, "Samsung Galaxy S20", "SamsungGalaxyS20.jpg",
                "5G Factory Unlocked New Android Cell Phone US Version | 128GB of Storage | Fingerprint ID and Facial Recognition | Long-Lasting Battery | US Warranty |Cosmic Gray",
                seller1, 999.47, "Samsung")));
        System.out.println(productService.save(Product.of(null, "iPhone X", "IphoneX.jpg", "Simple Mobile Prepaid - Apple iPhone XR (64GB) - Silver",
                seller1, 599.99, "Apple")));
        System.out.println(productService.save(Product.of(null, "iPhone XS Max", "IphoneXSMax.jpg", "iPhone XS Max features a 6.5-inch Super Retina display with custom-built OLED panels for an HDR display that provides the industry's best color accuracy, true blacks, and remarkable brightness",
                seller1, 749.99, "Apple")));
        System.out.println(productService.save(Product.of(null, "iPhone SE", "IPhoneSE.jpg", "New Apple iPhone SE (64GB, (Product) RED) [Carrier Locked] + Carrier Subscription [Cricket Wireless]",
                seller1, 649.99, "Apple")));

        System.out.println(productService.findProductsByIds(Arrays.asList(16, 18, 20)).stream().map(Product::getProductId).collect((Collectors.toList())));


        Cart cart = new Cart();
        cart.addItemToCart(16);
        cart.addItemToCart(18);
        cart.addItemToCart(19);
        cart.addItemToCart(26);
        cart.addItemToCart(27); cart.addItemToCart(27);
        cart.addItemToCart(28);

        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));
        Address address = Address.of(null, "address line 1", "address line 2", "IOWA", "FairField", "54333", "USA");
        orderService.save(cart, buyer, address);

        System.out.println("Print Order with status PLACE_ORDER");
        System.out.println("Print Order of user: " + seller1.getName());
        List<Order> orders1 = orderService.findOrderBySeller(seller1.getUserId());

        System.out.println("Order will be changed status");
        System.out.println(orders1);
        orderService.approvedOrder(orders1.get(0).getOrderId());
        orderService.changeToDeliveredOrder(orders1.get(0).getOrderId());
        // Write comment for product 1
        ProductComment productComment = ProductComment.of(null, "This is good product", 5, buyer, LocalDate.now(), CommentStatus.ADDED);
        ProductComment productComment1 = ProductComment.of(null, "This is good not good product I used but it bad", 1, buyer, LocalDate.now(), CommentStatus.ADDED);


        // Print comment
        System.out.println("Print comment");
        System.out.println(productCommentService.saveComment(16, productComment));
        System.out.println(productCommentService.saveComment(18, productComment1));


        System.out.println("Print Order of user: " + seller2.getName());
        System.out.println(orderService.findOrderBySeller(seller2.getUserId()));
    }
}
