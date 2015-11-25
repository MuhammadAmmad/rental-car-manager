package com.mmxb.mgr.dao;

import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.User;
import com.mmxb.mgr.pojo.UserExample;
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
public class UserDao {

    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public List<User> getUsers(String keyWord) {
        SqlSession sqlSession = openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = null;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(keyWord);
        userExample.or().andPhoneNameEqualTo(keyWord);
        if (keyWord == null || "".equals(keyWord)) {
            users = mapper.selectByExample(null);
        } else {
            users = mapper.selectByExample(userExample);
        }
        return users;
    }
}
