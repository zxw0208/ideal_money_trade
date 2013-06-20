package com.zxw.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-20
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface CryptsyService {

    public void updateMarket();
}
