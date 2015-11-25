package com.mmxb.mgr.dao;

import com.mmxb.mgr.mapper.CarMapper;
import com.mmxb.mgr.pojo.Car;
import com.mmxb.mgr.pojo.CarExample;
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
public class CarDao {
    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public List<Car> getCars(String keyWord) {
        SqlSession sqlSession = openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = null;
        CarExample carExample = new CarExample();
        carExample.createCriteria().andCarNumberEqualTo(keyWord);
        carExample.or().andCarTypeEqualTo(keyWord);
        carExample.or().andShopNameEqualTo(keyWord);
        if (keyWord == null || "".equals(keyWord)) {
            cars = mapper.selectByExample(null);
        } else {
            cars = mapper.selectByExample(carExample);
        }
        return cars;
    }
}
