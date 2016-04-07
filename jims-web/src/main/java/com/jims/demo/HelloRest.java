package com.jims.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.demo.api.SayHelloApi;
import com.jims.demo.entity.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path("list")
    public List<User> usersHello(){
        List<User> users = new ArrayList<User>() ;
        for( int i = 0 ;i<10;i++){
            User user = new User(""+i,"+i") ;
            users.add(user) ;
        }
        return users ;
    }


}
