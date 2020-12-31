package com.yc.blog.service;

import com.yc.blog.domain.User;
import com.yc.blog.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@Service
public class UserRestService {

    @Autowired
    private UserClient userClient;

//     public User findByAccount( String account){
//         return userClient.findByAccount(account);
//    }

    public String login(@RequestParam("user")User user, @RequestParam("errors") Errors errors, @RequestParam("session") HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        System.out.println(userClient.getClass());
        return userClient.login(user, errors, session,account,pwd);
    }

    public String register(@Valid User user,Errors errors,@RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email){
        System.out.println("zuul"+user+"|"+account+"|"+pwd+"|"+name+"|"+email+"3333333333333333333333");

        return userClient.register(user,errors,account,pwd,name,email);
    }
    /*public String register(@Valid User user, Errors errors, @RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email){
        System.out.println("zuul"+user+"|"+errors+"|"+account+"|"+pwd+"|"+name+"|"+email);

        return userClient.register(user, errors,account,pwd,name,email);
    }*/

    public String getQuestion(@RequestParam("account")String account){
        return userClient.getQuestion(account);
    }

    public String answer(@RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer){
        return userClient.answer(account, pwdAnswer);
    }

    public String resetPwd(@RequestParam("account")String account,@RequestParam("pwd") String pwd){
        System.out.println("resetpwdclient");
        return userClient.resetPwd(account, pwd);
    }

}
