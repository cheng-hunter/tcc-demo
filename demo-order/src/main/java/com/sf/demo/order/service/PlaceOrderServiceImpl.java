package com.sf.demo.order.service;

import org.apache.commons.lang3.tuple.Pair;
import org.mengyun.tcctransaction.CancellingException;
import org.mengyun.tcctransaction.ConfirmingException;
import com.sf.demo.order.entity.Order;
import com.sf.demo.order.entity.Shop;
import com.sf.demo.order.entity.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by changming.xie on 4/1/16.
 */
@Service
public class PlaceOrderServiceImpl {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    PaymentServiceImpl paymentService;


    public String placeOrder(long payerUserId, long shopId, long productId, BigDecimal redPacketPayAmount) {
        //获取商铺信息
    	Shop shop = shopRepository.findById(shopId);
    	//根据商铺主人,下单人,商品信息,创建订单
        Order order = orderService.createOrder(payerUserId, shop.getOwnerUserId(), productId);

        Boolean result = false;
        try {
        	//执行支付
            paymentService.makePayment(order, redPacketPayAmount, order.getTotalPayAmount().subtract(redPacketPayAmount));

        } catch (ConfirmingException confirmingException) {
            //exception throws with the tcc transaction status is CONFIRMING,
            //when tcc transaction is confirming status,
            // the tcc transaction recovery will try to confirm the whole transaction to ensure eventually consistent.

            result = true;
        } catch (CancellingException cancellingException) {
            //exception throws with the tcc transaction status is CANCELLING,
            //when tcc transaction is under CANCELLING status,
            // the tcc transaction recovery will try to cancel the whole transaction to ensure eventually consistent.
        } catch (Throwable e) {
            //other exceptions throws at TRYING stage.
            //you can retry or cancel the operation.
            e.printStackTrace();
        }

        return order.getMerchantOrderNo();
    }
}
