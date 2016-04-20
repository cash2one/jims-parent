package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.DictService;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("dict")
public class DictRest {

    @Reference(version = "1.0.0")
    private DictService dictService ;

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dict> merge(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<Dict> page = dictService.findPage(new Page<Dict>(request,response), new Dict());
        PageData<Dict> pageData=new PageData<Dict>();

        return page.getList();
    }

}
