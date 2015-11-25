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
}
