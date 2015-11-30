package com.mmxb.mgr.server.imp;

import com.mmxb.mgr.entity.OrderDetail;
import com.mmxb.mgr.entity.OrderList;
import com.mmxb.mgr.mapper.CarMapper;
import com.mmxb.mgr.mapper.OrderMapper;
import com.mmxb.mgr.mapper.ShopMapper;
import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.*;
import com.mmxb.mgr.server.BaseServer;
import com.mmxb.mgr.server.JsonResponse;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xing on 2015/11/29.
 */
@RequestMapping("/mgr/order")
@RestController
@Transactional
public class OrderServer extends BaseServer {
    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    public JsonResponse list(@ModelAttribute User user){
        JsonResponse json = new JsonResponse(false);
        if (validateUser(user.getPhoneName(),user.getPassword())){
            SqlSession session = openSession();
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            UserMapper userMapper = session.getMapper(UserMapper.class);
            CarMapper carMapper = session.getMapper(CarMapper.class);
            OrderExample orderExample = new OrderExample();
            UserExample userExample = new UserExample();

            //用户id
            userExample.clear();
            userExample.createCriteria().andPhoneNameEqualTo(user.getPhoneName());
            User user1 = userMapper.selectByExample(userExample).get(0);

            //该用户所有订单
            orderExample.clear();
            orderExample.createCriteria().andUserIdEqualTo(user1.getId());
            List<Order> orders = orderMapper.selectByExample(orderExample);
            List<OrderList> orderLists = new ArrayList<OrderList>();
            for (Order order : orders){
                Car car = carMapper.selectByPrimaryKey(order.getCarId());
                OrderList orderList = new OrderList();
                orderList.setOrderId(order.getOrderId());
                orderList.setCarType(car.getCarType());
                orderList.setOrderSatus(order.getOrderSatus());
                orderList.setPrice(car.getPrice());
                orderLists.add(orderList);
            }
            json.put("data",orderLists);
            json.setSuccess(true);
        }else {
            json.setSuccess(false);
        }
        return json;
    }

    @RequestMapping(value = "/orderDetail",method = {RequestMethod.POST})
    public JsonResponse orderDetail(@ModelAttribute User user,@RequestParam(value = "orderId") String orderId){
        JsonResponse json = new JsonResponse(false);
        if (validateUser(user.getPhoneName(),user.getPassword())){
            SqlSession session = openSession();
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            UserMapper userMapper = session.getMapper(UserMapper.class);
            CarMapper carMapper = session.getMapper(CarMapper.class);
            ShopMapper shopMapper = session.getMapper(ShopMapper.class);
            OrderExample orderExample = new OrderExample();

            //该订单
            orderExample.clear();
            orderExample.createCriteria().andOrderIdEqualTo(orderId);
            Order order = orderMapper.selectByExample(orderExample).get(0);

            //关联的车
            Car car = carMapper.selectByPrimaryKey(order.getCarId());

            //关联用户
            User user1 = userMapper.selectByPrimaryKey(order.getUserId());

            //关联商铺
            Shop shop = shopMapper.selectByPrimaryKey(car.getShopId());

            //订单详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(order.getId());
            orderDetail.setOrderId(order.getOrderId());
            orderDetail.setOrderSatus(order.getOrderSatus());
            orderDetail.setShopName(shop.getShopName());
            orderDetail.setPosition(shop.getPosition());
            orderDetail.setOutTime(order.getOutTime());
            orderDetail.setBackTime(order.getBackTime());
            orderDetail.setCarNumber(car.getCarNumber());
            orderDetail.setPrice(car.getPrice());
            orderDetail.setCarType(car.getCarType());
            orderDetail.setType(order.getType());
            orderDetail.setMemo(order.getMemo());
            orderDetail.setOtherServer(order.getOtherServer());
            json.put("data",orderDetail);
            json.setSuccess(true);
        }else {
            json.setSuccess(false);
        }
        return json;
    }
}
