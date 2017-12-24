package com.gyf.o2o.dto;

import com.gyf.o2o.entity.Product;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.enums.ProductStateEnum;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/19
 **/
public class ProductExecution
{
    private int state;

    private String statInfo;
    private int count;

    private Product product;

    private List<Product> productList;


    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public List<Product> getProductList()
    {
        return productList;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public void setProductList(List<Product> productList)
    {
        this.productList = productList;
    }

    public ProductExecution()
    {
    }

    public ProductExecution(ProductStateEnum stateEnum)
    {
        this.state = stateEnum.getState();
        this.statInfo = stateEnum.getStateInfo();
    }

    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList)
    {
        this.state = stateEnum.getState();
        this.statInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    public ProductExecution(ProductStateEnum stateEnum, Product product)
    {
        this.state = stateEnum.getState();
        this.statInfo = stateEnum.getStateInfo();
        this.product = product;
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



}
