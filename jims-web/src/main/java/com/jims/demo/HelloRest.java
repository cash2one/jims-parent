package com.jims.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.demo.api.SayHelloApi;
import com.jims.demo.entity.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("hello")
public class HelloRest  {

    @Reference(version = "1.0.0")
    private SayHelloApi sayHelloApi ;

    @GET
    @Path("{userName}/{password}")
    public String sayHello(@PathParam("userName")String userName,@PathParam("password")String password){
        User user = new User(userName,password) ;
        return sayHelloApi.sayHello(user) ;
    }

}
