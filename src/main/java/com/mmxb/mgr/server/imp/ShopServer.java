package com.mmxb.mgr.server.imp;

import com.mmxb.mgr.mapper.CarMapper;
import com.mmxb.mgr.mapper.ShopMapper;
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
@RequestMapping("/mgr/shop")
@RestController
@Transactional
public class ShopServer extends BaseServer {
    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @RequestMapping(value = "/listShop",method = {RequestMethod.POST})
    public JsonResponse listShop(@ModelAttribute User user,@RequestParam(value = "type") String type){
        JsonResponse json = new JsonResponse(false);
        if (validateUser(user.getPhoneName(),user.getPassword())){
            SqlSession session = openSession();
            CarMapper carMapper = session.getMapper(CarMapper.class);
            ShopMapper shopMapper = session.getMapper(ShopMapper.class);
            CarExample carExample = new CarExample();
            ShopExample shopExample = new ShopExample();
            carExample.clear();
            carExample.createCriteria().andTypeEqualTo(type).andIsRentalingEqualTo("0");
            List<Car> cars = carMapper.selectByExample(carExample);
            List<Integer> shopIds = new ArrayList<Integer>();
            if (cars != null && cars.size() > 0){
                for (Car car : cars ){
                    shopIds.add(car.getShopId());
                }
                shopExample.clear();
                shopExample.createCriteria().andIdIn(shopIds);
                List<Shop> shops = shopMapper.selectByExample(shopExample);
                json.put("shops",shops);
                json.setSuccess(true);
            }else {
                json.setSuccess(true);
            }
        }else {
            json.setSuccess(false);
        }
        return json;
    }
}
