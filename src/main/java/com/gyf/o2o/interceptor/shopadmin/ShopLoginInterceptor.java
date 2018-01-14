package com.gyf.o2o.interceptor.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyf.o2o.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by gaoyunfan on 2018/1/14
 **/
public class ShopLoginInterceptor extends HandlerInterceptorAdapter
{
    /**
     * 事前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null)
        {
            PersonInfo user = (PersonInfo) userObj;
            if (user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1)
            {
                return true;//去下一个拦截器里做校验
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('"+request.getContextPath()+"/local/login?usertype=2','_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
