package com.mmxb.mgr.server.imp;

import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.User;
import com.mmxb.mgr.pojo.UserExample;
import com.mmxb.mgr.server.BaseServer;
import com.mmxb.mgr.server.JsonResponse;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Xing on 2015/11/28.
 */
@RequestMapping("/mgr/user")
@RestController
@Transactional
public class UserServer extends BaseServer {

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }


    /**
     * 返回值0代表注册成功
     * 返回值-1代表注册失败
     *
     * @return
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public String register(@ModelAttribute User user) {
        SqlSession session = openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        String phoneName = user.getPhoneName();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneNameEqualTo(phoneName);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0){
            return "-1";
        }
        user.setHead("1");
        user.setCardClass("身份证");
        user.setIsRentaling("0");
        user.setIsLoging("1");
        user.setRegisterTime(new Date());
        user.setLevel(1);
        userMapper.insert(user);
        getLogger().info("insert a user");
        return "0";
    }

    /**
     * 返回0登录成功
     * 返回-1登录失败
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(@ModelAttribute User user){
        String phoneName = user.getPhoneName();
        String password = user.getPassword();
        SqlSession sqlSession = openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneNameEqualTo(phoneName).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0){
            return "0";
        }else {
            return "-1";
        }
    }

    /**
     * 返回用户信息
     * @param user
     */
    @RequestMapping(value = "/getUserMessage", method = {RequestMethod.POST})
    public JsonResponse getUserMessage(@ModelAttribute User user){
        JsonResponse json = new JsonResponse(false);
        if (validateUser(user.getPhoneName(),user.getPassword())){
            SqlSession session = openSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneNameEqualTo(user.getPhoneName());
            List<User> users = userMapper.selectByExample(userExample);
            if (users != null && users.size() > 0){
                User user1 = users.get(0);
                HashMap<String,Object> map = new HashMap<String, Object>();
                map.put("name", user1.getName());
                map.put("card_num",user1.getCardNum().substring(0,6) + "******" + user1.getCardNum().substring(16));
                json.put("user", map);
                json.setSuccess(true);
            }else {
                json.setSuccess(false);
            }
        }else {
            json.setSuccess(false);
        }
        return json;
    }

    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST})
    public String updatePassword(@ModelAttribute User user,@RequestParam(value = "oldPassword") String oldPassword,@RequestParam(value = "newPassword") String newPassword){
        if (validateUser(user.getPhoneName(),user.getPassword())){
            SqlSession session = openSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneNameEqualTo(user.getPhoneName()).andPasswordEqualTo(oldPassword);
            List<User> users = userMapper.selectByExample(userExample);
            if (users != null && users.size() > 0){
                User user1 = users.get(0);
                user1.setPassword(newPassword);
                userMapper.updateByExample(user1,userExample);
                return "0";
            }else {
                return "-1";
            }
        }else {
            return "-1";
        }
    }

}
