package com.mmxb.mgr.server;

import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.User;
import com.mmxb.mgr.pojo.UserExample;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by Xing on 2015/11/28.
 */
public abstract class BaseServer {
    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    public SqlSession openSession() {
        if (sqlSessionFactory == null) {
            return null;
        }
        return sqlSessionFactory.openSession();
    }

    public boolean validateUser(String phone, String password){
        SqlSession sqlSession = openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneNameEqualTo(phone).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
