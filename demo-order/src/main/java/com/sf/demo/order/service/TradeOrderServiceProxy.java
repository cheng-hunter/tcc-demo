package com.sf.demo.order.service;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import com.sf.demo.capital.api.CapitalTradeOrderService;
import com.sf.demo.capital.api.dto.CapitalTradeOrderDto;
import com.sf.demo.redpacket.api.RedPacketTradeOrderService;
import com.sf.demo.redpacket.api.dto.RedPacketTradeOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by changming.xie on 4/19/17.
 */
@Component
public class TradeOrderServiceProxy {

    @Autowired
    CapitalTradeOrderService capitalTradeOrderService;

    @Autowired
    RedPacketTradeOrderService redPacketTradeOrderService;


    /***
     *  事务继续传播,当前主事务开始分支事务,并注册
     * @param transactionContext
     * @param tradeOrderDto
     * @return
     */
    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "record", cancelMethod = "record", transactionContextEditor = MethodTransactionContextEditor.class)
    public String record(TransactionContext transactionContext, CapitalTradeOrderDto tradeOrderDto) {
        return capitalTradeOrderService.record(transactionContext, tradeOrderDto);
    }

    /***
     *  事务继续传播,当前主事务开始分支事务,并注册
     * @param transactionContext
     * @param tradeOrderDto
     * @return
     */
    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "record", cancelMethod = "record", transactionContextEditor = MethodTransactionContextEditor.class)
    public String record(TransactionContext transactionContext, RedPacketTradeOrderDto tradeOrderDto) {
        return redPacketTradeOrderService.record(transactionContext, tradeOrderDto);
    }
}
