package com.jims.common.filter;

import com.jims.util.JedisUtils;
import com.jims.oauth2.integration.utils.CacheManager;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/5/30.
 */
public class OauthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //HttpSession session = httpServletRequest.getSession();
        //String accesstoken = httpServletRequest.getParameter("access_token");
        //String json = "{data:'access_token已失效!'}";
        //String uname = "{data:'用户登录已超时!'}";
        //String scpes = "{data:'没有此类资源!'}";
        //String sb = "{data:'获取失败!'}";
        //httpServletResponse.setCharacterEncoding("UTF-8");
        //httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        //PrintWriter writer = httpServletResponse.getWriter();
        //if(CacheManager.getCacheInfo("accessToken") == null){
        //    writer.write(json);
        //    return;
        //}else if(CacheManager.getCacheInfo(accesstoken + "_userName") == null){
        //    writer.write(uname);
        //    return;
        //}
        //String cachetoken = null;
        //if (CacheManager.getCacheInfo("accessToken").getValue() != null) {
        //    cachetoken = CacheManager.getCacheInfo("accessToken").getValue().toString();
        //    if(accesstoken.equals(cachetoken)){
        //        if(CacheManager.getCacheInfo(accesstoken+"_userName") != null){
        //            String clientId  = CacheManager.getCacheInfo(accesstoken + "_clientId").getValue().toString();
        //            StringBuffer requestURL = httpServletRequest.getRequestURL();
        //            String path = requestURL.toString();
        //            String scope = JedisUtils.get(clientId);
        //            if(scope == null){
        //                writer.write(scpes);
        //                return;
        //            }
        //            String[] id = scope.split(",");
        //            for (int j = 0; j < id.length; j++) {
        //               if(path.contains(id[j])){
        //                   chain.doFilter(request, response);
        //                   return;
        //               }
        //            }
        //        }
        //    } else {
        //        writer.write(sb);
        //        return;
        //    }
        //}
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest)request ;
        HttpSession session =httpServletRequest.getSession();
        String orgId=httpServletRequest.getParameter("orgId");

        chain.doFilter(request,response);
        //res.sendRedirect("/index.html");
    }

    @Override
    public void destroy() {

    }
}
