/**
 * ProductPageQuery  2019/3/28
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

/**
 * @description: 商品根据条件分页查询对象
 * @program: dubbo-demo
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/28 下午5:04
 **/
public class ProductPageQuery implements Serializable{

    private static final long serialVersionUID = 1857252295004164403L;

    @NotEmpty(message = "商品名称不能为空") //名字不能为空，而且长度必须在2和30之间
    @Size(min = 2, max = 30, message = "商品名长度必须在2和30之间")
    @QueryParam("name")
    private String name;

    @NotNull(message = "商品类型不能为空")
    @QueryParam("type")
    private Integer type;

    /**
     * 这里没加@QueryParam，get请求绑定参数时，即使tag有值也会为null
     */
    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ProductPageQuery{" + "name='" + name + '\'' + ", type=" + type + ", tag='" + tag + '\'' + '}';
    }
}
