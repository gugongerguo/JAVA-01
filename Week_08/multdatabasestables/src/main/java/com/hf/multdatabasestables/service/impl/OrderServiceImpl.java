package com.hf.multdatabasestables.service.impl;

import com.hf.multdatabasestables.model.Order;
import com.hf.multdatabasestables.model.OrderExample;
import com.hf.multdatabasestables.repository.OrderRepository;
import com.hf.multdatabasestables.service.OrderService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public int addOrder(Order order) {
        int result = orderRepository.insertSelective(order);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public int addOrders(List<Order> orders) {
        for (Order order : orders) {
            orderRepository.insertSelective(order);
        }
        return 1;
    }

    @Override
    public int updateOrder(Order order) {
        int result = orderRepository.updateByPrimaryKey(order);
        return result;
    }

    @Override
    public int updateOrders(List<Order> orders) {
        return 0;
    }

    @Override
    public int deleteOrder(Long id) {
        int result = orderRepository.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public int deleteOrders(List<Order> orders) {
        return 0;
    }

    @Override
    public List<Order> selectOrderExample(OrderExample orderExample) {
        RowBounds rowBounds = new RowBounds(0, 10);
        List<Order> orders = orderRepository.selectByExampleWithRowbounds(orderExample, rowBounds);
        return orders;
    }

    @Override
    public Order selectOrderByPrimaryKey(Long id) {
        Order order = orderRepository.selectByPrimaryKey(id);
        return order;
    }
}
