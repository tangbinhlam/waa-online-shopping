package edu.miu.waa.onlineshopping.controller;

import edu.miu.waa.onlineshopping.domain.model.*;
import edu.miu.waa.onlineshopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping("/buyer**")
@SessionAttributes({"products", "user", "orders", "shippingAddress"})
public class BuyerController {

    private final ProductService productService;
    private final UserService userService;
    private final ServletContext servletContext;
    private final OrderService orderService;
    private final ProductCommentService productCommentService;
    private final FollowerUserService followerUserService;
    private final PaymentService paymentService;

    @Autowired
    public BuyerController(ProductService productService, UserService userService, ServletContext servletContext, OrderService orderService, ProductCommentService productCommentService, FollowerUserService followerUserService, PaymentService paymentService) {
        this.productService = productService;
        this.userService = userService;
        this.servletContext = servletContext;
        this.orderService = orderService;
        this.productCommentService = productCommentService;
        this.followerUserService = followerUserService;
        this.paymentService = paymentService;
    }

    @ModelAttribute("user")
    public void getUser(Model model) {
        User user = (User) model.getAttribute("user");
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("user", userService.findUserByUserName(auth.getName()));
        }
    }

    @GetMapping(value = {""})
    public String home(Model model) {
        List<Product> products = (List<Product>) model.getAttribute("products");
        if (products == null) {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        return "buyer/buyer-home";
    }

    @PostMapping(value = "/carts/{productId}/add")
    public String home(@PathVariable Integer productId, Product product, Model model) {
        Cart cart = getCurrentCart();
        cart.addItemToCart(productId, 1);
        servletContext.setAttribute("cart", cart);
        System.out.println(cart);
        return "redirect:/buyer/";
    }

    @PostMapping(value = "/carts/{productId}/remove")
    public String home(@PathVariable Integer productId, Model model) {
        Cart cart = getCurrentCart();
        cart.removeItemFormCart(productId);
        servletContext.setAttribute("cart", cart);
        System.out.println(cart);
        return "redirect:/buyer/carts/details/";
    }

    @GetMapping(value = "/carts/details")
    public String cartDetail(Model model) {
        Cart cart = getCurrentCart();
        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));
        double sum = cart.getCardItems().stream().map(cardItem -> cardItem.getQuantity() * cardItem.getProduct().getPrice()).reduce(0.0, Double::sum);
        servletContext.setAttribute("cart", cart);
        model.addAttribute("cardItems", cart.getCardItems());
        model.addAttribute("total", sum);
        System.out.println(cart);
        return "buyer/cart-detail";
    }

    @GetMapping(value = "/orders")
    public String orderView(Model model) {
        Cart cart = getCurrentCart();
        List<Product> products = productService.findProductsByIds(cart.getCardItems().stream().map(CardItem::getProductId).collect(Collectors.toList()));
        cart.setCardItems(cart.getCardItems().stream().peek(cardItem ->
                cardItem.setProduct(products.stream().filter(product -> product.getProductId().equals(cardItem.getProductId())).findFirst().get())).collect(Collectors.toList()));
        List<CardItem> cardItems = cart.getCardItems();
        Map<String, List<CardItem>> maps = cardItems.stream().collect(groupingBy(cardItem -> cardItem.getProduct().getSupplier().getUserName()));
        double sum = cart.getCardItems().stream().map(cardItem -> cardItem.getQuantity() * cardItem.getProduct().getPrice()).reduce(0.0, Double::sum);
        User user = (User) model.getAttribute("user");
        servletContext.setAttribute("cart", cart);
        model.addAttribute("orders", maps.values());
        model.addAttribute("address", user.getAccount().getBillingAddress());
        model.addAttribute("total", sum);
        System.out.println(cart);
        return "buyer/order-review";
    }

    @PostMapping(value = "/orders")
    public String placeOrder(@Valid Address address, Model model) {
        orderService.save(getCurrentCart(), (User) model.getAttribute("user"), address);
        servletContext.removeAttribute("cart");
        return "redirect:/buyer/";
    }

    @GetMapping(value = "/orders/history")
    public String reviewOrders(Model model) {
        User user = (User) model.getAttribute("user");
        List<Order> orders = orderService.findOrderHistory(user.getUserId());
        model.addAttribute("orders", orders);
        return "buyer/order-history";
    }

    @GetMapping(value = "/orders/{orderId}/history")
    public String reviewOrders(@PathVariable Integer orderId, Model model) {
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("payment", paymentService.getPaymentOfOrder(orderId));
        return "buyer/order-history-view";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Integer orderId, Model model) {
        orderService.cancelOrder(orderId);
        return "redirect:/buyer/orders/history";
    }

    @GetMapping(value = "/products/{productId}/review")
    public String reviewProduct(@PathVariable Integer productId, Model model) {
        model.addAttribute("productComment", new ProductComment());
        return "buyer/product-review";
    }

    @PostMapping(value = "/products/{productId}/review")
    public String addReviewProduct(@PathVariable Integer productId, ProductComment productComment, Model model) {
        User user = (User) model.getAttribute("user");
        productComment.setReviewUser(user);
        productCommentService.saveComment(productId, productComment);
        return "redirect:/buyer/orders/history";
    }

    @GetMapping(value = "/products/{productId}")
    public String productDetail(@PathVariable Integer productId, Model model) {
        Product product = productService.findProductByProductId(productId);
        List<ProductComment> productComments = productCommentService.getApprovedProductComments(productId);
        Integer generalRating = 0;
        if(productComments.size()>0) {
            generalRating = (int) Math.round(productComments.stream()
                    .mapToInt(ProductComment::getRating)
                    .average().getAsDouble());
        }
        model.addAttribute("generalRating", generalRating);
        model.addAttribute("product", product);
        model.addAttribute("productComments", productComments);
        return "buyer/product-details";
    }

    @GetMapping(value = "/users/{userName}/profile")
    public String showUserProfile(@PathVariable String userName, Model model) {
        User currentUser = (User) model.getAttribute("user");
        User user = userService.findUserByUserName(userName);
        List<FollowerUser> followedUsers = followerUserService.findFollowersByUser(user.getUserId());
        boolean isFollowed = followedUsers.stream().anyMatch(followerUser -> followerUser.getFollowUser().getUserId().equals(currentUser.getUserId()));
        model.addAttribute("userProfile", user);
        model.addAttribute("numberFollowers", followedUsers.size());
        model.addAttribute("isFollowed", isFollowed);
        return "buyer/seller-profile";
    }

    @GetMapping(value = "/users/followed-users")
    public String listFolloweredUser(Model model) {
        User currentUser = (User) model.getAttribute("user");
        List<FollowerUser> followedUsers = followerUserService.findFollowedByUser(currentUser.getUserId());
        model.addAttribute("followedUsers", followedUsers);
        return "buyer/follower-users";
    }

    @PostMapping(value = "/users/followed-users/{userName}/unFollow")
    public String unFollowUserInList(@PathVariable String userName, Model model) {
        User currentUser = (User) model.getAttribute("user");
        User sellerUser = userService.findUserByUserName(userName);
        followerUserService.unFollow(currentUser.getUserId(), sellerUser.getUserId());
        return "redirect:/buyer/users/followed-users";
    }

    @PostMapping(value = "/users/{userName}/follow")
    public String followUser(@PathVariable String userName, Model model) {
        User currentUser = (User) model.getAttribute("user");
        User sellerUser = userService.findUserByUserName(userName);
        followerUserService.follow(currentUser.getUserId(), sellerUser.getUserId());
        return "redirect:/buyer/users/" + userName + "/profile";
    }

    @PostMapping(value = "/users/{userName}/unFollow")
    public String unFollowUser(@PathVariable String userName, Model model) {
        User currentUser = (User) model.getAttribute("user");
        User sellerUser = userService.findUserByUserName(userName);
        followerUserService.unFollow(currentUser.getUserId(), sellerUser.getUserId());
        return "redirect:/buyer/users/" + userName + "/profile";
    }

    private Cart getCurrentCart() {
        Cart cart = (Cart) servletContext.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    @GetMapping(value = "/orders/{orderId}/receipt")
    public String printReceiptOrders(@PathVariable Integer orderId, Model model) {
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "buyer/receipt-review";
    }
}
