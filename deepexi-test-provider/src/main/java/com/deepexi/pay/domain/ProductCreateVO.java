/**
 * ProductCreateVO  2019/3/28
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
 * @description: 商品创建对象
 * @program: dubbo-demo
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/28 下午5:04
 **/
public class ProductCreateVO implements Serializable{

    private static final long serialVersionUID = -270923131092402495L;

    @NotEmpty(message = "商品名称不能为空") //名字不能为空，而且长度必须在2和30之间
    @Size(min = 2, max = 30, message = "商品名长度必须在2和30之间")
    @QueryParam("name")
    private String name;

    @NotNull(message = "商品价格不能为空")
    @QueryParam("type")
    private Integer price;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductCreateVO{" + "name='" + name + '\'' + ", price=" + price + ", remark='" + remark + '\'' + ", type='" + type + '\'' + '}';
    }
}
