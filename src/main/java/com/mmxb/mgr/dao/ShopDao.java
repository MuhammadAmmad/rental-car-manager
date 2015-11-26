package com.mmxb.mgr.dao;

import com.mmxb.mgr.mapper.ShopMapper;
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
}
