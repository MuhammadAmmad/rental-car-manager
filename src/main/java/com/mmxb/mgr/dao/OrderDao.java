package com.mmxb.mgr.dao;

import com.mmxb.mgr.entity.OrderDetail;
import com.mmxb.mgr.mapper.CarMapper;
import com.mmxb.mgr.mapper.OrderMapper;
import com.mmxb.mgr.mapper.ShopMapper;
import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xing on 2015/11/25.
 */
@Service
@Transactional
public class OrderDao {

    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public List<OrderDetail> getOrders(String keyWord) {
        SqlSession session = openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        UserMapper userMapper = session.getMapper(UserMapper.class);
        CarMapper carMapper = session.getMapper(CarMapper.class);
        ShopMapper shopMapper = session.getMapper(ShopMapper.class);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        List<Order> orders;
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOrderIdEqualTo(keyWord);
        if (keyWord == null || "".equals(keyWord)) {
            orders = orderMapper.selectByExample(null);
        } else {
            orders = orderMapper.selectByExample(orderExample);
        }
        for (Order order : orders){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(order.getId());
            orderDetail.setOrderId(order.getOrderId());
            orderDetail.setOrderSatus(order.getOrderSatus());
            orderDetail.setOutTime(order.getOutTime());
            orderDetail.setBackTime(order.getBackTime());
            orderDetail.setOtherServer(order.getOtherServer());
            orderDetail.setMemo(order.getMemo());
            Integer userId = order.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            orderDetail.setName(user.getName());
            orderDetail.setPhoneName(user.getPhoneName());
            Integer carId = order.getCarId();
            Car car = carMapper.selectByPrimaryKey(carId);
            orderDetail.setCarNumber(car.getCarNumber());
            Integer shopId = car.getShopId();
            Shop shop = shopMapper.selectByPrimaryKey(shopId);
            orderDetail.setShopName(shop.getShopName());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    public Order selectById(Integer integer) {
        SqlSession session = openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        return orderMapper.selectByPrimaryKey(integer);
    }

    public boolean updateOrder(Integer integer, String orderStatus) {
        SqlSession session = openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.selectByPrimaryKey(integer);
        CarMapper carMapper = session.getMapper(CarMapper.class);
        if (orderStatus.equals("2")){
            //同意订单
            order.setOrderSatus("2");
            orderMapper.updateByPrimaryKey(order);
            return true;
        }else if (orderStatus.equals("3")){
            //拒绝订单
            order.setOrderSatus("3");
            orderMapper.updateByPrimaryKey(order);
            Integer carId = order.getCarId();
            Car car = carMapper.selectByPrimaryKey(carId);
            car.setIsRentaling("0");
            carMapper.updateByPrimaryKey(car);
            return true;
        }else if (orderStatus.equals("4")){
            //订单异常
            order.setOrderSatus("4");
            orderMapper.updateByPrimaryKey(order);
            Integer carId = order.getCarId();
            Car car = carMapper.selectByPrimaryKey(carId);
            car.setIsRentaling("0");
            carMapper.updateByPrimaryKey(car);
            return true;
        }else if (orderStatus.equals("5")){
            //订单完成
            order.setOrderSatus("5");
            orderMapper.updateByPrimaryKey(order);
            Integer carId = order.getCarId();
            Car car = carMapper.selectByPrimaryKey(carId);
            car.setIsRentaling("0");
            carMapper.updateByPrimaryKey(car);
            return true;
        }
        return false;
    }
}
