/**
 * ResultEnum  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.enums;

import com.deepexi.util.constant.BaseEnumType;

/**
 * @description: 错误信息枚举
 * @program: ResultEnum
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/27 下午3:24
 **/
public enum ResultEnum implements BaseEnumType{
    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),
    SUCCESS("200", "success"), USER_EXIST("100002", "用户已存在！"),
    NETWORK_LIMIT("100001", "网络限流！"),
    TOKEN_NOT_FOUND("200001", "token不能为空！"),
    TENANT_NOT_FOUND("200002", "tenantId不能为空！"),
    RPC_ERROR("300000", "RPC调用异常!请检查服务是否正常!!!");

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}