package com.mmxb.mgr.dao;

import com.mmxb.mgr.mapper.CarMapper;
import com.mmxb.mgr.mapper.ShopMapper;
import com.mmxb.mgr.pojo.Car;
import com.mmxb.mgr.pojo.CarExample;
import com.mmxb.mgr.pojo.Shop;
import com.mmxb.mgr.pojo.ShopExample;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Xing on 2015/11/25.
 */
@Service
@Transactional
public class ShopDao {

    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public List<Shop> getShops(String keyWord) {
        SqlSession sqlSession = openSession();
        ShopMapper mapper = sqlSession.getMapper(ShopMapper.class);
        List<Shop> shops = null;
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andShopNameEqualTo(keyWord);
        shopExample.or().andPositionEqualTo(keyWord);
        if (keyWord == null || "".equals(keyWord)) {
            shops = mapper.selectByExample(null);
        } else {
            shops = mapper.selectByExample(shopExample);
        }
        return shops;
    }

    public boolean insert(Shop shop){
        SqlSession sqlSession = openSession();
        ShopMapper mapper = sqlSession.getMapper(ShopMapper.class);
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andShopNameEqualTo(shop.getShopName());
        List<Shop> shops = mapper.selectByExample(shopExample);
        if (shops != null && shops.size() > 0){
            return false;
        }else {
            mapper.insert(shop);
            return true;
        }
    }

    public Shop selectById(Integer id) {
        SqlSession sqlSession = openSession();
        ShopMapper mapper = sqlSession.getMapper(ShopMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public boolean updateShop(Shop shop) {
        SqlSession sqlSession = openSession();
        ShopMapper mapper = sqlSession.getMapper(ShopMapper.class);
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andShopNameEqualTo(shop.getShopName()).andIdNotEqualTo(shop.getId());
        List<Shop> shops = mapper.selectByExample(shopExample);
        if (shops != null && shops.size() > 0){
            return false;
        }
        mapper.updateByPrimaryKey(shop);
        //修改车关联的商店名称
        CarMapper mapper1 = sqlSession.getMapper(CarMapper.class);
        CarExample example = new CarExample();
        example.createCriteria().andShopIdEqualTo(shop.getId());
        List<Car> cars = mapper1.selectByExample(example);
        for (Car car : cars){
            car.setShopName(shop.getShopName());
            mapper1.updateByPrimaryKey(car);
        }
        return true;
    }
}
