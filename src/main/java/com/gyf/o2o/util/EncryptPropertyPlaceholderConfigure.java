package com.gyf.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by gaoyunfan on 2018/1/12
 **/
public class EncryptPropertyPlaceholderConfigure extends PropertyPlaceholderConfigurer
{
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    /**
     * 如果已加密，则解密返回
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue)
    {
        if(isEncryptProp(propertyName))
        {
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        }else {
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptPropName : encryptPropNames)
        {
            if (encryptPropName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}
