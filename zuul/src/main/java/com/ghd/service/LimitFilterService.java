package com.ghd.service;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: Ghd
 * @Date: 2019/12/25 16:32
 *  网关限流
 */
@Component
@Slf4j
public class LimitFilterService extends ZuulFilter {

    static final RateLimiter rateLimiter = com.google.common.util.concurrent.RateLimiter.create(20);
    static int i = 0;

    /**
     * 这种过滤器在请求被路由之前调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 数字越小越，最前面执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * true返回一个boolean判断该过滤器是否要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //是否进入到下面具体内容方法，场景:判断当前用户是否有token
        return true;
    }

    /**
     * 过滤器执行具体内容
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info(String.valueOf(++i));
        if(!rateLimiter.tryAcquire()){
            log.info("未获得令牌");
            throw new RuntimeException("未获得令牌");
        }
        log.info("拿到令牌了....");
        return "拿到令牌了.......";
    }
}
