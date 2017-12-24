package com.gyf.o2o.enums;

/**
 * Created by gaoyunfan on 2017/12/19
 **/
public enum ProductStateEnum
{
    SUCCESS(1, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY_LIST(-1002, "添加数少于1") ,EMPTY (-1003, "商品为空");

    private int state;

    private String stateInfo;

     ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductStateEnum stateOf(int index) {
        for (ProductStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
