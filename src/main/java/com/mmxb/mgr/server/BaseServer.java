package com.mmxb.mgr.server;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Xing on 2015/11/23.
 */
public abstract class BaseServer {

    @Autowired
    @Qualifier("mgrSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    protected HttpServletRequest request;

    public SqlSession openSession(){
        if (sqlSessionFactory == null){
            return null;
        }
        return sqlSessionFactory.openSession();
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

}
