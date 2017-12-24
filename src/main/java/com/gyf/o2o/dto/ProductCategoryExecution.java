package com.gyf.o2o.dto;

import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/19
 **/
public class ProductCategoryExecution
{
    private int state;

    private String statInfo;

    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution()
    {
    }

    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum)
    {
        this.state = stateEnum.getState();
        this.statInfo = stateEnum.getStateInfo();
    }

    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList)
    {
        this.state = stateEnum.getState();
        this.statInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getStatInfo()
    {
        return statInfo;
    }

    public void setStatInfo(String statInfo)
    {
        this.statInfo = statInfo;
    }

    public List<ProductCategory> getProductCategoryList()
    {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList)
    {
        this.productCategoryList = productCategoryList;
    }
}
