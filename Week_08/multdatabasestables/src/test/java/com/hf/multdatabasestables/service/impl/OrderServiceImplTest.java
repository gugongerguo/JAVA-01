package com.hf.multdatabasestables.service.impl;

import com.hf.multdatabasestables.model.Order;
import com.hf.multdatabasestables.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    void addOrder() {
        Order order = new Order().withPrice(11.1f).withStatus(0).withUserId(1).withIsDeleted(false);
        int i = orderService.addOrder(order);
        System.out.println("addOrder()============> "+i);
    }

    @Test
    void addOrders() {
        List<Order> orders = new ArrayList<>();
        for(int i=0;i<100;i++){

            Order order = new Order().withPrice(11.1f).withStatus(0).withUserId(i).withIsDeleted(false);
            if(i==50){
                order = new Order().withPrice(11.1f).withStatus(0).withUserId(i);
            }
            orders.add(order);
        }
        orderService.addOrders(orders);
    }

    @Test
    void updateOrder() {
    }

    @Test
    void updateOrders() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void deleteOrders() {
    }

    @Test
    void selectOrderExample() {
    }

    @Test
    void selectOrderByPrimaryKey() {
    }
}