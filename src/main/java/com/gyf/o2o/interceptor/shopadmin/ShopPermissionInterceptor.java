package com.gyf.o2o.interceptor.shopadmin;

import com.gyf.o2o.entity.Shop;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by gaoyunfan on 2018/1/14
 **/

/**
 * 店家管理系统操作验证拦截器
 */
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        @SuppressWarnings("unchecked")
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");

        if (currentShop != null && shopList != null)
        {
            for (Shop shop : shopList)
            {
                if (shop.getShopId().equals(currentShop.getShopId()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
