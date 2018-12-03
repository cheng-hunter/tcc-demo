package com.sf.demo.order.web.controller.vo;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by changming.xie on 4/2/16.
 */
public class PlaceOrderRequest {

    private long payerUserId;

    private long shopId;

    private BigDecimal redPacketPayAmount;

    private long productId;

    public long getPayerUserId() {
        return payerUserId;
    }

    public void setPayerUserId(long payerUserId) {
        this.payerUserId = payerUserId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getRedPacketPayAmount() {
        return redPacketPayAmount;
    }

    public void setRedPacketPayAmount(BigDecimal redPacketPayAmount) {
        this.redPacketPayAmount = redPacketPayAmount;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
