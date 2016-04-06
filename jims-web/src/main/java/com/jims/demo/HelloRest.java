package com.jims.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.demo.api.SayHelloApi;
import com.jims.demo.entity.DemoUser;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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
        DemoUser user = new DemoUser(userName,password) ;
        return sayHelloApi.sayHello(user) ;
    }
    @GET
        @Path("get")
        public List<DemoUser> getDemo(){
        return sayHelloApi.getDemo() ;
    }
    @GET
    @Path("get1")
    private DemoUser getDemo1(){
        return sayHelloApi.getDemo1() ;
    }
}
