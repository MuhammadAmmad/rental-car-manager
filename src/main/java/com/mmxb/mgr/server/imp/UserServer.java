package com.mmxb.mgr.server.imp;

import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.User;
import com.mmxb.mgr.server.BaseServer;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Xing on 2015/11/23.
 */

@RestController
@Transactional
@RequestMapping("mgr/user")
public class UserServer extends BaseServer{
    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    public int count(){
        SqlSession session = openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByExample(null);
        return users.size();
    }
}
