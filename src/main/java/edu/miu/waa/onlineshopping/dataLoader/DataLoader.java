package edu.miu.waa.onlineshopping.dataLoader;

import edu.miu.waa.onlineshopping.domain.model.*;
import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.service.*;
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

    private final FollowerUserService followerUserService;

    @Autowired
    public DataLoader(UserService userService, ProductService productService, OrderService orderService, ProductCommentService productCommentService, FollowerUserService followerUserService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.productCommentService = productCommentService;
        this.followerUserService = followerUserService;
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
        System.out.println(productService.save(Product.of(null, "LG G7 fit 32GB", "lg_g7.jpg", "Use built-in AI to take better photos with the G7 fit LM-Q850QM 32GB Smartphone from LG. With AI Cam, the G7 fit will suggest the optimal effect and angle to use by automatically identifying what's in the shot. With AI Composition, the G7 fit can recognize up to 3 people in a photo and will adjust the picture for the best possible results",
                seller1, 190.99, "LG")));
        System.out.println(productService.save(Product.of(null, "Google Pixel 4 64GB", "google_p4.jpg", "The Pixel 4 64GB Smartphone (Unlocked, Just Black) from Google is designed to provide a more intelligent and intuitive mobile experience. With a front NIR (Near-Infrared) flood emitter & dot projector, the Pixel 4 is capable of unlocking via facial recognition, even in extreme low-light environments",
                seller1, 799.99, "Google")));
        System.out.println(productService.save(Product.of(null, "Samsung Galaxy A90", "samsung_a90.jpg", "Built for mobile entertainment, the Galaxy A90 SM-A908B 5G 128GB Smartphone from Samsung sports a huge 6.7\" Super AMOLED display with cinematic 20:9 aspect ratio, a triple-camera system capable of capturing 4K video, and access to select 5G networks for fast, low-latency downloading and streaming",
                seller1, 349.99, "Samsung")));
        System.out.println(productService.save(Product.of(null, "ASUS ZenFone 5Z", "asus_5z.jpg",
                "With a smart screen and smart cameras, the ASUS ZenFone 5Z ZS620KL Dual-SIM 64GB Smartphone is an intelligent choice for a feature-rich phone that provides sophisticated multimedia capabilities. Starting with the display, you get a 6.2\" screen on a 5.5\" body. How? ASUS manages this by pushing the screen to the edge for a 90% screen-to-body",
                seller1, 999.47, "Asus")));
        System.out.println(productService.save(Product.of(null, "Sony Xperia 1 J8170", "sony_j8170.jpg", "A pocket-sized cinema experience, the Xperia 1 J8170 128GB Smartphone from Sony features a huge 6.5\", 21:9 aspect ratio CinemaWide OLED that's capable of displaying video in 4K HDR quality. This high quality display comes from a collaboration with CineAlta engineers, who also give Xperia 1 owners Creator Mode.",
                seller1, 949.00, "Sony")));
        System.out.println(productService.save(Product.of(null, "Sony Xperia 1 II", "sony_jII.jpg", "From its network support to camera technology, the Xperia 1 II Dual-SIM 256GB 5G Smartphone from Sony is engineered for speed. Supporting 5G networks, you can enjoy blazing fast internet speeds in supported areas. When it comes to mobile photos and video, Sony worked to set the Xperia 1 II apart from the crowd",
                seller1, 549.99, "Sony")));
        System.out.println(productService.save(Product.of(null, "Sony AS210 Sport In-Ear", "sony_as210.jpg", "The pink AS210 Sport In-Ear Headphones from Sony feature adjustable loop hangers for a secure fit while playing sports, a 3.9' cord for room to move, and 0.5\" drivers that deliver an optimized frequency range. The headphones are water resistant for all-weather listening and can be used to enjoy your favorite music while you exercise.",
                seller1, 13.72, "Sony")));

        System.out.println(productService.findProductsByIds(Arrays.asList(16, 18, 20)).stream().map(Product::getProductId).collect((Collectors.toList())));


        Cart cart = new Cart();
        cart.addItemToCart(16, 1);
        cart.addItemToCart(18, 2);
        cart.addItemToCart(19, 1);
        cart.addItemToCart(26, 1);
        cart.addItemToCart(27, 2);
        cart.addItemToCart(28, 1);

        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));
        Address address = Address.of(null, "address line 1", "address line 2", "IOWA", "FairField", "54333", "USA");
        orderService.save(cart, buyer, address);

        Account account5 = Account.of(null, 500000.00,
                Address.of(null, "2020 S Main St", "2K", "IA", "Fairfield", "52556", "USA"),
                LocalDate.of(2020,5,1), LocalDate.of(2027,5,1));
        User buyer1 = userService.save(User.of(null, "Buyer1", "Buyer1", "Phuc", "Tang", true, "phuctang@yahoo.com", "0978548623",
                Role.BUYER, "", account5));
        System.out.println(buyer1);

        System.out.println("Print Order with status PLACE_ORDER");
        System.out.println("Print Order of user: " + seller1.getName());
        List<Order> orders1 = orderService.findOrderBySeller(seller1.getUserId());

        System.out.println("Order will be changed status");
        System.out.println(orders1);
        orderService.approvedOrder(orders1.get(0).getOrderId());
        orderService.changeToDeliveredOrder(orders1.get(0).getOrderId());


        Cart cart1 = new Cart();
        cart1.addItemToCart(16, 2);
        cart1.addItemToCart(18, 2);
        cart1.addItemToCart(19, 1);
        cart1.addItemToCart(25, 1);
        cart1.addItemToCart(27, 2);
        cart1.addItemToCart(29, 3);
        List<Product> products1 = productService.findProductsByIds(cart1.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart1.setCardItems(cart1.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products1.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));
        Address address1 = Address.of(null, "address line 1", "address line 2", "IOWA", "FairField", "54333", "USA");
        orderService.save(cart1, buyer, address1);

        System.out.println("Print Order of user: " + seller1.getName());
        List<Order> orders2 = orderService.findOrderBySeller(seller1.getUserId());
        List<Order> ordersSeller2 = orderService.findOrderBySeller(seller2.getUserId());

        System.out.println("Order will be changed status");
        System.out.println(orders2);
        orderService.approvedOrder(ordersSeller2.get(1).getOrderId());
        orderService.rejectOrder(orders2.get(1).getOrderId());


        // Write comment for product 1
        ProductComment productComment = ProductComment.of(null, "This is good product", 5, buyer, LocalDate.now(), CommentStatus.ADDED);
        ProductComment productComment1 = ProductComment.of(null, "Right out of the box and with very little reading of the manual I was able to get going with this very capable device on my complicated Canon 6D Mark II. I have a lot to learn about off-camera flash photography, but I already know enough to recommend that this flash is well served by using it conjunction with a soft-box.", 5, buyer, LocalDate.now(), CommentStatus.ADDED);
        ProductComment productComment2 = ProductComment.of(null, "I own multiple units of this and the original 600EX. This thing SLAYS the original 600 in terms of recycle time. When I rely on these for my wedding work it can absolutely make the difference between getting the shot and failure. The GUI on the rear display is greatly improved over the original 600 as well, much more intuitive, bravo. Radio triggering is sublime when it works, which is sadly only most of the time.", 5, buyer1, LocalDate.now(), CommentStatus.ADDED);
        ProductComment productComment3 = ProductComment.of(null, "I have purchased three of these flashes from certified sellers and all three have had malfunctions. I shoot weddings and have never had issues with the Yongs or old 600EXs. These new 600s will either not turn on, not fire or will simply be broken beyond repair should as much as sneeze next to them. Horrible product.", 2, buyer1, LocalDate.now(), CommentStatus.ADDED);


        // Print comment
        System.out.println("Print comment");
        System.out.println(productCommentService.saveComment(16, productComment));
        System.out.println(productCommentService.saveComment(18, productComment1));
        System.out.println(productCommentService.saveComment(18, productComment2));
        System.out.println(productCommentService.saveComment(18, productComment3));


        System.out.println("Print Order of user: " + seller2.getName());
        System.out.println(orderService.findOrderBySeller(seller2.getUserId()));

        // Print list comment not approve yet
        System.out.println("Print list comment not approve yet");
        System.out.println(productCommentService.getNewProductComments());

        System.out.println("Print follower user");
        System.out.println(followerUserService.follow(buyer.getUserId(), seller1.getUserId()));
    }
}
