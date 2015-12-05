package com.mmxb.mgr.dao;

import com.mmxb.mgr.mapper.FeedBackMapper;
import com.mmxb.mgr.pojo.FeedBack;
import com.mmxb.mgr.pojo.FeedBackExample;
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
public class FeedBackDao {
    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public List<FeedBack> getFeedBacks(String keyWord) {
        SqlSession sqlSession = openSession();
        FeedBackMapper mapper = sqlSession.getMapper(FeedBackMapper.class);
        List<FeedBack> cars = null;
        FeedBackExample feedBackExample = new FeedBackExample();
        feedBackExample.createCriteria().andFeedbackNumberEqualTo(keyWord);
        feedBackExample.or().andUserNameEqualTo(keyWord);
        feedBackExample.or().andUserPhoneEqualTo(keyWord);
        if (keyWord == null || "".equals(keyWord)) {
            cars = mapper.selectByExample(null);
        } else {
            cars = mapper.selectByExample(feedBackExample);
        }
        return cars;
    }

    public FeedBack selectById(Integer integer) {
        SqlSession sqlSession = openSession();
        FeedBackMapper mapper = sqlSession.getMapper(FeedBackMapper.class);
        return mapper.selectByPrimaryKey(integer);
    }
}
