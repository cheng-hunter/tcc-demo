package com.sf.demo.redpacket.dao;

import com.sf.demo.redpacket.entity.RedPacketAccount;

/**
 * Created by changming.xie on 4/2/16.
 */
public interface RedPacketAccountDao {

    RedPacketAccount findByUserId(long userId);

    int update(RedPacketAccount redPacketAccount);
}
