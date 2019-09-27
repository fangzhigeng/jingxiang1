/**
 * ProductBaseService  2019/3/28
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.manager.service;

import com.deepexi.pay.domain.ProductDO;

/**
 * @description: 商品通用基础接口
 * @program: dubbo-demo
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/28 上午11:13
 **/
public interface ProductBaseService {

    /**
     * 新增商品基础接口
     */
    void createProduct(ProductDO productDO);

    /**
     * 根据id删除商品基础接口
     */
    void deleteProductById(Long id);

    /**
     * 根据id查询商品信息
     */
    ProductDO getProductById(Long id);
}
