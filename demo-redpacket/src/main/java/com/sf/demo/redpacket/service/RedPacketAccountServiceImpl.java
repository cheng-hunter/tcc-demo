package com.sf.demo.redpacket.service;

import com.sf.demo.redpacket.api.RedPacketAccountService;
import com.sf.demo.redpacket.entity.repository.RedPacketAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by twinkle.zhou on 16/11/11.
 */
public class RedPacketAccountServiceImpl implements RedPacketAccountService {

    @Autowired
    RedPacketAccountRepository redPacketAccountRepository;

    @Override
    public BigDecimal getRedPacketAccountByUserId(long userId) {
        return redPacketAccountRepository.findByUserId(userId).getBalanceAmount();
    }
}
