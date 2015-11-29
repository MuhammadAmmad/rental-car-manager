package com.mmxb.mgr.server;

import java.util.HashMap;

/**
 * Created by Xing on 2015/11/28.
 */
public class JsonResponse extends HashMap<String,Object> {

    public JsonResponse(boolean success){
        setSuccess(success);
    }

    public void setSuccess(boolean success){
        put("success",success);
    }
}
