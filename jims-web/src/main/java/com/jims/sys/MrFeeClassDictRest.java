package com.jims.sys;

import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.MrFeeClassDictApi;
import com.jims.sys.entity.MrFeeClassDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by Administrator on 2016/4/20.
 */

@Component
@Produces("application/json")
@Path("MrFeeClassDict")
public class MrFeeClassDictRest {
    @Autowired
    private MrFeeClassDictApi mrFeeClassDictApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<MrFeeClassDict> page=mrFeeClassDictApi.findPage(new Page<MrFeeClassDict>(request,response),new MrFeeClassDict());
        PageData<MrFeeClassDict> pageData=new PageData<MrFeeClassDict>();
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
    public MrFeeClassDict get(String id) {
        MrFeeClassDict mrFeeClassDict = mrFeeClassDictApi.get(id);
        return mrFeeClassDict;
    }

    /**
     * 保存修改方法
     * @param mrFeeClassDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(MrFeeClassDict mrFeeClassDict){
        String num=mrFeeClassDictApi.save(mrFeeClassDict);
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
        String num=mrFeeClassDictApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
