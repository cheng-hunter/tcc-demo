package com.sf.demo.capital.service;

import com.sf.demo.capital.entity.repository.CapitalAccountRepository;
import com.sf.demo.capital.api.CapitalAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by twinkle.zhou on 16/11/11.
 */
public class CapitalAccountServiceImpl implements CapitalAccountService{


    @Autowired
    CapitalAccountRepository capitalAccountRepository;

    @Override
    public BigDecimal getCapitalAccountByUserId(long userId) {
        return capitalAccountRepository.findByUserId(userId).getBalanceAmount();
    }
}
