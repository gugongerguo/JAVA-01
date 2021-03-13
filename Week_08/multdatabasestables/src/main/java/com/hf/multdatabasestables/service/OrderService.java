package com.hf.multdatabasestables.service;

import com.hf.multdatabasestables.model.Order;
import com.hf.multdatabasestables.model.OrderExample;

import java.util.List;

public interface OrderService {

    int addOrder(Order order);

    int addOrders(List<Order> orders);

    int updateOrder(Order order);

    int updateOrders(List<Order> orders);

    int deleteOrder(Long id);

    int deleteOrders(List<Order> orders);

    List<Order> selectOrderExample(OrderExample orderExamples);

    Order selectOrderByPrimaryKey(Long id);
}
