package com.yc.blog.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.blog.client.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminRestService {

    @Autowired
    private AdminClient adminClient;

    @HystrixCommand(fallbackMethod = "selectByUserFallback")
    public String selectByUser(String account,String pwd)
    {
        return adminClient.selectByAdmin(account,pwd);
    }

    private String selectByUserFallback(String account,String pwd) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}
