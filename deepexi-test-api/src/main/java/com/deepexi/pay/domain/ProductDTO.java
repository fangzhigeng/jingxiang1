/**
 * ProductDTO  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.domain;

import java.io.Serializable;

/**
 * @description: 商品DTO
 * @program: ProductDTO
 * @author: donh
 * @mail: hudong@deepexi.com
 * @create: 2019/3/27 下午3:23
 **/
public class ProductDTO implements Serializable{

    private static final long serialVersionUID = -4966178075613641466L;
    private String name;
    private Integer type;
    private Integer price;
    private String tag;

    public ProductDTO(){}

    public ProductDTO(String name, Integer type){
        this.name = name;
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

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
        return "ProductDTO{" + "name='" + name + '\'' + ", price=" + price + ", type=" + type + ", tag='" + tag + '\'' + '}';
    }
}