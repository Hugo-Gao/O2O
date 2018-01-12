package com.gyf.o2o.web.frontend;

import com.gyf.o2o.dao.ProductImgDao;
import com.gyf.o2o.entity.Product;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.entity.ProductImg;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.service.ProductService;
import com.gyf.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyunfan on 2018/1/10
 **/
@Controller
@RequestMapping("/frontend")
public class ProductDetailController
{
    @Autowired
    private ProductService productService;


    /**
     * 获取商品详情，包括商品信息和缩略图
     * @param request
     * @return
     */
    @RequestMapping(value = "/getproductdetail", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductDetail(HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        long productId = HttpServletRequestUtil.getLong(request, "productId");
        if (productId > 0)
        {
            Product product = productService.getProductById(productId);
            List<ProductImg> productImgList = productService.getProductImgListById(productId);
            modelMap.put("success", true);
            modelMap.put("product",product);
            modelMap.put("productImgList",productImgList);
        } else
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Error productId");
        }
        return modelMap;
    }

}
