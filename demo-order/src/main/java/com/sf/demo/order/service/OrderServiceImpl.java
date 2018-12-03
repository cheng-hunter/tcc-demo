package com.sf.demo.order.service;

import com.sf.demo.order.entity.repository.ProductRepository;
import org.apache.commons.lang3.tuple.Pair;
import com.sf.demo.order.entity.Order;
import com.sf.demo.order.entity.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Order createOrder(long payerUserId, long payeeUserId, long productId) {
        Order order = new Order(payerUserId, payeeUserId);
        order.setTotalPayAmount(productRepository.findById(productId).getPrice());
        orderRepository.createOrder(order);
        return order;
    }

    public Order findOrderByMerchantOrderNo(String orderNo) {
        return orderRepository.findByMerchantOrderNo(orderNo);
    }
}
