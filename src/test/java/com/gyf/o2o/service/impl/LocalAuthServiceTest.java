package com.gyf.o2o.service.impl;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.dto.LocalAuthExecution;
import com.gyf.o2o.entity.LocalAuth;
import com.gyf.o2o.entity.PersonInfo;
import com.gyf.o2o.enums.LocalAuthStateEnum;
import com.gyf.o2o.service.LocalAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2018/1/14
 **/
public class LocalAuthServiceTest extends BaseTest
{

    @Autowired
    private LocalAuthService localAuthService;

    @Test
    public void testABindLocalAuth()
    {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        String username = "testusername";
        String password = "testpassword";
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
        assertEquals(localAuthExecution.getState(), LocalAuthStateEnum.SUCCESS.getState());
        localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
        System.out.println("用户昵称:" + localAuth.getPersonInfo().getName());
        System.out.println("密码:" + localAuth.getPassword());
    }

    @Test
    public void testBModifyLocalAuth()
    {
        long userId = 1;
        String username = "testusername";
        String password = "testpassword";
        String newPassword = "testpassword_new";
        LocalAuthExecution localAuthExecution = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
        assertEquals(LocalAuthStateEnum.SUCCESS.getState(), localAuthExecution.getState());
        LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, newPassword);
        System.out.println(localAuth.getPersonInfo().getName());

    }
}