package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.CardItem;
import edu.miu.waa.onlineshopping.domain.model.Cart;
import edu.miu.waa.onlineshopping.domain.model.Order;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.repository.OrderDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class OrderService {

    private final OrderDomainRepository orderDomainRepository;

    @Autowired
    public OrderService(OrderDomainRepository orderDomainRepository) {
        this.orderDomainRepository = orderDomainRepository;
    }

    public List<Order> placeOrder(Cart cart) {
        List<CardItem> cardItems = cart.getCardItems();
        Map<User, List<CardItem>> maps = cardItems.stream().collect(groupingBy(cardItem -> cardItem.getProduct().getSupplier()));
        return null;
    }

    public Order save(Order order) {
        return orderDomainRepository.save(order);
    }
}
