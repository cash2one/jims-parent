package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.DictService;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
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

    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
      @Path("list")
      @GET
      public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
            Page<Dict> page = dictService.findPage(new Page<Dict>(request,response), new Dict());
            PageData pageData=new PageData();
            pageData.setRows(page.getList());
            pageData.setTotal(page.getCount());
            return pageData;
         }

        /**
         * 获取单条数据
         * @param id
         * @return
         */
        @Path("get")
        @POST
        public Dict get(String id){
            Dict dict=dictService.get(id);
            return dict;
        }
    /**
     * 保存修改方法
     * @param dict
     * @return
     */
        @Path("save")
        @POST
        public StringData save(Dict dict){
            String num=dictService.save(dict);
            StringData stringData=new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }

        /**
        *
        * @param ids
        * @return
        */
        @Path("del")
        @POST
        public StringData del(String ids){
            String num=dictService.delete(ids);
            StringData stringData=new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
}
