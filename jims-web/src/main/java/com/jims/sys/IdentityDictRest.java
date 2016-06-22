package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.IdentityDictApi;
import com.jims.sys.entity.IdentityDict;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 身份字典表 Rest
 * Created by fyg on 2016/6/21.
 */
@Component
@Produces("application/json")
@Path("identity-dict")
public class IdentityDictRest {

    @Reference(version = "1.0.0")
    private IdentityDictApi identityDictApi;

    /**
     * 查询所有记录
     * @return 身份字典的list集合
     * @author fengyuguang
     */
    @GET
    @Path("list")
    public List<IdentityDict> findList(){
        return identityDictApi.findList();
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<IdentityDict> beanChangeVo) {
        List<IdentityDict> inserted = beanChangeVo.getInserted();
        String num = identityDictApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 根据身份名称模糊查询记录
     * @param identityName  身份名称
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("search")
    public List<IdentityDict> search(@QueryParam("identityName")String identityName){
        return identityDictApi.search(identityName);
    }
}
