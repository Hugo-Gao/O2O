package com.gyf.o2o.dao;

import com.gyf.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by gaoyunfan on 2018/1/14
 **/
public interface LocalAuthDao
{
    /**
     * 登陆使用
     *
     * @param username
     * @param password
     * @return
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     *
     * 根据用户ID查询对应LocalAuth
     * @param userId
     * @return
     */
    LocalAuth queryByUserId(@Param("userId") long userId);

    /**
     * 添加平台账号
     *
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);


    int updateLocalAuth(@Param("userId") Long userId,@Param("username") String username,
                        @Param("password") String password,@Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
