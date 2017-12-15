package com.gyf.o2o.dao;

import com.gyf.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public interface ShopDao
{
    /**
     * 新增店铺
     * @param shop
     * @return -1失败
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    Shop queryByShopId(long shopId);

    /**
     * 分页查询店铺
     * @param shopCondition
     * @param rowIndex 从第几行开始取
     * @param pageSize 每一页的大小
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
