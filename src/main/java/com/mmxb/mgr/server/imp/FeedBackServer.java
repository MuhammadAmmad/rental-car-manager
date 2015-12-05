package com.mmxb.mgr.server.imp;

import com.mmxb.mgr.mapper.FeedBackMapper;
import com.mmxb.mgr.mapper.UserMapper;
import com.mmxb.mgr.pojo.FeedBack;
import com.mmxb.mgr.pojo.User;
import com.mmxb.mgr.pojo.UserExample;
import com.mmxb.mgr.server.BaseServer;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Xing on 2015/11/29.
 */
@RequestMapping("/mgr/feedback")
@RestController
@Transactional
public class FeedBackServer extends BaseServer {
    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    /**
     * 反馈成功返回0
     * 反馈失败返回-1
     * @param feedBack
     * @return
     */
    @RequestMapping(value = "/feedQuestion",method = {RequestMethod.POST})
    public String feedQuestion(@ModelAttribute User user,@ModelAttribute FeedBack feedBack){
        if (validateUser(user.getPhoneName(),user.getPassword())){
            SqlSession session = openSession();
            FeedBackMapper feedBackMapper = session.getMapper(FeedBackMapper.class);
            UserMapper userMapper = session.getMapper(UserMapper.class);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneNameEqualTo(user.getPhoneName());
            User users = userMapper.selectByExample(userExample).get(0);
            FeedBack feedBack1 = new FeedBack();
            feedBack1.setContent(feedBack.getContent());
            feedBack1.setFeedbackTime(new Date());
            feedBack1.setUserName(users.getName());
            feedBack1.setUserPhone(user.getPhoneName());
            feedBack1.setFeedbackNumber("0");
            feedBackMapper.insert(feedBack1);
            return "0";
        }else {
            return "-1";
        }
    }
}
