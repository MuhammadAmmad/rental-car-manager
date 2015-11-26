package com.mmxb.mgr.dao;

import com.mmxb.mgr.entity.CarAdd;
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

    public boolean insertCar(Car car){
        SqlSession sqlSession = openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
        CarExample carExample = new CarExample();
        carExample.createCriteria().andCarNumberEqualTo(car.getCarNumber());
        List<Car> cars = mapper.selectByExample(carExample);
        if (cars != null && cars.size() > 0){
            return false;
        }
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andShopNameEqualTo(car.getShopName());
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        if (shops != null && shops.size() > 0){
            car.setShopId(shops.get(0).getId());
            mapper.insert(car);
            return true;
        }else {
            return false;
        }
    }

    public void deleteCar(String id) {
        SqlSession sqlSession = openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        mapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    public CarAdd selectById(Integer id) {
        CarAdd carAdd = new CarAdd();
        SqlSession sqlSession = openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
        Car car = mapper.selectByPrimaryKey(id);
        Shop shop = shopMapper.selectByPrimaryKey(car.getShopId());
        carAdd.setId(car.getId());
        carAdd.setCarStatus(car.getCarStatus());
        carAdd.setCarType(car.getCarType());
        carAdd.setCarNumber(car.getCarNumber());
        carAdd.setIsRental(car.getIsRentaling());
        carAdd.setShopName(shop == null ? "" : shop.getShopName());
        return carAdd;
    }


    public boolean updateCarAdd(CarAdd carAdd) {
        Car car = new Car();
        SqlSession sqlSession = openSession();
        ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
        CarMapper carMapper =sqlSession.getMapper(CarMapper.class);
        String shopName = carAdd.getShopName();
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andShopNameEqualTo(shopName);
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        if (shops != null && shops.size() > 0){
            car.setShopName(shops.get(0).getShopName());
            car.setShopId(shops.get(0).getId());
        }else {
            return false;
        }
        CarExample carExample = new CarExample();
        carExample.createCriteria().andCarNumberEqualTo(carAdd.getCarNumber()).andIdNotEqualTo(carAdd.getId());
        List<Car> cars = carMapper.selectByExample(carExample);
        if (cars != null && cars.size() > 0){
            return false;
        }else {
            car.setId(carAdd.getId());
            car.setCarStatus(carAdd.getCarStatus());
            car.setCarNumber(carAdd.getCarNumber());
            car.setCarType(carAdd.getCarType());
            car.setIsRentaling("on".equals(carAdd.getIsRental()) ? "1" : "0");
            carMapper.updateByPrimaryKey(car);
            return true;
        }
    }
}
