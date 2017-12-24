package com.gyf.o2o.web.shopAdmin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyf.o2o.dto.ImageHolder;
import com.gyf.o2o.dto.ProductExecution;
import com.gyf.o2o.entity.Product;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.enums.ProductStateEnum;
import com.gyf.o2o.exceptions.ProductOperationException;
import com.gyf.o2o.service.ProductCategoryService;
import com.gyf.o2o.service.ProductService;
import com.gyf.o2o.util.CodeUtil;
import com.gyf.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyunfan on 2017/12/21
 **/
@Controller
@RequestMapping("shopadmin")
public class ProductManagementController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;


    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        //检查验证码
        if (!CodeUtil.checkVerifyCode(request))
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try
        {
            //检查是否有图片
            if (multipartResolver.isMultipart(request))
            {
                thumbnail = handleImage((MultipartHttpServletRequest) request, productImgList);
            } else
            {
                modelMap.put("success", false);
                modelMap.put("errorMsg", "上传图片不能为空");
                return modelMap;
            }
        } catch (IOException e)
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try
        {
            product = mapper.readValue(productStr, Product.class);

        } catch (Exception e)
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        if (product != null && thumbnail != null && productImgList.size() > 0)
        {
            try
            {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                //减少对前端的依赖
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState())
                {
                    modelMap.put("success", true);
                } else
                {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStatInfo());
                }
            } catch (ProductOperationException e)
            {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    private ImageHolder handleImage(MultipartHttpServletRequest request, List<ImageHolder> productImgList) throws IOException
    {
        MultipartHttpServletRequest multipartRequest;
        ImageHolder thumbnail;
        multipartRequest = request;
        //构建ImageHolder
        CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
        thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
        for (int i = 0; i < IMAGEMAXCOUNT; i++)
        {
            CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
            if (productImgFile != null)
            {
                ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
                productImgList.add(productImg);
            } else
            {
                break;
            }
        }
        return thumbnail;
    }

    @RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductById(@RequestParam Long productId)
    {
        Map<String, Object> modelMap = new HashMap<>();
        if (productId > -1)
        {
            Product product = productService.getProductById(productId);
            List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(product.getShop().getShopId());
            modelMap.put("product", product);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty product id");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct",method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyProduct(HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        if (!statusChange && !CodeUtil.checkVerifyCode(request))
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try
        {
            if (multipartResolver.isMultipart(request))
            {
                thumbnail = handleImage((MultipartHttpServletRequest) request, productImgList);
            }
        } catch (Exception e)
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try
        {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            product = mapper.readValue(productStr, Product.class);
        }  catch (Exception e)
        {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        if (product != null)
        {
            try
            {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState())
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
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getproductlistbyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductListByShop(HttpServletRequest request)
    {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (pageIndex > -1 && pageSize > -1 && currentShop != null && currentShop.getShopId() != null)
        {
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            String productName = HttpServletRequestUtil.getString(request, "productName");
            Product productCondition = compactProductCondition(currentShop.getShopId(), productCategoryId, productName);
            // 传入查询条件以及分页信息进行查询，返回相应商品列表以及总数
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    /**
     * 封装商品查询条件到Product实例中
     * @param shopId
     * @param productCategoryId
     * @param productName
     * @return
     */
    private Product compactProductCondition(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        // 若有指定类别的要求则添加进去
        if (productCategoryId != -1L) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        // 若有商品名模糊查询的要求则添加进去
        if (productName != null) {
            productCondition.setProductName(productName);
        }
        return productCondition;
    }

}
