package com.sf.demo.order.dao;


import com.sf.demo.order.entity.Shop;

/**
 * Created by changming.xie on 4/1/16.
 */
public interface ShopDao {
    Shop findById(long id);
}
