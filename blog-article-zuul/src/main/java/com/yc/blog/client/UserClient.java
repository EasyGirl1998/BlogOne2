package com.yc.blog.client;

import com.yc.blog.config.FeignClientConfig;
import com.yc.blog.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",configuration = FeignClientConfig.class)
@Component
public interface UserClient {


    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/user-proxy/login")
    String login(@RequestParam("user") User user, @RequestParam("errors") Errors errors,
                 @RequestParam("session") HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/register")
    String register(@RequestParam("user") User user,@RequestParam("errors")Errors errors, @RequestParam("account")String account,
                    @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/getQuestion")
    String getQuestion( @RequestParam("account")String account);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/lanswer")
    String answer( @RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/resetPwd")
    String resetPwd( @RequestParam("account")String account,@RequestParam("pwd") String pwd);

}
