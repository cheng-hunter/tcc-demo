package com.sf.demo.order.entity.repository;


import com.sf.demo.order.entity.Order;
import com.sf.demo.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

/**
 * Created by changming.xie on 4/1/16.
 */
@Repository
public class OrderRepository {

    @Autowired
    OrderDao orderDao;


    public void createOrder(Order order) {
        orderDao.insert(order);
    }

    public void updateOrder(Order order) {
        order.updateVersion();
        int effectCount = orderDao.update(order);

        if (effectCount < 1) {
            throw new OptimisticLockingFailureException("update order failed");
        }
    }

    public Order findByMerchantOrderNo(String merchantOrderNo) {
        return orderDao.findByMerchantOrderNo(merchantOrderNo);
    }
}
