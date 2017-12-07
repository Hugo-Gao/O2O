package com.gyf.o2o.web.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.util.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaoyunfan on 2017/12/7
 **/
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController
{
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request)
    {
        //接受转化参数
        //注册店铺
        //返回结果
        Map<String, Object> modelMap = new HashMap<>();
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try
        {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e)
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return null;
    }
}
