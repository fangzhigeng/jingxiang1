/**
 * ProductUpdateVO  2019/3/28
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.domain;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

/**
 * @description: 商品更新实体
 * @program: dubbo-demo
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/28 下午5:41
 **/
public class ProductUpdateVO implements Serializable{

    private static final long serialVersionUID = 7556187526412141533L;

    @NotNull(message = "商品价格不能为空")
    @QueryParam("type")
    private Integer price;

    /**
     * 备注
     */
    private String remark;

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

    @Override
    public String toString() {
        return "ProductUpdateVO{" + "price=" + price + ", remark='" + remark + '\'' + '}';
    }
}
