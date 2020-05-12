package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.*;
import edu.miu.waa.onlineshopping.domain.repository.OrderDomainRepository;
import edu.miu.waa.onlineshopping.domain.repository.PaymentDomainRepository;
import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class OrderService {

    private final OrderDomainRepository orderDomainRepository;
    private final PaymentDomainRepository paymentDomainRepository;

    @Autowired
    public OrderService(OrderDomainRepository orderDomainRepository, PaymentDomainRepository paymentDomainRepository) {
        this.orderDomainRepository = orderDomainRepository;
        this.paymentDomainRepository = paymentDomainRepository;
    }

    public List<Order> placeOrder(Cart cart) {
        List<CardItem> cardItems = cart.getCardItems();
        Map<User, List<CardItem>> maps = cardItems.stream().collect(groupingBy(cardItem -> cardItem.getProduct().getSupplier()));
        return null;
    }

    public void save(Cart cart, User buyer, Address address) {
        List<CardItem> cardItems = cart.getCardItems();
        Map<String, List<CardItem>> maps = cardItems.stream().collect(groupingBy(cardItem -> cardItem.getProduct().getSupplier().getUserName()));

        maps.forEach((key, value) -> {
            Order order = new Order();
            double sum = value.stream().map(cardItem -> cardItem.getQuantity() * cardItem.getProduct().getPrice()).reduce(0.0, Double::sum);
            order.setOrderItems(value.stream().map(cardItem ->
                    OrderItem.of(null, cardItem.getQuantity(), cardItem.getProduct().getPrice(), cardItem.getProduct())).collect(Collectors.toList()));
            order.setOrderDate(LocalDate.now());
            order.setOwner(buyer);
            order.setSeller(order.getOrderItems().get(0).getProduct().getSupplier());
            order.setShippedDate(null);
            order.setShipto(address);
            order.setTotal(sum);
            order.setStatus(OrderStatus.PLACE_ORDER);
            System.out.println(orderDomainRepository.save(order));
        });
    }

    public List<Order> findOrderBySeller(Integer sellerId) {
        return orderDomainRepository.findOrderBySeller(sellerId);
    }

    public Order findOrderById(Integer orderId) {
        return orderDomainRepository.findOrderById(orderId);
    }

    public void approvedOrder(Integer orderId) {
        Order order = orderDomainRepository.approveOrder(orderId);
        // When order is approved we make a payment
        Payment payment = Payment.of(null, LocalDate.now(), order.getTotal(), "Pay for buying product", order, order.getOwner().getAccount(), order.getSeller().getAccount());
        paymentDomainRepository.save(payment);
    }

    public void rejectOrder(Integer orderId) {
        Order order = orderDomainRepository.rejectOrder(orderId);
    }

    public void changeToDeliveredOrder(Integer orderId) {
        Order order = orderDomainRepository.deliveredOrder(orderId);
    }

    public void cancelOrder(Integer orderId) {
        Order order = orderDomainRepository.cancelOrder(orderId);
    }

    public List<Order> findOrderHistory(Integer buyerId) {
        return orderDomainRepository.findOrderHistory(buyerId);
    }
}
