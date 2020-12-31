package com.yc.blog.client;

import com.yc.blog.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",contextId = "admin",configuration = FeignClientConfig.class)
public interface AdminClient {

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/admin-proxy/admin/login")
    String selectByAdmin(@RequestParam("account")String account, @RequestParam("pwd")String pwd);
}
