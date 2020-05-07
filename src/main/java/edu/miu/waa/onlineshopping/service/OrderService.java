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

    public Order save(Order order) {
        return orderDomainRepository.save(order);
    }

    public List<Order> findOrderBySeller(Integer sellerId) {
        return orderDomainRepository.findOrderBySeller(sellerId);
    }

    public Order findOrderById(Integer orderId) {
        return orderDomainRepository.findOrderById(orderId);
    }

    public void approvedOrder(Integer orderId) {
        Order order = orderDomainRepository.changeOrderStatus(orderId, OrderStatus.SHIPPED);
        Payment payment = Payment.of(null, LocalDate.now(), order.getTotal(), "Pay for buying", order, order.getOwner().getAccount(), order.getSeller().getAccount());
        paymentDomainRepository.save(payment);
    }

    public void rejectOrder(Integer orderId) {
        Order order = orderDomainRepository.changeOrderStatus(orderId, OrderStatus.REJECT);
    }

    public void changeToDeliveredOrder(Integer orderId) {
        Order order = orderDomainRepository.changeOrderStatus(orderId, OrderStatus.DELIVERED);
    }
}
