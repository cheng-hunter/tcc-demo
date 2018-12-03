package com.sf.demo.capital.api;

import com.sf.demo.capital.api.dto.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.TransactionContext;


public interface CapitalTradeOrderService {
    /**
     * 添加资金服务的接口
     * @param transactionContext
     * @param tradeOrderDto
     * @return
     */
    public String record(TransactionContext transactionContext, CapitalTradeOrderDto tradeOrderDto);
}
