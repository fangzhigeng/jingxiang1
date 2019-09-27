/**
 * ProductDO  2019/4/2
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @description: 商品DO实体
 * @program: ProductDO
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/4/2 下午2:35
 **/
@TableName("product")
public class ProductDO extends SuperEntity {

    private String name;

    private Integer price;

    public ProductDO() {
    }

    public ProductDO(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

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

    @Override
    public String toString() {
        return "ProductDO{" + "name='" + name + '\'' + ", price=" + price + '}';
    }
}

