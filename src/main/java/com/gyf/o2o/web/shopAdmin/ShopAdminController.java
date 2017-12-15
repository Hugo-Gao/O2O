package com.gyf.o2o.web.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gaoyunfan on 2017/12/10
 **/
@Controller
@RequestMapping(value = "/shopadmin",method = RequestMethod.GET)
public class ShopAdminController
{
    @RequestMapping(value = "/shopoperation")
    public String shopOperation()
    {
        return "shop/shopoperation";
    }
}
