package com.ghd.service;

import com.ghd.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 继承elasticsearchRespository接口 实现简单的crud   <当前操作model的对象，对象里主键的类型>
 */
@Service
public interface OrderService extends ElasticsearchRepository<Order,String> {

    /**
     * 根据用户名 and 订单号
     * @param username
     * @param orderNum
     * @return
     */
    List<Order> findByUsernameAndOrderNum(String keyword1,String keyword2);

    List<Order> findByProductName(String name);
}
