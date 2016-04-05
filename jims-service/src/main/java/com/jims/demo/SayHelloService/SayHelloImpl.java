package com.jims.demo.SayHelloService;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.demo.api.SayHelloApi;
import com.jims.demo.entity.User;

/**
 * Created by heren on 2016/4/5.
 */
@Service(version = "1.0.0")
public class SayHelloImpl implements SayHelloApi {
    @Override
    public String sayHello(User user) {
        return "hello "+user.getUserName() + ", your password is :"+user.getPassword();
    }

}
