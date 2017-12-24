package com.gyf.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaoyunfan on 2017/12/13
 **/
public class CodeUtil
{
    public static boolean checkVerifyCode(HttpServletRequest request)
    {
        String verifyCodeExpected= (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY
        );
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.toLowerCase().equals(verifyCodeExpected.toLowerCase()))
        {
            return false;
        }
        return true;
    }
}
