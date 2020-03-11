package com.yuer.util;

public interface IResultCode {

    /**
     * 成功
     */
    String S_OK = "1";
    
    /**
     * 失败
     */
    String S_FAIL = "0";
    
    /**
     * 错误默认提示
     */
    String S_FAIL_TIPS = "系统开小差了，请稍后再试";
    
    /**
     * 成功提示
     */
    String S_OK_TIPS = "加载成功";
    
    /**
     * 用户名非空提示
     */
    String USER_EMPT_TIPS = "请输入用户名";
    
    /**
     * 密码非空提示
     */
    String PWD_EMPT_TIPS = "请输入密码";
    
    /**
     * 验证码非空提示
     */
    String VERIFYCODE_EMPT_TIPS = "请输入验证码";
    
    /**
     * 验证码过期提示
     */
    String VERIFYCODE_EXPIRE_TIPS = "验证码已失效,请重新获取";
    
    /**
     * 验证码错误提示
     */
    String VERIFYCODE_ERROR_TIPS = "验证码输入错误";
    
    /**
     * 用户名或密码错误
     */
    String USER_PWD_ERROR_TIPS = "用户名或密码错误";
    
    /**
     * 秘钥(需要前端和后端保持一致)
     */
    String AES_KEY = "bWFsbHB3ZA==WNST";
    
}