package com.tp.soft.baseinfo.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.tp.soft.baseinfo.service.TaService;
import com.tp.soft.util.web.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: taop
 * @Date: 2019/3/10 下午4:05
 * @Version 1.0
 */
@Service
public class TaServiceImpl implements TaService {

    @Override
    @Transactional
    @LcnTransaction
    public ApiResponse add() {

        //int i = 1/0;
        return ApiResponse.success();
    }
}
