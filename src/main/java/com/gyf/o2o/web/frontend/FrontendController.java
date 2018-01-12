package com.gyf.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by gaoyunfan on 2017/12/24
 **/
@Controller
@RequestMapping("/frontend")
public class FrontendController
{
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index()
    {
        return "frontend/index";
    }

    @RequestMapping(value = "/shoplist",method = RequestMethod.GET)
    private String shopList()
    {
        return "frontend/shoplist";
    }

    @RequestMapping(value = "/shopdetail",method = RequestMethod.GET)
    private String showShopDetail()
    {
        return "frontend/shopdetail";
    }

    @RequestMapping(value = "/productdetail",method = RequestMethod.GET)
    private String showProductDetail(){return "frontend/productdetail";}
}
