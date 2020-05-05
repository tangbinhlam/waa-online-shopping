package edu.miu.waa.onlineshopping.dataLoader;

import edu.miu.waa.onlineshopping.domain.model.*;
import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.service.OrderService;
import edu.miu.waa.onlineshopping.service.ProductService;
import edu.miu.waa.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    @Autowired
    public DataLoader(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("INIT DATABASE");
        System.out.println("============================Init database for table USER==============================");
        System.out.println(userService.save(User.of(null, "Lamtang", "Lamtang", "Lam", "Tang", true, "lamtang@yahoo.com", "0978548677",
                Role.ADMIN, "I'm a admin I manager to approve new seller and review the review are made by buyer"

        )));
        System.out.println("SELLER:");
        User seller1 = userService.save(User.of(null, "Seller", "Seller", "Hoai", "Tang", true, "hoaittang@yahoo.com", "0978548622",
                Role.SELLER,
                "When i was a child i wish when i can become a seller to buy some great product to help who want to have a good product can be had it and now I'm a seller. I hope you can buy my product and i'm sure that it is great prodcut with price resonable"
        ));
        System.out.println(seller1);

        System.out.println("SELLER 1:");
        User seller2 = userService.save(User.of(null, "Seller1", "Seller", "Hung", "Tang", true, "hungtang@yahoo.com", "0978548621",
                Role.SELLER,
                "I very like the seller so I become a seller and I hope I can sell somethings"));
        System.out.println(seller2);

        System.out.println(userService.save(User.of(null, "Seller2", "Seller", "Trong", "Tang", false, "trongtang@yahoo.com", "0978548621",
                Role.SELLER,
                "You want to buy something? I have it for you let go to my product to get it")));

        System.out.println("BUYER:");
        User buyer = userService.save(User.of(null, "Buyer", "Buyer", "Dong", "Tang", true, "dongtang@yahoo.com", "0978548623",
                Role.BUYER, ""));
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
        System.out.println(productService.save(Product.of(null, "Galaxy S5", "galaxy_s5.jpg", "32GB, 2GB Ram, 1080HD, 5.1 inches, Android", seller1, 649.99, "Samsung")));
        System.out.println(productService.save(Product.of(null, "iPhone 6", "iPhone6.jpg", "32GB, 64Bit, 1080HD, 4.7 inches, iOS 8", seller1, 749.99, "Apple")));
        System.out.println(productService.save(Product.of(null, "Lumia 1520", "Lumia1520.jpg", "32GB, 4GB Ram, 1080HD, 6.3 inches, WP 8", seller1, 749.99, "Nokia")));
        System.out.println(productService.save(Product.of(null, "Samsung Galaxy S20", "SamsungGalaxyS20.jpg",
                "5G Factory Unlocked New Android Cell Phone US Version | 128GB of Storage | Fingerprint ID and Facial Recognition | Long-Lasting Battery | US Warranty |Cosmic Gray",
                seller1, 999.47, "Samsung")));
        System.out.println(productService.save(Product.of(null, "iPhone X", "IphoneX.jpg", "Simple Mobile Prepaid - Apple iPhone XR (64GB) - Silver", seller1, 599.99, "Apple")));
        System.out.println(productService.save(Product.of(null, "iPhone XS Max", "IphoneXSMax.jpg", "iPhone XS Max features a 6.5-inch Super Retina display with custom-built OLED panels for an HDR display that provides the industry's best color accuracy, true blacks, and remarkable brightness", seller1, 749.99, "Apple")));
        System.out.println(productService.save(Product.of(null, "iPhone SE", "IPhoneSE.jpg", "New Apple iPhone SE (64GB, (Product) RED) [Carrier Locked] + Carrier Subscription [Cricket Wireless]", seller1, 649.99, "Apple")));

        System.out.println(productService.findProductsByIds(Arrays.asList(16, 18, 20)).stream().map(Product::getProductId).collect((Collectors.toList())));


        Cart cart = new Cart();
        cart.addItemToCart(16);
        cart.addItemToCart(18);
        cart.addItemToCart(9);
        cart.addItemToCart(11);
        cart.addItemToCart(13);

        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));

        List<CardItem> cardItems = cart.getCardItems();
        Map<String, List<CardItem>> maps = cardItems.stream().collect(groupingBy(cardItem -> cardItem.getProduct().getSupplier().getUserName()));

        List<Order> orders = new ArrayList<>();
        maps.forEach( (key, value) -> {
            Order order = new Order();
            order.setOrderItems(value.stream().map( cardItem ->
                    OrderItem.of(null, cardItem.getQuantity(), cardItem.getProduct().getPrice(), cardItem.getProduct())).collect(Collectors.toList()));
            order.setOrderDate(LocalDate.of(2020,5,1));
            order.setOwner(buyer);
            order.setShippedDate(LocalDate.of(2020,5,1));
            Address address = Address.of(null, "address line 1", "address line 2", "IOWA", "FairField", "54333", "USA");
            order.setShipto(address);
            order.setStatus(OrderStatus.PLACE_ORDER);
            System.out.println(orderService.save(order));
        });
        System.out.println(maps);
    }
}
