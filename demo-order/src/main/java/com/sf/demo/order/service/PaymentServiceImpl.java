package com.sf.demo.order.service;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.mengyun.tcctransaction.api.Compensable;
import com.sf.demo.capital.api.dto.CapitalTradeOrderDto;
import com.sf.demo.redpacket.api.dto.RedPacketTradeOrderDto;
import com.sf.demo.order.entity.Order;
import com.sf.demo.order.entity.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by changming.xie on 4/1/16.
 */
@Service
public class PaymentServiceImpl {
	static final Logger logger = Logger.getLogger(PaymentServiceImpl.class.getSimpleName());
    @Autowired
    TradeOrderServiceProxy tradeOrderServiceProxy;

    @Autowired
    OrderRepository orderRepository;

    @Compensable(confirmMethod = "confirmMakePayment", cancelMethod = "cancelMakePayment", asyncConfirm = false)
    @Transactional
    public void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {

        logger.info("order try make payment called.time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
        /**
         * 检查当前状态为DRAFT ,未支付的订单
         */
        if (order.getStatus().equals("DRAFT")) {
        	//修改状态为 PAYING 支付中..
            order.pay(redPacketPayAmount, capitalPayAmount);
            try {
                orderRepository.updateOrder(order);
            } catch (OptimisticLockingFailureException e) {
                //ignore the concurrently update order exception, ensure idempotency.
            }
        }
        /**
         * 执行资金账户扣减
         */
        String result = tradeOrderServiceProxy.record(null, buildCapitalTradeOrderDto(order));
        /**
         * 执行红包账户扣减
         */
        String result2 = tradeOrderServiceProxy.record(null, buildRedPacketTradeOrderDto(order));
    }

    public void confirmMakePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) throws Exception {
    	logger.info("order confirm make payment called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        Order foundOrder = orderRepository.findByMerchantOrderNo(order.getMerchantOrderNo());

        //check order status, only if the status equals DRAFT, then confirm order
        if (foundOrder != null && foundOrder.getStatus().equals("PAYING")) {
            order.confirm();
            orderRepository.updateOrder(order);
        }
    }

    public void cancelMakePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {


    	logger.info("order cancel make payment called.time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        Order foundOrder = orderRepository.findByMerchantOrderNo(order.getMerchantOrderNo());

        if (foundOrder != null && foundOrder.getStatus().equals("PAYING")) {
            order.cancelPayment();
            orderRepository.updateOrder(order);
        }
    }


    private CapitalTradeOrderDto buildCapitalTradeOrderDto(Order order) {

        CapitalTradeOrderDto tradeOrderDto = new CapitalTradeOrderDto();
        tradeOrderDto.setAmount(order.getCapitalPayAmount());
        tradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        tradeOrderDto.setSelfUserId(order.getPayerUserId());
        tradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        tradeOrderDto.setOrderTitle(String.format("order no:%s", order.getMerchantOrderNo()));

        return tradeOrderDto;
    }

    private RedPacketTradeOrderDto buildRedPacketTradeOrderDto(Order order) {
        RedPacketTradeOrderDto tradeOrderDto = new RedPacketTradeOrderDto();
        tradeOrderDto.setAmount(order.getRedPacketPayAmount());
        tradeOrderDto.setMerchantOrderNo(order.getMerchantOrderNo());
        tradeOrderDto.setSelfUserId(order.getPayerUserId());
        tradeOrderDto.setOppositeUserId(order.getPayeeUserId());
        tradeOrderDto.setOrderTitle(String.format("order no:%s", order.getMerchantOrderNo()));

        return tradeOrderDto;
    }
}
