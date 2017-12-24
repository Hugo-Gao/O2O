package com.gyf.o2o.web.shopAdmin;

import com.gyf.o2o.dto.ProductCategoryExecution;
import com.gyf.o2o.dto.Result;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.enums.ProductCategoryStateEnum;
import com.gyf.o2o.exceptions.ProductCategoryOperationException;
import com.gyf.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyunfan on 2017/12/19
 **/

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController
{
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductCategoryList(HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (currentShop != null && currentShop.getShopId() > 0)
        {
            List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            modelMap.put("success", true);
            modelMap.put("productCategoryList", productCategoryList);
        } else
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "shopId错误!");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory : productCategoryList)
        {
            productCategory.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0)
        {
            try
            {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState())
                {
                    modelMap.put("success", true);
                } else
                {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStatInfo());
                }
            } catch (RuntimeException e)
            {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProductCategorys(Long productCategoryId, HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0)
        {
            try
            {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState())
                {
                    modelMap.put("success", true);
                }else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStatInfo());
                }
            }catch (ProductCategoryOperationException e)
            {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }
        return modelMap;
    }
}
