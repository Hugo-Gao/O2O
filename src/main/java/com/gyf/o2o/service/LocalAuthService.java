package com.gyf.o2o.service;

import com.gyf.o2o.dto.LocalAuthExecution;
import com.gyf.o2o.entity.LocalAuth;
import com.gyf.o2o.exceptions.LocalAuthOperationException;

/**
 * Created by gaoyunfan on 2018/1/14
 **/
public interface LocalAuthService
{
    /**
     * 根据用户名密码获取平台账号信息
     *
     * @param userName
     * @param password
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    /**
     * 根据传入的Id获取信息
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信
     * @param localAuth
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     * 修改账号密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException;


}
